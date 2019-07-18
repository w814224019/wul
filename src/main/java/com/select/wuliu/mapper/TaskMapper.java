package com.select.wuliu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.Task;

public interface TaskMapper {
	/**
	 * 分页查询网点id找线路
	 */
	Page <Task> PfindBywdId(Integer wlId);
	
/**
 * 获取所有数据
 */
List<Task> findAllTask();
/**
 * 根据公司序号查询计划
 */
Page<Task> findTasksBylineId(Integer lineId);
/**
 * 根据时间点查询计划条
 * 
 */
Task findBydsId(@Param("lineId")Integer id,@Param("time")String time);

/**
 * 找到线路中最大值
 * @param lineId
 * @return
 */
Task findMaxBylineId(Integer lineId);
/**
 * 模糊查询线路表
 */

Page<Task> findByLikeN(@Param("name")String Name);


/**
 * 添加任务
 * @param task
 * @return
 */
Integer addNewTask(Task task);	
	/**
	 * 删除任务,删除任务列表里的任务等
	 */
	Integer UpdateTask(@Param("lineId")Integer lineId,@Param("time")String time,@Param("status")Integer status);
	
}
