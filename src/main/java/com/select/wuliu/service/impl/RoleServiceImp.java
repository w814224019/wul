package com.select.wuliu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.select.wuliu.entity.Role;
import com.select.wuliu.mapper.RoleMapper;
import com.select.wuliu.service.RoleService;

@Service
public class RoleServiceImp implements RoleService{
	@Autowired
	RoleMapper roleMapper;

	@Override
	public Integer SaveNewRole(Role role) {
		return roleMapper.addNewRole(role);
	}

	@Override
	public Role GetRoleByrid(Integer rid) {
		return roleMapper.findRoleByrid(rid);
	}

	@Override
	public Integer UpdateRole(Role role, Integer rid) {
		String roleName=role.getRoleName();
		String userName=role.getUserName();
		return roleMapper.updateRole(rid, roleName, userName);
	}


}
