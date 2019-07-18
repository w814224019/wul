package com.select.wuliu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.International;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.member;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.ImemberSevice;
import com.select.wuliu.service.InternationService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;
/**
 * 国际线路控制器类和会员修改类
 * 显示所有国际线路公司,跳页查询getallintegerBylpage
 * 删除会员memberdel,会员删除方法,修改会员信息memberupdate
 * 添加新会员memberreg,获取公司会员信息,处理通id或者公司名找会员信息
 * 
 * @author Admin
 *
 */
@Controller
public class interController extends BaseController{
	@Autowired
	InternationService interService;
	@Autowired
	LoggerService loggerService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	ImemberSevice memberService;
	@Autowired
	private RedisService redisService;
	//显示所有国际线路公司
	@GetMapping("/article-list4")
	public String articlelist4(Model model,@RequestParam(defaultValue = "50",value ="pageSize") Integer pageSize,@RequestParam(defaultValue = "1",value ="pageNum") Integer pageNum,String name){

		Page<International> list=interService.getintersPBylName(name, pageSize, pageNum);
		//System.err.println("--显示所有国际线路--"+list);
		PageInfo<International> pageInfo = new PageInfo<International>(list);
		//System.out.println("---pageinfo"+pageInfo);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("name",name);
		return "article-list4";
	}

	//跳页查询getallintegerBylpage
	

	//删除会员memberdel
	@PostMapping("/memberdel")
	@ResponseBody
	public ResponseResult<Void> memberdel(HttpServletRequest request,Integer id){
		delmembermethod(request,id);
		return new ResponseResult<Void>(SUCCESS);
	}
	//会员删除方法
	public void delmembermethod(HttpServletRequest request,Integer id){
		member m=memberService.getmemberById(id);
		m.setIsDel(1);
		memberService.Updatemember(m, id);
		//取出cookie   m
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(m.getCompanyName(), u, m.getCompanyId(), "标记删除会员");
		loggerService.addLog(ba);
	}

	//修改会员信息memberupdate
	@PostMapping("/memberupdate")
	@ResponseBody
	public ResponseResult<Void> memberupdate(HttpServletRequest request,Integer id,Integer companyId,String companyName,String userName, String remarks,String startTime,
			String InvoiceNumber,	String endTime,String type) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sta=sdf.parse(startTime);
		Date end=sdf.parse(endTime);
		member m=new member();
		m.setCompanyId(companyId);
		m.setCompanyName(companyName);
		m.setStartTime(sta);
		m.setEndTime(end);
		m.setInvoiceNumber(InvoiceNumber);
		m.setIsDel(0);
		m.setRemarks(remarks);
		m.setType(type);
		m.setUserName(userName);
		memberService.Updatemember(m, id);
		//设置公司表会员等级
		setmeClevel(type, companyId);
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(companyName, u, companyId, "修改会员信息");
		loggerService.addLog(ba);
		return new ResponseResult<Void>(SUCCESS);
	}


	//添加新会员memberreg
	@PostMapping("/memberreg")
	@ResponseBody
	public ResponseResult<Void> memberreg(HttpServletRequest request,Integer companyId,String companyName,String userName, String remarks,String startTime,
			String endTime,String type) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(startTime.equals("")||endTime.equals("")){
			throw new warnException("时间不能为空！");
		}
		Date sta=sdf.parse(startTime);
		Date end=sdf.parse(endTime);
		if(companyName.equals("")){
			//用户名不能为空
			throw new warnException("公司名不能为空！");
		}
		List<member> l=memberService.getmemByName(companyName);
		if(l.size()!=0){
			//用户名已经注册过！
			throw new warnException("用户名"+companyName+"已经添加过！");
		}
		//System.err.println("----endTime---"+endTime);

		member m=new member();
		String InvoiceNumber=(System.currentTimeMillis()+""+companyId+"").substring(5);
		m.setCompanyId(companyId);
		m.setCompanyName(companyName);
		m.setStartTime(sta);
		m.setEndTime(end);
		m.setInvoiceNumber(InvoiceNumber);
		m.setIsDel(0);
		m.setRemarks(remarks);
		m.setType(type);
		m.setUserName(userName);
		memberService.SavaMember(m);
		//设置公司表会员等级
		setmeClevel(type, companyId);
		//取出cookie  companyName companyId
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(companyName, u, companyId, "增加新会员");
		loggerService.addLog(ba);
		return new ResponseResult<Void>(SUCCESS);
	}

	public void setmeClevel(String type,Integer companyId){
		if(type.equals("诚信会员")){
			//1
			Companier c=companyService.getById(companyId);
			if(c==null){
				throw new warnException("该公司已经被标记删除！");
			}
			c.setVipClass(1);
			companyService.UpdateCompany(c, companyId);
			System.err.println("开通诚信会员"+c);
		}else if(type.equals("金牌会员")){
			Companier c=companyService.getById(companyId);
			if(c==null){
				throw new warnException("该公司已经被标记删除！");
			}
			c.setVipClass(2);
			companyService.UpdateCompany(c, companyId);
		}else if(type.equals("银牌牌会员")){
			Companier c=companyService.getById(companyId);
			if(c==null){
				throw new warnException("该公司已经被标记删除！");
			}
			c.setVipClass(3);
			companyService.UpdateCompany(c, companyId);
		}else if(type.equals("铜牌会员")){
			Companier c=companyService.getById(companyId);
			if(c==null){
				throw new warnException("该公司已经被标记删除！");
			}
			c.setVipClass(4);
			companyService.UpdateCompany(c, companyId);
		}else if(type.equals("普通会员")){
			Companier c=companyService.getById(companyId);
			if(c==null){
				throw new warnException("该公司已经被标记删除！");
			}
			c.setVipClass(5);
			companyService.UpdateCompany(c, companyId);
		}else if(type.equals("试用会员")){
			Companier c=companyService.getById(companyId);
			if(c==null){
				throw new warnException("该公司已经被标记删除！");
			}
			c.setVipClass(6);
			companyService.UpdateCompany(c, companyId);
		}else if(type.equals("非会员")){
			Companier c=companyService.getById(companyId);
			if(c==null){
				throw new warnException("该公司已经被标记删除！");
			}
			c.setVipClass(7);
			companyService.UpdateCompany(c, companyId);
		}
	}


	//获取公司会员信息
	@GetMapping("/member-list")
	public String memberlist(Model model,@RequestParam(defaultValue = "1",value ="pageNum") Integer pageNum,String name,@RequestParam(defaultValue = "50",value ="pageSize") Integer pageSize){
		Page<member>list=memberService.getmembersByPLn(name, pageNum, pageSize);
		PageInfo<member> pageInfo = new PageInfo<member>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("name",name);
		return "member-list";
	}
	//处理通id或者公司名找会员信息
	

}
