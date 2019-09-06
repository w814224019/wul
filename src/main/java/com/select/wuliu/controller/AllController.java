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
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.userToCorbcompany;
import com.select.wuliu.entity.wdandCompany;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.IPUtil;
import com.select.wuliu.util.ResponseResult;
/**
 * 所有控制器类,绑定方法,我所管理的公司
 * 根据查询绑定的结果集合返回wdandCompany
 * @author Admin
 *
 */
@Controller
public class AllController extends BaseController {
	@Autowired
	private RedisService redisService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
	LoggerService loggerService;

	@GetMapping("/index")
	public String NewFile(HttpServletRequest request,Model model){
		addAttUser(request,model);
		return "index";
	}
	//绑定方法
	public void addAttUser(HttpServletRequest request,Model model){
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		//System.out.println("u"+cookie.getValue());
		User u=(User) redisService.get(cookie.getValue());
		//System.out.println("u"+u);
		//获取访问ip
		String ip=IPUtil.commgetIP(request);
		BaseEntity ba1=loggerService.GetBaseEntityByid(u.getRid());
		Date modifiedTime=new Date();
		if(ba1==null){
			ba1=new BaseEntity();
			ba1.setModifiedTime(new Date());
			ba1.setMark("shouci");
		}
		model.addAttribute("ba",ba1);
		BaseEntity ba=new BaseEntity();
		ba.setCompany(u.getName());
		ba.setMobile(u.getMobile());
		ba.setModifiedTime(modifiedTime);
		ba.setModifiedUser(u.getName());
		ba.setType("获得IP地址");
		ba.setId(u.getRid());
		ba.setMark(ip);
		loggerService.addLog(ba);
		/*  //获取访问 次数
	       String key = DateFormatUtils.format(new Date(), "yyyyMMdd")+cookie.getValue(); 
	       Integer count=redisService.getcount(key, ip);
	       //下次访问加
	       redisService.ipCount(ip,key);
		   System.err.println("key"+key+"访问次数："+count+"访问ip:"+ip);*/
		model.addAttribute("user",u);
	}
	@GetMapping("/admin")
	public String NewFil2e(HttpServletRequest request,Model model){
		addAttUser(request,model);
		return "admin";
	}
	@GetMapping("/index3")
	public String NewFile3(HttpServletRequest request,Model model){
		addAttUser(request,model);
		return "index3";
	}

	//index-2主页备用 
	@GetMapping("/index-2")
	public String index(Model model){
		//User u=(User) redisService.get("user");
		//model.addAttribute("user",u);
		return "index-2";
	}

	@GetMapping("/product-brand7")
	public String productbrand7(){
		return "product-brand7";
	}
	@GetMapping("/product-brand8")
	public String productbrand8(){
		return "product-brand8";
	}
	@GetMapping("/article-list6")
	public String articlelist6(){

		return "article-list6";
	}
	//根据公司id找我所管理的公司 memberById
	@PostMapping("/memberById")
	@ResponseBody
	public ResponseResult<wdandCompany> memberById(HttpServletRequest request,Integer companyId,Integer mark){
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		userToCorbcompany utc=companyService.getusertobOrcompanyByid(companyId, mark);
		//System.out.println(utc+"通过id查找我管理的公司utc"+u.getRid());
		if(utc==null){
			throw new warnException("没有找到结果!");
		}
		wdandCompany wdc=new wdandCompany();
		//公司在调方法
		if(mark==1){
			if(utc.getUserid().equals(u.getRid())){
				Companier company=companyService.getById(companyId);
				//System.out.println(company+"通过id查找我管理的公司company");

				if(company!=null){
					wdc.setCompanyId(company.getCompanyId());
					wdc.setCompanyName(company.getCompanyName());
					wdc.setAddress(company.getAddress());
					wdc.setContact(company.getContact());
					wdc.setPhone(company.getPhone());
					wdc.setDetailPicture(company.getDetailPicture());
					wdc.setMark(mark);
					String mobilepath="http://m.56114.com/i.html";
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

				}
			
			}else{
				throw new warnException("没有找到结果!");
			}
			
		}
		//网点在调方法
		if(mark==0){
			if(utc.getUserid().equals(u.getRid())){
				bCompany b=bcompanyService.getById(companyId);
				wdc.setCompanyId(b.getId());
				wdc.setCompanyName(b.getBranchName());
				wdc.setAddress(b.getAddress());
				wdc.setContact(b.getContact());
				wdc.setPhone(b.getPhone());
				wdc.setMark(mark);

			}else{
				throw new warnException("没有找到结果!");
			}
			
			
			
		}

		

		//System.out.println(wdc+"通过id查找我管理的公司");
		
		return new ResponseResult<wdandCompany>(SUCCESS,wdc);
	}
	//我所管理的公司
	@GetMapping("/selusertocorb")
	@ResponseBody
	public ResponseResult<List<wdandCompany>> selusertocorb(HttpServletRequest request,Integer rid,@RequestParam(defaultValue = "50",value = "pageSize")Integer pageSize,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Integer mark){
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		User u=(User) redisService.get(cookie.getValue());
		if(mark==null&&rid==null){
			rid=u.getRid();
			//System.out.println("rid"+rid);
		}
		else if(mark==0&&rid==null){
			throw new warnException("请选择销售员");
		}
		Page<userToCorbcompany> list=companyService.getusertoborCompanysPByid(rid, pageSize, pageNum);
		if(list.size()==0){
			throw new warnException("当前用户没有绑定公司或网点请联系管理员!");
		}
		//System.out.println("我所绑定的list"+list);
		//把公司网点封装一下
		List<wdandCompany> list1=reback(list);
		//System.err.println("查询绑定公司"+list1);
		return new ResponseResult<List<wdandCompany>>(SUCCESS,list1);
	}
	//根据查询绑定的结果集合返回wdandCompany
	public List<wdandCompany> reback(Page<userToCorbcompany> list){
		Integer pages=list.getPages();
		long totals=list.getTotal();
		List<wdandCompany> list1=new ArrayList<wdandCompany>();
		//System.out.println("pageNum"+pageNum);
		for (userToCorbcompany userToCorbcompany : list) {
			//根据用户rid 找到公司id
			Integer companyId=userToCorbcompany.getCompanyid();
			//找出是公司还是网点
			Integer mark=userToCorbcompany.getMark();
			if(mark==1){
				Companier b=companyService.getById(companyId);
				//物流公司id
				if(b!=null){
					//在把公司放进去
					//System.out.println("----在把公司放进去----");
					Integer companyId1=b.getCompanyId();
					//System.out.println("公司id："+companyId1);
					//找公司
					Companier company=companyService.getById(companyId1);
					//System.err.println("物流公司："+company);
					wdandCompany wdc=new wdandCompany();
					wdc.setCompanyId(company.getCompanyId());
					wdc.setCompanyName(company.getCompanyName());
					wdc.setAddress(company.getAddress());
					wdc.setContact(company.getContact());
					wdc.setPhone(company.getPhone());
					wdc.setDetailPicture(company.getDetailPicture());
					wdc.setMark(1);
					wdc.setMark(mark);
					String mobilepath="http://m.56114.com/i.html";
					wdc.setMobilepath(mobilepath);
					wdc.setPages(pages);
					wdc.setTotals(totals);
					wdc.setVipClass(company.getVipClass());
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
					list1.add(wdc);
				}

			}
			//网点
			if(mark==0){
				bCompany b=bcompanyService.getById(companyId);
				//物流公司id
				if(b!=null){
					//先把网点放进去
					wdandCompany wdc=new wdandCompany();
					wdc.setCompanyId(b.getId());
					wdc.setCompanyName(b.getBranchName());
					wdc.setAddress(b.getAddress());
					wdc.setContact(b.getContact());
					wdc.setPhone(b.getPhone());
					wdc.setMark(0);
					wdc.setPages(pages);
					wdc.setTotals(totals);
					list1.add(wdc);
				}

			}
		}
		return list1;

	}

	@GetMapping("/article-add")
	public String articleadd(){
		return "article-add";
	}
	@GetMapping("/shouye")
	public String shouye(){
		return "shouye";
	}
	@GetMapping("/ciye")
	public String ciye(Model model,Integer sta,Integer end,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "15",value = "pageSize") Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);
		if(sta==null||end==null){
			throw new warnException("请输入查询条件！");
		}
		List<Integer>wlId=companyService.GetBystaAd(sta, end, 2);
		if(wlId.size()==0){
			throw new warnException("没有找到结果！");
		}
		List<Companier> list=companyService.GetBystaAndend(wlId);
		PageInfo<Companier> pageInfo = new PageInfo<Companier>(list);
		//找到结果
		model.addAttribute("pageInfo",pageInfo);
		return "ciye";
	}
	@GetMapping("/article-list8")
	public String articlelist8(){
		return "article-list8";
	}
	@GetMapping("/product-category4")
	public String productcategory4(){
		return "product-category4";
	}

	@GetMapping("/product-category-add")
	public String productcategoryadd(){
		return "product-category-add";
	}
	@GetMapping("/product-list")
	public String productlist(){
		return "product-list";
	}
	@GetMapping("/product-brand5")
	public String productbrand5(){
		return "product-brand5";
	}

	@GetMapping("/change-password3")
	public String changepassword3(){
		return "change-password3";
	}

	@GetMapping("/change-password")
	public String changepassword(){
		return "change-password";
	}
	@GetMapping("/change-password2")
	public String changepassword2(){
		return "change-password2";
	}

	@GetMapping("/member-del")
	public String memberdel(){
		return "member-del";
	}
	@GetMapping("/member-level")
	public String memberlevel(){
		return "member-level";
	}

	@GetMapping("/member-scoreoperation")
	public String memberscoreoperation(){
		return "member-scoreoperation";
	}
	@GetMapping("/member-record-browse")
	public String memberrecordbrowse(){
		return "member-record-browse";
	}
	//member-record-download
	@GetMapping("/member-record-download")
	public String memberrecorddownload(){
		return "member-record-download";
	}
	@GetMapping("/member-record-share")
	public String memberrecordshare(){
		return "member-record-share";
	}
	@GetMapping("/charts-9")
	public String charts9(){
		return "charts-9";
	}
	
	@GetMapping("/charts-2")
	public String charts2(){
		return "charts-2";
	}
	@GetMapping("/charts-3")
	public String charts3(){
		return "charts-3";
	}
	@GetMapping("/charts-4")
	public String charts4(){
		return "charts-4";
	}
	@GetMapping("/charts-5")
	public String charts5(){
		return "charts-5";
	}
	@GetMapping("/charts-6")
	public String charts6(){
		return "charts-6";
	}
	@GetMapping("/charts-10")
	public String charts10(){
		return "charts-10";
	}

	@GetMapping("/system-base")
	public String systembase(){
		return "system-base";
	}
	@GetMapping("/system-category")
	public String systemcategory(){
		return "system-category";
	}
	@GetMapping("/system-data")
	public String systemdata(){
		return "system-data";
	}
	@GetMapping("/system-shielding")
	public String systemshielding(){
		return "system-shielding";
	}
	@GetMapping("/system-log")
	public String systemlog(){
		return "system-log";
	}

	@GetMapping("/error")
	public String error(){
		return "404";
	}
	@GetMapping("/logout")
	public String logout(){
		return "login";
	}

}
