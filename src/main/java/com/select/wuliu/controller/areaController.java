package com.select.wuliu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.arealike;
import com.select.wuliu.entity.ssarea;
import com.select.wuliu.mapper.arealikeMapper;
import com.select.wuliu.service.IbCompanyService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.ResponseResult;
/**
 * 处理线路控制器类,添加地区别名,处理网点批量添加线路,处理公司批量添加线路
 * ,
 * 获取市，地区模糊查询
 * @author Admin
 *
 */
@Controller
public class areaController extends BaseController{
	@Autowired
	IbCompanyService bcompanyService;
	@Autowired
    private RedisService redisService;
	@Autowired
	arealikeMapper arelikemapper;
	//添加地区别名
	@PostMapping("/addalis")
	@ResponseBody
	public ResponseResult<String> addalis(HttpServletRequest request,Integer areaid,String alias){
		if(areaid==null){
			throw new warnException("请选择添加别名的地区！");
		}
		if(alias.equals("")){
			throw new warnException("请输入地区的别名！");
		}
		area a=bcompanyService.getByareaid(areaid);
		String ids=a.getAlias();
		String endss=alias+";";
		if(ids==null){
			endss=alias+";";
		}else{
			String[] split = ids.split(";");
			for (String string : split) {
				if(string.equals(alias)){
					throw new warnException("别名"+alias+"已经存在！");
				}
				endss+=string+";";
				
			}
		}
		
		bcompanyService.UpdateaAlias(areaid, endss);
		//System.err.println("获取省份");
		return new ResponseResult<>(SUCCESS);
	}
	//处理网点批量添加线路
	@GetMapping("/article-list9")
	public String articlelist9(Integer companyId,Model model){
			 model.addAttribute("Idcompany",companyId);
			 return "article-list9";
		//System.err.println("---c---"+c);
		
	}
	//处理公司批量添加线路
	@GetMapping("/article-list7")
	public String articlelist7(Integer companyId,Model model){
	   model.addAttribute("Idcompany",companyId);
		return "article-list7";
	}
	//处理网点批量添加线路
		@GetMapping("/article-list13")
		public String articlelist13(Integer companyId,Model model){
		   model.addAttribute("Idcompany",companyId);
			return "article-list13";
		}
	@GetMapping("/sheng")
	@ResponseBody
	public ResponseResult<List<area>> sheng(){
		List<area> areas=bcompanyService.getallareas(4744);
		//System.err.println("获取省份");
		return new ResponseResult<List<area>>(SUCCESS,areas);
	}
	
	@GetMapping("/shengshi")
	@ResponseBody
	public ResponseResult<List<ssarea>> shengshi(){
		List<ssarea> areas=new ArrayList<ssarea>();
		List<arealike> ak=arelikemapper.findall();
		for (arealike arealike : ak) {
			ssarea a=new ssarea();
			String sheng=arealike.getSheng();
			String[] split = sheng.split("_");
				Integer shengid=Integer.parseInt(split[0]);
				a.setAreaid(shengid);
				a.setAreaname(split[1]);
			String shi=arealike.getShi();
			String[] split1 = shi.split(" ");
			List<area> shiss=new ArrayList<area>();
			for (String string : split1) {
					String[] split2 = string.split("_");
					Integer shiid=Integer.parseInt(split2[0]);
					area a1=new area();
					a1.setAreaid(shiid);
					a1.setAreaname(split2[1]);
					shiss.add(a1);
				}
			a.setDiqu(shiss);
			areas.add(a);
			
		}
		//System.err.println(areas+"风格省");
		return new ResponseResult<List<ssarea>>(SUCCESS,areas);
	}
	@GetMapping("/shi")
	@ResponseBody
	public ResponseResult<List<area>> shi(Integer areaid){
		//System.err.println("获取shi"+areaid);
		List<area> areas=bcompanyService.getallareas(areaid);
		/*if(areas.size()==0){
			throw new warnException("已经到底了！");
		}*/
		//System.err.println("获取shi"+areas);
		return new ResponseResult<List<area>>(SUCCESS,areas);
	}
	//获取市
	@GetMapping("/area")
	@ResponseBody
	public ResponseResult<List<ssarea>> area(Integer name){
		List<ssarea> areas=new ArrayList<ssarea>();
		//获取省下的市
		List<area> areashi=bcompanyService.getallareas(name);
		for (area area : areashi) {
			Integer parentid=area.getAreaid();
			//System.err.println("--parentid--"+parentid);
			//获取市下面的区
		List<area> qu=bcompanyService.getallareas(parentid);
			ssarea a=new ssarea();
			a.setAreaid(area.getAreaid());
			a.setAreaname(area.getAreaname());
			a.setParentId(area.getParentId());
			a.setPinyin(area.getPinyin());
			a.setDiqu(qu);
			areas.add(a);
		}
		//System.out.println("获取area"+areas);
		return new ResponseResult<List<ssarea>>(SUCCESS,areas);
	}
	//模糊查询
	@GetMapping("/area1")
	@ResponseBody
	public ResponseResult<List<area>> areas(String name){
		List<area> areas1=new ArrayList<area>();
		List<area> areas=bcompanyService.FindaeraLNMap(name);
		//System.out.println("--areas模糊查询-"+areas);
		for (area area : areas) {
			area b=new area();
		area a=bcompanyService.getByareaid(area.getParentId());
		b.setFareaname(a.getAreaname());
		b.setAreaid(area.getAreaid());
		b.setAreaname(area.getAreaname());
		areas1.add(b);
		}
		//System.out.println("--areas模糊查询-"+areas1);
		return new ResponseResult<List<area>>(SUCCESS,areas1);
	}
	
	
	
}
