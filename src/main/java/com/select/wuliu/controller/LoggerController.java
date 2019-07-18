package com.select.wuliu.controller;

import java.text.ParseException;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.member;
import com.select.wuliu.entity.picturer;
import com.select.wuliu.entity.sunPather;
import com.select.wuliu.entity.webUser;
import com.select.wuliu.entity.webs;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.ImemberSevice;
import com.select.wuliu.service.IpictureService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;
/**
 * 处理欢迎界面和删除会员表类
 * welcome.html,处理查询标记删除物流公司,处理查询标记删除的会员
 * 处理还原或者彻底删除会员,处理查询标记删除的网点selectAdelbCompanys
 * 处理还原或者彻底删除网点,处理查询标记删除线路selectAdelPathers,
 * 处理还原或者彻底删除线路,处理还原或者彻底删除公司,查询所有日志
 * 添加网站，删除网站@author Admin
 *
 */

@Controller
public class LoggerController extends BaseController{
	@Autowired
	LoggerService loggerService;
	@Autowired
	ICompanyService companyService;
	@Autowired
    RedisService redisService;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
	ImemberSevice memberService;
	@Autowired
	IpictureService pictureService;
	//welcome.html
	@GetMapping("/welcome")
	public String welcome(Model model,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Integer rid){
		PageHelper.startPage(pageNum,20);
		Page<webs>list=loggerService.GetwebsByrid(pageNum, pageSize, rid, "");
		//System.err.println(list+"收藏网站");
		PageInfo<webs> pageInfo = new PageInfo<webs>(list);
		model.addAttribute("rid",rid);
		model.addAttribute("pageInfo",pageInfo);
		return "welcome";
	}
	//添加网站
	@PostMapping("/addwebs")
	@ResponseBody
	public ResponseResult<String> addwebs(HttpServletRequest request,String type,String webAddress,String webName) {
		//System.err.println("---type--"+type);
		//取出cookie "彻底删除会员信息" id  
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User use=(User) redisService.get(cookie.getValue());
		if(webName==""){
			throw new warnException("请粘贴或输入内容");
		}
		if(webAddress==""){
			throw new warnException("请粘贴或输入内容");
		}
		webs web=new webs();
		web.setCreateTime(new Date());
		web.setRid(use.getRid());
		web.setType(type);
		web.setWebAddress(webAddress);
		web.setWebName(webName);
		loggerService.SaveNewWeb(web);
		BaseEntity ba=LogerUtils.logger(use.getName(), use,  use.getRid(), "添加收藏网站");
		loggerService.addLog(ba);
		return new ResponseResult<>(SUCCESS);
	}
	//添加网站
		@PostMapping("/delweb")
		@ResponseBody
		public ResponseResult<String> delweb(HttpServletRequest request,Integer id) {
			//System.err.println("---type--"+type);
			//取出cookie "彻底删除会员信息" id  
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			loggerService.DelteWeb(id);
			User use=(User) redisService.get(cookie.getValue());
			BaseEntity ba=LogerUtils.logger(use.getName(), use,  id, "删除收藏网站");
			loggerService.addLog(ba);
			return new ResponseResult<>(SUCCESS);
		}
		//查询所有日志getallrecordsBylname
		@GetMapping("/admin-role")
		public String adminrole(String name,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Model model) {
			Page<BaseEntity> slist=loggerService.getrecordsByLnamePage(pageNum, pageSize, name);
			PageInfo<BaseEntity> pageInfo = new PageInfo<BaseEntity>(slist);
			model.addAttribute("pageInfo",pageInfo);
			model.addAttribute("name",name);
			return "admin-role";
		}
	//处理查询标记删除物流公司
	@GetMapping("/selectdelcompanys")
	@ResponseBody
	public ResponseResult<List<Companier>> selectdelcompanys(String name,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		Page<Companier> list=companyService.GetdeleteCompanys(pageNum, pageSize, name);
		if(list.size()==0){
			throw new warnException("没有找到！");
		}
		List<Companier> list1=new ArrayList<Companier>();
		for (Companier companier : list) {
			companier.setPages(list.getPages());
			companier.setTotals(list.getTotal());
			list1.add(companier);
		}
		
		return new ResponseResult<List<Companier>>(SUCCESS,list1);
	}
	//处理查询标记删除的会员
	@GetMapping("/selectAdelmembers")
	@ResponseBody
	public ResponseResult<List<member>> selectAdelmembers(String name,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
		
		Page<member> list=memberService.getAdelmembers(name, pageNum, pageSize);
		if(list.size()==0){
			throw new warnException("没有找到！");
		}
		List<member> list1= new ArrayList<member>();
		for (member member : list) {
			member.setPages(list.getPages());
			member.setTotals(list.getTotal());
			list1.add(member);
		}
		return new ResponseResult<List<member>>(SUCCESS,list1);
	}
	//处理还原或者彻底删除会员
	@PostMapping("/tokenUserinfo5")
	@ResponseBody
	public ResponseResult<String> tokenUserinfo5(HttpServletRequest request,String password,Integer type) {
		//System.err.println("---type--"+type);
		//取出cookie "彻底删除会员信息" id  
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		String ids=(String) redisService.get(cookie.getValue()+"memberids");
		if(ids.equals("")){
			throw new warnException("请选择操作项!");
		}
		User use=(User) redisService.get(cookie.getValue());

		if(!password.equals(use.getPassword())){
			throw new warnException("密码不正确，请联系管理员！");
		}
		String[] split = ids.split(",");

		if(type==1){
			for (String string : split) {
				if(!string.equals("")){
					Integer id=Integer.parseInt(string);
					//彻底删除
					member m=memberService.getAelmemberById(id);
					if(m==null){
						throw new warnException("请选择正确的按钮！");
					}
					//存日志
					BaseEntity ba=LogerUtils.logger(m.getCompanyName(), use,  id, "彻底删除会员信息");
					loggerService.addLog(ba);
					memberService.YDelmember(id);
				}

			}
			return new ResponseResult<String>(SUCCESS1);
		}
		if(type==0){
			for (String string : split) {
				if(!string.equals("")){
					Integer id=Integer.parseInt(string);
					//还原操作 "还原标记删除会员信息"
					member m=memberService.getAelmemberById(id);
					if(m==null){
						throw new warnException("请选择正确的按钮！");
					}
					m.setIsDel(0);
					memberService.Updatemember(m, id);
					//System.err.println("split c:"+c);
					//存日志
					BaseEntity ba=LogerUtils.logger(m.getCompanyName(), use,  id, "还原标记删除会员信息");
					loggerService.addLog(ba);
				}

			}

		}
		return new ResponseResult<String>(SUCCESS);
	}


	
	
	
	//处理查询标记删除的网点selectAdelbCompanys
	@GetMapping("/selectAdelbCompanys")
	@ResponseBody
	public ResponseResult<List<bCompany>> selectAdelbCompanys(String name,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
		Page<bCompany> list=bcompanyService.findADelbCompanys(name, pageSize, pageNum);
		if(list.size()==0){
			throw new warnException("没有找到！");
		}
		List<bCompany> list1=new ArrayList<bCompany>();
		for (bCompany bCompany : list) {
			bCompany.setPages(list.getPages());
			bCompany.setTotals(list.getTotal());
			list1.add(bCompany);
		}
		
		return new ResponseResult<List<bCompany>>(SUCCESS,list1);
	}
	//处理还原或者彻底删除网点
			@PostMapping("/tokenUserinfo4")
			@ResponseBody
			public ResponseResult<String> tokenUserinfo4(HttpServletRequest request,String password,Integer type) {
				//System.err.println("---type--"+type);
				//取出cookie
				Cookie cookie= CookieUtil.getCookieByName(request,"user");
				String ids=(String) redisService.get(cookie.getValue()+"memberids");
				if(ids.equals("")){
					throw new warnException("请选择操作项!");
				}
				User use=(User) redisService.get(cookie.getValue());

				if(!password.equals(use.getPassword())){
					throw new warnException("密码不正确，请联系管理员！");
				}
				String[] split = ids.split(",");

				if(type==1){
					for (String string : split) {
						if(!string.equals("")){
							Integer id=Integer.parseInt(string);
							//日志信息 
							//存日志
							bCompany b=bcompanyService.getAdelbCompany(id);
							if(b==null){
								throw new warnException("请选择正确的按钮！");
							}
							BaseEntity ba=LogerUtils.logger(b.getBranchName(), use, id, "彻底删除网点信息");
							loggerService.addLog(ba);
							//彻底删除
							bcompanyService.YDelbCompany(id);
						}

					}
					return new ResponseResult<String>(SUCCESS1);
				}
				if(type==0){
					for (String string : split) {
						if(!string.equals("")){
							Integer id=Integer.parseInt(string);
							//还原操作 
							
						bCompany b=bcompanyService.getAdelbCompany(id);
						if(b==null){
							throw new warnException("请选择正确的按钮！");
						}
						     b.setIsDel(0);
							//System.err.println("split c:"+c);
						     bcompanyService.UpbCompany(b, id);
						   //存日志
								BaseEntity ba=LogerUtils.logger(b.getBranchName(), use, id, "还原标记删除网点信息");
								loggerService.addLog(ba);
						}

					}

				}
				return new ResponseResult<String>(SUCCESS);
			}



	
	//处理查询标记删除线路selectAdelPathers
	@GetMapping("/selectAdelPathers")
	@ResponseBody
	public ResponseResult<List<sunPather>> selectAdelPathers(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		List<sunPather> slist=new ArrayList<sunPather>();
		Page<Pather> list=bcompanyService.getAllDElPathers(pageNum, pageSize);
		//System.err.println(list+"list");
		Integer pages=list.getPages();
		long totals=list.getTotal();
		if(list.size()!=0){
			for (Pather pather : list) {
				sunPather sunp=new sunPather();
				Integer id=pather.getId();
				sunp.setId(id);
				Integer sta=pather.getSta();
				Integer end=pather.getEnd();
				area area1=bcompanyService.getByareaid(sta);
				if(area1==null){
					continue;
				}else{
					String sta1=area1.getAreaname();
					sunp.setSta(sta1);
				}
				area area2=bcompanyService.getByareaid(end);
				if(area2==null){
					continue;
				}else{
					String end1=area2.getAreaname();
					sunp.setEnd(end1);
				}
				sunp.setPages(pages);
				sunp.setTotals(totals);
				Integer wlId=pather.getWlId();
				//System.err.println("pather"+pather);
				sunp.setWlId(wlId);
				String wname;
				if(pather.getType()==1){
					bCompany b=bcompanyService.getAdelbCompany(wlId);
					if(b!=null){
						 wname=b.getBranchName();
						 sunp.setWname(wname);
					}else{
					bCompany c=bcompanyService.getById(wlId);
								if(c!=null){
									wname=c.getBranchName();
									sunp.setWname(wname);	 
								}
						 
						 
				}
				}
				if(pather.getType()==2){
					//System.err.println("wname");
					Companier d=companyService.getById(wlId);
					if(d!=null){
						wname=d.getCompanyName();
						sunp.setWname(wname);
					}else{
						Companier e=companyService.getAdelById(wlId);
						if(e!=null){
							wname=e.getCompanyName();
							sunp.setWname(wname);
							
						}
					}
				}
				
				sunp.setQz(pather.getQz());
				sunp.setDelFlag(pather.getDelFlag());
				slist.add(sunp);

			}
		}
		return new ResponseResult<List<sunPather>>(SUCCESS,slist);
	}
	//处理还原或者彻底删除线路
		@PostMapping("/tokenUserinfo3")
		@ResponseBody
		public ResponseResult<String> tokenUserinfo3(HttpServletRequest request,String password,Integer type) {
			//System.err.println("---type--"+type);
			//取出cookie
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			String ids=(String) redisService.get(cookie.getValue()+"memberids");
			if(ids.equals("")){
				throw new warnException("请选择操作项!");
			}
			User use=(User) redisService.get(cookie.getValue());

			if(!password.equals(use.getPassword())){
				throw new warnException("密码不正确，请联系管理员！");
			}
			String[] split = ids.split(",");

			if(type==1){
				for (String string : split) {
					if(!string.equals("")){
						Integer id=Integer.parseInt(string);
						//存日志
						Pather d=bcompanyService.getADelPather(id);
						if(d==null){
							throw new warnException("请选择正确的按钮！");
						}
						BaseEntity ba=LogerUtils.logger(d.getSta()+""+d.getEnd(), use, id, "彻底删除线路信息");
						loggerService.addLog(ba);
						//彻底删除 "彻底删除线路信息"
						bcompanyService.YEDelPatherById(id);
					}

				}
				return new ResponseResult<String>(SUCCESS1);
			}
			if(type==0){
				for (String string : split) {
					if(!string.equals("")){
						Integer id=Integer.parseInt(string);
						//还原操作
						Pather d=bcompanyService.getADelPather(id);
						if(d==null){
							throw new warnException("请选择正确的按钮！");
						}
						d.setDelFlag(0);
						//System.err.println("split c:"+c); 
						bcompanyService.setPatherResetdel(d, d.getId());
						//存日志
						BaseEntity ba=LogerUtils.logger(d.getSta()+""+d.getEnd(), use, id, "还原标记删除线路信息");
						loggerService.addLog(ba);
					}

				}

			}
			return new ResponseResult<String>(SUCCESS);
		}



	//处理还原或者彻底删除公司
	@PostMapping("/tokenUserinfo2")
	@ResponseBody
	public ResponseResult<String> tokenUserinfo(HttpServletRequest request,String password,Integer type) {
		//System.err.println("---type--"+type);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		String ids=(String) redisService.get(cookie.getValue()+"memberids");
		if(ids.equals("")){
			throw new warnException("请选择操作项!");
		}
		User use=(User) redisService.get(cookie.getValue());

		if(!password.equals(use.getPassword())){
			throw new warnException("密码不正确，请联系管理员！");
		}
		String[] split = ids.split(",");

		if(type==1){
			for (String string : split) {
				if(!string.equals("")){
					Integer id=Integer.parseInt(string);
					Companier c=companyService.getAdelById(id);
					if(c==null){
						throw new warnException("请选择正确的按钮！");
					}
					//存日志
					Companier p=companyService.getAdelById(id);
					
					BaseEntity ba=LogerUtils.logger(p.getCompanyName(), use, id, "彻底删除公司信息");
					loggerService.addLog(ba);
					//彻底删除 
					companyService.YDelCompany(id);
				}

			}
			return new ResponseResult<String>(SUCCESS1);
		}
		if(type==0){
			for (String string : split) {
				if(!string.equals("")){
					Integer id=Integer.parseInt(string);
					//还原操作 
					Companier c=companyService.getAdelById(id);
					if(c==null){
						throw new warnException("请选择正确的按钮！");
					}
					c.setIsDel(0);
					//System.err.println("split c:"+c);
					companyService.UpdateCompany(c, id);
					//把标记删除的图片还原
					List<picturer> pics=pictureService.getAdelfindById(id);
					if(pics.size()!=0){
					for (picturer np : pics) {
						np.setIsDel(0);
						pictureService.Updatepicture(np,np.getPicturePath());
					}
					  }
					//存日志
					BaseEntity ba=LogerUtils.logger(c.getCompanyName(), use, id, "还原标记删除公司信息");
					loggerService.addLog(ba);
				}

			}

		}
		return new ResponseResult<String>(SUCCESS);
	}



}
