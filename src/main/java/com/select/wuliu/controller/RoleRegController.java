package com.select.wuliu.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.select.wuliu.entity.Role;
import com.select.wuliu.entity.User;
import com.select.wuliu.mapper.RoleMapper;
import com.select.wuliu.service.IUserService;
import com.select.wuliu.service.RoleService;
import com.select.wuliu.util.ResponseResult;
/**
 * 角色控制
 * @author Admin
 *
 */
@Controller
public class RoleRegController extends BaseController{
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	IUserService userService;
	@Autowired
	RoleService roleService;
	@GetMapping("/reg/RoleReg")
	public String UserReg(){
		return "RoleReg";
	}
	@PostMapping("RoleReg")
	@ResponseBody
	public ResponseResult<Void> RegRole(String roleName ,String username,
			String password,String passwords,String phone){
		Date createdTime=new Date();
		Date modifiedTime=new Date();
		System.err.println("----roleName---"+roleName);
		//生成盐值
	   String salt=UUID.randomUUID().toString();
	   List list=userService.getUid();
	   Integer uid=list.size()+1;
	   User u=userService.findByUName(username);
	   if(username.equals("")){
			//用户名不能为空
			return new ResponseResult<>(WARN,"用户名不能为空！");
		}
	   if(u!=null){
			//用户名已经注册过！
			return new ResponseResult<>(WARN,"用户名已经注册过！");
		}
		System.err.println("----比较密码---"+password.equals(passwords));
		if(password.equals(passwords)){
			//前后输入密码一致
		}else{
			//前后输入密码不一致
			return new ResponseResult<>(WARN,"前后输入密码不一致！");
		}
		if(u==null){
			User user=new User();
			Role ro=new Role();
			
			
			
			String newPassword=getMd5Password(password,salt);
			user.setPassword(newPassword);
			
			Integer rid=uid;
			String username1=username;
			String roleNamea=roleName;
			ro.setRid(rid);
			ro.setRoleName(roleNamea);
		//	roleService.saveRole(ro);
			userService.save(user);
			
		}
		return new ResponseResult<Void>(SUCCESS);
	}
	   
	   
	   
	   
	   

	
	private String getMd5Password(String Srcpassword,String salt){
		String str=salt+Srcpassword+salt;
		for(int i=0;i<10;i++){
			str=DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}



}
