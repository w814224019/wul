package com.select.wuliu.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.webUser;

/**
 * 发货货主，车主..
 * @author Admin
 *
 */
public interface WebUserMapper {
/**
 * 分页查询所有货车主信息
 */
	Page<webUser> findAllweuser(@Param("name")String name);
	
	/**
	 * 根据货车主主键 id找到实体类信息
	 */
	webUser findwebUserById(Integer id);
	/**
	 * 根据或车主类型找到实体类
	 */
	Page<webUser> findBytype(Integer type);
	/**
	 * 修改或车主密码信息等
	 * rid,name,uid, khr_id as khrId,companyid,branchid,
	mobile,permissionid,company,password,weixin_unionid as weixinUnionid,
	province,city,address,del_flag as delFlag,headimgurl,sex,areabranchid,
	time_out as timeOut,type
	 */
	Integer updatewebUser(@Param("rid")Integer rid,@Param("name")String name,@Param("uid")String uid,@Param("companyid")Integer companyid,
			@Param("mobile")String mobile,@Param("permissionid")String permissionid,@Param("password")String password,@Param("weixin_unionid")String weixinUnionid,
			@Param("address")String address,@Param("del_flag")Integer delFlag,@Param("sex")String sex,@Param("headimgurl")String headimgurl,
			@Param("telephone")String telephone,@Param("company")String company,@Param("add_time")Date addTime
			);
	
	
}
