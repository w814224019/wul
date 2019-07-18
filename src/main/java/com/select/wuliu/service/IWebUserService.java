package com.select.wuliu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.carLine;
import com.select.wuliu.entity.webUser;
@Service
public interface IWebUserService {
	/**
	 * 分页查询所有货车主信息
	 */
		Page<webUser> GetAllweuser(@Param("name")String name,Integer pageSize,Integer pageNum);

		/**
		 * 根据货车主主键 id找到实体类信息
		 */
		webUser GetwebUserById(Integer id);
		/**
		 * 根据或车主类型找到实体类
		 */
		Page<webUser> GetBytype(Integer type,Integer pageSize,Integer pageNum);

		/**
		 * 修改或车主密码信息等
           */
	    Integer UpdatewebUser(webUser wuser,Integer rid);

	    /**
	     * 根据车辆id查询所有线路
	     */
	    List<carLine> GetcarLIneBycarid(Integer carid);
	    /**
	     * 根据车辆线路主键删除线路
	     */
	    Integer UpdatecarLIneByid(Integer id,Integer isDel);


}
