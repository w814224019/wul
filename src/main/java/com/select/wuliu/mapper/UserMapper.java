package com.select.wuliu.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.select.wuliu.entity.User;

public interface UserMapper {
	/**
	 * 根据部门查询用户信息
	 */
	List<User> findbycompanyId(Integer companyId);
	/**
	 * 查找用户所有信息
	 * @return
	 */
	List<User> findyUser();
	/**
	 * 员工注册
	 * @param user
	 * @return
	 */
	Integer addNewUser(User user);
	/**
	 * 根据用户名查找用户信息
	 * @param username
	 * @return
	 */
	User findByUserName(String name);
	/**
	 * 通过id找用户
	 * @param rid
	 * @return
	 */
	User findById(Integer rid);
	/**
	 * 查找用户uid
	 * @return
	 */
	List<Integer> findyId();
	//name,uid,khr_id,companyid,branchid,mobile,permissionid
	//,company,password,weixin_unionid,province,city,address,del_flag
	//,headimgurl,sex,areabranchid
	/**
	 * 更新修改信息
	 */
	Integer updateUser(@Param("rid")Integer rid,@Param("name")String name,@Param("uid")String uid,@Param("companyid")Integer companyid,
			@Param("mobile")String mobile,@Param("permissionid")String permissionid,@Param("password")String password,@Param("weixin_unionid")String weixinUnionid,
			@Param("address")String address,@Param("sex")String sex,@Param("headimgurl")String headimgurl,@Param("del_flag")Integer delFlag
		
			);
	

}
