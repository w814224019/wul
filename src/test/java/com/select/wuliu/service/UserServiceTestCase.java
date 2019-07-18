package com.select.wuliu.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Role;
import com.select.wuliu.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	IUserService userService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	RoleService roleService;
	
	@Test
	public void sss(){
		List<User>ss =userService.getUser();
		System.err.println("---sss--"+ss);
	}
	
	
	
	@Test
	public void r(){
		/*Integer rid=8;
		String roleName="普通用户";
		Role r=new Role();
		r.setRid(rid);
		
		Integer r1=roleService.saveRole(r);
		System.err.println("----测试角色---"+r);*/
	}
	
	@Test
	public void s(){
		String username="管理员";
		User u=userService.findByUName(username);
		System.err.println("---service测试用户名查找---"+u);
	}
	@Test
	public void ss(){
		List l=userService.getUid();
		System.err.println("----service测试查找用户id--"+l.size());
	}
	
	@Test
	public void contextLoads() {
		String username="管理员2";
		String password="1234562";
		String salt="salt2";

		User u=new User();
		/*u.setUid(2);
		u.setUsername(username);
		u.setPassword(password);
		u.setSalt(salt);
		u.setId(2);*/
		Integer a=userService.save(u);
		System.err.println("---service测试注册user--"+a);
		
	}
	@Test
	public void contex(){
		   String cityFrom="上海";
	       String cityTo="山东";
	       Integer cid=101;
	       Pather path=new Pather();
	      // path.setCid(cid);
	       
	      Integer b= companyService.SavePather(path);
	      System.err.println("---service测试新增路线--"+b);
	}
	@Test
	public void con(){
		Integer cid=102;
		Integer isDelete=0;
		String	createdUser="普通用户2";
		Date createdTime=new Date();
		String modifiedUser="普通用户2";
		Date modifiedTime=new Date();
		Integer balance=0;
		String companyName="德邦物流2";
		String city="新乡";
		String companyAddress="南三环";
		String contact="刘备";
		String telephone="23123 213212";
		String phone="111111 222222";
		String picturePath="D:/";
		String qq="4546 54 65";
		String email="814224019@qq.com";
		String web="www.56114.com/debang";
		String type="零担";
		Companier comp=new Companier();
		
		comp.setCompanyName(companyName);
		comp.setContact(contact);
	
		
		
		
		comp.setPhone(phone);
		comp.setPicturePath(picturePath);
	
		comp.setTelephone(telephone);
		
		
		Integer c=companyService.SaveCompany(comp);
		System.err.println("---serviec测试注册公司---"+c);
	
	}
}
