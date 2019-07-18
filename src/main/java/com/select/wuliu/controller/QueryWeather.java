package com.select.wuliu.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.select.wuliu.controller.exeception.warnException;
import com.select.wuliu.entity.Forecast;
import com.select.wuliu.entity.WeatherData;
import com.select.wuliu.entity.Yesterday;
import com.select.wuliu.entity.wdandCompany;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.util.ResponseResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
/***
 * 天气
 * @author Admin
 *
 */
public class QueryWeather extends BaseController {
	@Autowired
	RestTemplate restTemplate;
	@GetMapping("/charts8")
	@ResponseBody
	public ResponseResult<String> charts8(Model model){
		String apiURL = "http://tianqi.moji.com/api/citysearch";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
		String jsonsting=responseEntity.getBody();
		JSONObject str =JSONObject.fromObject(jsonsting);
			System.err.println(str+"str");
			String status1="";
			if(str.has("city_list")){
			status1=str.get("city_list").toString();
			System.err.println(status1+"status1");
			}
		return new ResponseResult<String>(SUCCESS);
	}
	
	
	
	@GetMapping("/charts-7")
	public String charts7(String city,Model model){
		String apiURL = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
		// ResponseEntity<String> str = (ResponseEntity<String>) restTemplate.getForObject(apiURL, String.class);
		//System.err.println(responseEntity+"responseEntity");
		//System.err.println(responseEntity.getStatusCodeValue());
		String jsonsting=responseEntity.getBody();
		WeatherData wed=new WeatherData();
		JSONObject str =JSONObject.fromObject(jsonsting);
	//	System.err.println(str+"str");
		String status="";
		if(str.has("status")){
		status=	str.get("status").toString();
		//System.err.println(status+"status");
		}
		if(status.equals("1000")){
		
		if(str.has("data")){
			//System.err.println(str.get("data")); 
			JSONObject str1 =JSONObject.fromObject(str.get("data"));
			if(str1.has("yesterday")){
				//System.err.println(str1.get("yesterday")); 
				JSONObject yy =JSONObject.fromObject(str1.get("yesterday"));
				//System.err.println(yy+"yy"); 
				//  System.err.println(tr+"tr"); 
				Yesterday y=new Yesterday();
				y.setDate(yy.getString("date"));
				y.setFl(yy.getString("fl"));
				y.setFx(yy.getString("fx"));
				y.setHigh(yy.getString("high"));
				y.setLow(yy.getString("low"));
				y.setType(yy.getString("type"));
				//System.err.println(y+"y"); 
				wed.setYesterday(y);
			}
			//预报
			List<Forecast> fc=new ArrayList<Forecast>();
			if(str1.has("forecast")){
				//System.err.println(str1.get("forecast"));  
				JSONArray tr=str1.getJSONArray("forecast");
				for (int i = 0; i < tr.size(); i++) {
					JSONObject yy1=JSONObject.fromObject(tr.get(i)); 
					Forecast f=new Forecast();
					f.setDate(yy1.getString("date"));
					f.setFengli(yy1.getString("fengli"));
					f.setFengxiang(yy1.getString("fengxiang"));
					f.setHigh(yy1.getString("high"));
					f.setLow(yy1.getString("low"));
					f.setType(yy1.getString("type"));
				//	System.err.println(f+"f"); 
					fc.add(f);
				}
				wed.setForecast(fc);
			}
			//感冒指数
			if(str1.has("ganmao")){
				//System.err.println(str1.get("ganmao"));  
				wed.setGanmao(str1.get("ganmao").toString());
			}
			//城市
			if(str1.has("city")){
				//System.err.println(str1.get("city"));  
				wed.setCity(str1.get("city").toString());
			}
			//温度
			if(str1.has("wendu")){
			//	System.err.println(str1.get("wendu")); 
				wed.setWendu(str1.get("wendu").toString());
			}
			//System.err.println(wed+"wed");
			model.addAttribute("WeatherData", wed);
		}
		
		
		}else{
			throw new warnException("请输入正确的城市名称！");
		}
		
		return "charts-7";
	}

}
