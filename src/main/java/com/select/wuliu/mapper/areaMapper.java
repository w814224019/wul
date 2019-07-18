package com.select.wuliu.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.area;

public interface areaMapper {
	
	
	/**
	 * 根据areaid增加地区别名
	 */
	Integer updateaAlias(@Param("areaid")Integer areaid,@Param("alias")String alias);
	
	/**
	 * 查询所有
	 */
	List<area> findAareas();
	/**
	 * 分页模糊查询全国区号
	 */
	Page<area> findChina();
	/**
	 * 查询全国所有id
	 */
	List<Integer> findChinaId();
	/**
	 * 按照等级查询((备用))
	 */
	List<area> findAareasByClass(Integer calss);
	
    /**
     * 通过地区名字找地区id
     * @param areaname
     * @return
     */
	area findByname(String areaname);
	/**
	 * 通过地区id找名字
	 * @param id
	 * @return
	 */
	area findByIntegerid(Integer id);
	/**
	 * 查询所有区号信息
	 */
	List<area> findAllareas(Integer parentId);
	/**
	 * 模糊查找所有区号信息
	 */
	List<area> findaeraLN(@Param("name")String name);
	/**
	 * 模糊查找所有区号地图专用
	 */
	List<area> findaeraLNMap(@Param("name")String name);
	
	
	
}
