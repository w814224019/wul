package com.select.wuliu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.webs;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.ResponseResult;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
 
 /**
  * 上传视频控制器
  * @author Admin
  *
  */
@Controller
//@RequestMapping("/file")
public class MyfileCOntroller extends BaseController {
	@Autowired
	RedisService redisService;
	@Autowired
	LoggerService loggerService;
    private String  url;
    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<String> uploadFile(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
    	Cookie cookie= CookieUtil.getCookieByName(request,"user");
    	User use=(User) redisService.get(cookie.getValue());
    	// System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            throw  new warnException("上传文件不可为空"); 
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
     // System.out.print("上传的文件名为: "+fileName+"\n");
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
       // System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");
        //加个时间戳，尽量避免文件名称重复
        String path = "D:/img/" +fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        //System.out.print("保存文件绝对路径"+path+"\n");
        //创建文件路径
        File dest = new File(path);
        //判断文件是否已经存在
        if (dest.exists()) {
        	 throw  new warnException("文件已经存在");
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
 
        try {
            //上传文件
            file.transferTo(dest); //保存文件
           // System.out.print("保存文件路径"+path+"\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
             url="http://192.168.0.154:8080/img/"+fileName;//本地运行项目
            //int jieguo= shiPinService.insertUrl(fileName,path,url);
             webs web=new webs();
             web.setCreateTime(new Date());
             web.setRid(use.getRid());
             web.setWebAddress(url);
             web.setWebName("本地视频");
             web.setType("视频");
             loggerService.SaveNewWeb(web);
           // System.out.print("插入结果"+jieguo+"\n");
              //System.out.print("保存的完整url===="+url+"\n");
        } catch (IOException e) {
        	throw  new warnException("上传失败");
        }
        ResponseResult rr=new ResponseResult();
        rr.setMessage(url);
        rr.setState(SUCCESS);
    	return rr;
    }
 
   
}
