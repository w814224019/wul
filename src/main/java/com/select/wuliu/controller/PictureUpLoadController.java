package com.select.wuliu.controller;


import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.select.wuliu.controller.exeception.FileEmptyException;
import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.picturer;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IUserService;
import com.select.wuliu.service.IpictureService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;
import com.select.wuliu.util.WaterMarkGenerate;
/**
 * 上传文件夹的名称,公司实名认证,获取网点下的图片列表
 * 删除图片方法,处理单个删除图片,处理批量删除图片,置顶操作
 * 通过id找公司图片,通过名称找公司图片
 * @author Admin
 *
 */
@Controller
public class PictureUpLoadController extends BaseController{
	@Value("${cbs.imagesPath}")
	private String mImagesPath;
	@Autowired
	IpictureService pictureService;
	@Autowired
	IUserService userService;
	
	//上传文件夹的名称
	//private static final String UPLOAD_NAME="img";
	@Autowired
	private RedisService redisService;
	private List<String>picturepaths=new ArrayList<String>();
	private List<String>picturepaths1=new ArrayList<String>();
	@Autowired
	ICompanyService companyService;
	@Autowired
	LoggerService loggerService;
	@GetMapping("/picture-add3")
	public String pictureadd3(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);
		List<picturer> p=pictureService.getAll();
		PageInfo<picturer> pageInfo = new PageInfo<picturer>(p);
		model.addAttribute("pageInfo",pageInfo);
		return "picture-add3";
	}
	@GetMapping("/picture-add")
	public String picturelist(Integer companyId,Model model){
		Companier c=companyService.getById(companyId);
		//System.err.println(c+"--c--公司信息");
		model.addAttribute("company",c);
		return "picture-add";
	}
	//展示修改头像页面
	@GetMapping("/picture-add6")
	public String picturelist6(HttpServletRequest request,Model model){
		//取出cookie
        Cookie cookie= CookieUtil.getCookieByName(request,"user");
        //System.out.println("u"+cookie.getValue());
		User u=(User) redisService.get(cookie.getValue());
		model.addAttribute("user",u);
		return "picture-add6";
	}
	//公司实名认证
	@GetMapping("/picture-list")
	public String pictureli(HttpServletRequest request,Model model){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Companier c=(Companier) redisService.get(cookie.getValue()+"cos");
		model.addAttribute("company",c);
		return "picture-list";
	}
	//获取网点下的图片列表
	@GetMapping("/picture-add2")
	public String pictureadd2(Integer companyId,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);
		//System.err.println(c+"c公司");
		List<picturer> p=pictureService.getBycompanyId(companyId);
		if(p.size()==0){
			Companier c=companyService.getById(companyId);
			//显示增加网点图片
			model.addAttribute("company",c);
			return "picture-add";
		}
		PageInfo<picturer> pageInfo = new PageInfo<picturer>(p);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("companyId",companyId);
		return "picture-add2";
	}
	@PostMapping("/upload")
	@ResponseBody
	public ResponseResult<String> upload(HttpServletRequest request,@RequestParam MultipartFile file) throws Exception{

		if(file.isEmpty()){
			throw new FileEmptyException("没有上传的文件，或者选中的文件为空！");
		}
		//检查文件大小
		//System.err.println(mImagesPath+"图片路径");
		//检查文件类型
		//String parentPath=request.getSession().getServletContext().getRealPath(mImagesPath);
		String parentPath="D:/img/";

		File parent=new File(parentPath);
		if(!parent.exists()){
			parent.mkdirs();
		}
		//确定文件名
		String originalFileName=file.getOriginalFilename();
		//System.err.println("---上传图片路径--"+originalFileName);
		int beginIndex=originalFileName.lastIndexOf(".");
		String suffix=originalFileName.substring(beginIndex);
		String fileName=System.currentTimeMillis()+""+(new Random().nextInt(9000000)+1000000)+suffix;
		//String path = "D:/img/" +fileName;
		File dest=new File(parent,fileName);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		try {
			file.transferTo(dest);
			String picturepath1="http://192.168.0.154:8080/img/"+fileName;//本地运行项目
			picturepaths1.add(picturepath1+","+cookie.getValue());
			redisService.set(cookie.getValue()+"pp1",picturepaths1);
			//添加水印
			WaterMarkGenerate.generateWithTextMark(dest, parentPath+"logo_"+fileName, "www.56114.com");
			//System.err.println("上传完成");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		String picturepath="http://192.168.0.154:8080/img/logo_"+fileName;//本地运行项目
		// //url="http://自己的域名/项目名/images/"+fileName;//正式项目
		//String picturepath="/"+mImagesPath+"/"+fileName;
		//含水印
		picturepaths.add(picturepath+"!"+cookie.getValue());
		redisService.set(cookie.getValue()+"pp",picturepaths);
		User u=(User) redisService.get(cookie.getValue());
		BaseEntity ba=LogerUtils.logger(u.getName(), u,  u.getRid(), "上传图片");
		loggerService.addLog(ba);
		return new ResponseResult<String>(SUCCESS);

	}
	
	//上传头像uploadpictureAVA
	@PostMapping("/uploadpictureAVA")
	@ResponseBody
	public ResponseResult<String> uploadpictureAVA(HttpServletRequest request,Integer rid,String src){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		if(src!=null){
			User u1=userService.getById(rid);
			u1.setHeadimgurl(src);
			userService.UpdateUserInfo(u1, rid);
			//放入缓存
			redisService.set(cookie.getValue(), u1);
		}else{
		List<String> picturepathss= (List<String>) redisService.get(cookie.getValue()+"pp1");
		if(picturepathss==null){
			throw new warnException("请先上传再保存！");
		}
		//把有水印的作废
		picturepaths.clear();
		//System.out.println(picturepathss+"图片集合2");
		for (String string : picturepathss) {
			String[] split = string.split(",");
			if(split[1].equals(cookie.getValue())){
				String path=split[0];
				User u1=userService.getById(rid);
				u1.setHeadimgurl(path);
				userService.UpdateUserInfo(u1, rid);
				//放入缓存
				redisService.set(cookie.getValue(), u1);
			}
		}
		}
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(u.getName(), u,  rid, "上传头像");
		loggerService.addLog(ba);
		picturepaths1.clear();
		redisService.set(cookie.getValue()+"pp1",null);
		return new ResponseResult<String>(SUCCESS);
	}

	//上传不带水印图片
	@PostMapping("/uploadPicture1")
	@ResponseBody
	public ResponseResult<String> uploadPicture1(HttpServletRequest request,Integer companyId,String type){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		List<String> picturepathss= (List<String>) redisService.get(cookie.getValue()+"pp1");
		if(picturepathss==null){
			throw new warnException("请先上传再保存！");
		}
		//把有水印的作废
		picturepaths.clear();
		//System.out.println(picturepathss+"图片集合2");
		for (String string : picturepathss) {
			String[] split = string.split(",");
			if(split[1].equals(cookie.getValue())){
				picturer p=new picturer();
				p.setCompanyId(companyId);
				Companier comp=companyService.getById(companyId);
				//System.err.println("----保存公司图片--"+comp);
				p.setCompanyName(comp.getCompanyName());
				//System.err.println("----保存公司图片--"+split[0]);
				p.setPicturePath(split[0]);
				p.setSor(0);
				p.setIsDel(0);
				p.setTypes(type);
				pictureService.addPicture(p);
				User u=(User) redisService.get(cookie.getValue());
				//存日志
				BaseEntity ba=LogerUtils.logger(comp.getCompanyName(), u,  comp.getCompanyId(), "保存公司不带水印图片");
				loggerService.addLog(ba);
			}
		}
		picturepaths1.clear();
		redisService.set(cookie.getValue()+"pp1",null);
		return new ResponseResult<String>(SUCCESS);
	}




	//上传带水印图片
	@PostMapping("/uploadPicture")
	@ResponseBody
	public ResponseResult<String> uploadPicture(HttpServletRequest request,Integer companyId,String type){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		List<String> picturepathss= (List<String>) redisService.get(cookie.getValue()+"pp");
		if(picturepathss==null){
			throw new warnException("请先上传再保存！");
		}
		//把有没有水印 的作废
		picturepaths1.clear();
		//System.out.println(picturepathss+"图片集合2");
		for (String string : picturepathss) {
			String[] split = string.split("!");
			if(split[1].equals(cookie.getValue())){
				picturer p=new picturer();
				p.setCompanyId(companyId);
				Companier comp=companyService.getById(companyId);
				//System.err.println("----保存公司图片--"+comp);
				p.setCompanyName(comp.getCompanyName());
				p.setPicturePath(split[0]);
				p.setSor(0);
				p.setIsDel(0);
				p.setTypes(type);
				pictureService.addPicture(p);
				User u=(User) redisService.get(cookie.getValue());
				//存日志
				BaseEntity ba=LogerUtils.logger(comp.getCompanyName(), u,  comp.getCompanyId(), "保存公司带水印图片");
				loggerService.addLog(ba);
			}
		}
		picturepaths.clear();
		redisService.set(cookie.getValue()+"pp",null);
		return new ResponseResult<String>(SUCCESS);
	}
	//删除图片方法
	public void delpicture(HttpServletRequest request,String picturePath){
		picturer p=pictureService.getByPath(picturePath);
		picturer np=new picturer();
		np.setCompanyId(p.getCompanyId());
		np.setCompanyName(p.getCompanyName());
		np.setIsDel(1);
		np.setSor(0);
		np.setPicturePath(picturePath);
		pictureService.Updatepicture(np, picturePath);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(p.getCompanyName(), u,  p.getCompanyId(), "删除公司图片");
		loggerService.addLog(ba);
	}
	//处理单个删除图片
	@PostMapping("/delpicture")
	@ResponseBody
	public ResponseResult<String> delPicture(HttpServletRequest request,String picturePath){
		delpicture(request, picturePath);
		return new ResponseResult<String>(SUCCESS);
	}
	//处理批量删除图片
	@PostMapping("/delpicturelist")
	@ResponseBody
	public ResponseResult<String> delpicturelist(HttpServletRequest request,String ids){
		if(ids.equals("")){
			throw new warnException("请选择到图片!");
		}
		String[] split = ids.split(",");
		for (String string : split) {
			if(!string.equals("")){

				delpicture(request,string);
			}
		}

		return new ResponseResult<String>(SUCCESS);
	}

	//置顶操作
	@PostMapping("/setfirst")
	@ResponseBody
	public ResponseResult<String> setfirst(HttpServletRequest request,String picturePath){
		picturer p=pictureService.getByPath(picturePath);
		Integer id=p.getCompanyId();
		//根据公司id找到公司
		Companier com=companyService.getById(id);
		//设置公司封面图片
		com.setDetailPicture(picturePath);
		companyService.UpdateCompany(com, com.getCompanyId());
		picturer np=new picturer();
		np.setCompanyId(p.getCompanyId());
		np.setCompanyName(p.getCompanyName());
		np.setIsDel(0);
		List<picturer>l=pictureService.getBycompanyId(id);
		for (picturer picturer : l) {
			if(picturer.getSor()==1){
				picturer.setCompanyId(p.getCompanyId());
				picturer.setCompanyName(p.getCompanyName());
				picturer.setIsDel(0);
				picturer.setSor(0);
				pictureService.Updatepicture(picturer, picturer.getPicturePath());
			}
		}
		np.setSor(1);
		np.setPicturePath(picturePath);
		pictureService.Updatepicture(np, picturePath);
		//取出cookie "修改公司图片设为封面" p
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(p.getCompanyName(), u,  p.getCompanyId(), "修改公司图片设为封面");
		loggerService.addLog(ba);

		return new ResponseResult<String>(SUCCESS);
	}


	//通过id找公司图片
	@PostMapping("/findPictureById")
	@ResponseBody
	public ResponseResult<String> SelctBladd(HttpServletRequest request,Integer companyId){
		//找到结果放到缓存中
		List<picturer> p=pictureService.getBycompanyId(companyId);
		if(p.size()==0){
			throw new warnException("没有找到公司id为："+companyId+"的图片列表");
		}
		Companier com=companyService.getById(companyId);
		if(com==null){
			throw new warnException("没有找到公司id为："+companyId+"的图片列表,请检查该公司是否已经标记删除！");
		}
		return new ResponseResult<String>(SUCCESS);
	}
	//通过名称找公司图片
	@PostMapping("/findPictureByName")
	@ResponseBody
	public ResponseResult<List<picturer>> findPictureByName(HttpServletRequest request,String companyName,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		//找到结果放到缓存中
		Page<picturer> p=pictureService.getBycompanyName(pageSize, pageNum, companyName);
		if(p.size()==0){
			throw new warnException("没有找到名称为："+companyName+"的图片！");
		}
		List<picturer> p1=new ArrayList<picturer> ();
		for (picturer picturer : p) {
			picturer.setPages(p.getPages());
			picturer.setTotals(p.getTotal());
			p1.add(picturer);
		}
		return new ResponseResult<List<picturer>>(SUCCESS,p1);
	}
   //在一个公司通过类型找公司图片
	@PostMapping("/findPictureByName1")
	@ResponseBody
	public ResponseResult<List<picturer>> findPictureByName1(HttpServletRequest request,Integer companyId,String companyName,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
		//找到结果放到缓存中
		Page<picturer> p=pictureService.getByLameIncompay(25, pageNum, companyName, companyId);
		if(p.size()==0){
			throw new warnException("没有找到名称为："+companyName+"的图片！");
		}
		List<picturer> p1=new ArrayList<picturer> ();
		for (picturer picturer : p) {
			picturer.setPages(p.getPages());
			picturer.setTotals(p.getTotal());
			p1.add(picturer);
		}
		return new ResponseResult<List<picturer>>(SUCCESS,p1);
	}


}