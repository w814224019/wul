package com.select.wuliu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.userToCorbcompany;
import com.select.wuliu.mapper.CompanyMapper;
import com.select.wuliu.mapper.areaMapper;
import com.select.wuliu.mapper.userToCorbcompanyMapper;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.exeception.CompanyNotFoundException;
import com.select.wuliu.service.exeception.DuplicateKeyException;
import com.select.wuliu.service.exeception.UpdateException;
import com.select.wuliu.service.exeception.areaNoFoundExeception;
@Service
public class CompanyServiceImp implements ICompanyService{

	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	areaMapper areamapper;
	@Autowired
	userToCorbcompanyMapper utocmapper;

	private Integer addNewPath(Pather pather){
		return companyMapper.addNewPath(pather);
	}
	private Integer addNewCompany(Companier company){
		return companyMapper.addNewCompany(company);
	}
	@Override
	public Integer SaveCompany(Companier company) throws DuplicateKeyException{
		List<Companier> com=findByName(company.getCompanyName());
		if(com.size()==0){
			Integer c=addNewCompany(company);
			return c;
		}else{
			throw new DuplicateKeyException("注册的用户名"+company.getCompanyName()+"已经存在！");
		}
	}
	@Override
	public Integer SavePather(Pather pather) {
		Integer p=addNewPath(pather);
		return p;
	}
	private List<Companier> findAll(){
		return companyMapper.findAll();
	}
	@Override
	public List<Companier> findAllCompany() {
		List<Companier> li=findAll();
		return li;
	}
	private Companier findById(Integer companyId){
		return companyMapper.findById(companyId);
	}
	//通过id找公司
	@Override
	public Companier getById(Integer companyId) throws CompanyNotFoundException {
		Companier c=findById(companyId);
		/*if (c==null){
			throw new CompanyNotFoundException("没有找到物流公司！");
		}*/
		return c;
	}
	private List<Pather> findBycf(Integer sta,Integer end){
		return companyMapper.findByft(sta, end);
	}
	//专线找公司
	@Override
	public List<Pather> getByft(Integer sta, Integer end) throws CompanyNotFoundException{
		List<Pather> ps=findBycf(sta, end);
		/*if (ps.size()==0){
			throw new CompanyNotFoundException("没有找到物流公司！");
		}*/
		return ps;
	}
	private area findByname(String areaname){
		return areamapper.findByname(areaname);
	}
	//地区找地区号
	@Override
	public area findByEname(String areaname) throws areaNoFoundExeception {
		area a=findByname(areaname);
		/*if (a==null){
			throw new areaNoFoundExeception("没有找到"+areaname+"的区号！");
		}*/
		return a;
	}
	private List<Companier> findByName(String companyName){
		return companyMapper.findByName(companyName);
	}
	//通过公司名称找公司
	@Override
	public List<Companier> getByName(String companyName)throws CompanyNotFoundException {
		List<Companier> lc=findByName(companyName);
		if (lc.size()==0){
			throw new CompanyNotFoundException("没有找到公司名称为："+companyName+"物流公司！");

		}

		return lc;
	}
	private List<Companier> findByLName(String name){
		return companyMapper.findByLName(name);
	}
	//模糊所有找公司
	@Override
	public List<Companier> getByLName(String name)throws CompanyNotFoundException {
		List<Companier> ll=findByLName(name);
		if (ll.size()==0){
			throw new CompanyNotFoundException("没有找到物流公司！");
		}
		return ll;
	}
	private List<Companier> findByLaddress(String address){
		return companyMapper.findByLaddress(address);
	}
	//通过地区找公司
	@Override
	public List<Companier> getByLadderss(String address) throws CompanyNotFoundException{
		List<Companier> la=findByLaddress(address);
		if (la.size()==0){
			throw new CompanyNotFoundException("没有找到物流公司！");
		}
		return la;
	}
	private Integer updatecompany(Companier company,Integer companyId){
		Integer isDel=company.getIsDel();
		long qz=company.getQz();
		String companyName=company.getCompanyName();
		String address=company.getAddress();
		String contact=company.getContact();
		String telephone=company.getTelephone();
		String phone=company.getPhone();
		String picturePath=company.getPicturePath();
		String directRemark=company.getDirectRemark();
		String transitRemark=company.getTransitRemark();
		String detailPicture=company.getDetailPicture();
		String depart=company.getDepart();
		String alias=company.getAlias();
		String intro=company.getIntro();
		String culture=company.getCulture();
		Integer vipClass=company.getVipClass();
		Integer att=company.getAtt();
		String province=company.getProvince();
		String city=company.getCity();
		Integer type=company.getType();
		String  longitude=company.getLongitude();
		String  dimensions=company.getDimensions();
		String lightCost=company.getLightCost();
		String heavyCost=company.getHeavyCost();
		String aging=company.getAging();
		String style=company.getStyle();
		return companyMapper.updateCompany(companyName, address, phone, picturePath, telephone, contact, directRemark, transitRemark, 
				depart, alias, detailPicture, qz, intro, culture, vipClass, isDel, 
				att, companyId, province, city, type, longitude, 
				dimensions, lightCost, heavyCost, aging, style);
	}
	@Override
	public Integer UpdateCompany(Companier company,Integer companyId)throws UpdateException,CompanyNotFoundException {
		/*Companier co=findById(companyId);
	if(co==null){
		throw new CompanyNotFoundException("你尝试访问的数据已经被删除！");
	}
		if(co.getIsDel()==1){
		throw new CompanyNotFoundException("你尝试访问的数据已经被删除！");
	}*/
		Integer up=updatecompany(company, companyId);
		return up;
	}
	@Override
	public Page<Companier> getCompanyPByName(String companyName, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);

		return companyMapper.findCompanyPByLName(companyName);
	}
	private Integer addNewUserToCompany(userToCorbcompany utoc){
		return utocmapper.addNewUserToCompany(utoc);
	}
	@Override
	public Integer SaveusettoCompany(userToCorbcompany utoc) {
		Integer a=addNewUserToCompany(utoc);
		return a;
	}
	private Integer delUsertoCompany(userToCorbcompany utoc,Integer companyid){
		return utocmapper.delUsertoCompany(companyid, utoc.getUserid(), 1,utoc.getMark());
	}
	@Override
	public Integer updatusertoCorbpany(userToCorbcompany utoc, Integer companyid) {
		Integer b=delUsertoCompany(utoc, companyid);
		return b;
	}
	private userToCorbcompany findByBorCompanyId(Integer companyid,Integer mark){
		return utocmapper.findByBorCompanyId(companyid,mark);
	}
	@Override
	public userToCorbcompany getusertobOrcompanyByid(Integer companyid,Integer mark) {
		userToCorbcompany utoc=findByBorCompanyId(companyid,mark);
		return utoc;
	}
	@Override
	public Page<userToCorbcompany> getusertoborCompanysPByid(Integer rid,Integer pageSize,Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		return utocmapper.findutoCPByrid(rid);
	}
	private Integer deleteCompanyById(Integer CompanyId){
		return companyMapper.deleteCompanyById(CompanyId);
	}
	@Override
	public Integer YDelCompany(Integer companyId) {
		Integer a=deleteCompanyById(companyId);
		return a;
	}
	@Override
	public Page<Companier> GetdeleteCompanys(Integer pageNum, Integer pageSize, String name) {
		PageHelper.startPage(pageNum,pageSize);
		return companyMapper.findDelCompanyPByLName(name);
	}
	private Companier findAdelById(Integer companyId){
		return companyMapper.findAdelById(companyId);
	}
	@Override
	public Companier getAdelById(Integer companyId) {
		Companier b=findAdelById(companyId);
		return b;
	}
	@Override
	public Page<Pather> GetBystaEnd(Integer sta, Integer end, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return companyMapper.findBystaEnd(sta, end);
	}
	@Override
	public List<Companier> GetBystaAndend(List<Integer> wlId) {
		return companyMapper.findBystaAndend(wlId);
	}
	@Override
	public List<Integer> GetBystaAd(Integer sta, Integer end, Integer type) {
		return companyMapper.findBystaAd(sta, end, type);
	}


}
