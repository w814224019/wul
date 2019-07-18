package com.select.wuliu.entity;

import java.io.Serializable;

/**
 * 图片实体类
 * @author Admin
 *
 */
public class picturer implements Serializable{

	
  /**
	 * 
	 */
	private static final long serialVersionUID = 9173075839548269896L;
//公司或网店id
	private Integer companyId;
	//公司名称
	private String companyName;
	//图片地址
	private String picturePath;
	//排序 初次设为1，封面设为1，以后都为0
	private Integer sor;
	//是否删除
	private  Integer isDel;
	//图片类型，公司图片，认证图片，二维码等
	private String types;
	//总页数
	private Integer pages;
	//总条数
	private long totals;
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
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public Integer getSor() {
		return sor;
	}
	public void setSor(Integer sor) {
		this.sor = sor;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
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
		return "picturer [companyId=" + companyId + ", companyName=" + companyName + ", picturePath=" + picturePath
				+ ", sor=" + sor + ", isDel=" + isDel + ", types=" + types + ", pages=" + pages + ", totals=" + totals
				+ "]";
	}
	
	
}
