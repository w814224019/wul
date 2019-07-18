package com.select.wuliu.entity;

import java.io.Serializable;

/**
 * 后台用户实体类
 * @author Admin
 *
 */


public class User implements Serializable{
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
	@Override
	public String toString() {
		return "User [rid=" + rid + ", name=" + name + ", uid=" + uid + ", khrId=" + khrId + ", companyid=" + companyid
				+ ", branchid=" + branchid + ", mobile=" + mobile + ", permissionid=" + permissionid + ", company="
				+ company + ", password=" + password + ", weixinUnionid=" + weixinUnionid + ", province=" + province
				+ ", city=" + city + ", address=" + address + ", del_flag=" + delFlag + ", headimgurl=" + headimgurl
				+ ", sex=" + sex + ", areabranchid=" + areabranchid + "]";
	}

}
