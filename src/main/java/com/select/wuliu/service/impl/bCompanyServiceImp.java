package com.select.wuliu.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.mapper.TaskMapper;
import com.select.wuliu.mapper.areaMapper;
import com.select.wuliu.mapper.bCompanyMapper;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.exeception.PatherNotFoundException;
import com.select.wuliu.service.exeception.TaskNotFoundException;
import com.select.wuliu.service.exeception.areaNoFoundExeception;
import com.select.wuliu.service.exeception.bCompanyNotFoundException;
@Service
public class bCompanyServiceImp implements IbCompanyService {
	@Autowired
	bCompanyMapper bcompanyMapper;
	@Autowired
	TaskMapper taskMapper;
	@Autowired
	areaMapper areamapper;
	private List<bCompany> findAll(){
    	return bcompanyMapper.findAll();
    }
	@Override
	public List<bCompany> getAll() {
		List<bCompany> l=findAll();
		return l;
	}
	private bCompany findById(Integer id){
		return bcompanyMapper.findById(id);
	}
	@Override
	public bCompany getById(Integer id) {
		bCompany b=findById(id);
		/*if(b==null){
			throw new bCompanyNotFoundException("没有找到"+id+"网点！");
		}*/
		return b;
	}
	private List<bCompany> findBycompanyId(Integer companyId){
		return bcompanyMapper.findBycompanyId(companyId);
	}
	@Override
	public List<bCompany> getBycompanyId(Integer companyId) {
		List<bCompany> l=findBycompanyId(companyId);
		/*if(l.size()==0){
			throw new bCompanyNotFoundException("没有找到"+companyId+"网点！");
		}*/
		return l;
	}
	private Integer UpdatebCompany(bCompany bc,Integer id){
		String branchName=bc.getBranchName();
		String address=bc.getAddress();
		String phone=bc.getPhone();
		String telephone=bc.getTelephone();
		Integer companyId=bc.getCompanyId();
		String contact=bc.getContact();
		long qz=System.currentTimeMillis();
		Integer isDel=bc.getIsDel();
		String longitude=bc.getLongitude();
		String dimensions=bc.getDimensions();
		String branchprovince=bc.getBranchprovince();
		String branchcity=bc.getBranchcity();
		String brancharea=bc.getBrancharea();
		Integer branchrelation=bc.getBranchrelation();
		String intro=bc.getIntro();
		String culture=bc.getCulture();
		Integer vipClass=bc.getVipClass();
		return bcompanyMapper.UpdatebCompany(id, branchName, phone, address, telephone, companyId, contact, longitude, qz, isDel, dimensions, branchprovince, branchcity, brancharea, branchrelation, intro, culture, vipClass);
	}
	@Override
	public Integer UpbCompany(bCompany bc, Integer id) {
		Integer a=UpdatebCompany(bc, id);
		return a;
	}
	private Integer addPather(Pather pa){
		return bcompanyMapper.addPather(pa);
	}
	@Override
	public Integer addNewPather(Pather p) {
		Integer a=addPather(p);
		
		return a;
	}
	private List<Task> findAllTask(){
		return taskMapper.findAllTask();
	}
	@Override
	public List<Task> getAlltask() {
		List<Task> l=findAllTask();
		return l;
	}
	private List<Pather> findAllPather(){
		return bcompanyMapper.findAllPather();
	}
	@Override
	public List<Pather> getAllPather() {
		List<Pather> p=findAllPather();
		return p;
	}
	private area getByIntegerid(Integer id){
		return areamapper.findByIntegerid(id);
	}
	@Override
	public area getByareaid(Integer id) {
		area a=getByIntegerid(id);
		/*if (a==null){
			throw new areaNoFoundExeception("没有找到"+id+"的区名！");
		}*/
		return a;
	}
	private List<Pather> findBywdId(Integer wlId){
		return bcompanyMapper.findBywdId(wlId);
	}
	@Override
	public List<Pather> getAllPatherbywdid(Integer wlid) {
		List<Pather> l=findBywdId(wlid);
		/*if(l.size()==0){
			throw new PatherNotFoundException("没有找到wlid为"+wlid+"的线路!");
			
		}*/
		return l;
	}
	private Integer addNewTask(Task task){
		return taskMapper.addNewTask(task);
	}
	@Override
	public Integer addTask(Task task) {
		Integer a=addNewTask(task);
		return a;
	}
	private Pather findBylineID(Integer lineId){
		return bcompanyMapper.findBylineID(lineId);
	}
	@Override
	public Pather getPather(Integer lineId) {
		Pather p=findBylineID(lineId);
		/*if(p==null){
			throw new PatherNotFoundException("没有找到lineId为"+lineId+"的线路!");
		}*/
		return  p;
	}
	private  Integer UpdateTask(Task task, String time){
		
		Integer lineId=task.getLineId();
		Integer status=1;
		return taskMapper.UpdateTask( lineId, time, status);
	}
	@Override
	public Integer updateTask(Task task, String time) {
		Integer a=UpdateTask(task, time);
		return a;
	}
	
	private  Page<Task> findTasksBylineId(Integer lineId){
		return taskMapper.findTasksBylineId(lineId);
	}
	@Override
	public List<Task> gettasksBylineId1(Integer lineId) {
		return taskMapper.findTasksBylineId(lineId);
	}
	@Override
	public Page<Task> gettasksBylineId(Integer lineId,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		Page<Task> list=findTasksBylineId(lineId);
		/*if(list.size()==0){
			throw new TaskNotFoundException("没有找到序号为："+lineId+"的任务！");
		}*/
		return list;
	}
	private Task findBydsId(String time,Integer id){
		return taskMapper.findBydsId(id, time);
	}
	@Override
	public Task getBydingshiId(Integer id, String time) {
		Task task=findBydsId(time, id);
		/*if(task==null){
			throw new TaskNotFoundException("没有找到序号为："+id+"时间点为："+time+"的任务！");
		}*/
		return task;
	}
	private Task findMaxBylineId(Integer lineId){
		return taskMapper.findMaxBylineId(lineId);
	}
	@Override
	public Task getMaxBylineId(Integer lineId) {
		Task task=findMaxBylineId(lineId);
		return task;
	}
	private Page<Task> findByLikeN(String name){
		return taskMapper.findByLikeN(name);
	}
	
	@Override
	public Page<Task> getTasksByLikeN(Integer pageNum, Integer pageSize,String name) {
		PageHelper.startPage(pageNum,pageSize);
		Page<Task> l=findByLikeN(name);
		/*if(l.size()==0){
			throw new TaskNotFoundException("没有找到"+name+"的任务！");
		}*/
		return l;
	}
	private List<bCompany> findbcompanyByLike(String name){
		return bcompanyMapper.findbcompanyByLike(name);
	}
	@Override
	public List<bCompany> getbCompanyByLN(String name) {
		List<bCompany> lis=findbcompanyByLike(name);
		if(lis.size()==0){
			throw new bCompanyNotFoundException("没有找到"+name+"网点！");
		}
		return lis;
	}
	private List<area> findAllareas(Integer parentId){
		return areamapper.findAllareas( parentId);
	}
	@Override
	public List<area> getallareas(Integer parentId) {
		List<area>a=findAllareas( parentId);
		return a;
	}
	private Integer SetdelPather(Pather p,Integer id){
		Integer wlId=p.getWlId();
		long qz=p.getQz();
		Integer delFlag=p.getDelFlag();
		Integer type=p.getType();
		Integer sta=p.getSta();
		Integer end=p.getEnd();
		String tui=p.getTui();
		String direct=p.getDirect();
		return bcompanyMapper.SetdelPather(id, wlId, qz, delFlag, type, sta, end, tui, direct);
	}
	@Override
	public Integer setPatherdel(Pather p,Integer id) {
		Integer a=SetdelPather(p,id);
		return a;
	}
	private List<area> findaeraLN(String name){
		return areamapper.findaeraLN(name);
	}
	@Override
	public List<area> getareasLn(String name) {
		List<area> li=findaeraLN(name);
		return li;
	}
	private Integer addbCompany(bCompany bc){
		return bcompanyMapper.addbCompany(bc);
	}
	@Override
	public Integer addNewbCompany(bCompany bc) {
		Integer a=addbCompany(bc);
		return a;
	}
	private bCompany findbcompanyBybrname(String branchName){
		return bcompanyMapper.findbcompanyBybrname(branchName);
	}
	@Override
	public bCompany getbCompanyBybname(String branchName) {
		bCompany b=findbcompanyBybrname(branchName);
		return b;
	}
	private List<area> findAareas(){
		return areamapper.findAareas();
	}
	@Override
	public List<area> getAareas() {
		List<area>a=findAareas();
		return a;
	}
	
	
	@Override
	public Page<Pather> findByPage(Integer pageNum, Integer pageSize,Integer id,Integer type) {
		PageHelper.startPage(pageNum,pageSize);
		return  bcompanyMapper.findByPage(id,type);
	}
	@Override
	public Page<bCompany> getbCompanyPByLN(String name, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		return bcompanyMapper.findbcompanyPByLike(name);
	}
	
	private Integer YdeltePatherById(Integer lineID){
		return bcompanyMapper.YdeltePatherById(lineID);
	}
	
	@Override
	public Integer YEDelPatherById(Integer id) {
		Integer a=YdeltePatherById(id);
		return a;
	}
	@Override
	public Page<Pather> getAllDElPathers(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return bcompanyMapper.findAdelPathers();
	}
	private Pather findAdelBylineID(Integer lineId){
		return bcompanyMapper.findAdelBylineID(lineId);
	}
	@Override
	public Pather getADelPather(Integer id) {
		Pather a=findAdelBylineID(id);
		return a;
	}
	private bCompany findAbCompById(Integer id){
		return bcompanyMapper.findAbCompById(id);
	}
	@Override
	public bCompany getAdelbCompany(Integer id) {
		bCompany d=findAbCompById(id);
		return d;
	}
	
	
	
	@Override
	public Integer setPatherResetdel(Pather p,Integer id) {
		Integer wlId=p.getWlId();
		long qz=p.getQz();
		Integer delFlag=p.getDelFlag();
		Integer type=p.getType();
		Integer sta=p.getSta();
		Integer end=p.getEnd();
		String tui=p.getTui();
		String direct=p.getDirect();
		return bcompanyMapper.SetdelPather(id, wlId, qz, delFlag, type, sta, end, tui, direct);
	}
	private Integer YdelbCompany(Integer id){
		return bcompanyMapper.YdelbCompany(id);
	}
	@Override
	public Integer YDelbCompany(Integer id) {
		Integer a=YdelbCompany(id);
		return a;
	}
	@Override
	public Page<bCompany> findADelbCompanys(String name, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		return bcompanyMapper.findDelbcompany(name);
	}
	@Override
	public Page<area> getChian(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return areamapper.findChina();
	}
	private List<Integer> findChinaId(){
		return areamapper.findChinaId();
	}
	@Override
	public List<Integer> getChianId() {
		List<Integer> a=findChinaId();
		return  a;
	}
	@Override
	public Integer setPatherDelBycompanyId(Pather p,Integer companyId) {
		Integer wlId=p.getWlId();
		Integer id=p.getId();
		long qz=p.getQz();
		Integer delFlag=p.getDelFlag();
		Integer type=p.getType();
		Integer sta=p.getSta();
		Integer end=p.getEnd();
		String tui=p.getTui();
		String direct=p.getDirect();
		return bcompanyMapper.SetdelPather(id, wlId, qz, delFlag, type, sta, end, tui, direct);
	}
	@Override
	public List<Integer> getends(Integer wlid) {
		return bcompanyMapper.ends(wlid);
	}
	@Override
	public List<String> getareanameThend(List<Integer> list) {
		return bcompanyMapper.findThend(list);
	}
	@Override
	public List<Pather> getPsBywdIdandsta(Integer wlid, Integer sta) {
		return bcompanyMapper.findBywdIdandsta(wlid, sta);
	}
	@Override
	public Integer YdeltePather(Integer sta, Integer wid) {
		return bcompanyMapper.YdelbPather(sta, wid);
	}
	@Override
	public Integer UpdateaAlias(Integer areaid, String alias) {
		return areamapper.updateaAlias(areaid, alias);
	}
	@Override
	public List<area> FindaeraLNMap(String name) {
		return areamapper.findaeraLNMap(name);
	}
	@Override
	public Integer AddPathers(List<Pather> pa) {
		return bcompanyMapper.addPathers(pa);
	}
	@Override
	public Integer SetdelPathers(Integer delFlag, List<Pather> ps) {
		return bcompanyMapper.setdelPathers(delFlag, ps);
	}
	@Override
	public List<Pather> GetallcolsePather(Integer delFlag, Integer wlid) {
		return bcompanyMapper.findallcolsePather(delFlag, wlid);
	}
	@Override
	public Page<Task> PGetBywdId(Integer pageNum, Integer pageSize,Integer wlId) {
		PageHelper.startPage(pageNum,pageSize);
		return taskMapper.PfindBywdId(wlId);
	}
	
	
	
	
	

}
