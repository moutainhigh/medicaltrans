package com.segi.uhomecp.medicaltrans.base.taskloop.model;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskLoopExecutors extends AbstractModel {
    private Integer executorId;

    private Integer taskLoopId;

    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Integer executorId) {
        this.executorId = executorId;
    }

    public Integer getTaskLoopId() {
        return taskLoopId;
    }

    public void setTaskLoopId(Integer taskLoopId) {
        this.taskLoopId = taskLoopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", executorId=").append(executorId);
        sb.append(", taskLoopId=").append(taskLoopId);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}