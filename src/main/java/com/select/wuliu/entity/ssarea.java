package com.select.wuliu.entity;

import java.util.List;
/**
 * 查询省后出现市涵盖所有内容返回ajax
 * @author Admin
 *
 */
public class ssarea extends BaseEntity {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer areaid;
private String areaname;
private Integer parentId;
private String pinyin;
private List<area>  diqu;
public Integer getAreaid() {
	return areaid;
}
public void setAreaid(Integer areaid) {
	this.areaid = areaid;
}
public String getAreaname() {
	return areaname;
}
public void setAreaname(String areaname) {
	this.areaname = areaname;
}
public Integer getParentId() {
	return parentId;
}
public void setParentId(Integer parentId) {
	this.parentId = parentId;
}
public String getPinyin() {
	return pinyin;
}
public void setPinyin(String pinyin) {
	this.pinyin = pinyin;
}
public List<area> getDiqu() {
	return diqu;
}
public void setDiqu(List<area> diqu) {
	this.diqu = diqu;
}
@Override
public String toString() {
	return "ssarea [areaid=" + areaid + ", areaname=" + areaname + ", parentId=" + parentId + ", pinyin=" + pinyin
			+ ", diqu=" + diqu + "]";
}





}
