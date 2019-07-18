package com.select.wuliu.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.carUser;
import com.select.wuliu.entity.fahRecoreder;
@Service
public interface IfahRecorederService {
	/**
	 * 分页查询所有发货记录
	 */
	Page<fahRecoreder> GetAfdhRecorder(Integer pageNum,Integer pageSize,String name,Integer userid);
	
/**
 * 根据发货人id查询发货记录
 */
	
	Page<fahRecoreder> GetAfdhRecorderByi(Integer pageNum,Integer pageSize,Integer rid);
	/**
	 * 根据发货状态查询发货记录
	 */
	
	Page<fahRecoreder> GetAfdhRecorderBystatus(Integer pageNum,Integer pageSize,String status);

	
	/**
	 * 分页查询所有车辆信息
	 */
		Page<carUser> GetAllcarusers(Integer pageNum,Integer pageSize,@Param("name")String name,@Param("userid")Integer userid);
		
		
		/**
		 * 根据车主id查询车辆信息
		 * 
		 */
		Page<carUser> GetAcarUserByi(Integer pageNum,Integer pageSize,Integer rid);
	
	
	
	
	
	
	
}
