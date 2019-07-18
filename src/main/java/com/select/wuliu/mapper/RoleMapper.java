package com.select.wuliu.mapper;

import org.apache.ibatis.annotations.Param;

import com.select.wuliu.entity.Role;


public interface RoleMapper {

	/**
	 * 增加角色
	 * @author 
	 *
	 */
	Integer addNewRole(Role role);
	
	/**
	 * 根据用户rid查找角色
	 */
	
	Role  findRoleByrid(Integer rid);
	
	/**
	 * 修改权限
	 */
	
	Integer updateRole(@Param("rid")Integer rid,@Param("roleName")String roleName,@Param("userName")String userName);
	
}
