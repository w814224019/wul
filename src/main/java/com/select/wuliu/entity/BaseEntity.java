package com.select.wuliu.entity;
/**
 * 日志实体类
 */
import java.io.Serializable;
import java.util.Date;


public class BaseEntity implements Serializable{

private static final long serialVersionUID = 1L;
//创建者
private String mobile;
//修改者
private String modifiedUser;
//修改时间
private Date modifiedTime;
//修改类型
private String type;
//公司名称
private String company;
//标记
private String mark;
//公司id
private Integer id;
//总页数
private Integer pages;
//总条数
private long totals;
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getModifiedUser() {
	return modifiedUser;
}
public void setModifiedUser(String modifiedUser) {
	this.modifiedUser = modifiedUser;
}
public Date getModifiedTime() {
	return modifiedTime;
}
public void setModifiedTime(Date modifiedTime) {
	this.modifiedTime = modifiedTime;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getMark() {
	return mark;
}
public void setMark(String mark) {
	this.mark = mark;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getPages() {
	return pages;
}
public void setPages(Integer pages) {
	this.pages = pages;
}
public long getTotals() {
	return totals;
}
public void setTotals(long totals) {
	this.totals = totals;
}
@Override
public String toString() {
	return "BaseEntity [mobile=" + mobile + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime
			+ ", type=" + type + ", company=" + company + ", mark=" + mark + ", id=" + id + ", pages=" + pages
			+ ", totals=" + totals + "]";
}




}
