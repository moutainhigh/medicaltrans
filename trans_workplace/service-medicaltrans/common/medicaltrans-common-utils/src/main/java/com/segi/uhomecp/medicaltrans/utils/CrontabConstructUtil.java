package com.segi.uhomecp.medicaltrans.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.Date;

import org.springframework.scheduling.support.CronSequenceGenerator;

import com.segi.uhomecp.medicaltrans.dto.CrontabDto;
import com.segi.uhomecp.wh.common.constant.Constant;
  
/** 
 * Title: CronConstructUtil.java    
 * @Description: cron表达式生成工具
 * @author yangyh@segimail.com       
 * @created 2018年5月21日 下午7:57:03    
 */
public class CrontabConstructUtil {
	
	public static String getTaskCron(CrontabDto crontabDto) {
		StringBuffer str = new StringBuffer();
		String weeks = crontabDto.getWeeks();
		// 秒 分 时 日 月 年
		str.append(crontabDto.getSec())
		.append(Constant.SPLIT_BLANK)
		.append(crontabDto.getMin())
		.append(Constant.SPLIT_BLANK)
		.append(crontabDto.getHrs())
		.append(Constant.SPLIT_BLANK)
		.append(crontabDto.getDays())
		.append(Constant.SPLIT_BLANK)
		.append(crontabDto.getMonths())
		.append(Constant.SPLIT_BLANK)
		.append(crontabDto.getYears());
		// 秒 分 时 日 月 周
		if (StringUtils.isNotBlank(weeks)) {
			str.delete(0, str.length())
			.append(crontabDto.getSec())
			.append(Constant.SPLIT_BLANK)
			.append(crontabDto.getMin())
			.append(Constant.SPLIT_BLANK)
			.append(crontabDto.getHrs())
			.append(Constant.SPLIT_BLANK)
			.append("*")
			.append(Constant.SPLIT_BLANK)
			.append("*")
			.append(Constant.SPLIT_BLANK)
			.append(crontabDto.getWeeks());
		}
		return str.toString();
	}
	
	/**
	 * 获取下一执行时间点
	 * @param cron
	 * @param currentDate
	 * @return
	 */
	public static Date generatorCronDate(String cron, Date currentDate) {
		Date date2 = null;  
        try {  
        	CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
            date2 = cronSequenceGenerator.next(currentDate);
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        
        return date2;
	}
}
