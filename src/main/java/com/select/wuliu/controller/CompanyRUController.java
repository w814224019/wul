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
import com.github.pagehelper.PageInfo;
import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.member;
import com.select.wuliu.entity.money;
import com.select.wuliu.entity.picturer;
import com.select.wuliu.entity.sunPather;
import com.select.wuliu.entity.userToCorbcompany;
import com.select.wuliu.entity.webUser;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.ImemberSevice;
import com.select.wuliu.service.ImoneyService;
import com.select.wuliu.service.IpictureService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;
import com.select.wuliu.util.listTodis;
/**
 * 处理物流公司注册和更新类
 * 公司信息注册，批量删除全国线路delchian，批量添加全国线路
 * 批量增加线路，批量删除 delcompanypaths，单独设置双向线路delCPather
 * 删除方法 ，查询公司所有日志，公司线路表里面根据到达地查询线路selpatherByend()
 * 网点线路表里面根据到达地查询线路selpatherByend()，重新写入方法sunpather
 * 查询所有线路方法,运营线路里面查询所有线路 getallcompanypaths,网点信息列表里查询网点所有线路
 * 更新公司资料,删除公司资料，批量增加网点线路，多选输入添加公司线路,公司地图标记,设置推广线路
 * 显示公司地图标记页面，关闭所有线路,开通所有线路,多项选择到达地,批量设置直达线路
 *
 */
@Controller
public class CompanyRUController extends BaseController{
	@Autowired
	LoggerService loggerService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	private RedisService redisService;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
	ImoneyService  moneyService;
	@Autowired
	ImemberSevice memberService;
	@Autowired
	IpictureService pictureService;
	//公司地图标记
	@PostMapping("/editMap1")
	@ResponseBody
	public ResponseResult<String> editMap1(HttpServletRequest request,Integer companyId,String ids){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Companier b=companyService.getById(companyId);
		//System.err.println(ids+"经纬度");
		if(ids.equals("")){
			throw new warnException("请点击地图获得经纬度！");
		}
		String[] split = ids.split(",");
		//经度 纬度
		b.setLongitude(split[0]);
		b.setDimensions(split[1]);
		companyService.UpdateCompany(b, companyId);
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(b.getCompanyName(), u, companyId, "地图标记公司");
		loggerService.addLog(ba);
		return new ResponseResult<String>(SUCCESS);
	}


	//显示公司地图标记页面
	@GetMapping("/article-list12")
	public String adminlist2(Model model,Integer companyId){
		Companier com=companyService.getById(companyId);
		model.addAttribute("base",com);
		return "article-list12";
	}

	//公司信息注册
	@PostMapping("/CompanyReg")
	@ResponseBody
	public ResponseResult<Void> CompanyReg(HttpServletRequest request,String companyName,String address,
			String detailPicture,String telephone,	String contact,	
			Integer att,Integer type,String style,String alias,	String	intro,	
	String directRemark,	String culture,String	depart,	String phone){
		Companier comp=new Companier();
		comp.setAddress(address);
		comp.setCompanyName(companyName);
		comp.setPhone(phone);
		comp.setTelephone(telephone);
		comp.setContact(contact);
		comp.setDepart(depart);
		comp.setIsDel(0);
		comp.setAtt(att);
		comp.setAlias(alias);
		comp.setIntro(intro);
		comp.setCulture(culture);
		comp.setVipClass(7);
		comp.setStyle(style);
		comp.setType(type);
		comp.setDirectRemark(directRemark);
		//comp.setQz(System.currentTimeMillis());
		companyService.SaveCompany(comp);
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(companyName, u, 000, "新增公司资料");
		loggerService.addLog(ba);
		redisService.set(cookie.getValue()+"cos",comp);
		sunPather sp=new sunPather();
		sp.setSta(depart);
		redisService.set(cookie.getValue()+"sp",sp);
		return new ResponseResult<Void>(SUCCESS);


	}
	//关闭所有线路
		@PostMapping("/colsePather")
		@ResponseBody
		public ResponseResult<String> colsePather(HttpServletRequest request,Integer wlid,Integer type){
			List<Pather> p=bcompanyService.getAllPatherbywdid(wlid);
			if(p.size()==0){
				throw new warnException("请添加线路！");
			}
			//关闭线路
			Integer delFlag=2;
			String oper="关闭线路";
			commcolseoropen(p,delFlag,request,type,wlid,oper);
			return new ResponseResult<>(SUCCESS);
		}
		//通用方法
		public void commcolseoropen(List<Pather> p,Integer delFlag,HttpServletRequest request,Integer type,Integer wlid,String oper){
			bcompanyService.SetdelPathers(delFlag, p);
			String name=null;
			if(type==2){
				name="公司";
			} else if(type==1){
				name="网点";
			}
			//取出cookie   
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			User u=(User) redisService.get(cookie.getValue());
			//存日志
			BaseEntity ba=LogerUtils.logger(name, u, wlid, oper);
			loggerService.addLog(ba);
		}
		
		
	//开通所有线路
		@PostMapping("/openPather")
		@ResponseBody
		public ResponseResult<String> openPather(HttpServletRequest request,Integer wlid,Integer type){
			List<Pather> p=bcompanyService.GetallcolsePather(2, wlid);
			if(p.size()==0){
				throw new warnException("没有关闭的线路！");
			}
			Integer delFlag=0;
			//开通线路
			String oper="开通线路";
			commcolseoropen(p,delFlag,request,type,wlid,oper);
			return new ResponseResult<>(SUCCESS);
		}
	
	
	
	//批量删除全国线路delchian
	@PostMapping("/delchian")
	@ResponseBody
	public ResponseResult<String> delchian(HttpServletRequest request,Integer sta,Integer id){
		//彻底删除全国线路
		bcompanyService.YdeltePather(sta,id);
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger("公司或网点", u, id, "彻底删除全国线路");
		loggerService.addLog(ba);
		//System.err.println("删除全国线路成功！");
		return new ResponseResult<>(SUCCESS);
	}

	//批量添加全国线路
	@PostMapping("/addchina")
	@ResponseBody
	public ResponseResult<String> addchina(HttpServletRequest request,Integer id,Integer sta){
		if(sta==null){
			throw new warnException("请输入出发地！");
		}
		//取出cookie 
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Integer type= 2;
		List<Pather> l=bcompanyService.getPsBywdIdandsta(id,sta);
		if(l.size()!=0){
			throw new warnException("该公司出发地已经有线路！");
		}
		List<Integer>areas=bcompanyService.getChianId();
		List<Pather>pa=new ArrayList<Pather>();
		for (Integer integer : areas) {
			long qz=System.currentTimeMillis();
			Integer delFlag=0;
			Pather p=new Pather();
			p.setDelFlag(delFlag);
			p.setEnd(integer);
			p.setSta(sta);
			p.setQz(qz);
			//把公司id放进去
			p.setWlId(id);
			p.setType(type);
			p.setTui("普通");
			p.setDirect("直达");
			pa.add(p);

		}
		bcompanyService.AddPathers(pa);

		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger("公司", u, id, "添加全国线路");
		loggerService.addLog(ba);
		//System.out.println("添加全国线路成功！");
		return new ResponseResult<>(SUCCESS);
	}

	//网点批量添加全国线路类型为1
	@PostMapping("/addbchina")
	@ResponseBody
	public ResponseResult<String> addbchina(HttpServletRequest request,Integer id,Integer sta){
		if(sta==null){
			throw new warnException("请输入出发地！");
		}
		//取出cookie 
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Integer type= 1;
		List<Pather> l=bcompanyService.getPsBywdIdandsta(id,sta);
		if(l.size()!=0){
			throw new warnException("该网点出发地已经有线路！");
		}
		List<Integer>areas=bcompanyService.getChianId();
		List<Pather>pa=new ArrayList<Pather>();
		for (Integer integer : areas) {
			long qz=System.currentTimeMillis();
			Integer delFlag=0;
			Pather p=new Pather();
			p.setDelFlag(delFlag);
			p.setEnd(integer);
			p.setSta(sta);
			p.setQz(qz);
			//把公司id放进去
			p.setWlId(id);
			p.setType(type);
			p.setTui("普通");
			p.setDirect("直达");
			pa.add(p);

		}
		bcompanyService.AddPathers(pa);
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger("网点", u, id, "添加全国线路");
		loggerService.addLog(ba);
		//System.out.println("添加全国线路成功！");
		return new ResponseResult<>(SUCCESS);
	}
    //多项选择到达地
	@GetMapping("/charts-1")
	public String charts1(Integer companyId,Integer cfd,Model model){
		//System.err.println("cfd"+cfd+"companyId"+companyId);
		model.addAttribute("companyId", companyId);
		model.addAttribute("cfd", cfd);
		return "charts-1";
	}
	   //多项选择到达地
		@GetMapping("/charts-8")
		public String charts8(Integer companyId,Integer cfd,Model model){
			//System.err.println("cfd"+cfd+"companyId"+companyId);
			model.addAttribute("companyId", companyId);
			model.addAttribute("cfd", cfd);
			return "charts-8";
		}
	//批量增加公司线路
	@PostMapping("/addcityspath")
	@ResponseBody
	public ResponseResult<String> dataNpath(HttpServletRequest request,Integer id,Integer cfd,String ids,String direct){
		//取出cookie  id
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Integer type= 2;
		if(cfd==null){
			throw new warnException("请输入出发地！");
		}
		if(ids.equals("")){
			throw new warnException("请选择到达地!");
		}else if(ids==null){
			throw new warnException("请选择到达地!");
		}
		List<Pather> pss=new ArrayList<Pather>();
		String[] split = ids.split(",");
		for (String string : split) {
			//System.err.println("spl的长度"+spl.length);
			if(!string.equals("")){
				Integer end=Integer.parseInt(string);
				String name=bcompanyService.getByareaid(end).getAreaname();
				//如果有相同跳过去
				List<Pather> ps=companyService.getByft(cfd, end);
				for (Pather pather : ps) {
					//System.err.println("----增加网点线路sta1--"+pather.getWlId());
					if((id).equals(pather.getWlId())&&pather.getType()==2){
						throw new warnException("到达地:"+name+"已经存在，请不要重复添加！");
					}
				}
				//System.err.println("----增加网点线路sta1--"+sta1);
				long qz=System.currentTimeMillis();
				Integer delFlag=0;
				Pather p=new Pather();
				p.setDelFlag(delFlag);
				p.setEnd(end);
				p.setSta(cfd);
				p.setQz(qz);
				//把公司id放进去
				p.setWlId(id);
				p.setType(type);
				p.setTui("普通");
				p.setDirect(direct);
				pss.add(p);
				
			}
		}
		bcompanyService.AddPathers(pss);
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger("公司", u, id, "批量增加线路");
		loggerService.addLog(ba);
		return new ResponseResult<String>(SUCCESS);
	}
	//批量增加网点线路
	@PostMapping("/addbcityspath")
	@ResponseBody
	public ResponseResult<String> databNpath(HttpServletRequest request,Integer id,Integer cfd,String ids,String direct){
		//取出cookie  id
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Integer type= 1;
		if(cfd==null){
			throw new warnException("请输入出发地！");
		}
		if(ids.equals("")){
			throw new warnException("请选择到达地!");
		}else if(ids==null){
			throw new warnException("请选择到达地!");
		}
		List<Pather> pss=new ArrayList<Pather>();
		String[] split = ids.split(",");
		for (String string : split) {
			//System.err.println("spl的长度"+spl.length);
			if(!string.equals("")){
				Integer end=Integer.parseInt(string);
				String name=bcompanyService.getByareaid(end).getAreaname();
				//如果有相同跳过去
				List<Pather> ps=companyService.getByft(cfd, end);
				for (Pather pather : ps) {
					//System.err.println("----增加网点线路sta1--"+pather.getWlId());
					if((id).equals(pather.getWlId())&&pather.getType()==1){
						throw new warnException("到达地:"+name+"已经存在，请不要重复添加！");
					}
				}
				//System.err.println("----增加网点线路sta1--"+sta1);
				long qz=System.currentTimeMillis();
				Integer delFlag=0;
				Pather p=new Pather();
				p.setDelFlag(delFlag);
				p.setEnd(end);
				p.setSta(cfd);
				p.setQz(qz);
				//把公司id放进去
				p.setWlId(id);
				p.setType(type);
				p.setTui("普通");
				p.setDirect(direct);
				pss.add(p);
			}
		}
		bcompanyService.AddPathers(pss);
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger("网点", u, id, "批量增加线路");
		loggerService.addLog(ba);
		return new ResponseResult<String>(SUCCESS);
	}
	//批量删除 delcompanypaths
	@PostMapping("/delcompanypaths")
	@ResponseBody
	public ResponseResult<String> delcompanypaths(HttpServletRequest request,String ids){
		if(ids.equals("")){
			throw new warnException("请选择线路！");
		}
		String[] split = ids.split(",");
		for (String string : split) {
			if(!string.equals("")){
				Integer id=Integer.parseInt(string);
				//调用删除方法
				delcompanyPather(request,id);
			}
		}

		return new ResponseResult<String>(SUCCESS);
	}
	//批量设置推广线路setTUPather
	@PostMapping("/setTUPather")
	@ResponseBody
	public ResponseResult<String> setTUPather(HttpServletRequest request,String tui,String ids){
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		if(ids==null){
			throw new warnException("请选择添加项！");
		}else if(ids.equals("")){
			throw new warnException("请选择添加项！");
		}
		String[] split = ids.split(",");
		for (String string : split) {
			//System.err.println("spl的长度"+spl.length);
			if(!string.equals("")){
				Integer id=Integer.parseInt(string);
				
				Pather p=bcompanyService.getPather(id);
				p.setTui(tui);
				//设为推广线路
				bcompanyService.setPatherdel(p,id);
			}
		}
		//存日志
		//System.err.println(p.getType()+"线路类型"+p.getWlId());
		 BaseEntity	ba=LogerUtils.logger(u.getName(), u, u.getRid(), "批量设置"+tui+"线路");
		loggerService.addLog(ba);

		return new ResponseResult<String>(SUCCESS);
	}
	//批量设置直达线路
		@PostMapping("/setzdPather")
		@ResponseBody
		public ResponseResult<String> setzdPather(HttpServletRequest request,String zd,String ids){
			//取出cookie   
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			User u=(User) redisService.get(cookie.getValue());
			if(ids==null){
				throw new warnException("请选择添加项！");
			}else if(ids.equals("")){
				throw new warnException("请选择添加项！");
			}
			String[] split = ids.split(",");
			for (String string : split) {
				//System.err.println("spl的长度"+spl.length);
				if(!string.equals("")){
					Integer id=Integer.parseInt(string);
					
					Pather p=bcompanyService.getPather(id);
					p.setDirect(zd);
					//设为推广线路
					bcompanyService.setPatherdel(p,id);
				}
			}
			//存日志
			//System.err.println(p.getType()+"线路类型"+p.getWlId());
			 BaseEntity	ba=LogerUtils.logger(u.getName(), u, u.getRid(), "批量设置"+zd+"线路");
			loggerService.addLog(ba);

			return new ResponseResult<String>(SUCCESS);
		}

	//批量设置双向线路delCPather	
	@PostMapping("/delCPather")
	@ResponseBody
	public ResponseResult<String> delbPather(HttpServletRequest request,String ids){
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		List<Pather> pss=new ArrayList<Pather>();
		if(ids==null){
			throw new warnException("请选择添加项！");
		}else if(ids.equals("")){
			throw new warnException("请选择添加项！");
		}
		String[] split = ids.split(",");
		for (String string : split) {
			//System.err.println("spl的长度"+spl.length);
			if(!string.equals("")){
				Integer id=Integer.parseInt(string);
				Pather p=bcompanyService.getPather(id);
				List<Pather> ps=companyService.getByft(p.getEnd(), p.getSta());
				for (Pather pather : ps) {
					//System.err.println("----增加网点线路sta1--"+pather.getWlId());
					if((p.getWlId()).equals(pather.getWlId())&&pather.getType()==p.getType()){
						
						throw new warnException("该条线路:"+pather.getId()+"已经存在，请不要重复添加！");
					}
				}
				Pather p1=new Pather();
				p1.setSta(p.getEnd());
				p1.setEnd(p.getSta());
				p1.setWlId(p.getWlId());
				p1.setDelFlag(0);
				p1.setQz(System.currentTimeMillis());
				p1.setType(p.getType());
				p1.setTui("普通");
				p1.setDirect("直达");
				pss.add(p1);
				
			}
		}
		
		bcompanyService.AddPathers(pss);
		//存日志
		//System.err.println(p.getType()+"线路类型"+p.getWlId());
		 BaseEntity	ba=LogerUtils.logger(u.getName(), u, u.getRid(), "批量设置双向线路");
		loggerService.addLog(ba);
		return new ResponseResult<String>(SUCCESS);
	}
	//删除方法 
	public void delcompanyPather(HttpServletRequest request,Integer id){
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		Pather p=bcompanyService.getPather(id);
		BaseEntity ba;
		//存日志
		//System.err.println(p.getType()+"线路类型"+p.getWlId());
		if(p.getType()==1){
			bCompany b=bcompanyService.getById(p.getWlId());
			//System.err.println(b+"网点");
			ba=LogerUtils.logger(b.getBranchName(), u, p.getWlId(), "删除公司:"+bcompanyService.getById(p.getWlId()).getBranchName()+"线路");
		}else{
			ba=LogerUtils.logger(companyService.getById(p.getWlId()).getCompanyName(), u, p.getWlId(), "删除公司:"+companyService.getById(p.getWlId()).getCompanyName()+"线路");
		}
		loggerService.addLog(ba);
		p.setDelFlag(1);
		bcompanyService.setPatherdel(p,id);
	}

	//查询公司所有日志
	@PostMapping("/getallrecords")
	@ResponseBody
	public ResponseResult<List<BaseEntity>> getallcompanypat(Integer id,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize) throws ParseException{
		Page<BaseEntity> slist=loggerService.getrecordsByPage(pageNum, pageSize, id);
		/*if(slist.size()==0){
			throw new warnException("没有找到日志信息！");
		}*/
		//System.err.println("---查看公司日志--"+slist);
		List<BaseEntity> slist1=new ArrayList<BaseEntity>();
		for (BaseEntity baseEntity : slist) {
			baseEntity.setPages(slist.getPages());
			baseEntity.setTotals(slist.getTotal());
			slist1.add(baseEntity);
		}
		return new ResponseResult<List<BaseEntity>>(SUCCESS,slist1);
	}
	//公司线路表里面根据到达地查询线路selpatherByend()
	@GetMapping("/selpatherByend")
	@ResponseBody
	public ResponseResult<List<sunPather>> SelctBcf(HttpServletRequest request,Integer depart,Integer end,Integer companyId){
		//System.err.println(depart+"出发地");
		//System.err.println(companyId+"companyId");
		if(depart==null){
			throw new warnException("出发地为空！");
		}
		if(end==null){
			throw new warnException("请输入到达地！");
		}
		List<Pather> listp=companyService.getByft(depart, end);
		//System.err.println(listp+"listp");
		List<sunPather> slist=new ArrayList<sunPather>();
		String wname="";
		for (Pather pather : listp) {
			Integer type=pather.getType();
			Integer cid=pather.getWlId();
			if(type==2&&cid.equals(companyId)){
				//System.err.println(type+"type");
				//System.err.println(companyId+"companyId");
				//System.err.println(cid+"cid");
				Companier c=companyService.getById(companyId);
				wname=c.getCompanyName();
				//重新写入
				sunPather s=setsunpather( pather, wname);
				slist.add(s);
			}

		}
	//	System.err.println("---slist--"+slist);
		if(slist.size()==0){
			throw new warnException("没有找到结果！");
		}
		return new ResponseResult<List<sunPather>>(SUCCESS,slist);
	}
	//网点线路表里面根据到达地查询线路selpatherByend()(2为公司)
	@GetMapping("/selbpatherByend")
	@ResponseBody
	public ResponseResult<List<sunPather>> SelctbBcf(HttpServletRequest request,Integer depart,Integer end,Integer companyId){
		if(depart==null){
			throw new warnException("出发地为空！");
		}
		if(end==null){
			throw new warnException("请输入到达地！");
		}
		List<Pather> listp=companyService.getByft(depart, end);
		//System.err.println(listp+"listp");
		List<sunPather> slist=new ArrayList<sunPather>();
		String wname="";
		for (Pather pather : listp) {
			Integer type=pather.getType();
			Integer cid=pather.getWlId();
			//System.err.println(type+"type");
			//System.err.println(companyId+"companyId");
			//System.err.println(cid+"cid");
			if(type==1&&cid.equals(companyId)){
				wname=bcompanyService.getById(companyId).getBranchName();
				//重新写入
				sunPather s=setsunpather(pather, wname);
				slist.add(s);
			}
		}
		//System.err.println("---slist--"+slist);
		if(slist.size()==0){
			throw new warnException("没有找到结果！");
		}
		return new ResponseResult<List<sunPather>>(SUCCESS,slist);
	}


	//重新写入方法
	public sunPather setsunpather(Pather pather,String wname){
		sunPather sunp=new sunPather();
		Integer id=pather.getId();
		sunp.setId(id);
		Integer sta=pather.getSta();
		Integer end=pather.getEnd();
		area a1=bcompanyService.getByareaid(sta);
		if(a1==null){
			a1=new area();
		}
		area d1=bcompanyService.getByareaid(end);
		if(d1==null){
			d1=new area();
		}
		String sta1=a1.getAreaname();
		String end1=d1.getAreaname();
		sunp.setSta(sta1);
		sunp.setEnd(end1);
		Integer wlId=pather.getWlId();
		sunp.setWlId(wlId);
		sunp.setWname(wname);
		sunp.setDirect(pather.getDirect());
		//sunp.setQz(pather.getQz());
		sunp.setTui(pather.getTui());
		sunp.setDelFlag(pather.getDelFlag());
		return sunp;
	}


	//查询所有线路方法
	public List<sunPather> getallpaths(List<Pather> listp,String wname,Integer pages,long yeshu){
		List<sunPather> slist=new ArrayList<sunPather>();
		for (Pather pather : listp) {
			sunPather sunp=	setsunpather(pather, wname);
			sunp.setPages(pages);
			sunp.setTotals(yeshu);
			slist.add(sunp);
		}
		return  slist;
	}
	//运营线路里面查询所有线路 getallcompanypaths(2为公司)
	@GetMapping("/getallcompanypaths")
	@ResponseBody
	public ResponseResult<List<sunPather>> getallcompanypaths(HttpServletRequest request,Integer companyId,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		Page<Pather> listp=bcompanyService.findByPage(pageNum, pageSize, companyId,2);
		List<sunPather> slist;
		if(listp.size()==0){
			//throw new warnException("当前公司没有运营线路，请添加！");
			slist=new ArrayList<sunPather>();
			return new ResponseResult<List<sunPather>>(SUCCESS,slist);
		}
		Companier c=companyService.getById(companyId);
		//System.err.println("listp"+listp);
		String	wname=c.getCompanyName();
		Integer total=listp.getPages();
		//System.err.println("total"+total);
		long  yeshu=listp.getTotal();
		slist=getallpaths(listp, wname,total,yeshu);
		//System.err.println("slist"+slist);
		return new ResponseResult<List<sunPather>>(SUCCESS,slist);
	}
	//网点信息列表里查询网点所有线路
	@GetMapping("/getallBcompanypaths")
	@ResponseBody
	public ResponseResult<List<sunPather>> getallBcompanypaths(Integer companyId,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
		Page<Pather> listp=bcompanyService.findByPage(pageNum, 20, companyId,1);
		List<sunPather> slist;
		if(listp.size()==0){
			//throw new warnException("当前网点没有运营线路，请添加！");
			slist=new ArrayList<sunPather>();
			return new ResponseResult<List<sunPather>>(SUCCESS,slist);
		}
		bCompany b=bcompanyService.getById(companyId);
		String wname;
		if(b==null){
			wname="空白";
		}else{
			wname=b.getBranchName();
		}
		//System.err.println("wname"+wname);
		Integer total=listp.getPages();
		long yeshu=listp.getTotal();
		slist=getallpaths(listp, wname,total,yeshu);
		//System.err.println("slist"+slist);
		return new ResponseResult<List<sunPather>>(SUCCESS,slist);
	}

	//更新公司资料
	@PostMapping("/updateCompany")
	@ResponseBody
	public ResponseResult<String> updateCompany(HttpServletRequest request,String companyName,String address,
			String depart,String detailPicture,String telephone,	String contact,	
			Integer type,String style,String	intro,	String culture,	
	String 	directRemark,String lightCost,String heavyCost,	String	aging,String	alias,	Integer att,String phone,Integer companyId){
		Companier comp=new Companier();
		comp.setAddress(address);
		comp.setCompanyName(companyName);
		comp.setPhone(phone);
		comp.setTelephone(telephone);
		comp.setContact(contact);
		comp.setAlias(alias);
		comp.setDepart(depart);
		comp.setType(type);
		comp.setStyle(style);
		comp.setLightCost(lightCost);
		comp.setHeavyCost(heavyCost);
		comp.setAging(aging);
		Companier co=companyService.getById(companyId);
		comp.setCompanyId(companyId);
		comp.setQz(co.getQz());
		comp.setIsDel(co.getIsDel());
		comp.setAtt(att);
		comp.setIntro(intro);
		comp.setCulture(culture);
		comp.setPicturePath(co.getPicturePath());
		comp.setDetailPicture(co.getDetailPicture());
		comp.setDirectRemark(directRemark);
		//System.err.println("---修改公司资料--");
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(companyName, u, companyId, "修改公司资料");
		loggerService.addLog(ba);
		companyService.UpdateCompany(comp, companyId);
		redisService.set(cookie.getValue()+"cos", comp);
		return new ResponseResult<String>(SUCCESS);

	}
	//多选输入添加公司线路sureadd
	@PostMapping("/sureadd")
	@ResponseBody
	public ResponseResult<List<String>> sureadd(HttpServletRequest request,String end,Integer companyId,Integer cfd,Integer type){
		List<String> not=new ArrayList<String>();
		if(end.equals("")){
			throw new warnException("请输入达到地!");
		}
		String ends[]=end.split(" ");
		//System.err.println(ends+"到达地");
		for (String string : ends) {
			if(!string.equals("")){
				area a=companyService.findByEname(string);
				if(a==null){
					not.add("到达地："+string+"没有找到区号！");
				}else{
					Integer end1=a.getAreaid();
					List<Pather> ps=companyService.getByft(cfd, end1);
					for (Pather pather : ps) {
						//System.err.println("----增加网点线路sta1--"+pather.getWlId());
						if(companyId.equals(pather.getWlId())&&pather.getType()==type){
							throw new warnException("线路:"+string+"已经存在，请不要重复添加！");
						}
					}
					Pather p=new Pather();
					p.setSta(cfd);
					p.setEnd(end1);
					p.setWlId(companyId);
					p.setQz(System.currentTimeMillis());
					p.setDelFlag(0);
					p.setType(type);
					p.setTui("普通");
					p.setDirect("直达");
					bcompanyService.addNewPather(p);
				}
			}
		}
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger("公司或网点"+type, u, companyId, "输入增加线路");
		loggerService.addLog(ba);
		return new ResponseResult<List<String>>(SUCCESS,not);
	}


	//删除公司资料
	@PostMapping("/delCompany")
	@ResponseBody
	public ResponseResult<Void> updateCompany(HttpServletRequest request,Integer companyId){
		Companier co=companyService.getById(companyId);
		co.setIsDel(1);
		/*//标记删除会员
		member m=memberService.getmebeByCompanyId(companyId);
		Integer id=m.getId();
		m.setIsDel(1);
		memberService.Updatemember(m, id);*/
		//标记删除图片
		List<picturer> pics=pictureService.getBycompanyId(companyId);
		if(pics.size()!=0){
			for (picturer np : pics) {
				np.setIsDel(1);
				pictureService.Updatepicture(np,np.getPicturePath());
			}
		}
		//取出cookie   
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(co.getCompanyName(), u, companyId, "标记删除公司");
		loggerService.addLog(ba);
		companyService.UpdateCompany(co, companyId);


		return new ResponseResult<Void>(SUCCESS);
	}










}
