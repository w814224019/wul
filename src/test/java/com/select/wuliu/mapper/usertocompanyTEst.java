package com.select.wuliu.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.select.wuliu.entity.userToCorbcompany;

@RunWith(SpringRunner.class)
@SpringBootTest
public class usertocompanyTEst {
	 @Autowired
	 userToCorbcompanyMapper utoc;
	 @Autowired
	 evaluateMapper evMapper;
	 @Test
		public void asssdddew(){
		 Date edittime=new Date();
		 Integer a=evMapper.updateEvluate(1, "已审核", "", edittime);
		 
	 }

		 
	 @Test
		public void asssew(){
		 Integer companyid=28146;
		 /*userToCorbcompany ust=utoc.findByBorCompanyId(companyid,56,1);
		 System.err.println(ust);*/
	 }
	 @Test
		public void assssss(){
		 Integer companyid=28130;
		 Integer rid=56;
		 Integer a=utoc.delUsertoCompany(companyid, rid, 0,0);
		 System.err.println("a"+a);
	 }
	 
	 
	 
	 
	 @Test
		public void asss(){
		 Integer companyid=28130;
		 Integer rid=56;
		 userToCorbcompany ust=new userToCorbcompany();
		 ust.setCompanyid(companyid);
		 ust.setIsDel(1);
		 ust.setUserid(rid);
		 Integer a=utoc.addNewUserToCompany(ust);
		 System.err.println("--a--"+a);
				 
		 
	 }
}
