package com.select.wuliu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;

public interface CompanyMapper {
	/**
	 * 公司注册
	 * @param company
	 * @return
	 */
Integer addNewCompany(Companier company);

/**
 * 更新修改公司资料
 * @return
 */
Integer updateCompany(@Param("companyName")String companyName,@Param("address")String address,
		@Param("phone")String phone,@Param("picturePath")String picturePath,
		@Param("telephone")String telephone,@Param("contact")String contact,
		@Param("directRemark")String directRemark,@Param("transitRemark")String transitRemark,
		@Param("depart")String depart,@Param("alias")String alias,
		@Param("detailPicture")String detailPicture,@Param("qz")long qz,
		@Param("intro")String intro,@Param("culture")String culture,
		@Param("vipClass")Integer vipClass,@Param("isDel")Integer isDel,@Param("att")Integer att,@Param("companyId")Integer companyId,
		@Param("province")String province,@Param("city")String city,@Param("type")Integer type,
		@Param("longitude")String longitude,@Param("dimensions")String dimensions,
		@Param("lightCost")String lightCost,@Param("heavyCost")String heavyCost,
		@Param("aging")String aging,@Param("style")String style
		);



/**
 * 增加线路
 * @param pather
 * @return
 */
Integer addNewPath(Pather pather);
/**
 * 根据线路找公司
 * @param pather
 * @return
 */
List<Pather> findByft(@Param("sta")Integer sta,@Param("end")Integer end);
/**
 * 分页查询线路
 */
Page<Pather> findBystaEnd(@Param("sta")Integer sta,@Param("end")Integer end);
/**
 * 根据出发地到达地查询公司信息
 */
List<Companier> findBystaAndend(List<Integer> wlId);

List<Integer> findBystaAd(@Param("sta")Integer sta,@Param("end")Integer end,@Param("type")Integer type);

/**
 * 查询所有公司
 * @return
 */
List<Companier> findAll();
/**
 * 通过id查找公司
 * @return
 */
Companier findById(Integer companyId);
/**
 * 通过id查找标记删除的公司
 */
Companier findAdelById(Integer companyId);

/**
 * 通过公司名称查找公司
 * @return
 */
List<Companier> findByName(String companyName);

/**
 * 模糊查询SELECT * FROM `company1` WHERE companyName LIKE "%公司%";
 */

List<Companier> findByLName(@Param("name")String Name);
/**
 * 根据地区模糊查询
 * @param address
 * @return
 */
List<Companier> findByLaddress(@Param("depart")String depart);
/**
 * 模糊分页查询公司
 */
Page<Companier> findCompanyPByLName(@Param("name")String Name);

/**
 * 模糊分页查询删除的公司
 */

Page<Companier> findDelCompanyPByLName(@Param("name")String Name);
/**
 * 彻底删除公司信息
 */
Integer deleteCompanyById(Integer CompanyId);
}
