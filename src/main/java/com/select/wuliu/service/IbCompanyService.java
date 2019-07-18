package com.select.wuliu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.bCompany;
@Service
public interface IbCompanyService {
	/**
	 * 模糊查找所有区号地图专用
	 */
	List<area> FindaeraLNMap(String name);
	/**
	 * 根据areaid增加地区别名
	 */
	Integer UpdateaAlias(Integer areaid,String alias);
	
	/**
     * 根据物流id彻底删除公司线路
     */
	Integer YdeltePather(Integer sta,Integer wid);
	
	/**
	 * 根据网点id获取到达地的地名
	 */
	List<Integer> getends(Integer wlid);
	List<String> getareanameThend(List<Integer> list);
	/**
	 * 通过网点(公司)id和出发地找线路
	 */
	
	List <Pather> getPsBywdIdandsta(Integer wlid,Integer sta);
	
	/**
	 * 添加网点
	 * 
	 */
	Integer addNewbCompany(bCompany bc);
	/**
	 * 根据网点id彻底删除网点
	 */
	Integer YDelbCompany(Integer id);
	/**
	 * 分页模糊查询删除的网点信息
	 */
	Page<bCompany> findADelbCompanys(String name,Integer pageSize,Integer pageNum);
	/**
	 * 通过网点名称找网点
	 */
	bCompany getbCompanyBybname(String branchName);
	
	/**
	 * 查询网点所有信息
	 * @return
	 */
	List<bCompany> getAll();
	/**
	 * 通过网点id找网点
	 * @param id
	 * @return
	 */
	bCompany getById(Integer id);
	/**
	 * 通过网点id找到标记删除的网点
	 */
	bCompany getAdelbCompany(Integer id);
	
	
	
	/**
	 * 通过公司id找网点
	 * @param companyId
	 * @return
	 */
	List<bCompany> getBycompanyId(Integer companyId);
	/**
	 * 模糊查询网点
	 * @param name
	 * @return
	 */
	List<bCompany> getbCompanyByLN(String name);
	/**
	 * 分页模糊查询网点
	 */
	
	Page<bCompany> getbCompanyPByLN(String name,Integer pageSize,Integer pageNum);
	/**
	 * 手动刷新或者自动更新网点
	 * @param bc
	 * @param id
	 * @return
	 */
	Integer UpbCompany(bCompany bc,Integer id);
	/**
	 * 增加网点线路
	 * @param p
	 * @return
	 */
	Integer addNewPather(Pather p);
	/**
	 * 批量添加线路
	 */
	Integer AddPathers(List<Pather> pa);
	/**
	 * 批量删除线路
	 */
	Integer SetdelPathers(Integer delFlag,List<Pather> ps);
	/**
	 * 查询所有关闭的线路
	 */
	List<Pather> GetallcolsePather(Integer delFlag,Integer wlid);
	/**
	 * 彻底删除线路
	 */
	Integer YEDelPatherById(Integer id);
	
	/**
	 * 分页模糊查询所有标记删除线路
	 */
	Page<Pather> getAllDElPathers(Integer pageNum, Integer pageSize);
	/**
	 * 根据主键id 找到标记删除的线路
	 */
	Pather getADelPather(Integer id);
	/**
	 * 查询所有线路
	 * @return
	 */
	List<Pather> getAllPather();
	
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	
	Page<Pather> findByPage(Integer pageNum, Integer pageSize,Integer id,Integer type);
	/**
	 * 根据区号找区名称
	 * @param id
	 * @return
	 */
	area getByareaid(Integer id);
	
	/**
	 * 根据parentid查找所有区号信息
	 */
	List<area> getallareas(Integer parentId);
	
	/**
	 * 查询全国线路
	 * @return
	 */
	List<area> getAareas();
	
	/**
	 * 模糊查询区号
	 */
	
	List<area> getareasLn(String name);
	/**
	 * 分页查询全国所有
	 */
	Page<area> getChian(Integer pageNum, Integer pageSize);
	/**
	 * 查询全国区号
	 */
	List<Integer> getChianId();
	/**
	 * 查询所有定时任务
	 * @return
	 */
	List<Task> getAlltask();
	/**
	 * 根据时间或者序号模糊找任务
	 * @param name
	 * @return
	 */
	Page<Task> getTasksByLikeN(Integer pageNum, Integer pageSize,String name);
	
	/**
	 * 删除任务
	 * @param task
	 * @param time
	 * @return
	 */
	Integer updateTask(Task task,String time);
	
	
	
	
	/**
	 * 根据定时表主键id找任务条
	 * @param id
	 * @param time
	 * @return
	 */
	Task getBydingshiId(Integer id,String time);
	/**
	 * 找到序号中最大值
	 * @param lineId
	 * @return
	 */
	Task getMaxBylineId(Integer lineId);
	/**
	 * 根据序号找到任务
	 * @param lineId
	 * @return
	 */
	List<Task> gettasksBylineId1(Integer lineId);
	Page<Task> gettasksBylineId(Integer lineId,Integer pageNum, Integer pageSize);
	/**
	 * 增加新的任务
	 * @param task
	 * @return
	 */
	Integer addTask(Task task);
	
	
	
	/**
	 * 根据网点id找所有线路
	 * @param wlid
	 * @return
	 */
	List<Pather> getAllPatherbywdid(Integer wlid);
	/**
	 * 分页查询网点id找线路
	 */
	Page <Task> PGetBywdId(Integer pageNum, Integer pageSize,Integer wlId);
	
	
	/**
	 * 通过线路主键id，找到网点或者公司id 
	 * @param lineId
	 * @return
	 */
	Pather getPather(Integer lineId);
	
	/**
	 * 根据线路序号设置删除
	 */
	Integer setPatherdel(Pather p,Integer id);

	
	/**
	 * 根据公司ID删除线路
	 */
	Integer setPatherDelBycompanyId(Pather p,Integer companyId);
	/**
	 * 根据线路序号设置还原
	 */
	
	Integer setPatherResetdel(Pather p,Integer id);
	
	
}
