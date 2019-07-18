package com.select.wuliu.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.carUser;

public interface carUserMapper {
/**
 * 分页查询所有车辆信息
 */
	Page<carUser> findAllcarusers(@Param("name")String name,@Param("userid")Integer userid);
	
	
	/**
	 * 根据车主id查询车辆信息
	 * 
	 */
	Page<carUser> findAcarUserByi(Integer rid);
	
}
