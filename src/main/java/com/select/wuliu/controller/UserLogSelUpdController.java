package com.select.wuliu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Role;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.member;
import com.select.wuliu.entity.userToCorbcompany;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IUserService;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.ImemberSevice;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.service.RoleService;
import com.select.wuliu.service.exeception.UserNotFoundException;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.IPUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;
/**
 * 修改更新用信息,标记删除用户
 * 开通会员页面需要验证密码tokenUserinfo,展示修改会员信息页面,
 * 绑定页面通过名字找到id,获取所有用户列表 公司内部人员,
 * 模糊查询网点返回查询结果findbcompanysPBylname,模糊查询公司返回查询结果findbcompanysPBylname
 * 解除绑定delusertocpr,批量绑定用户与公司信息addnewusertoborc,
 * 登录,增加会员页面,展示修改界面
 * @author Admin
 *
 */
@Controller
public class UserLogSelUpdController extends BaseController{
	private static Logger log=LoggerFactory.getLogger(UserLogSelUpdController.class);
	private static final String CODE_KEY = "_code";
	@Autowired
	IUserService userService;
	@Autowired
	LoggerService loggerService;
	@Autowired
	private RedisService redisService;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	ImemberSevice memberService;
	@Autowired
    private RoleService roleService;
	//展示修改界面
		@GetMapping("/admin-add2")
		public String adminadd2(Integer rid,Model model){
			//取出cookie
			User u=userService.getById(rid);
			model.addAttribute("user",u);
			return "admin-add2";
		}
	//获取所有用户列表 公司内部人员
		@GetMapping("/admin-list")
		public String adminlist(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
			PageHelper.startPage(pageNum,pageSize);
			List<User> users=userService.getUser();
			PageInfo<User> pageInfo = new PageInfo<User>(users);
			model.addAttribute("pageInfo",pageInfo);
			return "admin-list";
		}
	//显示销售员绑定信息
	@GetMapping("/admin-permission")
	public String adminpermission(Model model){
		//查询销售员
		List<User> users=userService.GetbycompanyId(1);
		model.addAttribute("users",users);
		return "admin-permission";
	}
	//修改更新用信息
		@PostMapping("/updateUserinfo1")
		@ResponseBody
		public ResponseResult<String> updateUserinfo1(HttpServletRequest request,String name, String passwords,String mobile,
				Integer companyId,String permissionid,String sex){
			User u=userService.findByUName(name);
			Integer rid=u.getRid();
			User user=new User();
			user.setAddress(u.getAddress());
			user.setName(name);
			user.setMobile(mobile);
			user.setUid(u.getUid());
			user.setPassword(passwords);
			user.setDelFlag(0);
			
			userService.UpdateUserInfo(user, rid);
			//取出cookie
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			User u1=(User) redisService.get(cookie.getValue());
			//存日志
			BaseEntity ba=LogerUtils.logger(u1.getCompany(), u1, u1.getRid(), "修改管理员密码");
			loggerService.addLog(ba);  
			return new ResponseResult<String>(SUCCESS);
		}
	
	
	
	//修改更新用信息
	@PostMapping("/updateUserinfo")
	@ResponseBody
	public ResponseResult<String> updateUserinfo(HttpServletRequest request,String name,  String password,String passwords,String mobile,
			Integer companyId,String permissionid,String sex){
		User u=userService.findByUName(name);
		Integer rid=u.getRid();
		Role role=roleService.GetRoleByrid(rid);
		if(role==null){
			role=new Role();
		
		role.setRid(rid);
		if(permissionid.equals("7")){
			role.setRoleName("最高权限");
		}else if(permissionid.equals("5")){
			role.setRoleName("管理员");
		}else if(permissionid.equals("1")){
			role.setRoleName("普通用户");
		}
		role.setUserName(name);
		roleService.SaveNewRole(role);
		}else{
			if(permissionid.equals("7")){
				role.setRoleName("最高权限");
			}else if(permissionid.equals("5")){
				role.setRoleName("管理员");
			}else if(permissionid.equals("1")){
				role.setRoleName("普通用户");
			}
			role.setUserName(name);
			roleService.UpdateRole(role, rid);
		}
		
		User user=new User();
		user.setAddress(u.getAddress());
		user.setName(name);
		user.setMobile(mobile);
		user.setUid(u.getUid());
		if(passwords.equals("")){
			user.setPassword(password);
		}else{
			
			user.setPassword(passwords);
		}
		user.setCompanyid(companyId);
		user.setPermissionid(permissionid);
		user.setSex(sex);

		user.setDelFlag(0);
		userService.UpdateUserInfo(user, u.getRid());
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u1=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(u1.getCompany(), u1, u1.getRid(), "修改管理员信息");
		loggerService.addLog(ba);  
		return new ResponseResult<String>(SUCCESS);
	}
	//标记删除用户
	@PostMapping("/delUserinfo")
	@ResponseBody
	public ResponseResult<String> delUserinfo(HttpServletRequest request,String name, String passwords,String mobile,
			Integer rid,Integer companyId,String permissionid,String sex){
		User u=userService.getById(rid);

		u.setDelFlag(1);
		userService.UpdateUserInfo(u, rid);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u1=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(u1.getCompany(), u1, u1.getCompanyid(), "标记删除用户信息");
		loggerService.addLog(ba);  
		return new ResponseResult<String>(SUCCESS);
	}
	//增加会员页面
		@GetMapping("/member-add")
		public String memberadd(HttpServletRequest request,Model model){
			//取出cookie
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			//获取会员主键id 
			Integer id=(Integer) redisService.get(cookie.getValue()+"memberid");
			System.err.println(id+"公司id");
			Companier  c=companyService.getById(id);
			/*member m=memberService.getmemberById(id);
			if(m==null){
				throw new warnException("该公司还没有开通会员，请先开通！");
			}*/
			model.addAttribute("m",c);
			return "member-add";
		}
	
	//开通会员页面需要验证密码tokenUserinfo
	@PostMapping("/tokenUserinfo")
	@ResponseBody
	public ResponseResult<String> tokenUserinfo(HttpServletRequest request,String password,Integer type) {
		//System.err.println("---type--"+type);
		//取出cookie
		User use=userService.findByUName("郭瑞午");
		if(!password.equals(use.getPassword())){
			throw new warnException("密码不正确，请联系管理员！");
		}
		if(type==1){
			//跳转到修改会员信息
			return new ResponseResult<String>(SUCCESS1);
		}


		return new ResponseResult<String>(SUCCESS);
	}
	//展示修改会员信息页面
	@GetMapping("/member-show")
	public String membershow(HttpServletRequest request,Model model,Integer id){
		//取出cookie
		//Cookie cookie= CookieUtil.getCookieByName(request,"user");
		//获取会员主键id 
		//Integer id=(Integer) redisService.get(cookie.getValue()+"memberid");
		member m=memberService.getmemberById(id);
		if(m==null){
			throw new warnException("请选择正确页面！");
		}
		//System.err.println("m--"+m);
		model.addAttribute("m",m);
		return "member-show";
	}
	

	//绑定页面通过名字找到id
	@GetMapping("/findadmins")
	@ResponseBody
	public ResponseResult<User> findByid1(String name){
		User u=userService.findByUName(name);
		if(u==null){
			throw new warnException("你输入的:"+name+"没有找到!");
		}
		return new ResponseResult<User>(SUCCESS,u);
	}
	



	//模糊查询网点返回查询结果findbcompanysPBylname
	@GetMapping("/findbcompanysPBylname")
	@ResponseBody
	public ResponseResult<List<bCompany>> findbcompanysPBylname(String name,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize")Integer pageSize){
		Page<bCompany> list=bcompanyService.getbCompanyPByLN(name, pageSize, pageNum);
		List<bCompany> list1=new ArrayList<bCompany>();
		for (bCompany bCompany : list) {
			bCompany.setPages(list.getPages());
			bCompany.setTotals(list.getTotal());
			list1.add(bCompany);
		}
		return new ResponseResult<List<bCompany>>(SUCCESS,list1);
	}
	//模糊查询公司返回查询结果findbcompanysPBylname
	@GetMapping("/findCompanysPBylname")
	@ResponseBody
	public ResponseResult<List<Companier>> findCompanysPBylname(String name,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize")Integer pageSize){
		Page<Companier> list=companyService.getCompanyPByName(name, pageSize, pageNum);
		List<Companier> list1=new ArrayList<Companier>();
		for (Companier companier : list) {
			companier.setPages(list.getPages());
			companier.setTotals(list.getTotal());
			list1.add(companier);
		}
		return new ResponseResult<List<Companier>>(SUCCESS,list1);
	}

	//解除绑定delusertocpr
	@PostMapping("/delusertocpr")
	@ResponseBody
	public ResponseResult<String> delusertocpr(HttpServletRequest request,String ids,Integer rid){
		if(ids.equals("")){
			throw new warnException("请选择公司或网点!");
		}
		String[] split = ids.split(",");
		for (String string : split) {
			//System.err.println("spl的长度"+spl.length);
			if(!string.contains("!")){
				throw new warnException("请点击查询我所绑定的公司或网点后再选择删除项！");
			}


			if(!string.equals("")){
				String[] string1=string.split("!");


				Integer companyid=Integer.parseInt(string1[0]);
				Integer mark=Integer.parseInt(string1[1]);
				userToCorbcompany a=companyService.getusertobOrcompanyByid(companyid,mark);
				if(a==null){
					throw new warnException("当前公司或者网点"+companyid+"已经解除绑定!");
				}
				userToCorbcompany utoc=new userToCorbcompany();
				utoc.setCompanyid(companyid);
				utoc.setIsDel(1);
				utoc.setUserid(rid);
				utoc.setMark(mark);
				companyService.updatusertoCorbpany(utoc, companyid);
				//取出cookie
				Cookie cookie= CookieUtil.getCookieByName(request,"user");
				User u1=(User) redisService.get(cookie.getValue());
				//存日志
				BaseEntity ba=LogerUtils.logger(u1.getCompany(), u1, companyid, "解除绑定销售与公司或者网点信息");
				loggerService.addLog(ba);  
			}
		}

		return new ResponseResult<String>(SUCCESS);
	}

	//批量绑定用户与公司信息addnewusertoborc
	@PostMapping("/addnewusertoborc")
	@ResponseBody
	public ResponseResult<String> addnewusertoborc(HttpServletRequest request,String ids,Integer rid,Integer mark){
		//System.err.println("mark:"+mark);
		if(ids.equals("")){
			throw new warnException("请选择公司或网点!");
		}
		String[] split = ids.split(",");
		for (String string : split) {
			//System.err.println("spl的长度"+spl.length);
			if(!string.equals("")){
				Integer companyid=Integer.parseInt(string);
				userToCorbcompany a=companyService.getusertobOrcompanyByid(companyid,mark);
				//查询如果该公司已经绑定提醒
				if(a!=null){
					throw new warnException("公司或网点id为"+a.getCompanyid()+"已经绑定过!");
				}
				userToCorbcompany utoc=new userToCorbcompany();
				utoc.setCompanyid(companyid);
				utoc.setIsDel(0);
				utoc.setUserid(rid);
				utoc.setMark(mark);
				companyService.SaveusettoCompany(utoc);
				//取出cookie
				Cookie cookie= CookieUtil.getCookieByName(request,"user");
				User u1=(User) redisService.get(cookie.getValue());
				//存日志
				BaseEntity ba=LogerUtils.logger(u1.getCompany(), u1, companyid, "绑定销售与公司或者网点信息");
				loggerService.addLog(ba);  
			}
		}

		return new ResponseResult<String>(SUCCESS);
	}





	@GetMapping("login")
	public String UserReg(){
		return "login";
	}
	@PostMapping("/login")
	@ResponseBody
	public ResponseResult<String> login(String name,String password,String code,Boolean rememberMe,HttpServletResponse response ){
		//System.err.println(name+"登录用户名");
		if(name.equals("")){
			//用户名不能为空
			return new ResponseResult<>(WARN,"用户名不能为空！");
		}


		if (!StringUtils.isNotBlank(code)) {
			return 	new ResponseResult<>(WARN,"验证码不能为空");
		}
		
		
		Session session=super.getSession();
		User user=userService.findByUName(name);
		
		if(!password.equals(user.getPassword())){
			return 	new ResponseResult<>(WARN,"密码错误！");
		}
		//生成uuid存放在cookie
		String uuid =UUID.randomUUID().toString();
		// System.err.println(user+"user");
		//添加保存cookie
		CookieUtil.addCookie(response, "user",uuid);
		redisService.set(uuid, user);
		String sessionCode = (String) session.getAttribute(CODE_KEY);
		if (!code.equalsIgnoreCase(sessionCode)) {
			return 	new ResponseResult<>(WARN,"验证码错误");
		}
		//System.err.println("测试rememberMe:"+rememberMe);
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
				name, password,true);
		Subject subject=getSubject();
		log.info("------subject---"+subject);
		if(subject!=null){
			subject.logout();
		}
		super.login(usernamePasswordToken);
		//存日志
		BaseEntity ba=LogerUtils.logger(user.getCompany(), user, user.getRid(), "登录");
		loggerService.addLog(ba);
		Integer rid=user.getRid();
		String roleName=roleService.GetRoleByrid(rid).getRoleName();
		//System.err.println("roleName"+roleName);
		if(roleName.equals("最高权限")){
			//去管理员页面
			return new ResponseResult<String>(SUCCESS);
		}else if(roleName.equals("管理员")){
			
			//PUBLIC 去普通页面
			return new ResponseResult<>(ADMIN);
		}else if(roleName.equals("普通用户")){
			//PUBLIC 去普通页面
			return new ResponseResult<>(PUBLIC);
		}else{
			return new ResponseResult<>(PUBLIC);
		}
		/*String role=userService.getRoleByUid(Integer.parseInt(userService.getUserByName(name).getId())).getName();
        System.err.println("----登录controller获取角色--"+role);
        if ("管理员".equals(role)) {
            return 	new ResponseResult<>(ADMIN);
        }*/
	}

}
