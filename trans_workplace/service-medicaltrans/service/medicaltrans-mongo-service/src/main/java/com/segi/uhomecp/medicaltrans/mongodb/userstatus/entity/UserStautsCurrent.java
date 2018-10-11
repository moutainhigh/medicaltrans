package com.segi.uhomecp.medicaltrans.mongodb.userstatus.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 当前人员状态
 *     
 * 包: com.segi.uhomecp.medicaltrans.mongodb.trail.entity 
 * 类名称: TaskTrack.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午6:46:53
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
@Document(collection="user_status_current")
public class UserStautsCurrent {
	/**
     * @ID 生成MongoDB文档的_id
     */
    @Id
    private String id;

    /**
     * @Field 映射成MongoDB文档的字段内容
     */
    @Field("organId")
    private Integer organId;
    
    /**
     * @Field 映射成MongoDB文档的字段内容
     */
    @Field("userId")
    private Integer userId;
    
    /**
     * @Field 映射成MongoDB文档的字段内容
     */
    @Field("status")
    private String status;

    /**
     * @Field 映射成MongoDB文档的字段内容
     */
    @Field("updateTime")
    private Long updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserStautsCurrent [id=");
		builder.append(id);
		builder.append(", organId=");
		builder.append(organId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append("]");
		return builder.toString();
	}



	
	
	
}
