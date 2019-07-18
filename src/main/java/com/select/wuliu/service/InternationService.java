package com.select.wuliu.service;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.International;
@Service
public interface InternationService {
/**
 * 增加国际线路
 */
	
	Integer SaveInternational(International inter);
	/**
	 * 根据国际物流公司
	 * id 找公司
	 */
	International getinterById(Integer id);
	/**
	 * 分页模糊查找国际物流公司
	 */
	
	Page<International> getintersPBylName(String name,Integer pageSize,Integer pageNum);
	
	/**
	 * 修改国际物流资料
	 */
	Integer Updateinterinfo(International inter,Integer id);
	
	
}
