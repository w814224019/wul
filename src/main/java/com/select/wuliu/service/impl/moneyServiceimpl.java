package com.select.wuliu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.select.wuliu.entity.money;
import com.select.wuliu.mapper.moneyMapper;
import com.select.wuliu.service.ImoneyService;
import com.select.wuliu.service.exeception.moneyNotFoundException;
@Service
public class moneyServiceimpl implements ImoneyService {
     @Autowired
     moneyMapper moneymapper;
     private Integer addmoney(money m){
    	 return moneymapper.addmoney(m);
     }
	@Override
	public Integer addcwmoney(money m) {
		Integer a=addmoney(m);
		return a;
	}
    private  money findBycwid(Integer id){
    	return moneymapper.findBycwid(id);
    }
	@Override
	public money getwcmoney(Integer companyId) {
		money m=findBycwid(companyId);
		/*if(m==null){
			throw new moneyNotFoundException("没有找到"+companyId+"的物流币信息！");
		}*/
		
		
		return m;
	}
    private Integer updatemoney(money m,Integer companyId){
    	money m1=findBycwid(companyId);
    	/*if(m1==null){
			throw new moneyNotFoundException("没有找到"+companyId+"的物流币信息！");
		}*/
    	Integer id=m1.getId();
    	Integer type=m1.getType();
    	Integer balance=m.getMoney();
    	return moneymapper.updatemoney(id, companyId, type, balance);
    }
	@Override
	public Integer updatewcmoney(money m, Integer companyId) {
		Integer a=updatemoney(m, companyId);
		return a;
	}

}
