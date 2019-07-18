package com.select.wuliu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.webs;

public interface websMapper {
/**
 * 添加网站
 */
	Integer addNewWeb(webs web);
	/**
	 * 根据用户rid
	 * 查找收藏内容
	 */
	Page<webs> findByrid(@Param("rid")Integer rid,@Param("name")String name);
	/**
	 * 删除收藏网站
	 * 
	 */
	Integer Delteweb(Integer id);
	/**
	 * 导出表格查询个人所有列表 
	 */
	List<webs> findallwebsByrid(Integer rid);
	
	
}
