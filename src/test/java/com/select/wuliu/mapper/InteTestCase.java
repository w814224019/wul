package com.select.wuliu.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.International;
import com.select.wuliu.entity.member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InteTestCase {
	@Autowired
	internationalMapper intermapper;
	@Autowired
	memberMapper membermapper;
	@Test
	public void asseees(){
		//List<member> a=membermapper.findMemberByName("郑州聚时物流有限公司");
	//Page<member> a=membermapper.findAdelmembers("聚时");
		//Integer a=membermapper.ydelmember(12);
		//member a=membermapper.findADelmebeById(9);
		//List<member> a=membermapper.findMemberByName("郑州聚时物流有限公司0909");
		//member a=membermapper.findmebeByCompanyId(28130);
		//System.err.println(a);
	}
	@Test
	public void asseeess(){
		Integer a=membermapper.UpdateMember(1, "郑州聚时", 1, 
				"王", "冲7000", new Date(), new Date(), "2222", "", 0);
	}
	
	
	
	
	@Test
	public void assss(){
		member a=membermapper.findmebeById(1);
		System.err.println(a);
	}
	
	
	@Test
	public void asmm(){
		Page<member>l=membermapper.findmeberByPLname("郑州");
			System.err.println(l);	
	}
	
	
	@Test
	public void assslh(){
		member m=new member();
		m.setCompanyId(1);
		m.setCompanyName("郑州聚时");
		m.setStartTime(new Date());
		m.setEndTime(new Date());
		m.setInvoiceNumber("2222");
		m.setIsDel(0);
		m.setRemarks("冲6000");
		//m.setType(1);
		m.setUserName("王");
	
		Integer a=membermapper.addNewMember(m);
	}
	@Test
	public void assshh(){
		Integer b=intermapper.updateinter(25692, "郑州聚时有限公司", "000111", "郭经理", "cc", "000", 
				"0371", "南三环", 0);
	}
	
	@Test
	public void assssx(){
		Page<International> l=intermapper.findintersByPLname("上海");
		System.err.println(l);
	}
	
	
	
	@Test
	public void assssxxa(){
		International a=intermapper.findintercById(25692);
		System.err.println(a);
	}
	
	
	
	@Test
	public void assssa(){
		/**
		 * companyName,content,phone,zhuye,chuanzhen,youbian,
	companyAddress,isDel
		 * 
		 * 
		 */
		International a=new International();
		a.setCompanyName("郑州聚时有限公司");
		a.setChuanzhen("000");
		a.setCompanyAddress("南三环");
		a.setContent("郭经理");
		a.setIsDel(0);
		a.setPhone("000111");
		a.setYoubian("0371");
		a.setZhuye("aa");
		Integer a1=intermapper.addInternation(a);
				
	}
}
