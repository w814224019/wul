package com.select.wuliu.controller;


import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONObject;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Role;
import com.select.wuliu.entity.User;
import com.select.wuliu.service.IUserService;
import com.select.wuliu.service.LoggerService;
import com.select.wuliu.service.RedisService;
import com.select.wuliu.service.RoleService;
import com.select.wuliu.util.CookieUtil;
import com.select.wuliu.util.LogerUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import javax.xml.transform.Source;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/account")
public class WxDevController extends BaseController{
	@Autowired
	IUserService userService;
	@Autowired
	LoggerService loggerService;
	@Autowired
	private RedisService redisService;
	@Autowired
    private RoleService roleService;
	private  String appID="wxa86c3f546dac0557" ;//这里是AppId,我放到配置文件中了,也可以在这里写,直接定义全局变量,下面的开发者密码一样
	private String appSecret="a881b7c297972b34d7327ec7f2ddfa75";//AppSecret,开发者密码
	private String redirect_url="http://9mghv4.natappfree.cc";
	
	/**
	 * 用户信息临时保存处
	 */
	private static Object quert;
	
	/**
	 * pc点击微信登录，生成登录二维码
	 * 
	 * @author wangsong
	 * @date 2019年6月19日 下午5:58:56
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wxLoginPage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> wxLoginPage(HttpServletRequest request) throws Exception {
           //System.err.println("微信登录00000");
		String sessionId = request.getSession().getId();
		// 设置redirect_uri和state=sessionId以及测试号信息，返回授权url
		String uri = this.getAuthorizationUrl("pc", sessionId);
		Map<String, String> data = new HashMap<String, String>();
		data.put("sessionId", sessionId);
		// 用来前端生成二维码
		data.put("uri", uri);
		// 生成二维码清除上一个用户的数据
		quert = null;
		
		return data;
	}
	/**
	 * 获取生成的二维码url连接
	 * 
	 * @author wangsong
	 * @date 2019年6月19日 下午5:55:36
	 * @param appid：公众号的唯一标识
	 * @param redirect_uri：授权后重定向的回调链接地址
	 * @param response_type：返回类型，填写code
	 * @param scope：应用授权作用域，snsapi_base，snsapi_userinfo
	 * @param state：非必传，重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @param wechat_redirect：无论直接打开还是做页面302重定向时候，必须带此参数
	 * @return
	 */
	public String getAuthorizationUrl(String type, String state) {
		// url
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
		String callbackUrl = "";
		Object urlState = "";
		try {
			if ("pc".equals(type)) {
				// pc端回调方法（没处理，我这里回调一致）
				callbackUrl = URLEncoder.encode(redirect_url + "/account/pcAuth", "utf-8");
				urlState = state;
			} else if ("mobile".equals(type)) {
				// pc端回调方法
				callbackUrl = URLEncoder.encode(redirect_url + "/account/pcAuth", "utf-8");
				urlState = System.currentTimeMillis();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 权限 snsapi_userinfo snsapi_base
		String scope = "snsapi_userinfo";
		url = String.format(url, appID, callbackUrl, scope, urlState);
		return url;
	}
	/**
	 * 扫描二维码授权成功，取到code，设置的回调方法
	 * 
	 * @author wangsong
	 * @date 2019年6月19日 下午5:58:36
	 * @param code
	 * @param state
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pcAuth")
	@ResponseBody
	public String pcCallback(String code, String state, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// 根据code获取access_token和openId，不懂看微信文档
		String result = this.getAccessToken(code);
		JSONObject jsonObject = JSONObject.parseObject(result);
		// String refresh_token = jsonObject.getString("refresh_token");
		String access_token = jsonObject.getString("access_token");
		String openId = jsonObject.getString("openId");
		// 授权成功 --> 根据token和openId获取微信用户信息，不懂看我上一篇文章开始分享的链接
		JSONObject infoJson = this.getUserInfo(access_token, openId);
		if (infoJson != null) {
			infoJson.put("openId", openId);
		}
		// 登录成功保存用户数据
		quert = infoJson;
		return "登录成功";
	}
	/**
	 * 获取用户信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public JSONObject getUserInfo(String accessToken, String openId) {
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
		url = String.format(url, accessToken, openId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();
		String resp = getRestTemplate().getForObject(uri, String.class);
		if (resp.contains("errcode")) {
			return null;
		} else {
			JSONObject data = JSONObject.parseObject(resp);
			JSONObject result = new JSONObject();
			result.put("id", data.getString("unionid"));
			result.put("sex", data.getString("sex"));
			result.put("nickName", data.getString("nickname"));
			result.put("avatar", data.getString("headimgurl"));
			return result;
		}
	}

	
	
	/**
	 * 获取 token, openId（token有效期2小时）
	 * 
	 * @author wangsong
	 * @date 2019年6月19日 下午5:55:11
	 * @param code
	 * @return
	 */
	public String getAccessToken(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		url = String.format(url, appID, appSecret, code);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();

		String resp = getRestTemplate().getForObject(uri, String.class);
		if (resp.contains("openid")) {
			JSONObject jsonObject = JSONObject.parseObject(resp);
			String access_token = jsonObject.getString("access_token");
			String openId = jsonObject.getString("openid");
			JSONObject res = new JSONObject();
			res.put("access_token", access_token);
			res.put("openId", openId);
			res.put("refresh_token", jsonObject.getString("refresh_token"));
			return res.toJSONString();
		} else {
			return null;
		}
	}
	/**
	 * 微信的token只有2小时的有效期，过时需要重新获取，所以官方提供了,根据refresh_token
	 * 刷新获取token的方法，本项目仅仅是获取用户，信息，并将信息存入库，所以两个小时也已经足够了
	 * 
	 * @author wangsong
	 * @date 2019年6月19日 下午5:34:07
	 * @param refresh_token
	 * @return
	 */
	public String refreshToken(String refresh_token) {
		String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
		url = String.format(url, appID, refresh_token);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();
		ResponseEntity<JSONObject> resp = getRestTemplate().getForEntity(uri, JSONObject.class);
		JSONObject jsonObject = resp.getBody();
		String access_token = jsonObject.getString("access_token");
		return access_token;
	}

	
	/**
	 * 
	 * @author wangsong
	 * @date 2019年6月19日 下午5:35:43
	 * @return
	 */
	public static RestTemplate getRestTemplate() {// 手动添加
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setReadTimeout(120000);
		List<HttpMessageConverter<?>> messageConverters = new LinkedList<>();
		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<Source>());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate(messageConverters);
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}

	
	
	
	
	/**
	 * 检测登录状态(获取用户信息) 每秒被调用一次，
	 * 
	 * @param 登录成功，立马得到用户信息返回前台，并取消监听
	 * 
	 * @author wangsong
	 * @return
	 * @date 2019年6月19日 下午8:18:38
	 */
	@RequestMapping(value = "/getInfoJson")
	@ResponseBody
	public String getInfoJson(HttpSession session,HttpServletResponse response) {
		System.out.println("666");
		if (quert == null) {
			return "no";
		}else{
		String	tr=quert.toString();
		JSONObject jsonObject = JSONObject.parseObject(tr);	
		String name=jsonObject.getString("nickName");
		//openId avatar sex
		String openId=jsonObject.getString("openId");
		String avatar=jsonObject.getString("avatar");
		String sex=jsonObject.getString("sex");
		User u=userService.findByUName(name);
		if(u!=null){
			//直接登录
			String uuid =UUID.randomUUID().toString();
			redisService.set(uuid, u);
			Integer rid=u.getRid();
			String roleName=roleService.GetRoleByrid(rid).getRoleName();
			return roleName;
		}else{
			User user=new User();
			user.setAddress("郑州聚时物流有限公司");
			user.setProvince("河南");
			user.setCity("郑州");
			user.setName(name);
			user.setPassword(openId);
			user.setCompanyid(1);
			user.setPermissionid("1");
			user.setMobile("");
			user.setUid("");
			if(sex.equals("0")){
				user.setSex("男");
			}else{
				user.setSex("女");
			}
			user.setHeadimgurl(avatar);
			user.setDelFlag(0);
			userService.save(user);
			User u1=userService.findByUName(name);
			Integer rid1=u1.getRid();
			Role role=new Role();
			role.setRoleName("普通用户");
			role.setRid(rid1);
			role.setUserName(name);
			roleService.SaveNewRole(role);
			//生成uuid存放在cookie
			String uuid =UUID.randomUUID().toString();
			// System.err.println(user+"user");
			//添加保存cookie
			CookieUtil.addCookie(response, "user",uuid);
			redisService.set(uuid, user);
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
					name, openId,true);
			Subject subject=getSubject();
			System.err.println("------subject---"+subject);
			if(subject!=null){
				subject.logout();
			}
			super.login(usernamePasswordToken);
			//存日志
			BaseEntity ba=LogerUtils.logger(user.getCompany(), user, user.getRid(), "微信登录");
			loggerService.addLog(ba);
				return "普通用户";
			}
			
		}
	}

	
	
	
	/**
	 * 网页微信授权登录接口
	 * @return
	 *//*
	@RequestMapping(value = "/login1")
	public void wxLogin( HttpServletResponse response) throws Exception {
		String url="http://9mghv4.natappfree.cc/account/goIndex";
		//http://keem3b.natappfree.cc/sell/test
		String urls = SnsAPI.connectOauth2Authorize(appID, url, true, "STATE");
		System.out.println("授权登录urls:"+urls);
		
		response.sendRedirect(urls);
	}

	@GetMapping(value = "/goIndex")
	public String goToIndex(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		String code = request.getParameter("code");
		System.err.println(code+"wexina ");
		 if(code==null||"".equals(code)){
	            return "code为空";
	        }
		SnsToken snsToken = SnsAPI.oauth2AccessToken(appID, appSecret, code);
		String errcode = snsToken.getErrcode();
        if(errcode!=null&&!"".equals(errcode)){
            return "微信获取出错";
        }
        User user1 = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN",1);  
		System.err.println(user1.getNickname()+"wexinauser");
		String uuid =UUID.randomUUID().toString();
		// System.err.println(user+"user");
		//添加保存cookie
		CookieUtil.addCookie(response, "user",uuid);
		redisService.set(uuid, userService.findByUName(user1.getNickname()));
		return "index";
	}*/
	
}
