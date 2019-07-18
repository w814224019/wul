package com.select.wuliu.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
	//获得ip方法
	public static String  commgetIP(HttpServletRequest request){
			String ip = request.getHeader("x-forwarded-for"); 
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	            ip = request.getHeader("Proxy-Client-IP"); 
	        } 
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	            ip = request.getHeader("WL-Proxy-Client-IP"); 
	        } 
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	            ip = request.getRemoteAddr(); 
	        } 
			//System.err.println(ip+"ip:地址");
			return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
		}
}
