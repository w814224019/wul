package com.select.wuliu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.select.wuliu.entity.User;



@Service
public interface IUserService {
	/**
	 * 根据部门查询用户信息
	 */
	List<User> GetbycompanyId(Integer companyId);
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	Integer save(User user);
	/**
	 * 通过用户名找用户
	 * @param username
	 * @return
	 */
    User findByUName(String username);
    /**
     * 查找用户的所有uid
     * @return
     */
    List<Integer> getUid();
    /**
     * 通过用户rid
     * 找用户
     * @param rid
     * @return
     */
    User getById(Integer rid);
    /**
     * 获取所有用户
     * @return
     */
    List<User> getUser();
    /**
     * 更新用户信息
     */
    Integer UpdateUserInfo(User u,Integer rid);
    
    
}
