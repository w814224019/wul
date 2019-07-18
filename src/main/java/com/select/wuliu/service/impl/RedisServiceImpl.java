package com.select.wuliu.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.select.wuliu.service.RedisService;


@Service
public class RedisServiceImpl implements RedisService {

	@Resource
    private RedisTemplate<String,Object> redisTemplate;
	public void set(String key, Object value) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
         vo.set(key, value);
    }
    public Object get(String key) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
	@Override
	public Integer getcount(String key, String hashKey) {
		String val = (String)redisTemplate.opsForHash().get(key, hashKey); 
        if(StringUtils.isEmpty(val)){ 
            return 0; 
        } 
 
        return Integer.parseInt(val); 
	}
	@Override
	public void add(String key, String hashKey, Integer count) {
		String countStr = count==null?"0":count.toString(); 
        redisTemplate.opsForHash().put(key,hashKey, countStr); 
        redisTemplate.expire(key, 24*60*2, TimeUnit.MINUTES);
		
	}
	@Override
	public void ipCount(String ip,String key) {
	        Integer oldCount = getcount(key,ip); 
	        oldCount = oldCount+1; 
	        add(key, ip,oldCount); 
		
	}
	@Override
	public void clear(String key, String hashKey) {
		 redisTemplate.opsForHash().delete(key, hashKey);
	}


}
