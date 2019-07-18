package com.select.wuliu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.select.wuliu.entity.carUser;
import com.select.wuliu.entity.fahRecoreder;
import com.select.wuliu.mapper.carUserMapper;
import com.select.wuliu.mapper.fahRecorederMapper;
import com.select.wuliu.service.IfahRecorederService;

@Service
public class fahRecorederServiceImpl implements IfahRecorederService{
	@Autowired
	 fahRecorederMapper fahrMapper;
	 @Autowired
	 carUserMapper carMapper;
	
	@Override
	public Page<fahRecoreder> GetAfdhRecorder(Integer pageNum,Integer pageSize,String name,Integer userid) {
		PageHelper.startPage(pageNum, pageSize);
		return fahrMapper.findAfdhRecorder(name,userid);
	}

	@Override
	public Page<fahRecoreder> GetAfdhRecorderByi(Integer pageNum,Integer pageSize, Integer rid) {
		PageHelper.startPage(pageNum, pageSize);
		return fahrMapper.findAfdhRecorderByi(rid);
	}

	@Override
	public Page<fahRecoreder> GetAfdhRecorderBystatus(Integer pageNum,Integer pageSize,String status) {
		PageHelper.startPage(pageNum, pageSize);
		return fahrMapper.findAfdhRecorderBystatus(status);
	}

	@Override
	public Page<carUser> GetAllcarusers(Integer pageNum,Integer pageSize,String name, Integer userid) {
		PageHelper.startPage(pageNum, pageSize);
		return carMapper.findAllcarusers(name, userid);
	}

	@Override
	public Page<carUser> GetAcarUserByi(Integer pageNum,Integer pageSize,Integer rid) {
		PageHelper.startPage(pageNum, pageSize);
		return carMapper.findAcarUserByi(rid);
	}

}
