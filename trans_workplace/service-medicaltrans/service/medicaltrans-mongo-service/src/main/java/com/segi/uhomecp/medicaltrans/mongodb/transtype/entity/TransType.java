package com.segi.uhomecp.medicaltrans.mongodb.transtype.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Title: TransSource.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年9月28日 下午5:20:20
 */
@Document(collection="trans_type")
public class TransType {
	/**
     * @ID 生成MongoDB文档的_id
     */
    @Id
    private Integer id;
    
	// 组织机构id
    private Integer organId;
    
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getSimpleName());
		sb.append("[id=").append(id);
		sb.append(", organId=").append(organId);
		sb.append(", drugTransTypeAmount=").append(drugTransTypeAmount);
		sb.append(", drugTransTypeAvgTime=").append(drugTransTypeAvgTime);
		sb.append(", sampleTransTypeAmount=").append(sampleTransTypeAmount);
		sb.append(", sampleTransTypeAvgTime=").append(sampleTransTypeAvgTime);
		sb.append(", bloodTransTypeAmount=").append(bloodTransTypeAmount);
		sb.append(", bloodTransTypeAvgTime=").append(bloodTransTypeAvgTime);
		sb.append(", patientTransTypeAmount=").append(patientTransTypeAmount);
		sb.append(", patientTransTypeAvgTime=").append(patientTransTypeAvgTime);
		sb.append(", goodTransTypeAmount=").append(goodTransTypeAmount);
		sb.append(", goodTransTypeAvgTime=").append(goodTransTypeAvgTime);
		sb.append(", bookTransTypeAmount=").append(bookTransTypeAmount);
		sb.append(", bookTransTypeAvgTime=").append(bookTransTypeAvgTime);
		sb.append("]");
		return sb.toString();
	}
    
}
