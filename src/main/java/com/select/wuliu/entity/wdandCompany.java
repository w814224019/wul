package com.select.wuliu.entity;

import java.io.Serializable;

/**
 * 专线查找后网点和物流公司的合并实体类显示贴近公司实体类
 * @author Admin
 *
 */
public class wdandCompany implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//网点或者公司ID
private Integer companyId;
//网点或者公司名称
private String companyName;
//网点或者公司图片
private String detailPicture;
//网点或者公司地址
private String address;
//网点或者公司联系人
private String contact;
//网点或者公司联系电话
private String phone;
//区分公司还是网点 公司为1，网点为0
private Integer mark;
//总页码数
private Integer pages;
//总条数
private long totals;
//会员级别
private Integer vipClass;
private String vip;

private String mobilepath;

public Integer getCompanyId() {
	return companyId;
}

public void setCompanyId(Integer companyId) {
	this.companyId = companyId;
}

public String getCompanyName() {
	return companyName;
}

public void setCompanyName(String companyName) {
	this.companyName = companyName;
}

public String getDetailPicture() {
	return detailPicture;
}

public void setDetailPicture(String detailPicture) {
	this.detailPicture = detailPicture;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getContact() {
	return contact;
}

public void setContact(String contact) {
	this.contact = contact;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public Integer getMark() {
	return mark;
}

public void setMark(Integer mark) {
	this.mark = mark;
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

public Integer getVipClass() {
	return vipClass;
}

public void setVipClass(Integer vipClass) {
	this.vipClass = vipClass;
}

public String getVip() {
	return vip;
}

public void setVip(String vip) {
	this.vip = vip;
}

public String getMobilepath() {
	return mobilepath;
}

public void setMobilepath(String mobilepath) {
	this.mobilepath = mobilepath;
}

@Override
public String toString() {
	return "wdandCompany [companyId=" + companyId + ", companyName=" + companyName + ", detailPicture=" + detailPicture
			+ ", address=" + address + ", contact=" + contact + ", phone=" + phone + ", mark=" + mark + ", pages="
			+ pages + ", totals=" + totals + ", vipClass=" + vipClass + ", vip=" + vip + ", mobilepath=" + mobilepath
			+ "]";
}




}
