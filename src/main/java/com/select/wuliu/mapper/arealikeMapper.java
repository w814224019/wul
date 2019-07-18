package com.select.wuliu.mapper;

import java.util.List;

import com.select.wuliu.entity.arealike;

public interface arealikeMapper {
/**
 * 添加
 */
	Integer addNewarealike(arealike areal);
	
	/**
	 * 查询所有
	 */
	List<arealike> findall();
	
}
