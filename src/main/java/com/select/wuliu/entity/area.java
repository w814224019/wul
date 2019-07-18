package com.select.wuliu.entity;
/**
 * 区号类
 * @author Admin
 *
 */
public class area extends BaseEntity {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer areaid;
//父级地名
private String fareaname;
private String areaname;
private Integer parentId;
private String pinyin;
//别名
private String alias;
public Integer getAreaid() {
	return areaid;
}
public void setAreaid(Integer areaid) {
	this.areaid = areaid;
}
public String getFareaname() {
	return fareaname;
}
public void setFareaname(String fareaname) {
	this.fareaname = fareaname;
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
public String getAlias() {
	return alias;
}
public void setAlias(String alias) {
	this.alias = alias;
}
@Override
public String toString() {
	return "area [areaid=" + areaid + ", fareaname=" + fareaname + ", areaname=" + areaname + ", parentId=" + parentId
			+ ", pinyin=" + pinyin + ", alias=" + alias + "]";
}




}
