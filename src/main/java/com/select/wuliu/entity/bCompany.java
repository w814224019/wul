package com.select.wuliu.entity;

import java.io.Serializable;

/**
 * 网点实体类
 * @author Admin
 *
 */
public class bCompany implements Serializable {
	private static final long serialVersionUID = 1L;
	//网点ID
	private Integer id;
	//公司名称
	private String branchName;
	//公司地址ַ
	private String address;
	//公司电话
	private String phone;
	//公司座机
	private String telephone;
	//联系人
	private String content;
	//联系人
    private String contact;
	//公司ID
	private Integer companyId;
	//权重
	private long qz;
	//标记删除，默认为0，1为删除；
	private Integer isDel;
	//经度
    private String longitude;
    //维度
    private String dimensions;
  //网点省份
    private String branchprovince;
  //网点所在市
    private String branchcity;
  //网点所在区
    private String brancharea;
  //网点所属类型
    private Integer branchrelation;
    //企业简介
    private String intro;
    //企业文化
    private String culture;
    //会员等级
    private Integer vipClass;
    //总页数
    private Integer pages;
    //总条数
    private long totals;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public String getBranchprovince() {
		return branchprovince;
	}
	public void setBranchprovince(String branchprovince) {
		this.branchprovince = branchprovince;
	}
	public String getBranchcity() {
		return branchcity;
	}
	public void setBranchcity(String branchcity) {
		this.branchcity = branchcity;
	}
	public String getBrancharea() {
		return brancharea;
	}
	public void setBrancharea(String brancharea) {
		this.brancharea = brancharea;
	}
	public Integer getBranchrelation() {
		return branchrelation;
	}
	public void setBranchrelation(Integer branchrelation) {
		this.branchrelation = branchrelation;
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
		return "bCompany [id=" + id + ", branchName=" + branchName + ", address=" + address + ", phone=" + phone
				+ ", telephone=" + telephone + ", content=" + content + ", contact=" + contact + ", companyId="
				+ companyId + ", qz=" + qz + ", isDel=" + isDel + ", longitude=" + longitude + ", dimensions="
				+ dimensions + ", branchprovince=" + branchprovince + ", branchcity=" + branchcity + ", brancharea="
				+ brancharea + ", branchrelation=" + branchrelation + ", intro=" + intro + ", culture=" + culture
				+ ", vipClass=" + vipClass + ", pages=" + pages + ", totals=" + totals + "]";
	}
	
    
}
