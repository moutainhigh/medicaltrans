package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskGroupRel extends AbstractModel {
    private Integer taskGroupId;

    private Integer taskId;

    private Integer groupId;

    private Date updateDate;

    private Integer updateBy;

    private Integer groupOrganId;

    private static final long serialVersionUID = 1L;

    public Integer getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(Integer taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskGroupId=").append(taskGroupId);
        sb.append(", taskId=").append(taskId);
        sb.append(", groupId=").append(groupId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append("]");
        return sb.toString();
    }
}