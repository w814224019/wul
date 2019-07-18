package com.select.wuliu.service;

import org.springframework.stereotype.Service;

@Service
public interface RedisService {
	/**
	 * 设置缓存键值对
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value);  
    /**
     * 获得缓存
     * @param key
     * @return
     */
    public Object get(String key);
    /**
     * 获得访问次数
     */
    Integer  getcount(String key, String hashKey);
    /**
     * 添加访问次数
     * 
     */
    public void add(String key, String hashKey, Integer count);
    /**
     * 获取旧的访问地址,把访问次数叠加
     */
    public void ipCount(String ip,String key);
    /**
     * 删除缓存
     */
    
    public void clear(String key, String hashKey);
}
