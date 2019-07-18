package com.select.wuliu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.evaluate;
import com.select.wuliu.entity.webs;
import com.select.wuliu.mapper.evaluateMapper;
import com.select.wuliu.mapper.loggerMapper;
import com.select.wuliu.mapper.websMapper;
import com.select.wuliu.service.LoggerService;
@Service
public class LoggerServiceImpl implements LoggerService {
	@Autowired
	loggerMapper loggerMapper;
	@Autowired
	evaluateMapper evMapper;
	@Autowired
	   websMapper websMapper;

	private Integer addLogger(BaseEntity baseEntity){
		return loggerMapper.addLogger(baseEntity);
	}
	private List<BaseEntity> findAll(){
		return loggerMapper.findAll();
	}


	@Override
	public Integer addLog(BaseEntity baseEntity) {
		Integer a=addLogger(baseEntity);
		return a;
	}

	@Override
	public List<BaseEntity> getAlllogger() {
		List<BaseEntity> bases=findAll();

		return bases;
	}
	private List<BaseEntity> findrecordsById(Integer id){
		return loggerMapper.findrecordsById(id);
	}
	@Override
	public List<BaseEntity> getrecordsByid(Integer id) {
		List<BaseEntity> list=findrecordsById(id);
		return list;
	}
	@Override
	public Page<BaseEntity> getrecordsByPage(Integer pageNum, Integer pageSize, Integer id) {
		PageHelper.startPage(pageNum,pageSize);
		return loggerMapper.findrecordsBypage(id);
	}
	@Override
	public Page<BaseEntity> getrecordsByLnamePage(Integer pageNum, Integer pageSize, String name) {
		PageHelper.startPage(pageNum,pageSize);

		return loggerMapper.findrecordsPLname(name);
	}
	private Integer updateEvluate(evaluate av, Integer id){
		return evMapper.updateEvluate(id, av.getStatus(), av.getAuditor(), av.getEdittime());
	}

	@Override
	public Integer UpdateEvas(evaluate av, Integer id) {
		Integer a=updateEvluate(av, id);
		return a;
	}
	@Override
	public Page<evaluate> getEvasByLnameP(Integer pageNum, Integer pageSize, String name) {
		PageHelper.startPage(pageNum,pageSize);
		return evMapper.findevasByPlname(name);
	}
	private evaluate findevasByid(Integer id){
		return evMapper.findevasByid(id);
	}
	
	@Override
	public evaluate getevaById(Integer id) {
		evaluate a =findevasByid(id);
		return a;
	}
	@Override
	public BaseEntity GetBaseEntityByid(Integer id) {
		return loggerMapper.findBaseEntityByid(id);
	}
	@Override
	public Integer SaveNewWeb(webs web) {
		return websMapper.addNewWeb(web);
	}
	@Override
	public Page<webs> GetwebsByrid(Integer pageNum, Integer pageSize,Integer rid, String name) {
		PageHelper.startPage(pageNum, pageSize);
		return websMapper.findByrid(rid, name);
	}
	@Override
	public Integer DelteWeb(Integer id) {
		return websMapper.Delteweb(id);
	}
	@Override
	public List<webs> GetallwebsByrid(Integer rid) {
		return websMapper.findallwebsByrid(rid);
	}

}
