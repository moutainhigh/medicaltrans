package com.segi.uhomecp.medicaltrans.report.taskrpt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.taskrpt.dto.MtHisTaskPageDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;

/**
 * @ClassName:  MtTaskHisRptQueryMapper   
 * @Description:运送记录报表库查询   
 * @author: zhaoqing
 * @date:   2018年8月16日 下午8:16:24
 */
public interface MtTaskHisRptQueryMapper {
	
	/**
	 * @Title: queryHisTaskIdPage   
	 *  分页查询运送记录的任务ID
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
    public List<Integer> queryHisTaskIdPage(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: queryHisTaskIdPageByUser   
     *  分页查询运送员运送明细的任务ID 
     * @author zhaoqing  
     * @param dto
     * @return      
     * List<Integer>      
     */
    public List<Integer> queryHisTaskIdPageByUser(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: queryHisTaskPageByTaskIds   
     *  根据任务ID查询运送记录 
     * @author zhaoqing  
     * @param taskIds
     * @return 
     */
    public List<MtHisTaskPageDto> queryHisTaskPageByTaskIds(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: countHisTaskPage   
     *  查询运送记录分页条数 
     * @author zhaoqing  
     * @param dto
     * @return 
     */
    public int countHisTaskPage(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: queryMtTaskHisByTaskId   
     *  查询运送记录详情 
     * @author zhaoqing  
     * @param taskId
     * @return 
     */
    public MtTask queryMtTaskHisByTaskId(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: queryMtTaskExtHisByTaskIds   
     *  根据任务Id查询任务扩展信息
     * @author zhaoqing  
     * @param taskId
     * @return 
     */
    public List<MtTaskExt> queryMtTaskExtHisByTaskIds(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: queryMtTaskExtHisByTaskId   
     *  根据任务Id查询任务扩展信息
     * @author zhaoqing  
     * @param taskId
     * @return 
     */
    public List<MtTaskExt> queryMtTaskExtHisByTaskId(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: countHisTaskPageByUser   
     *  查询运送员运送明细数据分页条数 
     * @author zhaoqing  
     * @param dto
     * @return 
     */
    public int countHisTaskPageByUser(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: getHisTaskUserInfo   
     *  查询执行人信息 
     * @author zhaoqing  
     * @param taskIds
     * @return
     */
    public List<MtTaskExecutors> getHisTaskUserInfo(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: queryMtTaskRouteIdHisPage   
     *  分页查询执行信息的ID 
     * @author zhaoqing  
     * @param dto
     * @return
     */
    public List<Integer> queryMtTaskRouteIdHisPage(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: queryMtTaskRouteHisByIds   
     *  根据分页Ids查询执行信息 
     * @author zhaoqing  
     * @param dto
     * @return
     */
    public List<MtTaskRoute> queryMtTaskRouteHisByIds(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: countMtTaskRouteIdHisPage   
     *  查询执行信息分页数据的总条数 
     * @author zhaoqing  
     * @param dto
     * @return 
     */
    public int countMtTaskRouteIdHisPage(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: queryMtTaskRouteHisByExample   
     *  查询执行信息
     * @author zhaoqing  
     * @param dto
     * @return 
     */
    public List<MtTaskRoute> queryMtTaskRouteHisByExample(@Param("dto")MtHisTaskPageDto dto);
    
    /**
     * @Title: countTableName   
     *  查询表名的数量 
     * @author zhaoqing  
     * @param dto
     * @return  
     */
    public int countTableName(@Param("dto")MtHisTaskPageDto dto);
}
