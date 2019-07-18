package com.select.wuliu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.select.wuliu.entity.International;
import com.select.wuliu.mapper.internationalMapper;
import com.select.wuliu.service.InternationService;

@Service
public class internationServiceImpl implements InternationService{
	@Autowired
	internationalMapper intermapper;
	
	private Integer addInternation(International inter){
    	return intermapper.addInternation(inter);
    }
	@Override
	public Integer SaveInternational(International inter) {
		Integer a=addInternation(inter);
		return a;
	}
	private International findintercById(Integer id){
		return intermapper.findintercById(id);
	}
	@Override
	public International getinterById(Integer id) {
		International a=findintercById(id);
		return a;
	}
	@Override
	public Page<International> getintersPBylName(String name, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		return intermapper.findintersByPLname(name);
	}
	private Integer updateinter(International inter, Integer id) {
		return intermapper.updateinter(id, inter.getCompanyName(), inter.getPhone(), 
				inter.getContent(), inter.getZhuye(), inter.getChuanzhen(), 
				inter.getYoubian(), inter.getCompanyAddress(), inter.getIsDel());
	}
	@Override
	public Integer Updateinterinfo(International inter, Integer id) {
		Integer b=updateinter(inter, id);
		return b;
	}

}
