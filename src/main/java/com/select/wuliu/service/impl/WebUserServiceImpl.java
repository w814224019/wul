package com.select.wuliu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.select.wuliu.entity.carLine;
import com.select.wuliu.entity.webUser;
import com.select.wuliu.mapper.WebUserMapper;
import com.select.wuliu.mapper.carLineMapper;
import com.select.wuliu.service.IWebUserService;

@Service
public class WebUserServiceImpl implements IWebUserService{
	@Autowired
	WebUserMapper wuserMapper;
	@Autowired
	carLineMapper carlineMapper;
	@Override
	public Page<webUser> GetAllweuser(String name, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		return wuserMapper.findAllweuser(name);
	}
	@Override
	public webUser GetwebUserById(Integer id) {
		return wuserMapper.findwebUserById(id);
	}
	@Override
	public Page<webUser> GetBytype(Integer type,Integer pageSize,Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		return wuserMapper.findBytype(type);
	}
	@Override
	public Integer UpdatewebUser(webUser wuser, Integer rid) {
		String name=wuser.getName();
		String uid=wuser.getUid();
		Integer companyid=wuser.getCompanyid();
		String mobile=wuser.getMobile();
		String permissionid=wuser.getPermissionid();
		String password=wuser.getPassword();
		String weixinUnionid=wuser.getWeixinUnionid();
		String address=wuser.getAddress();
		Integer delFlag=wuser.getDelFlag();
		String sex=wuser.getSex();
		String headimgurl=wuser.getHeadimgurl();
		String company=wuser.getCompany();
		String telephone=wuser.getTelephone();
		Date addTime=wuser.getAddTime();
		return wuserMapper.updatewebUser(rid, name, uid, companyid, mobile, permissionid, password, weixinUnionid, address, delFlag, sex, headimgurl, telephone, company, addTime);
				
	}
	@Override
	public List<carLine> GetcarLIneBycarid(Integer carid) {
		return carlineMapper.findcarLIneBycarid(carid);
	}
	@Override
	public Integer UpdatecarLIneByid(Integer id, Integer isDel) {
		return carlineMapper.UpdatecarLIneByid(id, isDel);
	}


}
