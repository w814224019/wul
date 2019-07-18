package com.select.wuliu.util;

import java.util.Date;

import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.User;

public class LogerUtils {
	public static BaseEntity logger(String companyName,User u,Integer companyId,String type){
	BaseEntity ba=new BaseEntity();
	Date modifiedTime=new Date();
	ba.setCompany(companyName);
	ba.setMobile(u.getMobile());
	ba.setModifiedTime(modifiedTime);
	ba.setModifiedUser(u.getName());
	ba.setType(type);
	ba.setId(companyId);
	return ba;
	}
	
}
