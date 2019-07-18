package com.select.wuliu.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.International;

public interface internationalMapper {
/**
 * 增加用户
 */
	Integer addInternation(International inter);
	
	/**
	 * 根据国际物流公司
	 * id 找公司
	 */
	International findintercById(Integer id);
	/**
	 * 分页模糊查找国际物流公司
	 */
	
	Page<International> findintersByPLname(@Param("name")String name);
	/**
	 * 修改国际物流资料companyName,content,phone,zhuye,chuanzhen,youbian,
	companyAddress,isDel
	 */
	Integer updateinter(@Param("id")Integer id,@Param("companyName")String companyName,
			@Param("phone")String phone,@Param("content")String content,
			@Param("zhuye")String zhuye,
			@Param("chuanzhen")String chuanzhen,@Param("youbian")String youbian,
			@Param("companyAddress")String companyAddress,
			@Param("isDel")Integer isDel);
	
	
	
}
