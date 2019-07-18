package com.select.wuliu.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.member;
import com.select.wuliu.entity.money;
import com.select.wuliu.entity.userToCorbcompany;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanySelectTest {
	@Autowired
	ICompanyService companyService;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
	ImoneyService  moneyService;
	@Autowired
	ImemberSevice memberService;
	@Test
	public void ssaos(){
		Companier c=companyService.getAdelById(28142);
		c.setIsDel(0);
		Integer a=companyService.UpdateCompany(c, 28142);
	}
	
	
	@Test
	public void ssaosss(){
		//Page<userToCorbcompany> us=companyService.getusertoborCompanysPByid(1, 10, 56);
		//PageInfo<userToCorbcompany> pageInfo = new PageInfo<>(us);
		//member ms=memberService.getmemByName("郑州聚时物流有限公司0909");
		
		//System.out.println("--"+ ms);
	}
	
	
	
	@Test
	public void ssao(){
	Page <Pather>Pathers=bcompanyService.findByPage(2, 3,21830,1);
		PageInfo<Pather> pageInfo = new PageInfo<>(Pathers);
        System.err.println(pageInfo);
		
	}
		
	@Test
	public void ssaooa(){
		List<Pather> list=bcompanyService.getAllPather();
		System.err.println("11"+list);
		
	}
	
	
	
	@Test
	public void ssaaaa(){
		Integer id=139513;
		String time="2019-04-16 10:59";
		
		Task task=bcompanyService.getBydingshiId(id, time);
		/*Task ta=new Task();
		ta.setId(task.getId());
		ta.se*/
		Integer a=bcompanyService.updateTask(task, time);
		System.err.println(a);
	}
	@Test
	public void ssaa(){
		Integer id=2351;
		bCompany bc=bcompanyService.getById(2351);
		System.err.println("bc"+bc);
		Integer a=bcompanyService.UpbCompany(bc, id);
		
	}
	
	@Test
	public void sssss(){
		money a=moneyService.getwcmoney(1403);
		money m1=new money();
		m1.setCompanyId(1403);
		m1.setId(a.getId());
		m1.setMoney(888);
		//设置网点为1
		m1.setType(a.getType());
		
		//设置完后把物流币总数-total保存
		moneyService.updatewcmoney(m1, 1403);
		
		
		System.err.println("1aaaaa"+a);
	}
	
	@Test
	public void sss(){
		List<bCompany>l=bcompanyService.getAll();
		System.err.println("----l---"+l);
	}
	
	@Test
	public void s(){
		List<Companier> s=companyService.findAllCompany();
		System.err.println("---"+s);
	}
}
