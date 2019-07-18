package com.select.wuliu.service;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.select.wuliu.entity.picturer;
import com.select.wuliu.util.HttpUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class pictureTestCase {
	
	
	
	@Test
	public void ss() throws IOException{
		
		String url = "http://www.chinawutong.com/";
        String charset = "gb2312";
        Document rootDoc = HttpUtil.get(url, charset);
       System.err.println(rootDoc);
		
	}
	
	@Autowired
	IpictureService pictureservice;
	@Test
	public void s(){
		String picturePath="http://localhost:8080/img/15541940843161054609.jpg";
		String companyName="郑州聚时物流有限公司";
		Integer sor=0; 
		Integer companyId=28130;
		Integer isDel=0;
		picturer p=new picturer();
		p.setCompanyId(companyId);
		p.setIsDel(isDel);
		p.setSor(sor);
		p.setCompanyName(companyName);
		p.setPicturePath(picturePath);
		Integer a=pictureservice.Updatepicture(p, picturePath);
		System.err.println("----a---"+a);
		
		
		
	}
	
}
