package com.select.wuliu.entity;

import java.io.Serializable;

/**
 * 物流公司实体类
 * @author Admin
 *
 */
public class Companier implements Serializable {

private static final long serialVersionUID = 1L;
//公司ID
private Integer companyId;
//公司名称
private String companyName;
//公司地址ַ
private String address;
//公司电话
private String phone;
//公司座机
private String telephone;
//联系人
private String contact;
//图片地址ַ
private  String picturePath;
//别名
private String alias;
//出发地
private String depart;
//
private String directRemark;
//
private String transitRemark;
//图片地址
private String detailPicture;
//权重
private long qz;
//标记删除，默认为0，1为删除；
private Integer isDel;
//是否认证 默认为0未认证，1为已认证；
private Integer att;
//公司简介 intro
private String intro;
//企业文化 culture
private String culture;
//会员等级
private Integer vipClass;
//所在省
private String province;
//所在市
private String city;
//1,物流公司，2，配货信息部，3，发货企业 
private Integer type;
//经度
private String longitude;
//维度
private String dimensions;
//运价，轻货价
private String lightCost;
//重货价
private String heavyCost;
//运营时效
private String aging;
//运营方式：例整车零担
private String style;
//总页数
private Integer pages;
//总条数
private long totals;
//
private static final String mobilepath="http://m.56114.com/i.html";
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getPicturePath() {
	return picturePath;
}
public void setPicturePath(String picturePath) {
	this.picturePath = picturePath;
}
public String getAlias() {
	return alias;
}
public void setAlias(String alias) {
	this.alias = alias;
}
public String getDepart() {
	return depart;
}
public void setDepart(String depart) {
	this.depart = depart;
}
public String getDirectRemark() {
	return directRemark;
}
public void setDirectRemark(String directRemark) {
	this.directRemark = directRemark;
}
public String getTransitRemark() {
	return transitRemark;
}
public void setTransitRemark(String transitRemark) {
	this.transitRemark = transitRemark;
}
public String getDetailPicture() {
	return detailPicture;
}
public void setDetailPicture(String detailPicture) {
	this.detailPicture = detailPicture;
}
public long getQz() {
	return qz;
}
public void setQz(long qz) {
	this.qz = qz;
}
public Integer getIsDel() {
	return isDel;
}
public void setIsDel(Integer isDel) {
	this.isDel = isDel;
}
public Integer getAtt() {
	return att;
}
public void setAtt(Integer att) {
	this.att = att;
}
public String getIntro() {
	return intro;
}
public void setIntro(String intro) {
	this.intro = intro;
}
public String getCulture() {
	return culture;
}
public void setCulture(String culture) {
	this.culture = culture;
}
public Integer getVipClass() {
	return vipClass;
}
public void setVipClass(Integer vipClass) {
	this.vipClass = vipClass;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public Integer getType() {
	return type;
}
public void setType(Integer type) {
	this.type = type;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public String getDimensions() {
	return dimensions;
}
public void setDimensions(String dimensions) {
	this.dimensions = dimensions;
}
public String getLightCost() {
	return lightCost;
}
public void setLightCost(String lightCost) {
	this.lightCost = lightCost;
}
public String getHeavyCost() {
	return heavyCost;
}
public void setHeavyCost(String heavyCost) {
	this.heavyCost = heavyCost;
}
public String getAging() {
	return aging;
}
public void setAging(String aging) {
	this.aging = aging;
}
public String getStyle() {
	return style;
}
public void setStyle(String style) {
	this.style = style;
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
public static String getMobilepath() {
	return mobilepath;
}
@Override
public String toString() {
	return "Companier [companyId=" + companyId + ", companyName=" + companyName + ", address=" + address + ", phone="
			+ phone + ", telephone=" + telephone + ", contact=" + contact + ", picturePath=" + picturePath + ", alias="
			+ alias + ", depart=" + depart + ", directRemark=" + directRemark + ", transitRemark=" + transitRemark
			+ ", detailPicture=" + detailPicture + ", qz=" + qz + ", isDel=" + isDel + ", att=" + att + ", intro="
			+ intro + ", culture=" + culture + ", vipClass=" + vipClass + ", province=" + province + ", city=" + city
			+ ", type=" + type + ", longitude=" + longitude + ", dimensions=" + dimensions + ", lightCost=" + lightCost
			+ ", heavyCost=" + heavyCost + ", aging=" + aging + ", style=" + style + ", pages=" + pages + ", totals="
			+ totals + "]";
}



	
}
