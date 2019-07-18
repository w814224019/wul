package com.select.wuliu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.money;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.ImoneyService;
import com.select.wuliu.service.LoggerService;
/**
 * 通用组件定时自动刷新类
 * 自动刷新为每分钟后台刷新一次,扫描数据库 
 * @author Admin
 *
 */

@Component
@Scope("prototype")
public class DynamicTask {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
	IbCompanyService bcompanyService;
    @Autowired
	LoggerService loggerService;
    @Autowired
	ImoneyService  moneyService;
    private String cron;
    private ScheduledFuture future;
   public void startCron() {
    	//自动刷新为每分钟后台刷新一次
        cron = "0 0/1 * * * ? ";
        String name = Thread.currentThread().getName();
        System.err.println(name+"开始执行");
        future = threadPoolTaskScheduler.schedule(new myTask(), new CronTrigger(cron));
      
    }
    public void stop() {
        if (future != null) {
            future.cancel(true);
            System.err.println("关闭自动刷新");
        }
    }
 
    private class myTask implements Runnable {
        @Override
        public void run() {
          //扫描数据库 
        	List<Task>Tasklist=bcompanyService.getAlltask();
        	//取出时间戳和id 和现在时间比较等就根据id update
        	for (Task task : Tasklist) {
				String time=task.getTime();
				Date date= new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm");
				String date1=sdf.format(date)+"";
				//System.err.println("---date1"+date1);
				
				if(time.equals(date1)){
					Integer id=task.getLineId();
					//从线路表里通过序号找到网点或者公司id
					Pather p=bcompanyService.getPather(id);
					//获得公司或者网点id查询实体
					bCompany bc=bcompanyService.getById(p.getWlId());
		    		String branchName=bc.getBranchName();
		    		String phone=bc.getPhone();
		    		bcompanyService.UpbCompany(bc, p.getWlId());
		    		System.err.println("---更新--"+branchName);
		    		//把执行过任务的状态改为已刷新  "网点自动刷新" branchName p.getWlId()
		    		
		    		BaseEntity ba=new BaseEntity();
		    		ba.setCompany(branchName);
		    		ba.setModifiedUser("自动刷新");
		    		ba.setMobile(phone);
		    		Date modifiedTime=new Date();
		    		ba.setModifiedTime(modifiedTime);
		    		ba.setType("网点自动刷新");
		    		ba.setId(p.getWlId());
		    		loggerService.addLog(ba);
		        	
		    		//根据刷新id找网点物流币刷新一次减一
		    		//修改物流币
		    		//读取物流币如果为0则跳过
		    		money m=moneyService.getwcmoney(p.getWlId());
		    		if(m.getMoney()==0){
		    			throw new warnException("物流币余额不足请及时充值!");
		    		}
		    		Integer balance=m.getMoney()-1;
		    		money m1=new money();
		    		m1.setCompanyId(p.getWlId());
		    		m1.setId(m.getId());
		    		m1.setMoney(balance);
		    		m1.setType(1);
		    		moneyService.updatewcmoney(m1, p.getWlId());
		    		
		    		
				}else{
					//如果没有值，打印当前无任务
					//System.out.println(sdf.format(new Date())+"---当前无任务--");
				}
						
			}
        	
        	
        }
    }
 

}