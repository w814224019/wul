package com.select.wuliu.service;

import com.alipay.api.AlipayApiException;
import com.select.wuliu.entity.AlipayVo;

/**
 * 支付宝支付接口
 * @author Admin
 *
 */
public interface AliPayService {

	String aliPay(AlipayVo staticVo) throws AlipayApiException;
	
	
	
}
