package com.select.wuliu.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class SendCode {
	//http://www.miaodiyun.com/



	/**
	 * ACCOUNT_SID:开发者主账号ID(注册后自动生成)
	 */
	public static final String ACCOUNT_SID = "25903444";


	/**
	 * AUTH_TOKEN:开发者账号认证密匙(注册后自动生成)
	 */
	public static final String AUTH_TOKEN = "54c5683e2e36824503a909869d9f164d";


	/**
	 * BASE_URL:请求地址
	 */
	public static final String BASE_URL = "'https://dxyzm.market.alicloudapi.com/chuangxin/dxjk?content=【创信】你的验证码是：5873，3分钟内有效！&mobile=13568813957'  -H 'Authorization:APPCODE 你自己的AppCode'";

	/**
	 * RESP_DATA_TYPE:数据返回格式为JSON格式
	 */
	public static final String RESP_DATA_TYPE = "json";

	public static String sendMsgTo(String to, String createRandNum) {
		String randomNum = createRandNum;
		String smsContent = "【柴火科技】您的验证码为" + randomNum + "，请于2分钟内正确输入，如非本人操作，请忽略此短信。";
		/**
		 * 获取时间戳
		 */
		String timestamp = getTimestamp();

		/**
		 * 获取签名
		 */
		String sig = sig_MD5(ACCOUNT_SID + AUTH_TOKEN + timestamp);

		/**
		 * 要提交的post数据
		 */
		String http_post = "accountSid=" + ACCOUNT_SID + "&smsContent=" + smsContent + "&to=" + to + "×tamp="
				+ timestamp + "&sig=" + sig + "&respDataType=" + RESP_DATA_TYPE;



		OutputStreamWriter osw = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {

			/**
			 * 获取连接
			 */
			URL url = new URL(BASE_URL);

			/**
			 * 打开连接
			 */
			URLConnection conn = url.openConnection();

			/**
			 * 设置连接参数
			 */
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(20000);

			/**
			 * 提交数据
			 */
			osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			osw.write(http_post);
			osw.flush();

			/**
			 * 读取返回数据
			 */
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();


	}

	public static String createRandNum() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i <= 5; i++) {
            String s = random.nextInt(10) + "";
            sb.append(s);
        }
        return sb.toString();
    }
	
	

	
	
	
	public static String sig_MD5(String str) {
		String sig_md5 = DigestUtils.md5Hex(str);
		return sig_md5;
	}

	private static String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		return date;
	}


}
