package com.select.wuliu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.select.wuliu.entity.User;
import com.select.wuliu.mapper.UserMapper;
import com.select.wuliu.service.IUserService;
import com.select.wuliu.service.exeception.UserNotFoundException;
import com.select.wuliu.service.exeception.picturerNotFoundException;
@Service
public class UserServiceImp implements IUserService{
@Autowired
UserMapper userMapper;

	@Override
	public Integer save(User user) {
     Integer i=addNewUser(user);

		return i;
	}
private Integer addNewUser(User user){
	return userMapper.addNewUser(user);
}
private User findByUserName(String username){
	return userMapper.findByUserName(username);
}
private List<Integer> findyId(){
	return userMapper.findyId();
}
private List<User> findUser(){
	return userMapper.findyUser();
}



@Override
public User findByUName(String username) {
	User u=findByUserName(username);
	return u;
}
@Override
public List<Integer> getUid() {
	List l=findyId();
	return l;
}
@Override
public List<User> getUser() {
	List users=findUser();
	return users;
}
private User findById(Integer rid){
	return userMapper.findById(rid);
}
@Override
public User getById(Integer rid) {
	User a=findById(rid);
	if(a==null){
		throw new UserNotFoundException("没有找到主键id为："+rid+"用户！");
	}
	return a;
}
private Integer updateUser(User u,Integer rid){
	String name=u.getName();
	String uid=u.getUid();
	Integer companyid=u.getCompanyid();
	String mobile=u.getMobile();
	String permissionid=u.getPermissionid();
	String password=u.getPassword();
	String weixinUnionid=u.getWeixinUnionid();
	String address=u.getAddress();
	Integer delFlag=u.getDelFlag();
	String sex=u.getSex();
	String headimgurl=u.getHeadimgurl();
	return userMapper.updateUser(rid, name, uid, companyid, mobile, 
			permissionid, password, weixinUnionid, address,
			sex, headimgurl, delFlag);
			
          }
@Override
public Integer UpdateUserInfo(User u, Integer rid) {
	Integer a=updateUser(u, rid);
	return a;
}
@Override
public List<User> GetbycompanyId(Integer companyId) {
	return userMapper.findbycompanyId(companyId);
}

	
	
	
	
}
