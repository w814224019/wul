package com.select.wuliu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.select.wuliu.controller.exeception.RequestException;
import com.select.wuliu.controller.exeception.UserAlreadyExistsException;
import com.select.wuliu.controller.exeception.passwordNotSameExeception;
import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.User;
import com.select.wuliu.service.exeception.CompanyNotFoundException;
import com.select.wuliu.service.exeception.DuplicateKeyException;
import com.select.wuliu.service.exeception.PatherNotFoundException;
import com.select.wuliu.service.exeception.ServiceException;
import com.select.wuliu.service.exeception.TaskNotFoundException;
import com.select.wuliu.service.exeception.UpdateException;
import com.select.wuliu.service.exeception.UserNotFoundException;
import com.select.wuliu.service.exeception.areaNoFoundExeception;
import com.select.wuliu.service.exeception.bCompanyNotFoundException;
import com.select.wuliu.service.exeception.moneyNotFoundException;
import com.select.wuliu.service.exeception.picturerNotFoundException;
import com.select.wuliu.util.ResponseResult;




/**
 * 当前项目中所有控制器类的基类
 * 跳到网点代号202，管理员代号201，普通用户代号203
 */
public class BaseController {
	/**
	 * 正确响应时的代号
	 */
	public static final Integer SUCCESS = 200;
	//跳到网点
	public static final Integer SUCCESS1 = 202;
	//管理员
	public static final Integer ADMIN = 201;
	//普通用户
	public static final Integer PUBLIC = 203;
	public static final Integer SHIRO = 401;
	public static final Integer WARN = 402;
	protected static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	protected User getCurrentUser(){
		return (User)getSubject().getPrincipal();
	}
	protected Session getSession(){
		return getSubject().getSession();
	}
	protected Session getSession(Boolean flag){
		return getSubject().getSession(flag);
	}
	
	protected void login(AuthenticationToken token){
		getSubject().login(token);
	}
	
	@ExceptionHandler({RequestException.class,ServiceException.class,AuthenticationException.class})
	@ResponseBody
	public ResponseResult<Void> handleException(
			Exception e) {
		Integer state = null;
		
		if (e instanceof AccountException) {
			// 401-违反了shiro验证的异常
			state = 401;
			
		} else if (e instanceof UserAlreadyExistsException) {
			// 401-用户数据已经存在
			state = 402;
		}else if (e instanceof passwordNotSameExeception) {
			// 401-用户数据不存在
			state = 402;
		}else if (e instanceof areaNoFoundExeception) {
			// 400-根据地区找公司数据不存在
			state = 402;
		}else if (e instanceof CompanyNotFoundException) {
			// 400-根据公司名称公司数据不存在
			state = 400;
		}else if (e instanceof DuplicateKeyException) {
			// 402-根据公司名称公司数据不存在
			state = 402;
		}else if (e instanceof UpdateException) {
			// 402-更新数据出现异常
			state = 402;
		}else if (e instanceof picturerNotFoundException) {
			// 402-根据公司id找图片信息没找到
			state = 402;
			
		}else if (e instanceof UserNotFoundException) {
			// 402-根据用户名找用户
			state = 402;
		
		}else if (e instanceof bCompanyNotFoundException) {
			// 402-根据网点id 找网点
			state = 402;
		
		}else if (e instanceof PatherNotFoundException) {
			// 402-根据网点id 找线路
			state = 402;
		
		}else if (e instanceof warnException) {
			// 402-前端警告异常
			state = 402;
		
		}else if (e instanceof moneyNotFoundException) {
			// 402-没有找到物流币信息
			state = 402;
		
		}else if (e instanceof TaskNotFoundException) {
			// 402-没有找到任务条信息
			state = 402;
		
		}
		
		return new ResponseResult<>(state, e.getMessage());
	
}
	
	
	
	
}