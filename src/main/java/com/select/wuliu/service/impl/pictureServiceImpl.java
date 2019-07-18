package com.select.wuliu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.select.wuliu.entity.picturer;
import com.select.wuliu.mapper.pictureMapper;
import com.select.wuliu.service.IpictureService;
import com.select.wuliu.service.exeception.picturerNotFoundException;
@Service
public class pictureServiceImpl implements IpictureService {
	@Autowired
	pictureMapper picturemapper;
	private Integer addNewPicture(picturer pic){
		return picturemapper.addNewPicture(pic);
	}
	
	
	
	@Override
	public Integer addPicture(picturer p) {
		Integer a=addNewPicture(p);
		return a ;
	}

private List<picturer> findById(Integer companyId){
	return picturemapper.findById(companyId);
}

	@Override
	public List<picturer> getBycompanyId(Integer companyId) {
		List<picturer> l=findById(companyId);
		/*if(l.size()==0){
			throw new picturerNotFoundException("没有找到图片信息!");
		}*/
		return l;
	}

	private Integer Updatepic(picturer p,String picturePath){
		Integer isDel=p.getIsDel();
		Integer companyId=p.getCompanyId();
		Integer sor=p.getSor();
		String companyName=p.getCompanyName();
		p.setPicturePath(picturePath);
	return	picturemapper.Updatepic(companyId, companyName, picturePath, sor, p.getTypes(), isDel);
		

		}



@Override
public Integer Updatepicture(picturer p, String picturePath) {
	//通过路径找图片路径不对时异常
	/*picturer a=findBypath(picturePath);
	if(a==null){
		throw new picturerNotFoundException("没有找到图片信息!");
	}*/
	Integer ap=Updatepic(p, picturePath);
	return ap;
}

private picturer findBypath(String picturePath){
	return picturemapper.findBypath(picturePath);
}

@Override
public picturer getByPath(String picturePath) {
	picturer a=findBypath(picturePath);
	
	if(a==null){
		throw new picturerNotFoundException("没有找到图片信息!");
	}
	
	return a;
}

private List<picturer> findAll(){
	return picturemapper.findAll();
}

@Override
public List<picturer> getAll() {
	List<picturer>a=findAll();
	return a;
}

private  Page<picturer>  findByName(String companyName){
	return picturemapper.findByName(companyName);
}

@Override
public Page<picturer> getBycompanyName(Integer pageSize,Integer pageNum,String companyName) {
	PageHelper.startPage(pageNum, pageSize);
	Page<picturer> l=findByName(companyName);
	/*if(l.size()==0){
		throw new picturerNotFoundException("没有找到图片信息!");
	}*/
	return l;
}



@Override
public List<picturer> getAdelfindById(Integer companyId) {
	return picturemapper.AdelfindById(companyId);
}



@Override
public Page<picturer> getByLameIncompay(Integer pageSize,Integer pageNum,String companyName, Integer companyId) {
	PageHelper.startPage(pageNum, pageSize);
	return picturemapper.findByLame(companyName, companyId);
}
}