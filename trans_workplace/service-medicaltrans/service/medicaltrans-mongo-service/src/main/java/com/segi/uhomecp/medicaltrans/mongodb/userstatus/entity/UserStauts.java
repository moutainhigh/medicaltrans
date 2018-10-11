package com.segi.uhomecp.medicaltrans.mongodb.userstatus.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 医疗任务轨迹
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
@Document(collection="user_status")
public class UserStauts {
	/**
     * @ID 生成MongoDB文档的_id
     */
    @Id
    private Long id;

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
    @Field("paramTime")
    private Long paramTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getParamTime() {
		return paramTime;
	}

	public void setParamTime(Long paramTime) {
		this.paramTime = paramTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserStauts [id=");
		builder.append(id);
		builder.append(", organId=");
		builder.append(organId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", paramTime=");
		builder.append(paramTime);
		builder.append("]");
		return builder.toString();
	}

	
	
}
