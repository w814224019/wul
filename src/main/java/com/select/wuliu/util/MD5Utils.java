package com.select.wuliu.util;

import java.util.UUID;

import org.springframework.util.DigestUtils;

public class MD5Utils {
	//生成盐值
	public  String getMd5Password(String Srcpassword){
		String salt=UUID.randomUUID().toString();
		
		
		String str=salt+Srcpassword+salt;
		for(int i=0;i<10;i++){
			str=DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}
}
