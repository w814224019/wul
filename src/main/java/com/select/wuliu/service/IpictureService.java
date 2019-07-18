package com.select.wuliu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.picturer;
@Service
public interface IpictureService {
	/**
	 * 添加图片
	 * @param p
	 * @return
	 */
Integer addPicture(picturer p);
/**
 * 通过公司id找图片列表
 * @param companyId
 * @return
 */
List<picturer> getBycompanyId(Integer companyId);
/**
 * 通过公司id找被标记删除的图片
 * @param companyId
 * @return
 */
List<picturer> getAdelfindById(Integer companyId);

/**
 * 通过关键名称找图片列表
 * @param companyName
 * @return
 */
Page<picturer> getBycompanyName(Integer pageSize,Integer pageNum,String companyName);
/**
 * 在公司里面按照类型查找图片列表
 */
Page<picturer> getByLameIncompay(Integer pageSize,Integer pageNum,String companyName,Integer companyId);
/**
 * 获取所有图片信息
 * @return
 */
List<picturer> getAll();
/**
 * 通过图片路径找图片信息
 * @param picturePath
 * @return
 */
picturer getByPath(String picturePath);
/**
 * 更新图片
 * @param p
 * @param picturePath
 * @return
 */
Integer Updatepicture(picturer p,String picturePath);
}
