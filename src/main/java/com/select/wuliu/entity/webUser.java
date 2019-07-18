package com.select.wuliu.entity;

import java.io.Serializable;
import java.util.Date;

public class webUser implements Serializable{

	private static final long serialVersionUID = 1L;
	//序号
	private Integer rid;
	//用户名
	private String name;
	//用户id
	private String uid;
	//开户人id
	private String khrId;
	//companyid 公司id
	private Integer companyid;
	//branchid  网点 id
	private Integer branchid;
	//电话
	private String mobile;
	//角色权限
	private String permissionid;
	//公司名
	private String company;
	//密码
	private String password;
	//微信识别码
	private String weixinUnionid;
	//省
	private String province;
	//市
	private String city;
	//地址
	private String address;
	//用户状态 0 为正常     1为废除
	private Integer delFlag;
	//头像地址
	private String headimgurl;
	//性别 0 女 1男
	private String sex;
	//区域网点id 逗号分隔
	private String areabranchid;
	//用户通行证
	private String token;
	//用户过期时间
	private Date timeOut;
	//类别；1物流公司,2车主，3发货企业或个人,4配货信
	private Integer type;
	//把车主货主信息存入字符串
	private String chhuo;
	//展示手机号
	private String telephone;
	//注册时间
	private Date addTime;
	//总页数
	private Integer pages;
	//总条数
	private long totals;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getKhrId() {
		return khrId;
	}
	public void setKhrId(String khrId) {
		this.khrId = khrId;
	}
	public Integer getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public Integer getBranchid() {
		return branchid;
	}
	public void setBranchid(Integer branchid) {
		this.branchid = branchid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWeixinUnionid() {
		return weixinUnionid;
	}
	public void setWeixinUnionid(String weixinUnionid) {
		this.weixinUnionid = weixinUnionid;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAreabranchid() {
		return areabranchid;
	}
	public void setAreabranchid(String areabranchid) {
		this.areabranchid = areabranchid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getChhuo() {
		return chhuo;
	}
	public void setChhuo(String chhuo) {
		this.chhuo = chhuo;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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
		return "webUser [rid=" + rid + ", name=" + name + ", uid=" + uid + ", khrId=" + khrId + ", companyid="
				+ companyid + ", branchid=" + branchid + ", mobile=" + mobile + ", permissionid=" + permissionid
				+ ", company=" + company + ", password=" + password + ", weixinUnionid=" + weixinUnionid + ", province="
				+ province + ", city=" + city + ", address=" + address + ", delFlag=" + delFlag + ", headimgurl="
				+ headimgurl + ", sex=" + sex + ", areabranchid=" + areabranchid + ", token=" + token + ", timeOut="
				+ timeOut + ", type=" + type + ", chhuo=" + chhuo + ", telephone=" + telephone + ", addTime=" + addTime
				+ ", pages=" + pages + ", totals=" + totals + "]";
	}
	

}
