package com.segi.uhomecp.medicaltrans.mongodb.track.entity;

import java.util.ArrayList;
import java.util.List;

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
@Document(collection="task_track")
public class TaskTrack {
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
    @Field("taskId")
    private Integer taskId;
    
    /**
     * @Field 映射成MongoDB文档的字段内容
     */
    @Field("beginTime")
    private Long beginTime;

    /**
     * 集合类型最好使用 ? 不确定类型(或者说任意类型)
     * 否则会info（Found cycle for field 'itemList' in type 'Order' for path ''）表明你的代码中有潜在的循环使用
     *
     * 像这样有另一个对象的集合，另一个对象不用加任何的MongoDB 注释
     */
    private List<?> flowList = new ArrayList();

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

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public List<?> getFlowList() {
		return flowList;
	}

	public void setFlowList(List<?> flowList) {
		this.flowList = flowList;
	}
	
	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	
	@Override
	public String toString() {
		return "TaskTrack [id=" + id + ", organId=" + organId + ", taskId="
				+ taskId + ", beginTime=" + beginTime + ", flowList="
				+ flowList + "]";
	}
	
}
