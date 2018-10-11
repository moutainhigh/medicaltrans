package com.segi.uhomecp.medicaltrans.trans.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

@Component
public class UpdateUserPositUnTaskNumUtils {
	
	public static final Logger logger = LoggerFactory.getLogger(UpdateUserPositUnTaskNumUtils.class);
	
	/**
	 * @discription 批量修改人员未完成任务数量
	 *              此方法无法提供人员的当前位置，以及状态
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月18日 下午3:21:23      
	 * @param numUserIds
	 * @param flag     
	 */
	public void updateUserPositUnTaskNum(Integer organId,List<Integer> userIds,
			short unTaskNum, short runTaskNum, Date executeDate, String locationId) {
		if(AppUtils.isNotEmpty(userIds)) {
			try {
				MtCommonServiceUtils.updateUserPositInfo(organId, userIds,
						unTaskNum, runTaskNum, DateUtil.formatDateToString(executeDate, DateUtil.FORMAT_YYYY_MM_DD), locationId);
			} catch (Exception e) {
				logger.error("updateUserPositUnTaskNum", e);
				e.printStackTrace();
			}
		}
	}
	public void updateBatchUserPositUnTaskNum(Integer organId, List<Integer> addNumUserList,
			List<Integer> subNumUserList, Long executeDate, String locationId) {
		Date beginDate = DateUtil.parseStringToDateYYYYMMDDHHMMSS(
				String.valueOf(executeDate * 100));
		updateBatchUserPositUnTaskNum(organId, addNumUserList, subNumUserList, beginDate, locationId);
	}
	
	public void updateUserPositUnTaskNum(Integer organId,List<Integer> userIds,
			short unTaskNum, short runTaskNum, Long executeDate, String locationId) {
		Date beginDate = DateUtil.parseStringToDateYYYYMMDDHHMMSS(
				String.valueOf(executeDate * 100));
		updateUserPositUnTaskNum(organId, userIds, unTaskNum, runTaskNum, beginDate, locationId);
	}
	
	public void updateUserPositUnTaskNum(Integer organId, Integer userId, short unTaskNum, short runTaskNum,
			Long executeDate, String locationId) {
		Date beginDate = DateUtil.parseStringToDateYYYYMMDDHHMMSS(
				String.valueOf(executeDate * 100));
		updateUserPositUnTaskNum(organId, userId, unTaskNum, runTaskNum, beginDate, locationId);
	}
	
	public void updateUserPositUnTaskNum(Integer organId, Integer userId,
			short unTaskNum, short runTaskNum, Date executeDate, String locationId) {
		List<Integer> list = new ArrayList<>();
		list.add(userId);
		updateUserPositUnTaskNum(organId, list, unTaskNum, runTaskNum, executeDate, locationId);
	}
	
	/**
	 * @discription 在此输入一句话描述作用
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月18日 下午3:42:55      
	 * @param addNumUserIds   +1 的userId 列表
	 * @param subNumUserIds   -1 的userId 列表
	 * @param updateBy
	 * @param updateDate     
	 */
	public void updateBatchUserPositUnTaskNum(Integer organId, List<Integer> addNumUserIds,
			List<Integer> subNumUserIds, Date executeDate, String locationId) {
		// 增加用户列表的未完成数据 +1
		updateUserPositUnTaskNum(organId, addNumUserIds, (short)1, (short)0, executeDate, locationId);
		// 增加用户列表的未完成数据 -1
		updateUserPositUnTaskNum(organId, subNumUserIds, (short)-1, (short)0, executeDate, locationId);
	}
	
	public void updateUserPositUnTaskNum(List<MtTaskExecutors> executorsList, MtTask mtTask, String locationId) {
		Set<Integer> userList = AppUtils.list2ParamsSet(executorsList, new InvocationHandler<Integer, MtTaskExecutors>(){
			@Override
			public Integer invoke(MtTaskExecutors paramO) {
				return paramO.getExeUserId();
			}
		});
		if (AppUtils.isNotEmpty(userList)) {
			/* 更新人员位置信息表 对人员未完成数量进行加- 1  */
			Date executeDate = DateUtil.parseStringToDateYYYYMMDDHHMMSS(
					String.valueOf(mtTask.getBeginTime() * 100));
			this.updateUserPositUnTaskNum(mtTask.getOrganId(), new ArrayList<Integer>(userList), (short)-1, (short)0, executeDate, locationId);
		}
	}
}
