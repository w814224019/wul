package com.select.wuliu.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.select.wuliu.entity.AlipayVo;
import com.select.wuliu.service.AliPayService;
import com.select.wuliu.shiro.AliPayConfig;
/**
 * 支付宝控制器
 * @author Admin
 *
 */

@RestController
public class PayController {
	Logger logger = LoggerFactory.getLogger("PayController.class");
	@Autowired
	AliPayService aliPayService;
	@RequestMapping(value = "alipay/toPay/{amount}", method = RequestMethod.GET)
	public String alipay(@PathVariable(value = "amount") Integer amount) throws AlipayApiException {
		AlipayVo staticVo=new AlipayVo ();
		String s=amount+"";
		String s1=Double.parseDouble(s)+"";
		//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]必须有
		staticVo.setTotal_amount(s1);
		//订单标题 必须有
		staticVo.setSubject("56114会员充值");
		//该笔订单允许的最晚付款时间取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
		staticVo.setTimeout_express("10m");
		return aliPayService.aliPay(staticVo);
	}

	@PostMapping("alipay/notify_url")
	public String notifyAlipay(HttpServletRequest request) throws Exception {
		logger.info("支付成功, 进入异步通知接口...");
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
      //这里面的公钥是开发中心里的支付宝公钥，要不验签通不过
        boolean signVerified = AlipaySignature.rsaCheckV1(params,
        		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAutmquKYtr+P0nTcZdUHlDVRXQ/8BPsYa69deVNLhUKqR+naLvX3eZEMzO2YRJaNunY/ibrJI/P1FWz4T2c+V2AvGFMx2KxUBhcCHrq2p498EuW7jXs8678vRLGhgrGma6dhBChEXSk0mN8E76cXIuW1COFuBzeV0BZ8ARZunL0XsZgNYO4MfCLud37ee/1DtqZD1cM0TScHp6YprnWXSCH1npxEQER5XSFCKep7QLnJaXBj9/WnUKmnd5XNAgMiNxdg6ioWUstO1LxknrLSDpetMIbbDQr8834nNI2RGzcCp8oz7c6uvmwOMK1I+G0B06I+uNw8p+b+uwquk75VzkwIDAQAB", AliPayConfig.charset, AliPayConfig.sign_type); //调用SDK验证签名
    
        
        //——请在这里编写您的程序（以下代码仅作参考）——
      		/* 实际验证过程建议商户务必添加以下校验：
              1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
      		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
      		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
      		4、验证app_id是否为该商户本身。
      		*/
              if (signVerified) {//验证成功
                  //商户订单号
                  String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                  //支付宝交易号
                  String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                  //交易状态
                  String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

                  //付款金额
                  String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
                  if (trade_status.equals("TRADE_FINISHED")) {
                      //判断该笔订单是否在商户网站中已经做过处理
                      //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                      //如果有做过处理，不执行商户的业务程序

                      //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                      //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                  } else if (trade_status.equals("TRADE_SUCCESS")) {
                      //判断该笔订单是否在商户网站中已经做过处理
                      //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                      //如果有做过处理，不执行商户的业务程序

                      //注意：
                      //付款完成后，支付宝系统发送该交易状态通知

                      // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水

                      //这里不用 查  只是为了 看日志 查的方法应该卸载 同步回调 页面 中

                	  logger.info("********************** 支付成功(支付宝异步通知)查询 只是为了 看日志  **********************");
                      logger.info("* 订单号: {}", out_trade_no);
                      logger.info("* 支付宝交易号: {}", trade_no);
                      logger.info("* 实付金额: {}", total_amount);
                      logger.info("***************************************************************");

                  }
                  logger.info("支付成功...");

              }else {//验证失败
            	  logger.info("支付, 验签失败...");
              }
	
	    return " a li pay notify ";
	}

	@RequestMapping("alipay/return_url")
	public String returnAlipay(HttpServletRequest request)throws Exception {
		 //获取支付宝GET过来反馈信息
		logger.info("支付成功, 进入同步通知接口...");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用,没有乱码时候不用，要不会出现验签不通过
          //  valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
         //这里面的公钥是开发中心里的支付宝公钥，要不验签通不过
		 boolean signVerified = AlipaySignature.rsaCheckV1(params, 

				 "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAutmquKYtr+P0nTcZdUHlDVRXQ/8BPsYa69deVNLhUKqR+naLvX3eZEMzO2YRJaNunY/ibrJI/P1FWz4T2c+V2AvGFMx2KxUBhcCHrq2p498EuW7jXs8678vRLGhgrGma6dhBChEXSk0mN8E76cXIuW1COFuBzeV0BZ8ARZunL0XsZgNYO4MfCLud37ee/1DtqZD1cM0TScHp6YprnWXSCH1npxEQER5XSFCKep7QLnJaXBj9/WnUKmnd5XNAgMiNxdg6ioWUstO1LxknrLSDpetMIbbDQr8834nNI2RGzcCp8oz7c6uvmwOMK1I+G0B06I+uNw8p+b+uwquk75VzkwIDAQAB",  AliPayConfig.charset,  AliPayConfig.sign_type); //调用SDK验证签名
		 if (signVerified) {
	            //商户订单号
	            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

	            //支付宝交易号
	            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

	            //付款金额
	            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
	            logger.info("----notify-----"+out_trade_no+total_amount+trade_no);
		 } else {
			 logger.info("支付, 验签失败...");
	        }
	    logger.info("----return-----");
	    return " a li pay return ";
	}

}

