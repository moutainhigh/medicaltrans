package com.segi.uhomecp.medicaltrans.taskgroup;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.medicaltrans.reportjob.inf.HandlerExecute;
import com.segi.uhomecp.medicaltrans.reportjob.inf.bean.ExtractData;
import com.segi.uhomecp.utils.SpringContextUtils;

public class TaskGroupContainer {
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskGroupContainer.class);

    /**
     * 当前taskGroup所属jobId
     */
    private long jobId;

    /**
     * 当前taskGroupId
     */
    private int taskGroupId;
    
    private int organId;
    
    private Date nextDate;
    
    private List<?> dataList;
    
    private ExtractData<?> data;

    public TaskGroupContainer(ExtractData<?> data) {
		this.data = data;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public int getTaskGroupId() {
		return taskGroupId;
	}

	public void setTaskGroupId(int taskGroupId) {
		this.taskGroupId = taskGroupId;
	}
	
	public int getOrganId() {
		return organId;
	}

	public void setOrganId(int organId) {
		this.organId = organId;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	
	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}
	
	/**
	 * start
	 */
	public void start() {
    	try {
        	LOG.info("=====>organId:{}开始执行处理", organId);
			HandlerExecute handler = (HandlerExecute) SpringContextUtils.getBean("reportHandlerExecute");
			handler.execute(data);
    	} catch (Throwable e) {
    		LOG.error(e.getMessage(), e);
    		e.printStackTrace();
    	}
    }
    
}
