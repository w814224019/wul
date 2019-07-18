package com.select.wuliu.mapper;

import org.apache.ibatis.annotations.Param;

import com.select.wuliu.entity.money;

public interface moneyMapper {
	/**
	 * 充值
	 * @param m
	 * @return
	 */
    Integer addmoney(money m);
   /**
    * 通过公司或者网点id查询余额 
    * @param id
    * @return
    */
   money findBycwid(Integer id);
   /**
    * 修改金额
    * 
    */
	Integer updatemoney(@Param("id")Integer id,@Param("companyId")Integer companyId,
			@Param("type")Integer type,@Param("balance")Integer balance);
   
   
}
