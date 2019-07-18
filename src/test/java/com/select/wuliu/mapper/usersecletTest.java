package com.select.wuliu.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.select.wuliu.entity.User;
@RunWith(SpringRunner.class)
@SpringBootTest
public class usersecletTest {
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void fds(){
		User a=userMapper.findById(1);
		System.err.println("--查找uid--"+a);
	}
	
	@Test
	public void f(){
	List<Integer>list=userMapper.findyId();
		System.err.println("--查找uid--"+list);
		
	}
	@Test
	public void s(){
		String username="刘庆";
		User user=userMapper.findByUserName(username);
		System.err.println("---测试mapper查找用户---"+user);
	}
	@Test
	public void as(){
		String a="17335577115";
		String b=a.substring(5);
		System.err.println("----b---"+b);
		
	}
	
	@Test
	public void ass(){
		List<User>f=userMapper.findyUser();
		System.err.println("--f--"+f);
	}
	
}
