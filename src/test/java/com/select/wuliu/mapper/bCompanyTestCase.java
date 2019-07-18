package com.select.wuliu.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.bCompany;

@RunWith(SpringRunner.class)
@SpringBootTest
public class bCompanyTestCase {
	@Autowired
	bCompanyMapper bcompanymapper;
	@Autowired
	TaskMapper taskmapper;
	@Test
  	public void rsdddda(){
		//Integer a=bcompanymapper.YdeltePatherById(616590);
		//Pather a=bcompanymapper.findAdelBylineID(616591);
		//Page<Pather> a=bcompanymapper.findAdelPathers();
		//bCompany b=bcompanymapper.findAbCompById(30798);
		//Page<bCompany> b=bcompanymapper.findDelbcompany("聚时");
		//Integer b=bcompanymapper.YdelbCompany(30801);
		/*List<Integer>b=bcompanymapper.ends(28146);
		System.err.println(b);
		List<String> b1=bcompanymapper.findThend(b);*/
		//List<Pather> b1=bcompanymapper.findBywdIdandsta(28130,165);
		//Integer b1=bcompanymapper.YdelbPather(412, 28130);
		/*Integer sta=1;
		Integer end=2;
		Integer wlId=1374;
		long qz=System.currentTimeMillis();
		Integer delFlag=0;
		Pather p=new Pather();
		p.setDelFlag(delFlag);
		p.setEnd(end);
		p.setSta(sta);
		p.setQz(qz);
		p.setWlId(wlId);
		p.setType(2);
		p.setTui("普通");
		p.setDirect("直达");*/
		//Pather p=bcompanymapper.findBylineID(10617601);
		/*List<Pather>pa=new ArrayList<Pather>();
		pa.add(p);
		*/
		//Integer b1=bcompanymapper.setdelPathers(2,pa);
		//Integer b1=bcompanymapper.addPathers(pa);
		Page <Task>b1=taskmapper.PfindBywdId(30816);
		System.err.println(b1);
		
	}
	
	
	@Test
  	public void rsddda(){
		//bCompany b=bcompanymapper.findbcompanyBybrname("郑州聚时");
		//List<Pather>b=bcompanymapper.findallcolsePather(2, 28130);
	//获取所有公司名称
		String a="鸿城物流限公司";
		//String b1=a.substring(0,3);
		//截取简称
		//String[] b=a.split("有");
		//插入数据库中
		System.err.println("b"+a.length());
	}
	
	
	@Test
  	public void rss(){
		String branchName="郑州聚时";
		String address="南三环";
		String phone="11111";
		String telephone="22222";
		Integer companyId=28130;
		String content="郭经理";
		long qz=0;
		Integer isDel=0;
		String branchprovince="河南省";
		String branchcity="郑州";
		String brancharea="二七区";
		Integer branchrelation=1;
		
		
		bCompany bc=new bCompany();
		bc.setAddress(address);
		bc.setBrancharea(brancharea);
		bc.setBranchcity(branchcity);
		bc.setBranchName(branchName);
		bc.setBranchprovince(branchprovince);
		bc.setBranchrelation(branchrelation);
		bc.setCompanyId(companyId);
		bc.setContent(content);
		bc.setIsDel(isDel);
		bc.setPhone(telephone);
		bc.setTelephone(telephone);
		bc.setQz(qz);
		
		Integer a=bcompanymapper.addbCompany(bc);
		System.err.println("---a---"+a);
	}
	
	@Test
  	public void rsssss(){
		Pather p=bcompanymapper.findBylineID(616557);
		Integer wlId=p.getWlId();
		System.err.println(wlId);
	//	Integer a=bcompanymapper.SetdelPather(p.getId(), wlId, p.getQz(), 1, 1, p.getSta(), p.getEnd());
		//System.err.println(a);
	}
	
	
	
	
	@Test
  	public void rssssssss(){
		List<bCompany> l=bcompanymapper.findbcompanyByLike("南省郑州市南三环黄河科技学院2楼202");
		System.err.println("sdfsd"+l);
	}
	
	@Test
  	public void rsss(){
		Pather p=bcompanymapper.findBylineID(10);
		System.err.println("----p---"+p);
	}
	
	
	@Test
  	public void rssss(){
		List<Pather> l=bcompanymapper.findBywdId(1400);
		System.err.println("---l---"+l);
	}
	
	
	@Test
  	public void rs(){
		List<Pather> l=bcompanymapper.findAllPather();
		System.err.println("---l---"+l);
	}
	
	
	@Test
  	public void rsds(){
		Integer sta=1;
		Integer end=2;
		Integer wlId=1374;
		long qz=System.currentTimeMillis();
		Integer delFlag=0;
		Pather p=new Pather();
		p.setDelFlag(delFlag);
		p.setEnd(end);
		p.setSta(sta);
		p.setQz(qz);
		p.setWlId(wlId);
		
		
		Integer a=bcompanymapper.addPather(p);
		System.err.println("--a--"+a);
		
	}
	
	
	
	@Test
  	public void rds(){
		Integer id=1374;
		String branchName="湛江网点";
		String address="赤坎区康宁路68号中心仓7-8号";
		String phone="18022609391";
		String telephone="0759-3710588";
		Integer companyId=2051;
		String content="";
		long qz=System.currentTimeMillis();
		Integer isdel=1;
		String longitude="";
		String dimensions="";
		//Integer a=bcompanymapper.UpdatebCompany(id, branchName, phone, address, telephone, companyId, content, longitude, qz, isdel, dimensions,
				
				//null,null,null,1);
		//System.err.println("---a--"+a);
	}
	
	
	@Test
  	public void rdsss(){
		Integer id=2051;
		List<bCompany>l=bcompanymapper.findBycompanyId(id);
		System.err.println("--d--"+l);
		
		
	}
	
	
	
	@Test
  	public void rds2l(){
		Integer id=1536;
		bCompany b=bcompanymapper.findById(id);
		System.err.println(b);
		
	}
	
	
	@Test
  	public void rds2(){
		List<bCompany>li=bcompanymapper.findAll();
		System.err.println("--li--"+li);
	}
}
