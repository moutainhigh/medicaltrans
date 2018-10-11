package com.segi.uhomecp.medicaltrans.mongodb.transsource.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Title: TransSource.java    
 * @Description: 运送出发地TOP5
 * @author yangyh@segimail.com       
 * @created 2018年9月28日 下午5:20:20
 */
@Document(collection="trans_source")
public class TransSource {
	/**
     * @ID 生成MongoDB文档的_id
     */
    @Id
    private Integer id;
    
	// 组织机构id
    private Integer organId;
 
    // 科室数
    private Integer fromHouseNum;
    
	// 科室1id
	private String fromHouseId1;
	
	// 科室1运送量
	private String fromHouseAmount1;
	
	// 科室2id
	private String fromHouseId2;
	
	// 科室2运送量
	private String fromHouseAmount2;
	
	// 科室3id
	private String fromHouseId3;
	
	// 科室3运送量
	private String fromHouseAmount3;
	
	// 科室4id
	private String fromHouseId4;
	
	// 科室4运送量
	private String fromHouseAmount4;
	
	// 科室5id
	private String fromHouseId5;
	
	// 科室5运送量
	private String fromHouseAmount5;
	
	// 其他科室运送量
	private String fromHouseAmountOthers;
	
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

	public Integer getFromHouseNum() {
		return fromHouseNum;
	}

	public void setFromHouseNum(Integer fromHouseNum) {
		this.fromHouseNum = fromHouseNum;
	}
    
	public String getFromHouseId1() {
		return fromHouseId1;
	}

	public void setFromHouseId1(String fromHouseId1) {
		this.fromHouseId1 = fromHouseId1;
	}

	public String getFromHouseAmount1() {
		return fromHouseAmount1;
	}

	public void setFromHouseAmount1(String fromHouseAmount1) {
		this.fromHouseAmount1 = fromHouseAmount1;
	}

	public String getFromHouseId2() {
		return fromHouseId2;
	}

	public void setFromHouseId2(String fromHouseId2) {
		this.fromHouseId2 = fromHouseId2;
	}

	public String getFromHouseAmount2() {
		return fromHouseAmount2;
	}

	public void setFromHouseAmount2(String fromHouseAmount2) {
		this.fromHouseAmount2 = fromHouseAmount2;
	}

	public String getFromHouseId3() {
		return fromHouseId3;
	}

	public void setFromHouseId3(String fromHouseId3) {
		this.fromHouseId3 = fromHouseId3;
	}

	public String getFromHouseAmount3() {
		return fromHouseAmount3;
	}

	public void setFromHouseAmount3(String fromHouseAmount3) {
		this.fromHouseAmount3 = fromHouseAmount3;
	}

	public String getFromHouseId4() {
		return fromHouseId4;
	}

	public void setFromHouseId4(String fromHouseId4) {
		this.fromHouseId4 = fromHouseId4;
	}

	public String getFromHouseAmount4() {
		return fromHouseAmount4;
	}

	public void setFromHouseAmount4(String fromHouseAmount4) {
		this.fromHouseAmount4 = fromHouseAmount4;
	}

	public String getFromHouseId5() {
		return fromHouseId5;
	}

	public void setFromHouseId5(String fromHouseId5) {
		this.fromHouseId5 = fromHouseId5;
	}

	public String getFromHouseAmount5() {
		return fromHouseAmount5;
	}

	public void setFromHouseAmount5(String fromHouseAmount5) {
		this.fromHouseAmount5 = fromHouseAmount5;
	}

	public String getFromHouseAmountOthers() {
		return fromHouseAmountOthers;
	}

	public void setFromHouseAmountOthers(String fromHouseAmountOthers) {
		this.fromHouseAmountOthers = fromHouseAmountOthers;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getSimpleName());
		sb.append("[id=").append(id);
		sb.append(", organId=").append(organId);
		sb.append(", fromHouseId1=").append(fromHouseId1);
		sb.append(", fromHouseAmount1=").append(fromHouseAmount1);
		sb.append(", fromHouseId2=").append(fromHouseId2);
		sb.append(", fromHouseAmount2=").append(fromHouseAmount2);
		sb.append(", fromHouseId3=").append(fromHouseId3);
		sb.append(", fromHouseAmount3=").append(fromHouseAmount3);
		sb.append(", fromHouseId4=").append(fromHouseId4);
		sb.append(", fromHouseAmount4=").append(fromHouseAmount4);
		sb.append(", fromHouseId5=").append(fromHouseId5);
		sb.append(", fromHouseAmount5=").append(fromHouseAmount5);
		sb.append(", fromHouseAmountOthers=").append(fromHouseAmountOthers);
		sb.append("]");
		return sb.toString();
	}
}
