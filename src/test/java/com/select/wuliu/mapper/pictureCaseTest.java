package com.select.wuliu.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.picturer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class pictureCaseTest {
	@Autowired
	pictureMapper picturemapper;
	
	@Test
  	public void rdddaaaa(){
		String companyName="郑州聚时物流有限公司";
		//List<picturer>a=picturemapper.findByName(companyName);
		Page<picturer> a=picturemapper.findByLame("公司图片", 28130);
		System.err.println("---a--"+a);
	}
	
	
	
	@Test
  	public void rdddaa(){
		List<picturer>a=picturemapper.AdelfindById(28130);
		System.err.println("---a--"+a);
				}
	
	@Test
  	public void rddd(){
		String picturePath="http://localhost:8080/img/15541940843161054609.jpg";
		String companyName="郑州聚时物流有限公司";
		Integer sor=0; 
		Integer companyId=28130;
		Integer isDel=0;
		/*Integer a=picturemapper.Updatepic(companyId, companyName, picturePath, sor, isDel);
		System.err.println("---a---"+a);*/
		
	}
	
	
	@Test
  	public void rddss(){
		String picturePath="http://localhost:8080/img/15541940843161054609.jpg";
		picturer a=picturemapper.findBypath(picturePath);
		System.err.println("----测试---"+a);
	}
	
	@Test
  	public void rdd(){
		Integer id=28130;
		List<picturer>a=picturemapper.findById(id);
		System.err.println("----测试---"+a);
	}
	
	
	@Test
  	public void rd(){
		Integer companyId=28130;
		String picturePath="";
		Integer sor=0;
		picturer pic=new picturer();
		pic.setPicturePath(picturePath);
		pic.setSor(sor);
		pic.setCompanyId(companyId);
		Integer da=picturemapper.addNewPicture(pic);
		System.err.println("---测试增加图片--"+da);
	}
}
