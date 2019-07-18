package com.select.wuliu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.select.wuliu.entity.carLine;

/**
 * 车辆线路
 * @author Admin
 *
 */
public interface carLineMapper {
/**
 * 根据车辆id查询所有线路
 */
List<carLine> findcarLIneBycarid(Integer carid);


/**
 * 根据车辆线路主键删除线路
 */
Integer UpdatecarLIneByid(@Param("id")Integer id,@Param("isDel")Integer isDel);
}
