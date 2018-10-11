package com.segi.uhomecp.medicaltrans.taskhis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.taskhis.dto.MtHisTaskPageDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;

/**
 * @ClassName:  MtTaskHisQueryMapper   
 * @Description:任务历史数据查询   
 * @author: zhaoqing
 * @date:   2018年8月15日 下午7:20:48
 */
public interface MtTaskHisQueryMapper {
	
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
    
}
