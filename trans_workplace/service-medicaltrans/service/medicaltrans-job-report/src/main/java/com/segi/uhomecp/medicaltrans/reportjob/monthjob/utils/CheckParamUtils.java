package com.segi.uhomecp.medicaltrans.reportjob.monthjob.utils;

import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.DateUtil;

public class CheckParamUtils {
  
  /**
   * @discription 入参检查
   * @author yangyh@segimail.com       
   * @created 2018年8月11日 下午6:22:14     
   * @param reportJobTimeDto(参数对象)
   * @param name(表名)
   * @return
   */
  public static ResultDto<String, String, Integer> check(ReportJobTimeDto reportJobTimeDto, String name) {
    ResultDto<String, String, Integer> resultDto = new ResultDto<>(true, name + "更新成功！");
    String fields = "exeYearMonth, groupOrganId, startTime, endTime";
    String messages = "年月, 一级物业id, 开始时间, 结束时间";
    String errInfo = CheckRestParams.checkEmpty(reportJobTimeDto, fields, messages);
    if (null != errInfo) {
      resultDto.setIsSucc(false);
      resultDto.setMessage(name + "更新失败！" + errInfo);
      return resultDto;
    }
    if (!AppUtils.isNotEmpty(reportJobTimeDto.getOrganIdList())) {
      resultDto.setIsSucc(false);
      resultDto.setMessage(name + "更新失败！项目Id集合为空！");
      return resultDto;
    }
    return checkTime(reportJobTimeDto, name, resultDto);
  }

  /**
   * @discription 开始时间, 结束时间检查
   * @author yangyh@segimail.com       
   * @created 2018年8月13日 上午10:01:30     
   * @param reportJobTimeDto
   * @param name(表名)
   * @param resultDto
   * @return
   */
  private static ResultDto<String, String, Integer> checkTime(ReportJobTimeDto reportJobTimeDto,
      String name,  ResultDto<String, String, Integer> resultDto) {
    String startTime = String.valueOf(reportJobTimeDto.getStartTime());
    String endTime = String.valueOf(reportJobTimeDto.getEndTime());
    String startTimeYm = DateUtil.getYearMonthOfDate(startTime);
    String endTimeYm = DateUtil.getYearMonthOfDate(endTime);
    if (startTime == null || endTime == null) {
      resultDto.setIsSucc(false);
      resultDto.setMessage(name + "更新失败！开始时间, 结束时间格式错误,startTime=" 
          + startTime + ",endTime=" + endTime);
      return resultDto;
    }
    if (!startTimeYm.equals(endTimeYm)) {
      resultDto.setIsSucc(false);
      resultDto.setMessage(name + "更新失败！开始时间和结束时间不在同一个月,startTime="
          + startTime + ",endTime=" + endTime);
      return resultDto;
    }
    return resultDto;
  }
}
