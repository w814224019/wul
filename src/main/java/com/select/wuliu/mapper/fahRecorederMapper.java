package com.select.wuliu.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.fahRecoreder;

public interface fahRecorederMapper {
	/**
	 * 分页查询所有发货记录
	 */
	Page<fahRecoreder> findAfdhRecorder(@Param("name")String name,@Param("userid")Integer userid);
	
/**
 * 根据发货人id查询发货记录
 */
	
	Page<fahRecoreder> findAfdhRecorderByi(Integer rid);
	/**
	 * 根据发货状态查询发货记录
	 */
	
	Page<fahRecoreder> findAfdhRecorderBystatus(String status);
	
}
