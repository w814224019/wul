package com.select.wuliu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.picturer;

public interface pictureMapper {
	
	/**
	 * 查询所有公司图片
	 * @return
	 */
	List<picturer> findAll();
	
	
	/**
	 * 增加新图片
	 * @param pic
	 * @return
	 */
Integer addNewPicture(picturer pic);
/**
 * 通过公司id找序号
 * @param companyId
 * @return
 */
List<picturer> findById(Integer companyId);
/**
 * 通过公司id找被标记删除的图片
 * @param companyId
 * @return
 */
List<picturer> AdelfindById(Integer companyId);

/**
 * 通过公司companyName查找
 * 
 * @return
 */
Page<picturer> findByName(@Param("name")String companyName);
/**
 * 在公司里面按照类型查找图片列表
 */
Page<picturer> findByLame(@Param("name")String companyName,@Param("companyId")Integer companyId);
/**
 * 通过路径找到图片
 * @param picturePath
 * @return
 */

picturer findBypath(String picturePath);


/**
 * 修改图片
 * @param companyId
 * @param companyName
 * @param picturePath
 * @param sor
 * @param isDel
 * @return
 */



Integer Updatepic(@Param("companyId")Integer companyId,@Param("companyName")String companyName,
		@Param("picturePath")String picturePath,@Param("sor")Integer sor,@Param("types")String types,@Param("isDel")Integer isDel);

}
