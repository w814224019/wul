package com.select.wuliu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.member;
@Service
public interface ImemberSevice {
	/**
	 * 增加会员
	 */
	Integer SavaMember(member m);
	/**
	 * 彻底删除会员
	 */
	Integer YDelmember(Integer id);
	/**
	 * 分页模糊查询所有标记删除的会员
	 */
	Page<member> getAdelmembers(String name,Integer pageNum, Integer pageSize);
	/**
	 * 根据主键id找到标记删除的会员
	 */
	member getAelmemberById(Integer id);
	/**
	 * 分页模糊查询所有会员记录
	 */
	Page<member> getmembersByPLn(String name,Integer pageNum, Integer pageSize);
	/**
	 * 根据会员id主键查询所有信息
	 */
	member getmemberById(Integer id);
	/**
	 * 根据公司Id找会员信息
	 */
	
	member getmebeByCompanyId(Integer companyId);
	/**
	 * 修改会员信息
	*/
	Integer Updatemember(member m,Integer id);
	/**
	 * 根据会员公司名称找会员信息
	 */
	List<member> getmemByName(String companyName);
	
	
}
