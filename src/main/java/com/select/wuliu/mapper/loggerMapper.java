package com.select.wuliu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.BaseEntity;

public interface loggerMapper {
	/*
	 * 
	 * 查找以前注册的用户时间
	 */
	BaseEntity findregtime(String mobile);
	/***
	 * 跟踪日志
	 * @param baseEntity
	 * @return
	 */
Integer addLogger(BaseEntity baseEntity);
	/**
	 * 查到所有记录
	 * @return
	 */
	List<BaseEntity> findAll();
	/**
	 * 查询登入记录
	 */
	BaseEntity findBaseEntityByid(Integer id);
	
	/**
	 * 根据公司或者网点id 找到公司操作记录
	 * 
	 */
	List<BaseEntity> findrecordsById(Integer id);
	/**
	 * 分页查询
	 */
	Page<BaseEntity> findrecordsBypage(Integer id);
	/**
	 * 模糊分页查询所有日志信息
	 */
    Page<BaseEntity> findrecordsPLname(@Param("name")String name);
}
