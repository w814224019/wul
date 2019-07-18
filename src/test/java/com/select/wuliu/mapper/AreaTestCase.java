package com.select.wuliu.mapper;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.carLine;
import com.select.wuliu.entity.webs;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaTestCase {
     
	
	@Test
		@Scheduled(cron ="*0/5 * * * * ? " )
		public void testTask2() {
		System.err.println("启动第一个定时任务testTask2");
		System.err.println("Scheduling Tasks Examples: The time is now " + new Date());
		
	}

	  @Autowired
	TaskMapper taskMapper;
	   @Autowired
	   areaMapper areaMapper;
	   @Autowired
	   websMapper websMapper;

		  @Autowired
		carLineMapper carlineMapper;
	   @Test
		public void asssss() throws ParseException{
		   //List<area> l=areaMapper.findAareas();
		   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");    

		   java.util.Date beginDate= format.parse("2007-12-24");    

		   java.util.Date endDate= format.parse("2007-12-25");    

		   long day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);    

		   System.out.println("相隔的天数="+day); 
	   }
	   
	   @Test
		public void asss(){
		   //List<area> l=areaMapper.findaeraLN("河南");
		  // Page<area>l=areaMapper.findChina();
			//List<Integer>l=areaMapper.findChinaId();
		   //Integer l=areaMapper.updateaAlias(412, "绿城");
		   //List<area> l=areaMapper.findaeraLNMap("北京");
		 /* webs web=new webs();
		  web.setCreateTime(new Date());
		  web.setRid(56);
		  web.setWebName("56114查询网");
		  web.setWebAddress("http://www.56114.net.cn/");
		  web.setType("物流");
		   Integer l=websMapper.addNewWeb(web);*/
		   //Integer l=websMapper.Delteweb(2);
		 /*  Page<webs>l=websMapper.findByrid(56, "");
		   System.err.println("l"+l);*/
		   
		   
		   
		 //  List<area> s=areaMapper.findAllareas();
		  // System.err.println(s);
		   //List<carLine> a=carlineMapper.findcarLIneBycarid(16);
		// Integer a=carlineMapper.UpdatecarLIneByid(8,1);
		  List<area> a=areaMapper.findAareasByClass(4);
		   System.err.println(a);
		   
		   
	   }
	   
	   
	   @Test
		public void assaa(){
		   List<Task> l=taskMapper.findByLikeN("2019");
		   System.err.println("lll"+l);
	   }
	   @Test
	 		public void assaasss(){
		  Task a=taskMapper.findMaxBylineId(63123);
		   System.err.println("-----"+a);
	   }
	   
	   
	   @Test
		public void assaaaaa(){
		   /*List<Task> list=taskMapper.findTasksBylineId(1);
		   System.err.println("list"+list);*/
		  /*Integer a=taskMapper.findMaxBylineId(63123);
		   System.err.println(a);
		   */
	   }
	   
	   
	   
	   
	   @Test
		public void assw(){
		   //Task a=taskMapper.findBydsId(139513,"2019-04-16 08:00");
		   //System.err.println("Task"+a);
	   }
	   
	   @Test
		public void asswww(){
		   Integer id=703;
		   Integer lineId=63123;
		   String time="2019-04-16 10:59";
		   Integer status=1;
		   	Integer a=taskMapper.UpdateTask( lineId, time, status);
		   	System.err.println("---a--"+a);
		   
	   }
	   
	   
	   
	   @Test
		public void asssk(){
		   Integer lineId=7;
		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   String time=dateFormat.format(Calendar.getInstance().getTime());
		   Task t=new Task();
		   t.setLineId(lineId);
		   t.setStatus(0);
		   t.setTime(time);
		   Integer a=taskMapper.addNewTask(t);
		   System.err.println("---a---"+a);
		   
		   
	   }
	   
	   
	   
	   
	   
	   
	   @Test
		public void asssksk() throws ParseException{
		   
		   
		   
		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        //方法1(推荐，功能强大灵活多变)
		   //13-18 5*60   300  30分钟刷一次  10
		   
		   //19-21 2*60   120   60分钟刷一次  2
		   
		   //8-12 4*60   240   30分钟刷一次     8
		   List<String> time=new ArrayList<String>();
		   Calendar calendar = Calendar.getInstance();
		   for(int a=0;a<100;a++){
			   int hour = calendar.get(Calendar.HOUR_OF_DAY);
			   System.err.println("获取时间小时"+hour);
			   if(hour>=8&&hour<12){
				   System.err.println("8-12");
				 //判断时间在8-12 
		        calendar.add(Calendar.MINUTE, 15);	//加30分钟
			   }else if(hour>=12&&hour<13){
				   int minu=calendar.get(calendar.MINUTE);
				   
				   calendar.add(Calendar.MINUTE, 60-minu+1);
				   
			   }
			   else if(hour>=13&&hour<18){
				 //判断时间在13-18
				   System.err.println("13-18");
				   //还可以刷次数
				  // int minu=calendar.get(calendar.MINUTE);
				 //int a1=(18-hour)*60-minu;
				// System.err.println("---a--"+a1);
				 //还有时间再往下推
				 //9物流币或者刷新次数
			        
			       calendar.add(Calendar.MINUTE, 9);
					
			   }else if(hour>=18&&hour<19){
				   int minu=calendar.get(calendar.MINUTE);
				   
				   calendar.add(Calendar.MINUTE, 60-minu+1);
				   
			   }
			   
			   
			   else if(hour>=19&&hour<21){
				 //判断时间在19-21
		        	calendar.add(Calendar.MINUTE, 17);
				   System.err.println("19-21");
			   }else{
				   System.err.println("---其他时间段--");
				   calendar.add(Calendar.HOUR_OF_DAY,11);
			   }
			   
			   String date=dateFormat.format(calendar.getTime());
			   System.err.println("........"+calendar.getTime());
			   time.add(date);
			  // System.err.println(dateFormat.format(Calendar.getInstance().getTime()));
		   }
		   System.err.println("----"+time.size()+time);
		  
		   
		   
		   
	        
	   }
	   
	   
	   @Test
		public void aksk(){
		   //area a=areaMapper.findByIntegerid(35);
		// List<area> a=areaMapper.findAareasByClass(2);
 		  // System.err.println(a);
	   }
	   
	   
	   
	   
	   @Test
		public void akk(){
		   Date date= new Date();
		   SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm");
		   
		   List<Task> a=taskMapper.findAllTask();
		   System.err.println("===a=="+a+sdf.format(date));
	   }
	   
	   
	   
	   @Test
		public void are(){
		   String ereaname="北京";
		  area a=areaMapper.findByname(ereaname);
		   System.err.println("----通过area查找---"+a);
		  Integer b= (int) System.currentTimeMillis();
		   System.err.println("---b---"+b);
	   }
	
}
