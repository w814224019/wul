package com.select.wuliu.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.select.wuliu.entity.money;

@RunWith(SpringRunner.class)
@SpringBootTest
public class moneyTestCAse {
	  @Autowired
	moneyMapper moneymapper;
	
	  @Test
		public void as(){
		  money a=moneymapper.findBycwid(1432);
		  System.err.println("aaaaaaa"+a);
	  }
	  
	  @Test
		public void assss(){
		  
		  Integer companyId=1403;
		  money a=moneymapper.findBycwid(1403);
		  Integer id=a.getId();
			 Integer type=1;
			 Integer money=990;
			 Integer c=moneymapper.updatemoney(id, companyId, type, money);
			 System.err.println("ccc"+c);
	  }
	  
	  
	  
	  
	  
	  
	 @Test
		public void asssk(){
		 Integer companyId=1403;
		 Integer type=1;
		 Integer money=1000;
		 money m=new money();
		 m.setCompanyId(companyId);
		 m.setMoney(money);
		 m.setType(type);
		 Integer a=moneymapper.addmoney(m);
		 System.err.println("sss"+a);
		 
	 }
}
