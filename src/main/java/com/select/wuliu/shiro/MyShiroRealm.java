package com.select.wuliu.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.select.wuliu.entity.Role;
import com.select.wuliu.entity.User;
import com.select.wuliu.service.IUserService;
import com.select.wuliu.service.RoleService;




//实现AuthorizingRealm接口用户用户认证

public class MyShiroRealm extends AuthorizingRealm {
	
	
	//用于用户查询
    @Autowired
    private IUserService userService;
    @Autowired
    private RoleService roleService;
    //角色权限和对应权限添加
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		 String name = (String) SecurityUtils.getSubject().getPrincipal();
		//获取登录用户名
       // String name= (String) principalCollection.getPrimaryPrincipal();
      //查询用户名称
        User user = userService.findByUName(name);
        Integer uid=user.getRid();
        Role role= roleService.GetRoleByrid(uid);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        String rol =role.getRoleName();
        set.add(rol);
        System.out.println("————权限认证————"+set+name);
        //System.err.println(set+"权限认证");
        //List<Permission> permiss=Permission.getPermissionById(pid);
      //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            //添加角色
            simpleAuthorizationInfo.setRoles(set);
                //添加权限
               // simpleAuthorizationInfo.addStringPermission(permission.getPermission());
		return simpleAuthorizationInfo;
	}
	//用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("————身份认证方法————");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
     // 获取用户信息
		String password=userService.findByUName(token.getUsername()).getPassword();
		System.out.println("----shiro 获取password---"+password);
		 if (null==password){
			 throw new AccountException("用户名不存在");
		 }else if(!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
            
        String name = authenticationToken.getPrincipal().toString();
        System.out.println("----shiro获取用户名---"+name);
        User user = userService.findByUName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
           
            return simpleAuthenticationInfo;
	}
	}
}
