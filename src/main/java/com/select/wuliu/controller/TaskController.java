package com.select.wuliu.controller;



import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.money;
import com.select.wuliu.entity.sunTask;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.ImoneyService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;
/**
 * 开启自动刷新，制定刷新计划，删除单个计划2,删除单个计划4
 * 批量删除计划
 * @author Admin
 *
 */
@Controller
public class TaskController extends BaseController{
	@Autowired
	private DynamicTask task;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
    private RedisService redisService;
	@Autowired
	ImoneyService  moneyService;
	@Autowired
	LoggerService loggerService;
	@PostMapping("/startCron")
	@ResponseBody
	public ResponseResult<String>startCron(Integer id){
		//开启自动刷新
		task.startCron();
		System.err.println("-----开启自动刷新---");
		return new ResponseResult<String>(SUCCESS);
	}
	
	@RequestMapping(value = "setFrequencyCron", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> setFrequencyCron(HttpServletRequest request,Integer a1,Integer a2,Integer a3,Integer id,Integer companyId,Integer total,Integer shengyu) {
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		//制定刷新计划，存入数据库中，时间点，id，
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//8-12 4*60   240   30分钟刷一次     8
	   //Integer a1=30;  
		//System.err.println("aaaaaaaaaaaa:"+a1);
		//System.err.println("aaaaaaaaaaaa:"+a2);
		//System.err.println("aaaaaaaaaaaa:"+a3);
	   //13-18 5*60   300  30分钟刷一次  10
	   //Integer a2=30;
	   //19-21 2*60   120   60分钟刷一次  2
	   //Integer a3=60;
		if(shengyu==0){
			throw new warnException("物流币余额不足请及时充值!");
		}
		if(a1==null&&a2==null&&a3==null){
			throw new warnException("请设置刷新时间段!");
		}
		if(total==null||total.equals(0)){
			throw new warnException("刷新次数不能为空或者0!");
		}
	
		/*sunTask st= (sunTask) redisService.get(cookie.getValue()+"st");
		if(st.getMoney()==null){
			throw new warnException("请返回到线路后再设置刷新计划!");
		}*/
		//获取物流币余额 如果小于toatl直接返回抛出物流币余额不足请及时充值异常
		
	   //List<planlist> time=new ArrayList<planlist>();
	   Calendar calendar = Calendar.getInstance();
	   
	   for(int a=1;a<total+1;a++){
		   int hour = calendar.get(Calendar.HOUR_OF_DAY);
		   int minute=calendar.get(Calendar.MINUTE);
		  // System.err.println("获取时间小时"+hour+":"+minute);
		   if(hour>=8&&hour<=11){
			   System.err.println("8-12");
			   int hour2 = calendar.get(Calendar.HOUR_OF_DAY);
			   int pa2=(hour2-8)*60;
			 //  System.err.println("8-pa-12:"+pa2);
			   if(a1==null&&a2!=null&&a3!=null){
					a1=240+60;
					calendar.add(Calendar.MINUTE, a1-pa2);
				}
			   else  if(a1!=null&&a2==null&&a3!=null){
					a2=300+60;
					calendar.add(Calendar.MINUTE, a1);
				}
			   else  if(a1!=null&&a2!=null&&a3==null){
					a3=120+660;
					calendar.add(Calendar.MINUTE, a1);
				}

			   else  if(a1!=null&&a2==null&&a3==null){
				   a3=120+660;
				   a2=300+60+120+660;
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				   calendar.add(Calendar.MINUTE, a1-pa2);
			   }
			   else  if(a1==null&&a3==null&&a2!=null){
				   a1=240+60;
				   a3=120+660+240+60;
				   calendar.add(Calendar.MINUTE, a1-pa2);
			   }
			   else  if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1<=240&&a2>300&&a3>120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else   if(a1>240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else if(a1>240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1<=240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			 //判断时间在8-12 
	        	//加30分钟
		   }else if(hour==12){
			   int minu=calendar.get(calendar.MINUTE);
			   if(a1==null&&a2!=null&&a3!=null){
				   a1=240+60;
				 //  System.err.println(".......a1.....");
				   calendar.add(Calendar.MINUTE, 60-minu);
				}
			   
			   else  if(a2!=null&&a1!=null&&a3==null){
				   a3=120+660;
				  // System.err.println("..........a3..");
				   calendar.add(Calendar.MINUTE, 60-minu);
			   }
			   else  if(a2==null&&a1!=null&&a3!=null){
				   a2=300+60;
				  // System.err.println("...a2.........");
				   calendar.add(Calendar.MINUTE, 60+300+60-minu);
			   }
			   
			   else   if(a1==null&&a2==null&&a3!=null){
				   a1=60+300+60;
				   a2=300+60;
				  // System.err.println(".......a1...a2..");
				   calendar.add(Calendar.MINUTE, 60+300+60-minu);
			   }
			   
			   else  if(a1==null&&a3==null&&a2!=null){
				   a1=240+60;
				   a3=120+660+240+60;
				  // System.err.println("...a1.....a3....");
				   calendar.add(Calendar.MINUTE, 60-minu);
			   }
			   else  if(a2==null&&a1!=null&&a3==null){
				   a2=300+60+120+660;
				   a3=120+660;
				  // System.err.println("...a2.......a3..");
				   calendar.add(Calendar.MINUTE, 60+300+60+120+660-minu);
			   }
			   
			   
			   else  if(a1>240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60+300+60);
			   }
			   else   if(a1>240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   
			   else  if(a1<=240&&a2>300&&a3>120){
				  // System.err.println("........这里.......");
				   calendar.add(Calendar.MINUTE, 60+300+60+120+660);
			   }
			   else   if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else  if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60+300+60);
			   }
			   else  if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else   if(a1<=240&&a2<=300&&a3<=120){
				   //System.err.println("...............");
				   
				   calendar.add(Calendar.MINUTE, 60-minu);
				}
			   
		   }
		   else if(hour>=13&&hour<=17){
			 //判断时间在13-18
			  // System.err.println("13-18");
			   if(a2==null&&a1!=null&&a3!=null){
				   int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa=(hour1-13)*60;
				  // System.err.println("13??????-pa-18:"+pa);
					a2=300+60;
					 calendar.add(Calendar.MINUTE, a2-pa);
				}
			   
			   else  if(a2!=null&&a1!=null&&a3==null){
					a3=120+660;
					 calendar.add(Calendar.MINUTE, a2);
				}
			   else  if(a2!=null&&a1==null&&a3!=null){
					a1=240+60;
					 calendar.add(Calendar.MINUTE, a2);
				}
			   
			   else if(a2==null&&a1==null&&a3!=null){
				   int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa=(hour1-13)*60;
				  // System.err.println("13-pa-###18:"+pa);
				   a2=300+60;
				   a1=240+60+300+60;
				   calendar.add(Calendar.MINUTE, a2-pa);
			   }
			   else   if(a2==null&&a1!=null&&a3==null){
				   int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa=(hour1-13)*60;
				   //System.err.println("13-!!!pa-18:"+pa);
				   a2=300+60+120+660;
				   a3=120+660;
				   calendar.add(Calendar.MINUTE, a2-pa);
			   }
			   else   if(a2!=null&&a1==null&&a3==null){
				   a1=240+60;
				   a3=120+660+240+60;
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   
			   
			   else  if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else   if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else  if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else  if(a1>240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else  if(a1<=240&&a2>300&&a3>120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else  if(a1>240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else   if(a1<=240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   //还可以刷次数
			  // int minu=calendar.get(calendar.MINUTE);
			 //int a1=(18-hour)*60-minu;
			// System.err.println("---a--"+a1);
		      
		   }else if(hour>=18&&hour<19){
			   int minu=calendar.get(calendar.MINUTE);
			   if(a1==null&&a2!=null&&a3!=null){
				   a1=240+60;
				   calendar.add(Calendar.MINUTE, 60-minu);
				}
			   else if(a2!=null&&a1!=null&&a3==null){
					a3=120+660;
					calendar.add(Calendar.MINUTE, 60+120+660-minu);
				}
			   else if(a2==null&&a1!=null&&a3!=null){
					a2=300+60;
					calendar.add(Calendar.MINUTE, 60-minu);
				}
			   else if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				   calendar.add(Calendar.MINUTE, 60-minu);
			   }
			   else if(a1==null&&a3==null&&a2!=null){
				   a1=240+60;
				   a3=120+660+240+60;
				   calendar.add(Calendar.MINUTE, 60+120+660+240+60-minu);
			   }
			   
			   else if(a2==null&&a1!=null&&a3==null){
				   a2=300+60+120+660;
				   a3=120+660;
				   calendar.add(Calendar.MINUTE, 60+120+660-minu);
			   }
			   else if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, 60+120+660);
			   }
			   
			   else if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else  if(a3<=120&&a1>240&&a2>300){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else if(a3>120&&a1>240&&a2<=300){
				   calendar.add(Calendar.MINUTE, 60+120+660+240+60);
			   }
			   else  if(a3>120&&a1<=240&&a2>300){
				   calendar.add(Calendar.MINUTE, 60+120+660);
			   }
			   else  if(a1<=240&&a2<=300&&a3<=120){
				 //  System.err.println("...............");
				   
				   calendar.add(Calendar.MINUTE, 60-minu);
				}
		   }
		   else if(hour>=19&&hour<=20){
			 //判断时间在19-21
			  // System.err.println("19-21");
			   if(a3==null&&a1!=null&&a2!=null){
				   int hour3 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa3=(hour3-19)*60;
				  // System.err.println("19-21-pa-:"+pa3);
					a3=120+660;
					calendar.add(Calendar.MINUTE, a3-pa3);
				}
			   
			   else if(a1!=null&&a2==null&&a3!=null){
				   
				   a2=300+60;
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else  if(a1==null&&a2!=null&&a3!=null){
					
					a1=240+60;
					calendar.add(Calendar.MINUTE, a3);
				}
			   else if(a3==null&&a1==null&&a2!=null){
				   int hour3 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa3=(hour3-19)*60;
				  // System.err.println("19-21-pa-:"+pa3);
					a3=120+660+240+60;
					a1=240+60;
					calendar.add(Calendar.MINUTE, a3-pa3);
				}
			   else if(a3==null&&a2==null&&a1!=null){
				   int hour3 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa3=(hour3-19)*60;
				  // System.err.println("19-21-pa-:"+pa3);
					a3=120+660;
					a2=300+61+120+660;
					calendar.add(Calendar.MINUTE, a3-pa3);
				}
			   else if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else  if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else  if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else  if(a1>240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   
			   else if(a1<=240&&a2>300&&a3>120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else if(a1>240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else if(a1<=240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
	        	
		   }else if(hour>=21||hour<8){
			  // System.err.println("---其他时间段--");
			   
			   if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				 //  System.err.println("111....111");
				   calendar.add(Calendar.MINUTE, 660+240+60+300+60);
			   }
			   else  if(a1==null&&a3==null&&a2!=null){
				   a1=240+60;
				   a3=120+660+240+60;
				  // System.err.println("118888111");
				   calendar.add(Calendar.MINUTE, 660+240+60);
			   }
			   else if(a2==null&&a1!=null&&a3==null){
				   a2=300+60+120+660;
				   a3=120+660;
				  // System.err.println("11177111");
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else if(a1==null&&a2!=null&&a3!=null){
				   a1=240+60;
				  // System.err.println("1116661111");
				   calendar.add(Calendar.MINUTE, 660+240+60);
				}
			   else if(a2!=null&&a1!=null&&a3==null){
					a3=120+660;
					// System.err.println("11166611");
					calendar.add(Calendar.MINUTE, 660);
				}
			   else if(a2==null&&a1!=null&&a3!=null){
				   a2=300+60;
				   //System.err.println("11555111");
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else if(a1>240&&a2>300&&a3<=120){
				  // System.err.println("11111111");
				   
				   calendar.add(Calendar.MINUTE, 660+240+60+300+60);
			   }
			   else if(a1>240&&a2<=300&&a3>120){
				  // System.err.println("1333311");
				   calendar.add(Calendar.MINUTE, 660+240+60);
			   }
			   else  if(a1<=240&&a2>300&&a3>120){
				   //System.err.println("1144111");
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else if(a1>240&&a2<=300&&a3<=120){
				  // System.err.println("*********1111111");
				   
				   calendar.add(Calendar.MINUTE, 660+240+60);
			   }
			   else  if(a1<=240&&a2<=300&&a3>120){
				  // System.err.println("11111111");
				   
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else  if(a1<=240&&a2>300&&a3<=120){
				  // System.err.println("11111111");
				   
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else if(a1<=240&&a2<=300&&a3<=120){
				  // System.err.println("12111");
				   calendar.add(Calendar.HOUR_OF_DAY,11);
				   
				}
			   
		   }
		   String date;
		    date=dateFormat.format(calendar.getTime());
		   //根据计算的时间点和id从数据库中找到是否有相同的数据
		   Task t=bcompanyService.getBydingshiId(id, date);
		   
		   //如果有
		   if(t!=null){
			   Task t1=bcompanyService.getMaxBylineId(t.getLineId());
			   String time1=t1.getTime();
			   calendar.add(Calendar.DAY_OF_MONTH,1);
			    date=dateFormat.format(calendar.getTime());
		   }
		   
		   //如果没有就直接添加
		   Task task=new Task();
		   task.setLineId(id);
		   task.setTime(date);
		   task.setStatus(0);
		   
		   //刷新一次，物流币根据刷新要求把时间点和id存入，
		   bcompanyService.addTask(task);
		   //time.add(p);
		  // System.err.println(dateFormat.format(Calendar.getInstance().getTime()));
	   }
	   //System.err.println("----"+time.size()+time);
	   sunTask st1=new sunTask();
		st1.setId(companyId);
		redisService.set(cookie.getValue()+"st", st1);
	   //剩余物流币
		money m=moneyService.getwcmoney(companyId);
	   Integer la=shengyu-total;
	   if(la<0){
		   throw new warnException("物流币不足！");
	   }
	   System.err.println("--------m1保存物流币--"+la);
		System.err.println("--------m1保存物流币--"+m);
		money m1=new money();
		m1.setCompanyId(companyId);
		m1.setId(m.getId());
		m1.setMoney(la);
		//设置网点为1
		m1.setType(m.getType());
		System.err.println("--------m1保存物流币--"+m1);
		//设置完后把物流币总数-total保存
		moneyService.updatewcmoney(m1, companyId);
		
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(bcompanyService.getById(companyId).getBranchName(), u, companyId, "设置刷新计划");
		loggerService.addLog(ba);  

		return new ResponseResult<String>(SUCCESS);
	}

	@PostMapping("/stopCron")
	@ResponseBody
	public ResponseResult<String> stopCron(Integer id) throws Exception{
		task.stop();
		return new ResponseResult<String>(SUCCESS);
	}
   //删除单个计划2
	@PostMapping("/deltask")
	@ResponseBody
	public ResponseResult<String> deltask(HttpServletRequest request,String time) {
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		sunTask st= (sunTask) redisService.get(cookie.getValue()+"st");
		Integer id=st.getXid();
		Task task=bcompanyService.getBydingshiId(id, time);
			bcompanyService.updateTask(task, time);
			//物流币加一
			//根据任务序号找到物流网点id
			Integer lineId=task.getLineId();
			Pather paa=bcompanyService.getPather(lineId);
			Integer wlId=paa.getWlId();
			//修改物流币
			money m=moneyService.getwcmoney(wlId);
			Integer balance=m.getMoney()+1;
			money m1=new money();
			m1.setCompanyId(wlId);
			m1.setId(m.getId());
			m1.setMoney(balance);
			m1.setType(1);
			moneyService.updatewcmoney(m1, wlId);
			//序号
			Integer lineid=task.getLineId();
			//物流id号
			Integer companyId=bcompanyService.getPather(lineid).getWlId();
			
			User u=(User) redisService.get(cookie.getValue());
			//存日志
			BaseEntity ba=LogerUtils.logger(bcompanyService.getById(companyId).getBranchName(), u, companyId, "删除刷新计划");
			loggerService.addLog(ba); 
		
		
		return new ResponseResult<String>(SUCCESS);
	}

	//删除单个计划4
		@PostMapping("/deltask4")
		@ResponseBody
		public ResponseResult<String> deltask4(HttpServletRequest request,String time,Integer lineId) {
			//sunTask st= (sunTask) redisService.get("st");
			System.out.println("time"+time);
			System.out.println("lineId"+lineId);
			Integer id=lineId;
			Task task=bcompanyService.getBydingshiId(id, time);
			//System.out.println("task:"+task);
			
				bcompanyService.updateTask(task, time);
				//物流币加一
				//根据任务序号找到物流网点id
				//Integer lineId=task.getLineId();
				Pather paa=bcompanyService.getPather(lineId);
				Integer wlId=paa.getWlId();
				//修改物流币
				money m=moneyService.getwcmoney(wlId);
				Integer balance=m.getMoney()+1;
				money m1=new money();
				m1.setCompanyId(wlId);
				m1.setId(m.getId());
				m1.setMoney(balance);
				m1.setType(1);
				moneyService.updatewcmoney(m1, wlId);
				//序号
				Integer lineid=task.getLineId();
				//物流id号
				Integer companyId=bcompanyService.getPather(lineid).getWlId();
				//取出cookie
				Cookie cookie= CookieUtil.getCookieByName(request,"user");
				User u=(User) redisService.get(cookie.getValue());
				//存日志
				BaseEntity ba=LogerUtils.logger(bcompanyService.getById(companyId).getBranchName(), u, companyId, "删除刷新计划");
				loggerService.addLog(ba); 
			
			
			return new ResponseResult<String>(SUCCESS);
		}

//批量删除计划
		@PostMapping("/removetasks")
		@ResponseBody
		public ResponseResult<String> removetasks(HttpServletRequest request,String ids) {
			    String[] split = ids.split(",");//因为传过来的ids以（1，2，3...）这种形式 所以要分割
			    //System.out.println("进来了"+split);
			    for (String string : split) {
					String[] spl=string.split("!");
					String time=spl[0];
					if(!time.equals("")){
						//System.err.println("spl的长度"+spl.length);
					Integer lineId=Integer.parseInt(spl[1]);
					Task task=bcompanyService.getBydingshiId(lineId, time);
					bcompanyService.updateTask(task, time);
					//物流币加一
					Pather paa=bcompanyService.getPather(lineId);
					Integer wlId=paa.getWlId();
					//修改物流币
					money m=moneyService.getwcmoney(wlId);
					Integer balance=m.getMoney()+1;
					money m1=new money();
					m1.setCompanyId(wlId);
					m1.setId(m.getId());
					m1.setMoney(balance);
					m1.setType(1);
					moneyService.updatewcmoney(m1, wlId);
					//取出cookie
					Cookie cookie= CookieUtil.getCookieByName(request,"user");
					User u=(User) redisService.get(cookie.getValue());
					//存日志
					BaseEntity ba=LogerUtils.logger(bcompanyService.getById(wlId).getBranchName(), u, paa.getWlId(), "批量删除刷新计划");
					loggerService.addLog(ba); 
					
					
					//System.err.println("time:"+time+"lineId:"+lineId);
					}
					
				}
			//	task.stop();
			return new ResponseResult<String>(SUCCESS);
		}	
		
		
		
		
}
