package com.select.wuliu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.ExcelData;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.webs;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.ExcelUtils;
/**
 * 导出表格
 * @author Admin
 *
 */
@Controller
public class ExcelController extends BaseController{
	@Autowired
	LoggerService loggerService;
	@Autowired
	RedisService redisService;
	@RequestMapping(value = "/uploadExcl")
    public @ResponseBody Map<String ,Object> uploadExcl(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file){
        Map<String ,Object> result = new HashMap<>();
        String path = request.getSession().getServletContext().getRealPath("/");
        try{
            // 如果文件不为空，写入上传路径
            if(!file.isEmpty()){
                result = uploadExcl(file);
            }else {
                result.put("code","1");
                result.put("message","上传文件为空！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

	 public Map<String,Object> uploadExcl(MultipartFile file) {
	        Map<String,Object> ruslt = new HashMap<>();
	        try {
	            String fileName = file.getOriginalFilename();
	            Workbook workbook;
	            if(fileName.endsWith("xls")){
	                workbook = new HSSFWorkbook(file.getInputStream());
	            }else if(fileName.endsWith("xlsx")){
	                workbook = new XSSFWorkbook(file.getInputStream());
	            } else {
	                ruslt.put("code","1");
	                ruslt.put("message","文件格式非excl");
	                return ruslt;
	            }
	            //判断第一页不为空
	            if(null != workbook.getSheetAt(0)){
	                //读取excl第二行，从1开始
	                for(int rowNumofSheet = 1;rowNumofSheet <=workbook.getSheetAt(0).getLastRowNum();rowNumofSheet++){
	                    if (null != workbook.getSheetAt(0).getRow(rowNumofSheet)) {
	                        //定义行，并赋值
	                        Row aRow = workbook.getSheetAt(0).getRow(rowNumofSheet);
	                        webs user = new webs();
	                        System.out.println(aRow.getLastCellNum());
	                        for(int cellNumofRow=0;cellNumofRow<aRow.getLastCellNum();cellNumofRow++){
	                            //读取rowNumOfSheet值所对应行的数据
	                            //获得行的列数
	                            Cell xCell = aRow.getCell(cellNumofRow);
	                            Object cell_val;
	                            if(cellNumofRow == 0){
	                                if(xCell != null && !xCell.toString().trim().isEmpty()){
	                                    cell_val = xCell.getStringCellValue();
	                                    if(cell_val != null){
	                                        String temp = (String)cell_val;
	                                        user.setWebName(temp);
	                                    }
	                                }
	                            }
	                            if(cellNumofRow == 1){
	                                if(xCell != null && !xCell.toString().trim().isEmpty()){
	                                    cell_val = xCell.getStringCellValue();
	                                    if(cell_val != null){
	                                        String temp = (String)cell_val;
	                                        user.setWebAddress(temp);
	                                        user.setCreateTime(new Date());
	                                    }
	                                }
	                            }
	                            if(cellNumofRow == 3){
	                                if(xCell != null && !xCell.toString().trim().isEmpty()){
	                                    cell_val = xCell.getStringCellValue();
	                                    if(cell_val != null){
	                                        String temp = (String)cell_val;
	                                        user.setType(temp);
	                                        user.setRid(56);
	                                    }
	                                }
	                            }
	                            
	                            loggerService.SaveNewWeb(user);  
	                            
	                        }
	 
	                    }
	                }
	                ruslt.put("code","0");
	                ruslt.put("message","成功插入数据库！");
	            }else {
	                ruslt.put("code","1");
	                ruslt.put("message","第一页EXCL无数据！");
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	            ruslt.put("code","1");
	            ruslt.put("message",e.getMessage());
	        }
	        return ruslt;
	    }
	
	@RequestMapping("/excel/export")
	@ResponseBody
	public void excel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Cookie cookie= CookieUtil.getCookieByName(request,"user");
		if(cookie==null){
			return;
		}
		User use=(User) redisService.get(cookie.getValue());
		//System.err.println("user"+use);
		ExcelData data = new ExcelData();
		data.setName("用户信息数据");
		//添加表头
		List<String> titles = new ArrayList();
		//for(String title: excelInfo.getNames())
		titles.add("网站名称");
		titles.add("网站地址");
		titles.add("修改时间");
		titles.add("网站类型");
		data.setTitles(titles);
		List<webs>list=loggerService.GetallwebsByrid(use.getRid());
		if(list.size()==0){
			throw new warnException("当前没有数据！");
		}
		// System.err.println(list+"收藏网站");
		List<List<Object>> rows = new ArrayList();
		List<Object> row = null;
		
		for (webs webs : list) {
			//添加列
			row=new ArrayList();
			row.add(webs.getWebName());
			row.add(webs.getWebAddress());
			row.add(webs.getCreateTime());
			row.add(webs.getType());
			rows.add(row);
			data.setRows(rows);
		}
		SimpleDateFormat fdate=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String fileName=fdate.format(new Date())+".xls";
		ExcelUtils.exportExcel(response, fileName, data);
	}
}