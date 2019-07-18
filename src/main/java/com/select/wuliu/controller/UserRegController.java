package com.select.wuliu.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Role;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.evaluate;
import com.select.wuliu.service.IUserService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.service.RoleService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;


/**
 * 更新用户addUser，增加用户addUser，查询所有留言板内容seleevlus
 * 批量审核留言updateEvals，
 * @author Admin
 *
 */
@Controller
public class UserRegController extends BaseController {
	@Autowired
	IUserService userService;
	@Autowired
	LoggerService loggerService;
	@Autowired
    private RedisService redisService;
	@Autowired
    private RoleService roleService;
	
	//更新用户addUser
		@GetMapping("/admin-add3")
		public String adminadd3(Integer rid,Model model){
			//取出cookie
			User u=userService.getById(rid);
			//System.out.println(u+"用户");
			model.addAttribute("user",u);
			return "admin-add3";
		}
	
	//增加用户addUser
	@GetMapping("/admin-add")
	public String adminadd(){
		return "admin-add";
	}
	@PostMapping("/regUser")
	@ResponseBody
	public ResponseResult<Void> Reg(HttpServletRequest request,String name,String password, String passwords,String mobile,
			Integer companyId,String permissionid,String sex){
		if(name.equals("")){
			//用户名不能为空
			return new ResponseResult<>(WARN,"用户名不能为空！");
		}
		if(mobile.equals("")){
			//用户名不能为空
			return new ResponseResult<>(WARN,"手机号不能为空！");
		}
		User u=userService.findByUName(name);
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
			String uid=mobile.substring(5);
			user.setAddress("郑州聚时物流有限公司");
			user.setProvince("河南");
			user.setCity("郑州");
			user.setName(name);
			user.setMobile(mobile);
			user.setUid(companyId+""+uid);
			user.setPassword(password);
			user.setCompanyid(companyId);
			user.setPermissionid(permissionid);
			user.setSex(sex);
			user.setDelFlag(0);
			userService.save(user);
			User ua=userService.findByUName(name);
			Integer rida=ua.getRid();
			Role role=new Role();
			if(permissionid.equals("7")){
				role.setRoleName("最高权限");
			}else if(permissionid.equals("5")){
				role.setRoleName("管理员");
			}else if(permissionid.equals("1")){
				role.setRoleName("普通用户");
			}
			role.setRid(rida);
			role.setUserName(name);
			roleService.SaveNewRole(role);
			//取出cookie
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			User u1=(User) redisService.get(cookie.getValue());
			//存日志
			BaseEntity ba=LogerUtils.logger("郑州聚时物流有限公司", u1, u1.getCompanyid(), "公司注册新用户");
			loggerService.addLog(ba);
			/*//总经理 创建角色
			if(Integer.parseInt(permissionid)==5){
				
				base.setMark("管理员");
			}else if(Integer.parseInt(permissionid)==7){
				//总经理 创建角色 最高
				
				base.setMark("最高权限");
			}else{
				// 创建角色
				base.setMark("普通用户");
			}*/

		}
		return new ResponseResult<Void>(SUCCESS);
	}
	//查询所有留言板内容seleevlus
	@GetMapping("/feedback-list")
	public String feedbacklist(Model model,String name,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		Page<evaluate> list=loggerService.getEvasByLnameP(pageNum, pageSize, name);
		if(list.size()==0){
			throw new warnException("没有找到"+name+"留言！");
		}
		PageInfo<evaluate> pageInfo = new PageInfo<evaluate>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("name",name);
		return "feedback-list";
	}
	
		
	//批量审核留言updateEvals
		@PostMapping("/updateEvals")
		@ResponseBody
		public ResponseResult<String> updateEvals(HttpServletRequest request,String ids){
			if(ids.equals("")){
				throw new warnException("请选择审核内容!");
			}
			String[] split = ids.split(",");
			for (String string : split) {
					//System.err.println("spl的长度"+spl.length);
				if(!string.equals("")){
				Integer id=Integer.parseInt(string);
				evaluate a=loggerService.getevaById(id);
				//取出cookie
				Cookie cookie= CookieUtil.getCookieByName(request,"user");
				User user=(User) redisService.get(cookie.getValue());
				Date modifiedTime=new Date();
				a.setAuditor(user.getName());
				a.setStatus("已审核");
				a.setEdittime(modifiedTime);
				loggerService.UpdateEvas(a, id);
				//存日志
				BaseEntity ba=LogerUtils.logger(user.getCompany(), user, user.getRid(), "审核留言板内容");
				loggerService.addLog(ba);  
				}
			}
				
			return new ResponseResult<String>(SUCCESS);
		}
		
	
	
	
	
	
	
	
	
}
