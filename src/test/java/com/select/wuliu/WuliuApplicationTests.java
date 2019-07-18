package com.select.wuliu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.select.wuliu.util.HttpClientUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WuliuApplicationTests {
    
	@Test
	public void contextLoads() {
		//url中的  appid 和  secret 开发者会给你  这相当于你小程序的ID和密码       js_code 也会给你  js_code是用微信开发者工具调用方法获得
        String  appid="wx3d7c42cf198a5eec";//你小程序Id
        String secret="1bf4f290a2b18abf4ad626f21960ebd4";//填入你小程序的secret
        String code="j8Hdx8uzz1IAeTUKA9y6G9Dxf86MQXysvaLOlBiWRsg";//用微信开发者工具获取到的code
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
         JSONObject jsonObj=HttpClientUtils.httpGet(url);
         System.out.println(jsonObj);
         //打印结果  

		
	}
	
	
	
	
	
	
	
	

}
