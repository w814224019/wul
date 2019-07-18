package com.select.wuliu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员实体类
 * @author Admin
 *
 */
public class member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2112546936615546688L;
	//主键id
	private Integer id;
	//公司id
	private Integer companyId;
	//公司名称
	private String  companyName;
	//销售员姓名
	private String userName;
	//开户详情
	private String remarks;
	//会员开通时间
	private Date startTime;
	//会员到期时间
	private Date endTime;
	//发票单据号
	private String InvoiceNumber;
	//是否删除
	private Integer isDel;
	//会员类型，金牌，银牌等
	private String type;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getInvoiceNumber() {
		return InvoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return "member [id=" + id + ", companyId=" + companyId + ", companyName=" + companyName + ", userName="
				+ userName + ", remarks=" + remarks + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", InvoiceNumber=" + InvoiceNumber + ", isDel=" + isDel + ", type=" + type + ", pages=" + pages
				+ ", totals=" + totals + "]";
	}
	
}
