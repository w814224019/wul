package com.select.wuliu.service;

import org.springframework.stereotype.Service;

import com.select.wuliu.entity.money;
@Service
public interface ImoneyService {
	/**
	 * 充值
	 * @param m
	 * @return
	 */
	Integer addcwmoney(money m);
	/**
	 * 通过网点或者物流id 查询余额
	 * @param companyId
	 * @return
	 */
	money getwcmoney(Integer companyId);
	/**
	 * 修改余额
	 * @param m
	 * @param companyId
	 * @return
	 */
	Integer updatewcmoney(money m,Integer companyId);


}
