package com.select.wuliu.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.select.wuliu.entity.area;
import com.select.wuliu.entity.arealike;

@RunWith(SpringRunner.class)
@SpringBootTest
public class arealikemapperTestCase {
	@Autowired
	arealikeMapper arealikemappper;
	@Autowired
	areaMapper areaMapper;
	@Test
	public void ass(){
		List<area>aers=areaMapper.findAllareas(4744);
		System.err.println("ares44444444"+aers);
		for (area area : aers) {
			arealike a=new arealike();
			String sheng=area.getAreaid()+"_"+area.getAreaname();
			a.setSheng(sheng);
			List<area> shi=areaMapper.findAllareas(area.getAreaid());
			String shi2="";
			for (area area2 : shi) {
				 shi2+=area2.getAreaid()+"_"+area2.getAreaname()+" ";
			    
			}
			a.setShi(shi2);
			
		
			System.err.println(a);
			arealikemappper.addNewarealike(a);
			
		}
		
		
	}
	@Test
	public void assd(){
		
		
		List<arealike> a=arealikemappper.findall();
		System.err.println(a);
	}
	@Test
	//递归
	public int fun(int   x){
		if(x>1)   
			return   x+fun(x-1);   
		else   
			return   x;  
	}
	
}
