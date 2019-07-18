package com.select.wuliu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.evaluate;
import com.select.wuliu.entity.webs;
@Service
public interface LoggerService {
	/**
	 * 添加日志信息
	 * @param baseEntity
	 * @return
	 */
 Integer  addLog(BaseEntity baseEntity);
 /**
  * 获取所有日志信息
  * @return
  */
 List<BaseEntity> getAlllogger();
 /**
	 * 查询登入记录
	 */
	BaseEntity GetBaseEntityByid(Integer id);
	
 /**
  * 根据公司或者网点id获取所有日志信息
  * @param id
  * @return
  */
 List<BaseEntity> getrecordsByid(Integer id);
 
 /**
  * 根据公司或者网点id查询分页日志记录
  * @param pageNum
  * @param pageSize
  * @param id
  * @return
  */
 Page<BaseEntity> getrecordsByPage(Integer pageNum, Integer pageSize,Integer id);
 /**
  * 分页模糊查询所有
  * @param pageNum
  * @param pageSize
  * @param name
  * @return
  */
 Page<BaseEntity> getrecordsByLnamePage(Integer pageNum, Integer pageSize,String name);
 /**
  * 留言板审核
  */
 Integer UpdateEvas(evaluate av,Integer id);
 /**
  * 根据主键id 找到留言
  */
 evaluate getevaById(Integer id);
 
 /**
  * 分页模糊查询所有留言信息
  */
 Page<evaluate> getEvasByLnameP(Integer pageNum, Integer pageSize,String name);
 /**
  * 添加网站
  */
 	Integer SaveNewWeb(webs web);
 	/**
 	 * 根据用户rid
 	 * 查找收藏内容
 	 */
 	Page<webs> GetwebsByrid(Integer pageNum, Integer pageSize,Integer rid,String name);
 	/**
 	 * 删除收藏网站
 	 * 
 	 */
 	Integer DelteWeb(Integer id);
	/**
	 * 导出表格查询个人所有列表 
	 */
	List<webs> GetallwebsByrid(Integer rid);


}
