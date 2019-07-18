package com.select.wuliu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.select.wuliu.entity.member;
import com.select.wuliu.mapper.memberMapper;
import com.select.wuliu.service.ImemberSevice;

@Service
public class memberServiceImpl implements ImemberSevice  {
	@Autowired
	memberMapper membermapper;
	private Integer addNewMember(member m){
		return membermapper.addNewMember(m);
	}
	@Override
	public Integer SavaMember(member m) {
		Integer a=addNewMember(m);
		return a;
	}
	@Override
	public Page<member> getmembersByPLn(String name, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return membermapper.findmeberByPLname(name);
	}
	private member findmebeById(Integer id){
		return membermapper.findmebeById(id);
	}
	@Override
	public member getmemberById(Integer id) {
		member b=findmebeById(id);
		return b;
	}
	private Integer UpdateMember(member m, Integer id) {
		return membermapper.UpdateMember(id, m.getCompanyName(), 
				m.getCompanyId(), m.getUserName(), m.getRemarks(),
				m.getStartTime(), m.getEndTime(), m.getInvoiceNumber(), 
				m.getType(), m.getIsDel());
	}
	@Override
	public Integer Updatemember(member m, Integer id) {
		Integer c=UpdateMember(m, id);
		return c;
	}
	@Override
	public List<member> getmemByName(String companyName) {
		return membermapper.findMemberByName(companyName);
	}
	private Integer ydelmember(Integer id){
		return membermapper.ydelmember(id);
	}
	@Override
	public Integer YDelmember(Integer id) {
		Integer a=ydelmember(id);
		return a;
	}
	@Override
	public Page<member> getAdelmembers(String name, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return membermapper.findAdelmembers(name);
	}
	private member findADelmebeById(Integer id){
		return membermapper.findADelmebeById(id);
	}
	@Override
	public member getAelmemberById(Integer id) {
		member m=findADelmebeById(id);
		return m;
	}
	@Override
	public member getmebeByCompanyId(Integer companyId) {
		return  membermapper.findmebeByCompanyId(companyId);
	}
	
	
}
