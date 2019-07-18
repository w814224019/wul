package com.select.wuliu.controller;

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
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.member;
import com.select.wuliu.entity.money;
import com.select.wuliu.entity.planlist;
import com.select.wuliu.entity.sunPather;
import com.select.wuliu.entity.sunTask;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.ImemberSevice;
import com.select.wuliu.service.ImoneyService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;
import com.select.wuliu.util.listTodis;
/**
 * 修改公司 显示页面数据,查看公司下面的网点 并显示列表信息 
 * 没有网点增加网点	网点增加页面,regbCompany网点注册,
 * 显示网点资料 有数据,充值方法,公司充值addcompanymoney,网点下面充值addmoney
 * 线路表里设置刷新计划,线路表查看所有计划列表,查看所有计划列表,模糊查询任务列表功能
 * 网点获取所有计划列表,标记删除网点delbCompany(),修改网点资料updatebCompany
 * 手动刷新 rebCompany,删除网点线路,批量删除线路delbpaths,删除线路方法,
 * 增加网点线路,增加公司线路，地图标记经纬度,显示公司框架,查找线路方法,绑定查询会员信息方法
 * /显示网点框架@author Admin
 *
 */
@Controller
public class productCategoryController extends BaseController{
	@Autowired
	private RedisService redisService;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	LoggerService loggerService;
	@Autowired
	ImoneyService  moneyService;
	@Autowired
	ImemberSevice memberService;
	//显示公司框架
	@GetMapping("/index4")
	public String index4(Integer companyId,Model model){
		Companier c=companyService.getById(companyId);

		model.addAttribute("icompany",c);
		return "index4";
	}
	//显示网点框架
		@GetMapping("/index5")
		public String index5(Integer companyId,Model model){
			bCompany c=bcompanyService.getById(companyId);

			model.addAttribute("icompany",c);
			return "index5";
		}
	
	
	
	//查找线路方法
	public sunPather  selpather(Integer companyId){
		//特定方法通过物流找区号再找区名称
		List<Integer> ends=bcompanyService.getends(companyId);
		List<Pather> pathers=bcompanyService.getAllPatherbywdid(companyId);
		List<Integer> a=new ArrayList<Integer>();
		sunPather sp=new sunPather();
		if(pathers.size()!=0){
			for (Pather pather : pathers) {
				if(pather.getType()==2){
					a.add(pather.getSta());
				}
			}

			//去重
			listTodis.removeDuplicate(a);
			//System.err.println(a+"去重");
			if(a.size()!=0){
				List<String> areanames1=bcompanyService.getareanameThend(a);
				String sta1="";
				for (String string : areanames1) {
					String sta=string;
					sta1+=sta+" ";
				}
				sp.setSta(sta1);
			}
		}else{
			sp.setSta("");
		}
		if(!ends.isEmpty()){
			List<String> areanames=bcompanyService.getareanameThend(ends);
			String endss="";
			for (String string : areanames) {
				String end1=string;
				endss+=end1+" ";
			}
			sp.setEnd(endss);
		}else{
			sp.setEnd("");
		}
		return sp;
	}

	
	//修改公司 显示页面有数据
	@GetMapping("/product-category")
	public String productcategory(Integer companyId,Model model){
		Companier c=companyService.getById(companyId);
		if(companyId==null){
			c=new Companier();
		}
		//取出线路
		sunPather  sp=selpather(companyId);
		member m=memberService.getmebeByCompanyId(companyId);
		if(m==null){
			m=new member();
			m.setCompanyName("");
		}
		//member m=selmember(c);
		//System.err.println(m+"--m-会员");
		if(!m.getCompanyName().equals("")){
			Date end=m.getEndTime();
			//System.err.println(end+"--end-会员剩余时间");
			long day1=end.getTime()-new Date().getTime();
			//	System.err.println(day1+"---会员剩余时间");
			if(day1>0){

				long days=(day1)/(24*60*60*1000);
				model.addAttribute("days",days);
			}else{
				model.addAttribute("days",0);
			}
		}
		//根据物流id查询物流币
		money money=moneyService.getwcmoney(companyId);
		Integer balance;
		if(money==null){
			//System.err.println("---剩余物流币为0---");
			balance=0;
		}else{
			balance=money.getMoney();
		}
		model.addAttribute("money",balance);
	   //System.err.println("--获取公司物流信息c--"+c);
		//System.err.println("---c---"+c);
		model.addAttribute("company",c);
		model.addAttribute("member",m);
		model.addAttribute("sp",sp);
		return "product-category";
	}
	//地图标记经纬度
	@PostMapping("/editMap")
	@ResponseBody
	public ResponseResult<String> editMap(HttpServletRequest request,Integer companyId,String ids){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		bCompany b=bcompanyService.getById(companyId);
		//System.err.println(ids+"经纬度");
		if(ids.equals("")){
			throw new warnException("请点击地图获得经纬度！");
		}
		String[] split = ids.split(",");
		//经度 纬度
		b.setLongitude(split[0]);
		b.setDimensions(split[1]);
		bcompanyService.UpbCompany(b, companyId);
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(b.getBranchName(), u, companyId, "地图标记网点");
		loggerService.addLog(ba);
		return new ResponseResult<String>(SUCCESS);
	}

	//查看公司下面的网点 并显示列表信息 
	@GetMapping("/article-list3")
	public String SelctBccn(Integer companyId,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);	
		List<bCompany> l=bcompanyService.getBycompanyId(companyId);
		//System.err.println("---通过公司l找网点--"+l);
		if(l.size()==0){
			Companier c=companyService.getById(companyId);
			model.addAttribute("company",c);
			return "product-category3";
		}
		PageInfo<bCompany> pageInfo = new PageInfo<bCompany>(l);
		//System.err.println("---通过公司PageInfo找网点--"+pageInfo);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("companyId",companyId);
		return "article-list3";
		
	}
	//没有网点增加网点	网点增加页面
	@GetMapping("/product-category3")
	public String productcategory3(HttpServletRequest request,Integer companyId,Model model){
		Companier c=companyService.getById(companyId);
		model.addAttribute("company",c);
		return "product-category3";
	}


	//regbCompany网点注册
	@PostMapping("/regbCompany")
	@ResponseBody
	public ResponseResult<String> regbCompany(HttpServletRequest request,String s1,String s2,String s3,
			String intro,String culture,Integer branchrelation,Integer companyId,String branchName,String address,String phone,String telephone,String content){
		//System.err.println("s1"+s1);
		//看下是否存在
		bCompany bco=bcompanyService.getbCompanyBybname(branchName);
		if(bco!=null){
			throw new warnException("你注册的"+branchName+"已经存在！");
		}
		bCompany bc=new bCompany();
		bc.setAddress(address);
		if(!s1.equals("选择省")){

			bc.setBranchprovince(s1);
		}

		if(!s2.equals("选择市")){

			bc.setBranchcity(s2);
		}
		if(!s3.equals("选择区")){

			bc.setBrancharea(s3);
		}
		bc.setBranchName(branchName);
		bc.setBranchrelation(branchrelation);
		bc.setCompanyId(companyId);
		bc.setContact(content);
		bc.setIsDel(0);
		bc.setPhone(phone);
		bc.setTelephone(telephone);
		bc.setQz(0);
		bc.setIntro(intro);
		bc.setCulture(culture);
		bcompanyService.addNewbCompany(bc);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(branchName, u, 0000, "增加新网点");
		loggerService.addLog(ba);

		return new ResponseResult<String>(SUCCESS);
	}


	//显示网点资料有数据
	@GetMapping("/product-category2")
	public String productcategory2(Integer companyId,Model model){
		//根据物流id查询物流币
				money m=moneyService.getwcmoney(companyId);
				Integer balance;
				if(m==null){
					System.err.println("---剩余物流币为0---");
					balance=0;
				}else{
					balance=m.getMoney();
				}
				model.addAttribute("money",balance);
		System.err.println("--获取网点物流币信息--"+balance);
		if(companyId.equals("")){
			throw new warnException("请输入网点id");
		}
		bCompany c=bcompanyService.getById(companyId);
		if(c==null){
			throw new warnException("没有找到id为："+companyId+"的网点");
		}
		//System.err.println("---c---"+c);
		model.addAttribute("company",c);
		return "product-category2";
	}

	//充值方法
	public void addmoneys(Integer id,Integer add){
		//充值
		Integer newbalance;
		//System.err.println(add+"--000000000000000000");
		//判断add不能为小数
		if(add==null){
			throw new warnException("请输入大于0 的整数！");
		}else if(add<=0){
			throw new warnException("请输入大于0 的整数！");
		}else if(add.equals("")){
			throw new warnException("请输入大于0 的整数！");
		
		}
		
		money my=moneyService.getwcmoney(id);
		//新用户
		if(my==null){
			newbalance=add;
			money m=new money();
			m.setCompanyId(id);
			m.setMoney(newbalance);
			m.setType(1);
			moneyService.addcwmoney(m);
		}else{
			//老用户
			newbalance=my.getMoney()+add;
			money m1=new money();
			m1.setCompanyId(id);
			m1.setId(my.getId());
			m1.setMoney(newbalance);
			m1.setType(1);
			moneyService.updatewcmoney(m1, id);

		}
		//redisService.set(cookie.getValue()+"money", newbalance);





	}

	//公司充值addcompanymoney
	@PostMapping("/addcompanymoney")
	@ResponseBody
	public ResponseResult<String> addcompanymoney(HttpServletRequest request,Integer id,Integer add){
		addmoneys( id, add);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(companyService.getById(id).getCompanyName(), u, id, "公司充值:"+add);
		loggerService.addLog(ba);  

		return new ResponseResult<String>(SUCCESS);
	}

	//网点下面充值addmoney
	@PostMapping("/addmoney")
	@ResponseBody
	public ResponseResult<String> addmoney(HttpServletRequest request,Integer id,Integer add){
		addmoneys( id, add);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(bcompanyService.getById(id).getBranchName(), u, id, "网点充值:"+add);
		loggerService.addLog(ba);  
		return new ResponseResult<String>(SUCCESS);
	}
	//设置刷新计划返回结果
		@PostMapping("/setrefresh")
		@ResponseBody
		public ResponseResult<String> setrefresh(HttpServletRequest request,Integer id,Integer xid){
			//取出cookie
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			//把网点id放入缓存
			Integer balance;
			money m=moneyService.getwcmoney(id);
			if(m==null){
				balance=0;
				throw new warnException("物流币余额不足请及时充值!");
			}else{
				balance=m.getMoney();
			}
			sunTask st=new sunTask();
			st.setId(id);
			st.setXid(xid);
			st.setMoney(balance);
			redisService.set(cookie.getValue()+"st", st);
			return new ResponseResult<String>(SUCCESS);
		}
	//线路表里设置刷新计划
	@GetMapping("/blank")
	public String blank(Integer id ,Model model,Integer xid){
		//把网点id放入缓存
		Integer balance;
		money m=moneyService.getwcmoney(id);
		if(m==null){
			balance=0;
		}else{
			balance=m.getMoney();
		}
		sunTask st=new sunTask();
		st.setId(id);
		st.setXid(xid);
		st.setMoney(balance);
		model.addAttribute("st", st);
		return "blank";
	}
	//线路表查看所有计划列表
	//设置完线路后看刷新计划表
	@PostMapping("/chakanFrequencyCron")
	@ResponseBody
	public ResponseResult<List<planlist>> chakanFrequencyCron(Integer companyId,Integer lineId,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		Page<Task> lista=bcompanyService.gettasksBylineId(lineId, pageNum, pageSize);
		//System.err.println(lista+"查看刷新计划");
		List<planlist> time=common(lista,companyId);
		//System.err.println(time+"查看刷新计划time");
		return new ResponseResult<List<planlist>>(SUCCESS,time);
	}
	
	//通用方法
	public List<planlist>  common(Page<Task> lista,Integer companyId){
		List<planlist> time=new ArrayList<planlist>();
		if(lista.size()!=0){
			for (Task task : lista) {
				//生成刷新计划列表，把各种信息放上去planlist计划列表
				planlist p=new planlist();
				p.setStatus("等待");
				p.setTime(task.getTime());
				p.setLinedId(task.getLineId());
				p.setId(companyId);
				//通过序号id把设置出发地和到达地
				Pather path=bcompanyService.getPather(task.getLineId());
				if(path==null){
					continue;
				}
				Integer sta=path.getSta();
				Integer end=path.getEnd();
				String sta1=bcompanyService.getByareaid(sta).getAreaname();
				String end1=bcompanyService.getByareaid(end).getAreaname();
				p.setSta(sta1);
				p.setEnd(end1);
				//通过物流id找到物流名称
				bCompany bcom=bcompanyService.getById(companyId);
				p.setWname(bcom.getBranchName());
				p.setPages(lista.getPages());
				p.setTotals(lista.getTotal());
				time.add(p);
			}}
		return time;
	}
	//查看所有计划列表
	@PostMapping("/blan")
	@ResponseBody
	public ResponseResult<List<planlist>> chakanFrequencyCron1(Integer id,Integer lineId,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		Page <Task> lista=bcompanyService.PGetBywdId(pageNum, pageSize, id);
		//System.err.println(lista+"查看刷新计划");
		List<planlist> time=common(lista,id);
		//System.err.println(time+"查看刷新计划time");
		return new ResponseResult<List<planlist>>(SUCCESS,time);
	}
	@GetMapping("/blank5")
	public String blank5(Integer id,Model model){
		model.addAttribute("bcompanyId", id);
		return "blank5";
	}
	//模糊查询任务列表功能
	@PostMapping("/getalltasksBylikename")
	@ResponseBody
	public ResponseResult<List<planlist>> getalltasks(String name,Integer id,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		Page<Task> lista=bcompanyService.getTasksByLikeN(pageNum, pageSize, name);
		//System.err.println("--lista--"+lista);
		List<planlist> time=common(lista,id);
		//System.err.println("--time--"+time);
		return new ResponseResult<List<planlist>>(SUCCESS,time);

	}
	//网点获取所有计划列表
	//标记删除网点delbCompany()
	@PostMapping("/delbCompany")
	@ResponseBody
	public ResponseResult<String> delbCompany( HttpServletRequest request,Integer id){
		bCompany bc=bcompanyService.getById(id);
		bc.setIsDel(1);
		bcompanyService.UpbCompany(bc, id);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(bc.getBranchName(), u, id, "标记删除网点信息");
		loggerService.addLog(ba); 


		return new ResponseResult<String>(SUCCESS);
	}
	//修改网点资料updatebCompany
	@PostMapping("/updatebCompany")
	@ResponseBody
	public ResponseResult<String> updatebCompany(HttpServletRequest request,Integer id,
			String intro,String culture,String s1,String s2,String s3,Integer branchrelation,Integer companyId,String branchName,String address,String phone,String telephone,String content){
		System.err.println("s1"+s1);
		bCompany bc=new bCompany();
		bc.setAddress(address);
		if(!s1.equals("选择省")){

			bc.setBranchprovince(s1);
		}

		if(!s2.equals("选择市")){

			bc.setBranchcity(s2);
		}
		if(!s3.equals("选择区")){

			bc.setBrancharea(s3);
		}
		bc.setBranchName(branchName);
		bc.setBranchrelation(branchrelation);
		bc.setCompanyId(companyId);
		bc.setContact(content);
		bc.setIsDel(0);
		bc.setPhone(phone);
		bc.setTelephone(telephone);
		bc.setQz(0);
		bc.setIntro(intro);
		bc.setCulture(culture);
		//bc.setVipClass(vipClass);
		bcompanyService.UpbCompany(bc, id);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(bcompanyService.getById(id).getBranchName(), u, id, "修改网点资料");
		loggerService.addLog(ba);

		return new ResponseResult<String>(SUCCESS);
	}


	//手动刷新 rebCompany
	@PostMapping("/rebCompany")
	@ResponseBody
	public ResponseResult<String> rebCompany(HttpServletRequest request,Integer id){
		bCompany bc=bcompanyService.getById(id);
		String branchName=bc.getBranchName();
		bcompanyService.UpbCompany(bc, id);
		//修改物流币
		money m=moneyService.getwcmoney(id);
		if(m==null||m.getMoney()<=0){
			throw new warnException("物流币余额不足请及时充值!");
		}
		Integer balance=m.getMoney()-1;
		if(balance<0){
			balance=0;
		}
		money m1=new money();
		m1.setCompanyId(id);
		m1.setId(m.getId());
		m1.setMoney(balance);
		m1.setType(1);
		moneyService.updatewcmoney(m1, id);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(branchName, u, id, "网点手动刷新,物流币-1");
		loggerService.addLog(ba);  

		return new ResponseResult<String>(SUCCESS);
	}
	//删除网点线路
	@PostMapping("/delbPather")
	@ResponseBody
	public ResponseResult<String> delbPather(HttpServletRequest request,Integer id){
		delbcPath(request, id);
		return new ResponseResult<String>(SUCCESS);
	}
	//批量删除线路delbpaths
	@PostMapping("/delbpaths")
	@ResponseBody
	public ResponseResult<String> delbpaths(HttpServletRequest request,String ids){
		if(ids.equals("")){
			throw new warnException("请选择线路！");
		}
		String[] split = ids.split(",");
		for (String string : split) {
			if(!string.equals("")){
				Integer id=Integer.parseInt(string);
				//调用删除方法
				delbcPath(request, id);
			}
		}
		return new ResponseResult<String>(SUCCESS);
	}
	//删除线路方法
	public void delbcPath(HttpServletRequest request,Integer id){
		//根据主键id找到任务表删除
		Page<Task> tasks=bcompanyService.gettasksBylineId(id,1,25);
		if(tasks!=null){
			for (Task task : tasks) {
				bcompanyService.updateTask(task, task.getTime());
				//同时物流币加1
				//修改物流币
				Pather p=bcompanyService.getPather(id);
				money m=moneyService.getwcmoney(p.getWlId());
				Integer balance=m.getMoney()+1;
				money m1=new money();
				m1.setCompanyId(p.getWlId());
				m1.setId(m.getId());
				m1.setMoney(balance);
				m1.setType(1);
				moneyService.updatewcmoney(m1, p.getWlId());
			}

		}
		//System.err.println("--删除线路--"+id);
		Pather p=bcompanyService.getPather(id);
		p.setDelFlag(1);
		bcompanyService.setPatherdel(p,id);
		//找到网点id
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(bcompanyService.getById(p.getWlId()).getBranchName(), u, p.getWlId(), "网点删除线路");
		loggerService.addLog(ba);  

	}

	//增加网点线路
	@PostMapping("/dNpath")
	@ResponseBody
	public ResponseResult<String> dataNpath(HttpServletRequest request,Integer id,Integer cfd,Integer ddd){
		//System.err.println("----增加网点线路sta1--"+sta1);
		if(cfd==null){
			throw new warnException("请输入出发地！");
		}
		if(ddd==null){
			throw new warnException("请输入到达地！");
		}
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		Integer type= 1;
		//System.err.println("----增加网点线路type--"+type);
		List<Pather> ps=companyService.getByft(cfd, ddd);
		for (Pather pather : ps) {
			//System.err.println("----增加网点线路sta1--"+pather.getWlId());
			if(id.equals(pather.getWlId())&&pather.getType()==type){
				throw new warnException("该条线路已经存在，请不要重复添加！");
			}
		}
		long qz=System.currentTimeMillis();
		Integer delFlag=0;
		Pather p=new Pather();
		p.setDelFlag(delFlag);
		p.setEnd(ddd);
		p.setSta(cfd);
		p.setQz(qz);
		p.setWlId(id);
		p.setType(type);
		p.setTui("普通");
		p.setDirect("直达");
		bcompanyService.addNewPather(p);

		User u=(User) redisService.get(cookie.getValue());
		//存日志
		String name="";
		bCompany b=bcompanyService.getById(id);
		if(b==null){
			Companier c=companyService.getById(id);
			name=c.getCompanyName();
		}else{
			name=b.getBranchName();
		}

		BaseEntity ba=LogerUtils.logger(name, u, id, ""+name+"增加线路");
		loggerService.addLog(ba);  
		return new ResponseResult<String>(SUCCESS);
	}
	//增加公司线路
	@PostMapping("/dcNpath")
	@ResponseBody
	public ResponseResult<String> datacNpath(HttpServletRequest request,Integer id,Integer cfd,Integer ddd){
		//System.err.println("----增加网点线路sta1--"+sta1);
		if(cfd==null){
			throw new warnException("请输入出发地！");
		}
		if(ddd==null){
			throw new warnException("请输入到达地！");
		}
		
		Integer type= 2;
		//System.err.println("----增加网点线路type--"+type);
		List<Pather> ps=companyService.getByft(cfd, ddd);
		for (Pather pather : ps) {
			//System.err.println("----增加网点线路sta1--"+pather.getWlId());
			if(id.equals(pather.getWlId())&&pather.getType()==type){
				throw new warnException("该条线路已经存在，请不要重复添加！");
			}
		}
		long qz=System.currentTimeMillis();
		Integer delFlag=0;
		Pather p=new Pather();
		p.setDelFlag(delFlag);
		p.setEnd(ddd);
		p.setSta(cfd);
		p.setQz(qz);
		p.setWlId(id);
		p.setType(type);
		p.setTui("普通");
		p.setDirect("直达");
		bcompanyService.addNewPather(p);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		String name="";
		bCompany b=bcompanyService.getById(id);
		if(b==null){
			Companier c=companyService.getById(id);
			name=c.getCompanyName();
		}else{
			name=b.getBranchName();
		}

		BaseEntity ba=LogerUtils.logger(name, u, id, ""+name+"增加线路");
		loggerService.addLog(ba);  
		return new ResponseResult<String>(SUCCESS);
	}

}
