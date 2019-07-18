package com.select.wuliu.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.evaluate;
import com.select.wuliu.entity.webs;
import com.select.wuliu.util.SendMessageUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class loooger {
	
 
    /**
     * 生成随机数
     * @param num 位数
     * @return
     */
    public static String createRandomNum(int num){
        String randomNumStr = "";
        for(int i = 0; i < num;i ++){
            int randomNum = (int)(Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }
 
    /**
     * 中国网建 SMS短信
     */
    @Test
    public void sendMessage(){
        int timeout = 5;
        String code = createRandomNum(6);
       String sendPhoneNum = "17335577115";
        Integer resultCode = SendMessageUtil.send("大海0741","d41d8cd98f00b204e980",sendPhoneNum,"尊敬的用户，您好，您正在注册**，您的验证码为："+code+"，请于"+timeout+"分钟内正确输入，如非本人操作，请忽略此短信。");
 
        System.out.println(SendMessageUtil.getMessage(resultCode));
    }
	@Autowired
	LoggerService loggerService;
	@Test
	public void sddd(){
		List<webs>list=loggerService.GetwebsByrid(1, 25, 56, "");
		System.err.println(list+"list");
		/*
		evaluate av=new evaluate();
		av.setId(1);
		av.setEdittime(new Date());
		Integer a=loggerService.UpdateEvas(av, 1);*/
	}
	@Test
	public void s(){
		BaseEntity ba=new BaseEntity();	
		Date createdTime=new Date();
		Date modifiedTime=new Date();
		String user="王阳阳";
		String oid="559622";
	
		ba.setModifiedTime(modifiedTime);
		ba.setModifiedUser(user);
		
		Integer a=loggerService.addLog(ba);
		System.err.println("----测试SErviceloggger---"+a);
	}
}
