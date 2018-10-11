package com.segi.uhomecp.medicaltrans.mongodb.transway.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Title: TransWay.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年9月28日 下午5:20:20
 */
@Document(collection="trans_way")
public class TransWay {
	/**
     * @ID 生成MongoDB文档的_id
     */
    @Id
    private Integer id;
    
	// 组织机构id
    private Integer organId;
    
	// 步行运输量
	private String walkTypeAmount;
	
	// 推床运输量
	private String pushingBedTypeAmount;
	
	// 平车运输量
	private String flatCartypeAmount;
	
	// 轮椅运输量
	private String wheelchairTypeAmount;

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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getSimpleName());
		sb.append("[id=").append(id);
		sb.append(", organId=").append(organId);
		sb.append(", walkTypeAmount=").append(walkTypeAmount);
		sb.append(", pushingBedTypeAmount=").append(pushingBedTypeAmount);
		sb.append(", flatCartypeAmount=").append(flatCartypeAmount);
		sb.append(", wheelchairTypeAmount=").append(wheelchairTypeAmount);
		sb.append("]");
		return sb.toString();
	}
    
}
