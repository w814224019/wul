package com.select.wuliu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Task;
import com.select.wuliu.entity.bCompany;



public interface bCompanyMapper {
    /**
     * 根据物流id彻底删除公司线路
     */
	Integer YdelbPather(@Param("sta")Integer sta,@Param("wl_id")Integer wid);
	
	/**
	 * 通过线路表中公司id找到地区表中地区名称
	 */
	List<Integer> ends(Integer wlid);
	List<String> findThend(List<Integer> list);
	
	
	
	/**
	 * 增加网点
	 */
	Integer addbCompany(bCompany bc);
	/**
	 * 彻底删除网点信息
	 */
	Integer YdelbCompany(Integer id);
	/**
	 * 分页模糊查询删除的网点信息
	 */
	
	Page<bCompany> findDelbcompany(@Param("name")String name);
	
	
	/**
	 * 通过网点名称找网点
	 * @param branchName
	 * @return
	 */
	bCompany findbcompanyBybrname(String branchName);
	
	/**
	 * 更新修改网点资料
	 * @return
	 */
	Integer UpdatebCompany(@Param("id")Integer id,@Param("branchName")String branchName,
			@Param("phone")String phone,@Param("address")String address,
			@Param("telephone")String telephone,
			@Param("companyId")Integer companyId,@Param("contact")String contact,
			@Param("longitude")String longitude,@Param("qz")long qz,
			@Param("isDel")Integer isDel,@Param("dimensions")String dimensions
			,@Param("branchprovince")String branchprovince,@Param("branchcity")String branchcity
			,@Param("brancharea")String brancharea,@Param("branchrelation")Integer branchrelation
			,@Param("intro")String intro,@Param("culture")String culture,@Param("vipClass")Integer vipClass
			);
	
	/**
	 * 查询所有网点
	 * @return
	 */
	List<bCompany> findAll();
	/**
	 * 分页模糊查找网点
	 * 
	 */
	Page<bCompany> findbcompanyPByLike(@Param("name")String name);
	
	/**
	 * 模糊查找网点
	 */
	List<bCompany> findbcompanyByLike(@Param("name")String name);
	/**
	 * 通过网点id找线路
	 * @return
	 */
	List <Pather> findBywdId(Integer wlId);
	
	
	/**
	 * 通过网点(公司)id和出发地找线路
	 */
	
	List <Pather> findBywdIdandsta(@Param("wl_id")Integer wlId,@Param("sta")Integer sta);
	
	
	/**
	 * 通过网点id找网点
	 * @return
	 */
	bCompany findById(Integer id);
	
	/**
	 * 通过网点id找到标记删除的网点
	 */
	bCompany findAbCompById(Integer id);
	
	
	/**
	 * 通过公司id找网点
	 * @return
	 */
	List<bCompany> findBycompanyId(Integer companyId);
	
	/**
	 * 增加网点线路
	 */
	
	Integer addPather(Pather pa);
	
	/**
	 * 批量添加线路
	 */
	
	Integer addPathers(List<Pather> pa);
	
	/**
	 * 删除线路
	 */
	Integer SetdelPather(@Param("id")Integer id,@Param("wl_id")Integer wlId,@Param("qz")long qz,@Param("del_flag")Integer delFlag,@Param("type")Integer type,
			@Param("sta")Integer sta,@Param("end")Integer end,@Param("tui")String tui,@Param("direct")String direct);
	
	/**
	 * 批量删除线路(此方法未使用)
	 */
	Integer setdelPathers(@Param("del_flag")Integer delFlag,@Param("list")List<Pather> ps);
	/**
	 * 查询所有关闭的线路
	 */
	List<Pather> findallcolsePather(@Param("del_flag")Integer delFlag,@Param("wl_id")Integer wlid);
	/**
	 * 查询所有线路
	 * @return
	 */
	List<Pather> findAllPather();
	/**
	 * 分页查询
	 * @return
	 */
	Page<Pather>  findByPage(@Param("wl_id")Integer id,@Param("type")Integer type);
	
	
	/**
	 * 通过线路主键id，找到网点或者公司 
	 * @return
	 */
	Pather findBylineID(Integer lineId);
	/**
	 * 通线路主键id,找到已经标记删除的网点或公司
	 */
	Pather findAdelBylineID(Integer lineId);
	
	/**
	 * 分页模糊查询所有删除线路
	 */
	Page<Pather> findAdelPathers();
	
	/**
	 * 彻底删除线路
	 */
	
	Integer YdeltePatherById(Integer lineID);
	
	
	
	
	
}
