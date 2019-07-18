package com.select.wuliu.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.evaluate;
import com.select.wuliu.entity.webUser;
import com.select.wuliu.entity.webs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class loggerTestCase {
	@Autowired
	loggerMapper logMapper;
	@Autowired
	websMapper websMapper;
	@Autowired
	WebUserMapper webusermapper;
	@Autowired
	 evaluateMapper evMapper;
	@Test
	public void fddsssss(){
		//evaluate a=evMapper.findevasByid(1);
		List<webUser> weuses=webusermapper.findAllweuser("");
		if(weuses.size()!=0){
		for (webUser wuser : weuses) {
			String mobile=wuser.getMobile();
			BaseEntity a=logMapper.findregtime(mobile);
			if(a!=null){
			Date addTime=a.getModifiedTime();
			String name=wuser.getName();
			String uid=wuser.getUid();
			Integer companyid=wuser.getCompanyid();
			//String mobile=wuser.getMobile();
			String permissionid=wuser.getPermissionid();
			String password=wuser.getPassword();
			String weixinUnionid=wuser.getWeixinUnionid();
			String address=wuser.getAddress();
			Integer delFlag=wuser.getDelFlag();
			String sex=wuser.getSex();
			String headimgurl=wuser.getHeadimgurl();
			String company=wuser.getCompany();
			String telephone=wuser.getTelephone();
			Integer rid=wuser.getRid();
			webusermapper.updatewebUser(rid, name, uid, companyid, mobile, permissionid, password, weixinUnionid, address, delFlag, sex, headimgurl, telephone, company, addTime);
			}
		}
		}//System.err.println(a);
	}
	
	@Test
	public void fddss(){
		//List<BaseEntity> list=logMapper.findrecordsById(28130);
		//BaseEntity list=logMapper.findBaseEntityByid(56);
		List<webs>list=websMapper.findallwebsByrid(56);
		System.err.println("list"+list);
	}
	@Test
	public void fdd(){
		List<BaseEntity> list=logMapper.findAll();
		System.err.println("---测试---"+list);
	}
	
	
	@Test
	public void f(){
		BaseEntity ba=new BaseEntity();	
		Date createdTime=new Date();
		Date modifiedTime=new Date();
		String user="王阳阳";
		String type="登录";
	    String mobile="15515559622";
		ba.setModifiedTime(modifiedTime);
		ba.setModifiedUser(user);
		ba.setMobile(mobile);
		ba.setType(type);
		Integer a=logMapper.addLogger(ba);
		
		System.err.println("====a测试日志==="+a);
		
		
	}
}
