package com.select.wuliu.controller;

import java.util.ArrayList;

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
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.wdandCompany;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.ResponseResult;
/**
 * 处理物流公司查询功能类
 * 显示所有公司信息 显示列表, id 名称 查找 不用分页显示 显示列表
 * 处理通过所有名称模糊查找显示列表 ,处理公司关键词模糊查询返回成功结果
 * 专线查找显示 ,处理专线查找 ,根据地区所在地模糊查找公司 显示
 * 根据地区所在地模糊查找公司处理结果
 * @author Admin
 *
 */
@Controller
public class CompanySelectController extends BaseController {
	@Autowired
	ICompanyService companyService;
	@Autowired
	private RedisService redisService;
	@Autowired
	IbCompanyService bcompanyService;
	//显示所有公司信息 显示列表
	@GetMapping("/article-list")
	public String articlelist(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);	
		//从缓存中取所有公司资料
		List<Companier> list=companyService.findAllCompany();
		PageInfo<Companier> pageInfo = new PageInfo<Companier>(list);
		model.addAttribute("pageInfo",pageInfo);
		return "article-list";
	}
	// id 名称 查找 不用分页显示 显示列表
		@GetMapping("/product-brand")
		public String articlelist(HttpServletRequest request,Model model){
			//取出cookie
			Cookie cookie= CookieUtil.getCookieByName(request,"user");
			PageInfo<Companier> pageInfo = (PageInfo<Companier>) redisService.get(cookie.getValue()+"pageInfo");
			//System.err.println("---pageInfo---"+pageInfo);
			model.addAttribute("pageInfo",pageInfo);
			return "product-brand";
		}
	
	//处理通过所有名称模糊查找显示列表 
	@GetMapping("/product-brand2")
	public String articlelist2(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		//System.err.println("---pageNum--"+pageNum);
		PageHelper.startPage(pageNum,pageSize);
		String Name=(String) redisService.get("name");
		List<Companier> list3=companyService.getByLName(Name);
		PageInfo<Companier> pageInfo = new PageInfo<Companier>(list3);
		//找到结果
		model.addAttribute("pageInfo",pageInfo);
		return "product-brand2";
	}
    //处理公司关键词模糊查询返回成功结果
	@PostMapping("/product-brand2")
	@ResponseBody
	public ResponseResult<String> SelctBlccn(String Name){
		companyService.getByLName(Name);
		redisService.set("name", Name);
		
		return new ResponseResult<String>(SUCCESS);
	}
	
     // 专线查找显示  通用方法
	public List<wdandCompany> commons(Page<Pather> p){
		Integer pages=p.getPages();
		long totals=p.getTotal();
	//	System.out.println("----先把网点放进去----"+p);
		List<wdandCompany> list=new ArrayList<wdandCompany>();
		
		for (Pather pather : p) {
			//查询网点id
			Integer companyId=pather.getWlId();
			//System.err.println("---网点id--"+companyId);
			//物流公司id
			if(pather.getType()==2){
				//在把公司放进去
				//System.out.println("----在把公司放进去----");
				//System.out.println("公司id："+companyId1);
				//找公司
				Companier company=companyService.getById(companyId);
				//System.err.println("物流公司："+company);
				if(company!=null){
				wdandCompany wdc=new wdandCompany();
				wdc.setCompanyId(company.getCompanyId());
				wdc.setCompanyName(company.getCompanyName());
				wdc.setAddress(company.getAddress());
				wdc.setContact(company.getContact());
				wdc.setPhone(company.getPhone());
				wdc.setDetailPicture(company.getDetailPicture());
				wdc.setMark(1);
				String mobilepath="http://192.168.0.109/index/demo/index.html";
				wdc.setMobilepath(mobilepath);
				Integer vipc=company.getVipClass();
				if(vipc==1){
					wdc.setVip("诚信会员");
				}else if(vipc==2){
					wdc.setVip("金牌会员");
				}else if(vipc==3){
					wdc.setVip("银牌会员");
				}else if(vipc==4){
					wdc.setVip("铜牌会员");
				}else if(vipc==5){
					wdc.setVip("普通会员");
				}else if(vipc==6){
					wdc.setVip("试用会员");
				}else if(vipc==7){
					wdc.setVip("非会员");
				}
				wdc.setPages(pages);
				wdc.setTotals(totals);
				list.add(wdc);
				}
			}
			
			if(pather.getType()==1){
				//先把网点放进去
				//System.out.println("----先把网点放进去----");
				bCompany b=bcompanyService.getById(companyId);
				if(b!=null){
				wdandCompany wdc=new wdandCompany();
				wdc.setCompanyId(b.getId());
				wdc.setCompanyName(b.getBranchName());
				wdc.setAddress(b.getAddress());
				wdc.setContact(b.getContact());
				wdc.setPhone(b.getPhone());
				wdc.setMark(0);
				wdc.setPages(pages);
				wdc.setTotals(totals);
				list.add(wdc);
				}
			}
			
		}
		return list;
	}
	
	//处理专线查找 
	@PostMapping("/article-list")
	@ResponseBody
	public ResponseResult<List<wdandCompany>> SelctBcf(Integer sta,Integer end,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		//System.err.println("---city--"+end);
		//System.err.println("---city--"+sta);
		//找到结果放到缓存中
		if(sta==null||end==null){
			throw new warnException("请输入查询条件！");
		}
		//显示25条
		Page<Pather> p=companyService.GetBystaEnd(sta, end, pageNum, pageSize);
		if(p.size()==0){
			throw new warnException("没有找到结果");
		}
		List<wdandCompany> list=commons(p);
		//System.err.println("--专线查询--"+list);
		return new ResponseResult<List<wdandCompany>>(SUCCESS,list);
	}
	//根据地区所在地模糊查找公司 显示
	@GetMapping("/product-brand4")
	public String articlelist4(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);
		String address=(String) redisService.get("address");
		List<Companier> list4=companyService.getByLadderss(address);
		PageInfo<Companier> pageInfo = new PageInfo<Companier>(list4);
		//找到结果
		model.addAttribute("pageInfo",pageInfo);
		return "product-brand4";
	}
	//根据地区所在地模糊查找公司处理结果
	@PostMapping("/article-list4")
	@ResponseBody
	public ResponseResult<String> SelctBladd(String address){
		//找到结果放到缓存中
		companyService.getByLadderss(address);
		redisService.set("address",address);
		return new ResponseResult<String>(SUCCESS);
	}

	
	
	
}
