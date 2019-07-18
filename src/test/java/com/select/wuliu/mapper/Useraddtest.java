package com.select.wuliu.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.select.wuliu.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Useraddtest {
	@Autowired
	UserMapper userMapper;
	
	
	
	@Test
	public void ass(){
		String name="李阳";
		Integer companyid=1;
		String mobile="15515559622";
		String uid=mobile.substring(5);
		String company="郑州聚时物流有限公司";
		String password="123456";
		String provinc="河南";
		String city="郑州";
		Integer del_flag=0;
		String permissionid="1";
		//Integer a=userMapper.updateUser(111, name, uid, companyid, mobile, permissionid, password, "", "", 0, "男", "");
		
		
	}
	@Test
	public void a(){
		User u=new User();
		//name,uid,khr_id,companyid,branchid,mobile,permissionid
		//,company,password,weixin_unionid,province,city,address,del_flag
		//,headimgurl,sex,areabranchid
		String name="李阳";
		Integer companyid=1;
		String mobile="15515559622";
		String uid=mobile.substring(5);
		String company="郑州聚时物流有限公司";
		String password="123456";
		String provinc="河南";
		String city="郑州";
		Integer del_flag=0;
		String permissionid="1";
		u.setPermissionid(permissionid);
		u.setName(name);
		u.setCompany(company);
		u.setCompanyid(companyid);
		u.setMobile(mobile);
		u.setUid(uid);
		u.setPassword(password);
		u.setProvince(provinc);
		u.setCity(city);
		u.setDelFlag(del_flag);
		Integer a=userMapper.addNewUser(u);
		System.err.println("----测试----a:"+a);
		
		
		
		
		
				
				
		
		
		
	}
}
