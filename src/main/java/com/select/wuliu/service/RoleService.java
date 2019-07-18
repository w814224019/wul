package com.select.wuliu.service;

import org.springframework.stereotype.Service;

import com.select.wuliu.entity.Role;
@Service
public interface RoleService {
	
	/**
	 * 增加角色
	 * @author 
	 *
	 */
	Integer SaveNewRole(Role role);
	
	/**
	 * 根据用户rid查找角色
	 */
	
	Role  GetRoleByrid(Integer rid);
	
	/**
	 * 修改权限
	 */
	
	Integer UpdateRole(Role role,Integer rid);
}
