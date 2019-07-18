package com.select.wuliu.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.userToCorbcompany;

public interface userToCorbcompanyMapper {
	
	/**
	 * 绑定销售与公司
	 */
	Integer addNewUserToCompany(userToCorbcompany utoc);
    /**
     * 解除绑定
     */
	Integer delUsertoCompany(@Param("companyid")Integer companyid,@Param("userid")Integer userid,@Param("isDel")Integer isDel,@Param("mark")Integer mark);
	/**
	 * 根据公司或者网点id 找到实体类
	 */
	userToCorbcompany findByBorCompanyId(@Param("companyid")Integer companyid,@Param("mark")Integer mark);
	
	/**
	 * 根据用户rid分页查询绑定公司
	 * 
	 */
	Page<userToCorbcompany> findutoCPByrid(Integer rid);
	
	
	
	
}
