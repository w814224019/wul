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
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.bCompany;
import com.select.wuliu.entity.carLine;
import com.select.wuliu.entity.carUser;
import com.select.wuliu.entity.fahRecoreder;
import com.select.wuliu.entity.webUser;
import com.select.wuliu.service.ICompanyService;
import com.select.wuliu.service.IUserService;
import com.select.wuliu.service.IWebUserService;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.IfahRecorederService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import com.select.wuliu.util.ResponseResult;
/**
 * 所有列表控制器类,显示所有网点信息
 * 模糊查找网点信息,显示模糊查找的网点信息,显示所有货车主信息,查找所有车(货)主serchallche
 * 通用方法车主货主,显示货车主修改页面，修改货车主信息,标记删除货车主
 * 显示根据发货人id查询的发货记录，根据关键词查询发货记录,根据关键词查询车辆信息findcarUsers
 * @author Admin,根据车辆id查询车辆线路，删除车辆线路
 *
 */
@Controller
public class AllListController extends BaseController {
	@Autowired
	IUserService userService;
	@Autowired
	LoggerService loggerService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	private RedisService redisService;
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
	IWebUserService wuserService;
	@Autowired
	IfahRecorederService fahRecorederService;
	//删除车辆线路
	@GetMapping("/updateCarline")
	@ResponseBody
	public ResponseResult<String> updateCarline(Integer id){
		//System.err.println(pageNum+"页面");
		wuserService.UpdatecarLIneByid(id, 1);
		return new ResponseResult<String>(SUCCESS);
	}
	
	//根据车辆id查询车辆线路chaxuncarLine
	@GetMapping("/chaxuncarLine")
	@ResponseBody
	public ResponseResult<List<carLine>> SelctBlccn(Integer carId){
		//System.err.println(pageNum+"页面");
		List<carLine> users=wuserService.GetcarLIneBycarid(carId);
		//System.err.println(users+"车辆线路");
		//把页数放里面
		return new ResponseResult<List<carLine>>(SUCCESS,users);
	}
	
	
	//显示所有货车主信息
	@GetMapping("/admin-list2")
	public String adminlist2(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,String name,@RequestParam(defaultValue = "50",value = "pageSize")Integer pageSize){
		Page<webUser> users=wuserService.GetAllweuser(name, pageSize, pageNum);
		//	System.err.println(users1+"货车主");
		PageInfo<webUser> pageInfo = new PageInfo<webUser>(users);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("name",name);
		//System.err.println(pageInfo+"货车主");
		return "admin-list2";
	}
	//查找所有车(货)主serchallche
	@GetMapping("/serchallche")
	@ResponseBody
	public ResponseResult<List<webUser>> SelctBlccn(Integer type,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		//System.err.println(pageNum+"页面");
		Page<webUser> users=wuserService.GetBytype(type, pageSize, pageNum);
		//System.err.println(users+"货车主");
		//把页数放里面
		List<webUser> users1=common(users);
		//System.err.println(users1+"货车主");
		return new ResponseResult<List<webUser>>(SUCCESS,users1);
	}
	//通用方法车主货主
	public List<webUser> common(Page<webUser> users){
		long totals=users.getTotal();
		List<webUser> users1=new ArrayList<webUser>();
		for (webUser webUser : users) {
			webUser.setTotals(totals);
			webUser.setPages(users.getPages());
			if(webUser.getType()==4){
				webUser.setChhuo("发货个人");
			}else if(webUser.getType()==3){
				webUser.setChhuo("发货企业");
			}else if(webUser.getType()==1){
				webUser.setChhuo("物流公司");
			}else if(webUser.getType()==2){
				webUser.setChhuo("配货信息部");
			}else if(webUser.getType()==5){
				webUser.setChhuo("车主");
			}else if(webUser.getType()==6){
				webUser.setChhuo("其他");
			}else if(webUser.getType()==100){
				webUser.setChhuo("仅注册");
			}
			
			users1.add(webUser);
		}

		return users1;
	}
	//显示货车主修改页面
	@GetMapping("/admin-add4")
	public String adminadd4(Integer rid,Model model){
		webUser weu=wuserService.GetwebUserById(rid);
		//System.err.println(weu+"车货主");
		model.addAttribute("wuser",weu);
		return "admin-add4";
	}
	//修改货车主信息updatewebUserinfo
	@PostMapping("/updatewebUserinfo")
	@ResponseBody
	public ResponseResult<String> updatewebUserinfo(HttpServletRequest request,Integer rid,String name,String password,String passwords,String sex,
			String mobile,String company,String address){
		webUser weu=wuserService.GetwebUserById(rid);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		weu.setName(name);
		if(passwords.equals("")){
		}else{
			weu.setPassword(passwords);
		}
		weu.setSex(sex);
		weu.setMobile(mobile);
		weu.setCompany(company);
		weu.setAddress(address);
		wuserService.UpdatewebUser(weu, rid);
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(company, u, rid, "修改货车主资料");
		loggerService.addLog(ba);
		return new ResponseResult<String>(SUCCESS);
	}
	//标记删除货车主delwebUserinfo
	@PostMapping("/delwebUserinfo")
	@ResponseBody
	public ResponseResult<String> delwebUserinfo(HttpServletRequest request,Integer rid){
		webUser weu=wuserService.GetwebUserById(rid);
		weu.setDelFlag(1);
		//取出cookie
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		wuserService.UpdatewebUser(weu, rid);
		User u=(User) redisService.get(cookie.getValue());
		//存日志
		BaseEntity ba=LogerUtils.logger(weu.getCompany(), u, rid, "标记删除货车主资料");
		loggerService.addLog(ba);
		return new ResponseResult<String>(SUCCESS);
	}
	//显示根据车辆人id查询车辆信息 根据关键词查询车辆信息findcarUsers
	@GetMapping("/article-list11")
	public String adminlist11(Integer rid,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,String name,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		Page<carUser>frecoreder=fahRecorederService.GetAllcarusers(pageNum, pageSize, name, rid);
		PageInfo<carUser> pageInfo = new PageInfo<carUser>(frecoreder);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("rid",rid);
		model.addAttribute("name",name);
		//System.err.println(pageInfo+"车辆信息");
		return "article-list11";

	}
	
	//显示根据发货人id查询的发货记录 //根据关键词查询发货记录findfahrecorders
	@GetMapping("/article-list10")
	public String adminlist10(Integer rid,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize,String name){
		Page<fahRecoreder>frecoreder=fahRecorederService.GetAfdhRecorder(pageNum, pageSize, name, rid);
		PageInfo<fahRecoreder> pageInfo = new PageInfo<fahRecoreder>(frecoreder);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("rid",rid);
		model.addAttribute("name",name);
		//System.err.println(pageInfo+"发货记录");
		return "article-list10";
	}
	//显示所有网点信息
	@GetMapping("/article-list2")
	public String articlelist(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);	
		List<bCompany> list=bcompanyService.getAll();
		PageInfo<bCompany> pageInfo = new PageInfo<bCompany>(list);
		model.addAttribute("pageInfo",pageInfo);
		return "article-list2";
	}
	//模糊查找网点信息/findbcompayLn

	//显示模糊查找的网点信息
	@GetMapping("/article-list5")
	public String articlelist5(Model model,String name,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "50",value = "pageSize") Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);	
		List<bCompany> list=bcompanyService.getbCompanyByLN(name);
		PageInfo<bCompany> pageInfo = new PageInfo<bCompany>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("name",name);
		return "article-list5";
	}




}
