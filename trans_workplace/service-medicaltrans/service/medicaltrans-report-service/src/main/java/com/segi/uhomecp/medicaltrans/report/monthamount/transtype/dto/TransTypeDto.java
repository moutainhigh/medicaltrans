package com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dto;

import java.io.Serializable;

import com.segi.uhomecp.medicaltrans.constant.MtConstant.TransTypeCode;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;

public class TransTypeDto implements Serializable {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -9016894444697753557L;

	private String drugTransTypeName;
	
	private Long drugTransTypeAmount;
	
	private String sampleTransTypeName;
	
	private Long sampleTransTypeAmount;
	
	private String bloodTransTypeName;
	
	private Long bloodTransTypeAmount;
	
	private String patientTransTypeName;
	
	private Long patientTransTypeAmount;
	
	private String goodTransTypeName;
	
	private Long goodTransTypeAmount;
	
	private String bookTransTypeName;
	
	private Long bookTransTypeAmount;
	
	public TransTypeDto() {
		drugTransTypeName = TransTypeEnum.TRANS_TYPE_01.getName();
		drugTransTypeAmount = 0L;
		sampleTransTypeName = TransTypeEnum.TRANS_TYPE_02.getName();
		sampleTransTypeAmount = 0L;
		bloodTransTypeName = TransTypeEnum.TRANS_TYPE_03.getName();
		bloodTransTypeAmount = 0L;
		patientTransTypeName = TransTypeEnum.TRANS_TYPE_04.getName();
		patientTransTypeAmount = 0L;
		goodTransTypeName = TransTypeEnum.TRANS_TYPE_05.getName();
		goodTransTypeAmount = 0L;
		bookTransTypeName = TransTypeEnum.TRANS_TYPE_06.getName();
		bookTransTypeAmount = 0L;
	}
	
	public String getDrugTransTypeName() {
		return drugTransTypeName;
	}
	
	public void setDrugTransTypeName(String drugTransTypeName) {
		this.drugTransTypeName = drugTransTypeName;
	}
	
	public Long getDrugTransTypeAmount() {
		return drugTransTypeAmount;
	}
	
	public void setDrugTransTypeAmount(Long drugTransTypeAmount) {
		this.drugTransTypeAmount = drugTransTypeAmount;
	}
	
	public String getSampleTransTypeName() {
		return sampleTransTypeName;
	}
	
	public void setSampleTransTypeName(String sampleTransTypeName) {
		this.sampleTransTypeName = sampleTransTypeName;
	}
	
	public Long getSampleTransTypeAmount() {
		return sampleTransTypeAmount;
	}
	
	public void setSampleTransTypeAmount(Long sampleTransTypeAmount) {
		this.sampleTransTypeAmount = sampleTransTypeAmount;
	}
	
	public String getBloodTransTypeName() {
		return bloodTransTypeName;
	}
	
	public void setBloodTransTypeName(String bloodTransTypeName) {
		this.bloodTransTypeName = bloodTransTypeName;
	}
	
	public Long getBloodTransTypeAmount() {
		return bloodTransTypeAmount;
	}
	
	public void setBloodTransTypeAmount(Long bloodTransTypeAmount) {
		this.bloodTransTypeAmount = bloodTransTypeAmount;
	}
	
	public String getPatientTransTypeName() {
		return patientTransTypeName;
	}
	
	public void setPatientTransTypeName(String patientTransTypeName) {
		this.patientTransTypeName = patientTransTypeName;
	}
	
	public Long getPatientTransTypeAmount() {
		return patientTransTypeAmount;
	}
	
	public void setPatientTransTypeAmount(Long patientTransTypeAmount) {
		this.patientTransTypeAmount = patientTransTypeAmount;
	}
	
	public String getGoodTransTypeName() {
		return goodTransTypeName;
	}
	
	public void setGoodTransTypeName(String goodTransTypeName) {
		this.goodTransTypeName = goodTransTypeName;
	}
	
	public Long getGoodTransTypeAmount() {
		return goodTransTypeAmount;
	}
	
	public void setGoodTransTypeAmount(Long goodTransTypeAmount) {
		this.goodTransTypeAmount = goodTransTypeAmount;
	}
	
	public String getBookTransTypeName() {
		return bookTransTypeName;
	}
	
	public void setBookTransTypeName(String bookTransTypeName) {
		this.bookTransTypeName = bookTransTypeName;
	}
	
	public Long getBookTransTypeAmount() {
		return bookTransTypeAmount;
	}
	
	public void setBookTransTypeAmount(Long bookTransTypeAmount) {
		this.bookTransTypeAmount = bookTransTypeAmount;
	}
	
	/**
	 * 根据code赋值
	 * @param code
	 * @param value
	 */
	public void setValue(String code, Long value) {
		String name = TransTypeEnum.getNameByCode(code);
		switch (code) {
		// 药品运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_01 :
				setDrugTransTypeName(name);
				setDrugTransTypeAmount(value);
				break;
			// 标本运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_02 :
				setSampleTransTypeName(name);
				setSampleTransTypeAmount(value);
				break;
			// 血制品运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_03 :
				setBloodTransTypeName(name);
				setBloodTransTypeAmount(value);
				break;
			// 病人护送
			case TransTypeCode.MT_TRANS_TYPE_CODE_04 :
				setPatientTransTypeName(name);
				setPatientTransTypeAmount(value);
				break;
			// 物品运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_05 :
				setGoodTransTypeName(name);
				setGoodTransTypeAmount(value);
				break;
			// 文书运送
			case TransTypeCode.MT_TRANS_TYPE_CODE_06 :
				setBookTransTypeName(name);
				setBookTransTypeAmount(value);
				break;
			// 其他
			default :
				break;
		}
	}
}
