package com.select.wuliu.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.member;

public interface memberMapper {
/**
 * 增加会员
 */
	Integer addNewMember(member m);
	
	/**
	 * 彻底删除会员
	 */
	
	Integer ydelmember(Integer id);
	
	/**
	 * 模糊分页查询删除的会员
	 */
	Page<member> findAdelmembers(@Param("name")String name);
	
	/**
	 * 根据主键id找到标记删除的会员
	 */
	member findADelmebeById(Integer id);
	
	
	/**
	 * 分页模糊查询所有会员记录
	 */
	Page<member> findmeberByPLname(@Param("name")String name);
	/**
	 * 根据会员id主键查询所有信息
	 */
	member findmebeById(Integer id);
	/**
	 * 根据公司Id找会员信息
	 */
	
	member findmebeByCompanyId(Integer companyId);
	
	
	/**
	 * 修改会员信息companyId,companyName,userName,remarks,startTime,endTime,
	InvoiceNumber,isDel,type
	 */
	Integer UpdateMember(@Param("id")Integer id,@Param("companyName")String companyName,
			@Param("companyId")Integer companyId,@Param("userName")String userName,
			@Param("remarks")String remarks,
			@Param("startTime")Date startTime,@Param("endTime")Date endTime,
			@Param("InvoiceNumber")String InvoiceNumber,@Param("type")String type,
			@Param("isDel")Integer isDel);
	/**
	 * 根据用户名查找会员信息
	 */
	List<member> findMemberByName(String companyName);
	
}
