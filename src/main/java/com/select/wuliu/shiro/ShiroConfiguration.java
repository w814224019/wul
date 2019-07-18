package com.select.wuliu.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {
	private static Logger log=LoggerFactory.getLogger(ShiroConfiguration.class);
	//将自己的验证方式加入容器
	@Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }
	/**
	  * cookie对象;
	  * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
	  * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
		System.out.println("ShiroConfiguration.rememberMeCookie()");
	      //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	      SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	      //防止xss读取cookie
	      simpleCookie.setHttpOnly(true);
	      simpleCookie.setPath("/");
	      //<!-- 记住我cookie生效时间30天 ,单位秒;-->
	      simpleCookie.setMaxAge(259200);
	      return simpleCookie;
	}
	 
	/**
	  * cookie管理对象;
	  * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	  * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(){
	      System.out.println("ShiroConfiguration.rememberMeManager()");
	      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	      cookieRememberMeManager.setCookie(rememberMeCookie());
	      //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
	      cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
	      return cookieRememberMeManager;
	}

	
	
	
	 //权限管理，配置主要是Realm的管理认证
	 @Bean
	    public SecurityManager securityManager() {
	        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	        securityManager.setRealm(myShiroRealm());
	      //注入记住我管理器
	        securityManager.setRememberMeManager(rememberMeManager());
	        return securityManager;
	    }
	 //Filter工厂，设置对应的过滤条件和跳转条件
	 @Bean
	    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
	        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	        shiroFilterFactoryBean.setSecurityManager(securityManager);
	        Map<String,String> map = new HashMap<String, String>();
	        //登出
	        map.put("/logout","logout");
	        //登录
	        shiroFilterFactoryBean.setLoginUrl("/login");
	        //首页
	        shiroFilterFactoryBean.setSuccessUrl("/index");
	      //用户，需要角色权限 “user”
	        map.put("/index/**", "roles[最高权限]");
	      //管理员，需要角色权限 “管理员”
	       map.put("/admin/**", "roles[管理员]");
	       map.put("/index3/**", "roles[普通用户]");
	     
	       //开放登陆接口       放开img下的权限控制
            map.put("/img/**", "anon");
	        map.put("/login", "anon");
	       // map.put("/**", "anon");
	        //对所有用户认证
	        map.put("/index","authc");
	        map.put("/admin","authc");
	        map.put("/index3","authc");
	        //错误页面，认证不通过跳转
	        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
	        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
	        log.info("---Shiro拦截器工厂类注入成功---");
	        return shiroFilterFactoryBean;
	        
	 }
	//加入注解的使用，不加入这个注解不生效
	    @Bean
	    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
	        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
	        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
	        return authorizationAttributeSourceAdvisor;
	    }
}
