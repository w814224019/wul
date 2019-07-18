package com.select.wuliu.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.carUser;
import com.select.wuliu.entity.fahRecoreder;
import com.select.wuliu.entity.member;
import com.select.wuliu.entity.money;
import com.select.wuliu.entity.sunPather;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.IfahRecorederService;
import com.select.wuliu.service.ImemberSevice;
import com.select.wuliu.service.ImoneyService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.service.exeception.CompanyNotFoundException;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.ResponseResult;
import com.select.wuliu.util.listTodis;
/**
 * 处理修改会员信息跳转,处理修改会员信息跳转,处理网点跳转
 * 绑定会员时公司预览,处理绑定会员时看下公司出发地,处理绑定会员时看下网点出发地
 * 处理通过公司id查找 
 * 处理通过公司名称查找 ,
 * @author Admin
 *
 */
@Controller
public class ToCategeroyRedisController  extends BaseController {
	@Autowired
	ICompanyService companyService;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
	private RedisService redisService;
	@Autowired
	ImoneyService  moneyService;
	@Autowired
	ImemberSevice memberService;
	@Autowired
	IfahRecorederService fahRecorederService;
	//处理修改会员信息跳转
	@GetMapping("/ToCategeroy5")
	@ResponseBody
	public ResponseResult<String> ToCategeroy5(HttpServletRequest request,String  ids){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		redisService.set(cookie.getValue()+"memberids",ids);
		return new ResponseResult<String>(SUCCESS);
	}

	//处理修改会员信息跳转
	@GetMapping("/ToCategeroy4")
	@ResponseBody
	public ResponseResult<String> ToCategeroy4(HttpServletRequest request,Integer id){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		redisService.set(cookie.getValue()+"memberid",id);
		//System.err.println(id+"会员开通");
		return new ResponseResult<String>(SUCCESS);
	}
	//处理修改会员信息跳转
		@GetMapping("/ToCategeroy44")
		@ResponseBody
		public ResponseResult<String> ToCategeroy44(HttpServletRequest request,Integer id){
			//取出cookie
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			redisService.set(cookie.getValue()+"memberid",id);
			//换个方法
			member c=memberService.getmebeByCompanyId(id);
			//System.err.println(id+"会员");
		//	System.err.println(c+"会员");
			if(c!=null){
				throw new  warnException("该公司已经开通会员！");
			}
			//System.err.println(id+"会员开通");
			return new ResponseResult<String>(SUCCESS);
		}

	//处理网点跳转
	@PostMapping("/ToCategeroy2")
	@ResponseBody
	public ResponseResult<bCompany> ToCategeroy2(HttpServletRequest request,String comp){
		if(comp.equals("")){
			throw new warnException("请输入网点id");
		}
		Integer companyId=Integer.parseInt(comp);
		
		bCompany cos=bcompanyService.getById(companyId);
		if(cos==null){
			throw new warnException("没有找到id为："+companyId+"的网点");
		}
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		redisService.set(cookie.getValue()+"cos2", cos);
		return new ResponseResult<bCompany>(SUCCESS,cos);
	}
	//处理绑定会员时看下网点出发地
		@GetMapping("/member-show3")
		public String gToCategeroy3(HttpServletRequest request,Model model){
			//取出cookie
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			bCompany cos= (bCompany) redisService.get(cookie.getValue()+"cos2");
			if(cos==null){
				cos=new bCompany();
			}
			model.addAttribute("m", cos);
			return	"member-show3";
		}
		
	//绑定会员时公司预览
	@PostMapping("/ToCategeroy")
	@ResponseBody
	public ResponseResult<String> ToCategeroy(HttpServletRequest request,String comp){
		Integer companyId=Integer.parseInt(comp);
		//System.err.println("split[0]"+companyId);
		//判断是物流公司
		Companier cos1=companyService.getById(companyId);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		redisService.set(cookie.getValue()+"cos", cos1);
	return new ResponseResult<String>(SUCCESS);
	}
	//处理绑定会员时看下公司出发地
	@GetMapping("/member-show2")
	public String gToCategeroy(HttpServletRequest request,Model model){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Companier cos1=(Companier) redisService.get(cookie.getValue()+"cos");
		if(cos1==null){
			cos1=new Companier();
		}
		model.addAttribute("m", cos1);
		return	"member-show2";
	}

	//处理通过公司id查找 
	@PostMapping("/article-list1")
	@ResponseBody
	public ResponseResult<String> SelctBcId(HttpServletRequest request,Integer companyId){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Companier com=companyService.getById(companyId);
		if(com==null){
			throw new CompanyNotFoundException("没有找到id为："+companyId+"的物流公司！");
		}
		List<Companier> list2=new ArrayList<Companier>();
		list2.add(com);
		PageInfo<Companier> pageInfo = new PageInfo<Companier>(list2);
		//找到结果放到缓存中
		redisService.set(cookie.getValue()+"pageInfo",pageInfo);
		return new ResponseResult<String>(SUCCESS);
	}
	//处理通过公司名称查找 
	@PostMapping("/article-list2")
	@ResponseBody
	public ResponseResult<String> SelctBccn(HttpServletRequest request,String companyName){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		List<Companier> list2=companyService.getByName(companyName);
		PageInfo<Companier> pageInfo = new PageInfo<Companier>(list2);
		//找到结果放到缓存中
		redisService.set(cookie.getValue()+"pageInfo",pageInfo);
		return new ResponseResult<String>(SUCCESS);
	}



}
