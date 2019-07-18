package com.select.wuliu.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.evaluate;

public interface evaluateMapper {
/**
 * 审核即修改
 */
	Integer updateEvluate(@Param("id")Integer id,@Param("status")String status,@Param("auditor")String auditor,@Param("edittime")Date edittime);
	/**
	 * 分页模糊查询所有
	 */
	Page<evaluate> findevasByPlname(@Param("name")String name);
	/**
	 * 根据主键找到评论
	 */
	evaluate findevasByid(Integer id);
	
}
