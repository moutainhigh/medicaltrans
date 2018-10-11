package com.segi.uhomecp.medicaltrans.dto;
  
/**
 * Title: CronDto.java    
 * @Description: cron表达式生成入参对象
 * @author yangyh@segimail.com       
 * @created 2018年5月21日 下午7:32:53
 */
public class CrontabDto {
	private String years;
	private String months;
	private String days;
	private String weeks;
	private String hrs; 
	private String min;
	private String sec;
	  
	public CrontabDto(String years, String months, String days, String weeks, String hrs, String min, String sec) {
		super();
		this.years = years;
		this.months = months;
		this.days = days;
		this.weeks = weeks;
		this.hrs = hrs;
		this.min = min;
		this.sec = sec;
	}

	public CrontabDto() {
		super();
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public String getHrs() {
		return hrs;
	}

	public void setHrs(String hrs) {
		this.hrs = hrs;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getSec() {
		return sec;
	}

	public void setSec(String sec) {
		this.sec = sec;
	}
}
