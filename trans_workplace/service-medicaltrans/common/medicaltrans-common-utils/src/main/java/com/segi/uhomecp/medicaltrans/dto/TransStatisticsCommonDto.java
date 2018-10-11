package com.segi.uhomecp.medicaltrans.dto;

import java.io.Serializable;

import com.segi.uhomecp.medicaltrans.constant.MtConstant.TransTypeCode;
import com.segi.uhomecp.medicaltrans.constant.MtConstant.TransWayCode;

public class TransStatisticsCommonDto implements Serializable {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 552874802106869457L;
	
	// 即时任务平均运输时间
	private String instantTaskAvgTime;
	
	// 药品运送运输量
	private String drugTransTypeAmount;
	
	// 药品平均运输时间
	private String drugTransTypeAvgTime;
	
	// 标本运输量
	private String sampleTransTypeAmount;
	
	// 标本平均运输时间
	private String sampleTransTypeAvgTime;

	// 血制品运输量
	private String bloodTransTypeAmount;
	
	// 血制品平均运输时间
	private String bloodTransTypeAvgTime;

	// 病人护送运输量
	private String patientTransTypeAmount;
	
	// 病人平均运输时间
	private String patientTransTypeAvgTime;

	// 物品运输量
	private String goodTransTypeAmount;
	
	// 物品平均运输时间
	private String goodTransTypeAvgTime;

	// 文书运输量
	private String bookTransTypeAmount;
	
	// 文书平均运输时间
	private String bookTransTypeAvgTime;
	
	// 步行运输量
	private String walkTypeAmount;
	
	// 推床运输量
	private String pushingBedTypeAmount;
	
	// 平车运输量
	private String flatCartypeAmount;
	
	// 轮椅运输量
	private String wheelchairTypeAmount;

	public String getInstantTaskAvgTime() {
		return instantTaskAvgTime;
	}

	public void setInstantTaskAvgTime(String instantTaskAvgTime) {
		this.instantTaskAvgTime = instantTaskAvgTime;
	}
	
	public String getDrugTransTypeAmount() {
		return drugTransTypeAmount;
	}

	public void setDrugTransTypeAmount(String drugTransTypeAmount) {
		this.drugTransTypeAmount = drugTransTypeAmount;
	}

	public String getDrugTransTypeAvgTime() {
		return drugTransTypeAvgTime;
	}

	public void setDrugTransTypeAvgTime(String drugTransTypeAvgTime) {
		this.drugTransTypeAvgTime = drugTransTypeAvgTime;
	}

	public String getSampleTransTypeAmount() {
		return sampleTransTypeAmount;
	}

	public void setSampleTransTypeAmount(String sampleTransTypeAmount) {
		this.sampleTransTypeAmount = sampleTransTypeAmount;
	}

	public String getSampleTransTypeAvgTime() {
		return sampleTransTypeAvgTime;
	}

	public void setSampleTransTypeAvgTime(String sampleTransTypeAvgTime) {
		this.sampleTransTypeAvgTime = sampleTransTypeAvgTime;
	}

	public String getBloodTransTypeAmount() {
		return bloodTransTypeAmount;
	}

	public void setBloodTransTypeAmount(String bloodTransTypeAmount) {
		this.bloodTransTypeAmount = bloodTransTypeAmount;
	}

	public String getBloodTransTypeAvgTime() {
		return bloodTransTypeAvgTime;
	}

	public void setBloodTransTypeAvgTime(String bloodTransTypeAvgTime) {
		this.bloodTransTypeAvgTime = bloodTransTypeAvgTime;
	}

	public String getPatientTransTypeAmount() {
		return patientTransTypeAmount;
	}

	public void setPatientTransTypeAmount(String patientTransTypeAmount) {
		this.patientTransTypeAmount = patientTransTypeAmount;
	}

	public String getPatientTransTypeAvgTime() {
		return patientTransTypeAvgTime;
	}

	public void setPatientTransTypeAvgTime(String patientTransTypeAvgTime) {
		this.patientTransTypeAvgTime = patientTransTypeAvgTime;
	}

	public String getGoodTransTypeAmount() {
		return goodTransTypeAmount;
	}

	public void setGoodTransTypeAmount(String goodTransTypeAmount) {
		this.goodTransTypeAmount = goodTransTypeAmount;
	}

	public String getGoodTransTypeAvgTime() {
		return goodTransTypeAvgTime;
	}

	public void setGoodTransTypeAvgTime(String goodTransTypeAvgTime) {
		this.goodTransTypeAvgTime = goodTransTypeAvgTime;
	}

	public String getBookTransTypeAmount() {
		return bookTransTypeAmount;
	}

	public void setBookTransTypeAmount(String bookTransTypeAmount) {
		this.bookTransTypeAmount = bookTransTypeAmount;
	}

	public String getBookTransTypeAvgTime() {
		return bookTransTypeAvgTime;
	}

	public void setBookTransTypeAvgTime(String bookTransTypeAvgTime) {
		this.bookTransTypeAvgTime = bookTransTypeAvgTime;
	}

	public String getWalkTypeAmount() {
		return walkTypeAmount;
	}

	public void setWalkTypeAmount(String walkTypeAmount) {
		this.walkTypeAmount = walkTypeAmount;
	}

	public String getPushingBedTypeAmount() {
		return pushingBedTypeAmount;
	}

	public void setPushingBedTypeAmount(String pushingBedTypeAmount) {
		this.pushingBedTypeAmount = pushingBedTypeAmount;
	}

	public String getFlatCartypeAmount() {
		return flatCartypeAmount;
	}

	public void setFlatCartypeAmount(String flatCartypeAmount) {
		this.flatCartypeAmount = flatCartypeAmount;
	}

	public String getWheelchairTypeAmount() {
		return wheelchairTypeAmount;
	}

	public void setWheelchairTypeAmount(String wheelchairTypeAmount) {
		this.wheelchairTypeAmount = wheelchairTypeAmount;
	}
	
	/**
	 * 根据运送类型code赋值
	 * @param code
	 * @param value
	 */
	public void setValueByTranstype(String code, String amout, String avgTime) {
		switch (code) {
			// 药品运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_01 :
				setDrugTransTypeAmount(amout);
				setDrugTransTypeAvgTime(avgTime);
				break;
			// 标本运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_02 :
				setSampleTransTypeAmount(amout);
				setSampleTransTypeAvgTime(avgTime);
				break;
			// 血制品运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_03 :
				setBloodTransTypeAmount(amout);
				setBloodTransTypeAvgTime(avgTime);
				break;
			// 病人护送
			case TransTypeCode.MT_TRANS_TYPE_CODE_04 :
				setPatientTransTypeAmount(amout);
				setPatientTransTypeAvgTime(avgTime);
				break;
			// 物品运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_05 :
				setGoodTransTypeAmount(amout);
				setGoodTransTypeAvgTime(avgTime);
				break;
			// 文书运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_06 :
				setBookTransTypeAmount(amout);
				setBookTransTypeAvgTime(avgTime);
				break;
			// 其他
			default :
				break;
		}
	}
	
	/**
	 * 根据运送方式code赋值
	 * @param code
	 * @param value
	 */
	public void setValueByTransWay(String code, String amout) {
		switch (code) {
			// 步行
			case TransWayCode.TRANS_TOOLS_CODE_WALK :
				setWalkTypeAmount(amout);
				break;
			// 推床
			case TransWayCode.TRANS_TOOLS_CODE_PUSHING_BED :
				setPushingBedTypeAmount(amout);
				break;
			// 平车
			case TransWayCode.TRANS_TOOLS_CODE_FLAT_CAR :
				setFlatCartypeAmount(amout);
				break;
			// 轮椅
			case TransWayCode.TRANS_TOOLS_CODE_WHEELCHAIR :
				setWheelchairTypeAmount(amout);
				break;
			// 其他
			default :
				break;
		}
	}
}
