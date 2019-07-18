package com.select.wuliu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.userToCorbcompany;
import com.select.wuliu.service.exeception.areaNoFoundExeception;
@Service
public interface ICompanyService {
	/**
	 * 增加公司
	 * @param company
	 * @return
	 */
Integer SaveCompany(Companier company);
/**
 * 彻底删除公司
 */
Integer YDelCompany(Integer companyId);
/**
 * 分页模糊查询标记删除的公司
 */
Page<Companier> GetdeleteCompanys(Integer pageNum,Integer pageSize,String name);

/**
 * 绑定销售与公司
 * @param utoc
 * @return
 */
Integer SaveusettoCompany(userToCorbcompany utoc);
/**
 * 解除绑定
 */
Integer updatusertoCorbpany(userToCorbcompany utoc,Integer companyid);
/**
 * 根据公司或者网点id找到绑定对象
 */
userToCorbcompany getusertobOrcompanyByid(Integer companyid,Integer mark);
/**
 * 根据销售id分页查询公司或者网点
 */
Page<userToCorbcompany> getusertoborCompanysPByid(Integer pageNum,Integer pageSize,Integer rid);
/**
 * 修改或更新公司资料
 * @param company
 * @param companyId
 * @return
 */
Integer UpdateCompany(Companier company,Integer companyId);
/**
 * 增加线路
 * @param pather
 * @return
 */
Integer SavePather(Pather pather);
/**
 * 查询公司所有信息
 * @return
 */
List<Companier> findAllCompany();
/**
 * 根据公司ID找公司
 * @param companyId
 * @return
 */
Companier getById(Integer companyId);
/**
 * 根据公司id 标记删除的公司
 */
Companier getAdelById(Integer companyId);


/**
 * 根据出发和到达找线路
 * @param sta
 * @param end
 * @return
 */
List<Pather> getByft(Integer sta,Integer end);
/**
 * 分页查询线路
 */
Page<Pather> GetBystaEnd(Integer sta,Integer end,Integer pageNum,Integer pageSize);
/**
 * 根据单一类型 出发地到达地查询公司信息
 */
List<Companier> GetBystaAndend(List<Integer> wlId);

List<Integer> GetBystaAd(@Param("sta")Integer sta,@Param("end")Integer end,@Param("type")Integer type);

/**
 * 根据地区名找号
 * @param areaname
 * @return
 * @throws areaNoFoundExeception
 */
area findByEname(String areaname) throws areaNoFoundExeception;


/**
 * 根据公司名称找公司
 * @param companyName
 * @return
 */
List<Companier> getByName(String companyName);
/**
 * 根据公司名称模糊查找
 * @param name
 * @return
 */
List<Companier> getByLName(String name);
/**
 * 根据地区模糊查找公司
 * @param address
 * @return
 */
List<Companier> getByLadderss(String address);
/**
 * 模糊分页查询公司
 */
Page<Companier> getCompanyPByName(String companyName,Integer pageSize,Integer pageNum); 
}
