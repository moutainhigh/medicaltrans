package com.segi.uhomecp.medicaltrans.taskloop.jobhandle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import resp.RpcRespIce;
import segi.datacachesvr.queryInfo.UserInfoV2;
import segi.medicaltrans.common.report.organ.ReportOrganIce;
import segi.medicaltrans.common.report.organ.TransReportOrganServiceIcePrx;
import segi.medicaltrans.common.taskloop.MtTaskLoopIce;
import segi.medicaltrans.common.taskloop.ReturnInteger;
import segi.medicaltrans.common.taskloop.TaskLoopStatusParam;
import segi.whcommon.push.MessageIce;
import segi.whcommon.push.PushServiceIcePrx;
import IceExt.IceClientUtil;

import com.alibaba.fastjson.JSONObject;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.taskloop.dto.MsgParamsDto;
import com.segi.uhomecp.medicaltrans.utils.CrontabConstructUtil;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;

public abstract class TaskLoopHandle extends IJobHandler {

	private TransReportOrganServiceIcePrx getTransReportOrganServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(TransReportOrganServiceIcePrx.class);
	}

	private PushServiceIcePrx getPushServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(PushServiceIcePrx.class);
	}

	private String pregenerateminute = "";
	
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		ReportOrganIce response = this.getTransReportOrganServiceIcePrx().getOragnList();
		if (!response.getCode().equals(RpcError.SUCCESS.getCode())) {
			FAIL.setMsg("调用获取组织机构ICE异常:" + response.getMessage());
			return FAIL;
		}

		try {
			List<Integer> organList = response.getOrganIds();
			List<Integer> reportOrganList = new ArrayList<Integer>();
			// 获取分片信息
			ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
			// 获取取模后的组织信息
			if (shardingVO.getTotal() == 1) {
				reportOrganList.addAll(organList);
			} else {
				for (Integer organ : organList) {
					if (organ.intValue() % shardingVO.getTotal() == shardingVO.getIndex()) {
						reportOrganList.add(organ);
					}
				}
			}
			if (!AppUtils.isNotEmpty(reportOrganList)) {
				return null;
			}
			// 当前时间
			Date currentDate = new Date();
			Calendar currentCal = Calendar.getInstance();
			currentCal.setTime(currentDate);
			currentCal.set(Calendar.MINUTE, 0);
			currentCal.set(Calendar.SECOND, 0);
			currentDate = currentCal.getTime();
			// 定义日期格式
			SimpleDateFormat formatHm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// 比较时间=当前时间+一天
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DATE, 1);
			Date compareDate = calendar.getTime();
			// 开始时间
			Date date = null;
			Calendar generateCal = Calendar.getInstance();
			// 执行时间
			Date generateDate = null;
			TaskLoopStatusParam taskLoopStatusParam = new TaskLoopStatusParam();
			MsgParamsDto msgParamsDto = new MsgParamsDto();
			// 生成循环任务对象
			MessageIce msg = new MessageIce();
			List<Integer> houseIdList = null;
			List<Integer> userIdListAll = null;
			List<Integer> userIdList = null;
			// 循环任务List
			List<MtTaskLoopIce> list = null;
			// 循环任务主键
			String taskLoopId = null;
			Calendar judgeCal = Calendar.getInstance();
			pregenerateminute = UhomePropUtils.getProperty("PREGENERATEMINUTE_MAX");
			StringBuffer str = new StringBuffer();
			for (Integer organId : reportOrganList) {
				try {
					// 查询有效并且类型为月或周的循环任务
					list = getMtTaskLoopList(organId);
					if (!AppUtils.isNotEmpty(list)) {
						continue;
					}
					for (MtTaskLoopIce dto : list) {
						taskLoopId = dto.getTaskLoopId();
						try {
							Integer preGenerateMinute = Integer.valueOf(dto.getPreGenerateMinute());
							// 判断是否满足执行时间
							if (StringUtils.isBlank(dto.getTaskCron()) || null == preGenerateMinute) {
								continue;
							}
							date = CrontabConstructUtil.generatorCronDate(dto.getTaskCron(), currentDate);
							if (null == date) {
								XxlJobLogger.log("===========>生成执行时间异常: taskLoopId:" + taskLoopId);
								continue;
							}
							// 执行时间=开始时间-提前触发时间
							generateCal.setTime(date);
							generateCal.add(Calendar.MINUTE, -(preGenerateMinute));
							generateDate = generateCal.getTime();
							// 判断执行时间是否满足生成循环任务时间范围（当前时间到第二天23：00）
							if (!generateDate.after(currentDate) || generateDate.after(compareDate)) {
								judgeCal.setTime(currentDate);
								// 将当前时间加最大的限定分钟
								judgeCal.add(Calendar.MINUTE, Integer.valueOf(pregenerateminute));
								date = CrontabConstructUtil.generatorCronDate(dto.getTaskCron(), judgeCal.getTime());
								if (null == date) {
									XxlJobLogger.log("===========>生成执行时间异常: taskLoopId:" + taskLoopId);
									continue;
								}
								// 执行时间=开始时间-提前触发时间
								generateCal.setTime(date);
								generateCal.add(Calendar.MINUTE, -(preGenerateMinute));
								generateDate = generateCal.getTime();
								// 判断执行时间是否满足生成循环任务时间范围（当前时间到第二天23：00）
								if (!generateDate.after(currentDate) || generateDate.after(compareDate)) {
									XxlJobLogger.log("===========>执行时间不满足生成循环任务时间范围: taskLoopId:" + taskLoopId);
									continue;
								}
							}
							// 获取循环任务的信息 路线|执行人
							ReturnInteger locationIdReturn = MtCommonServiceUtils.selectLocationIdList(taskLoopId);
							ReturnInteger userIdReturn = MtCommonServiceUtils.selectUserIdList(taskLoopId);
							houseIdList = locationIdReturn.getResultList();
							userIdListAll = userIdReturn.getResultList();
							//查出有效执行人
							userIdList = this.queryUserListByStatus(userIdListAll);
							if (!AppUtils.isNotEmpty(userIdList)) {
								//如果所有执行人都失效,要将该任务置为停用
								taskLoopStatusParam.setTaskLoopId(taskLoopId);
								taskLoopStatusParam.setStatus(Constant.STATUS_CD_STOP);
								taskLoopStatusParam.setLoseRemark(MtConstant.USER_LOST);
								MtCommonServiceUtils.updateTaskLoopStatus(taskLoopStatusParam);
								XxlJobLogger.log("===========>所有执行人都失效,该任务被置为停用: taskLoopId:" + taskLoopId);
								continue;
							}
							
							// organId、transTools、transTypeParentCode、createBy、limitMinute
							msgParamsDto = BeanCopierUtils.copyProperties(dto, MsgParamsDto.class, true);
							if (AppUtils.isNotEmpty(houseIdList)) {
								// 出发地
								msgParamsDto.setFromHouseId(String.valueOf(houseIdList.get(0)));
								// 目的地
								msgParamsDto.setToHouseId(String.valueOf(houseIdList.get(houseIdList.size() - 1)));
								// 路线
								msgParamsDto.setRouteList(houseIdList.toArray(new Integer[houseIdList.size()]));
							}
							if (AppUtils.isNotEmpty(userIdList)) {
								// 执行人数
								msgParamsDto.setTransPersonCount(String.valueOf(userIdList.size()));
								// 执行人
								msgParamsDto.setTransactors(userIdList.toArray(new Integer[userIdList.size()]));
							}
							// 任务开始时间
							msgParamsDto.setBeginTime(formatHm.format(date));
							// 生成循环任务
							msg.setBusinessKey(taskLoopId);
							msg.setMsgTypeCode(MtConstant.TAKS_LOOP_MSG_TYPE_CODE); // 推送CODE
							msg.setMsgTime(generateDate.getTime()); // 执行时间
							msg.setMsgParams(JSONObject.toJSONString(msgParamsDto)); // 参数
							RpcRespIce rpc = this.getPushServiceIcePrx().push(msg);
							if (RpcError.FAIL.getCode().equals(rpc.code)) {
								throw new RuntimeException("调取生循成环任务失败" + rpc.message);
							}
						} catch (Exception e) {
							str.delete(0, str.length());
							XxlJobLogger.log(str.append("循环任务id:[").append(taskLoopId).append("]生成任务异常")
									.append(e.getMessage()).toString());
						}
					}
				} catch (Exception e) {
					StringWriter sw = new StringWriter();
					e.printStackTrace(new PrintWriter(sw, true));
					XxlJobLogger.log(sw.toString());
					str.delete(0, str.length());
					XxlJobLogger.log(str.append("项目id:[").append(organId).append("]生成任务异常")
							.append(e.getMessage()).toString());
				}
			}
		} catch (Exception e) {
			XxlJobLogger.log("TaskLoopHandle Exception" + e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 获取循环任务
	 * @param organId
	 * @return
	 */
	public abstract List<MtTaskLoopIce> getMtTaskLoopList(Integer organId);
	
	/**
	 * 根据人员查询有效的人员状态
	 * @param userIdList
	 * @param organId
	 * @return
	 */
	public List<Integer> queryUserListByStatus(List<Integer> userIdList) {
		List<Integer> result = new ArrayList<Integer>();
		String users = StringUtils.join(userIdList.toArray(), ",");  
		UserInfoV2[] resultUser = MtIbatchQueryServiceUtils.queryUserListByUserIds(users);
		for (UserInfoV2 user:resultUser) {
			if (user != null && MtConstant.USER_EFF == user.getStatus()) {
				result.add(user.getUserId());
			}
		}
		return result;
	}
}
