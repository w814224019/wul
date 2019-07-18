package com.select.wuliu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言板实体类
 * @author Admin
 *
 */
public class evaluate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private Integer id;
	//留言人
	private Integer userid;
	//留言时间
	private Date addtime;
	//审核时间
	private Date edittime;
	//审核状态
	private String status;
	//评论内容
	private String content;
	//评论公司或者网点id
	private Integer companyId;
	//审核人
	private String auditor;
	//图片地址
	private String picture;
	//好评 中评 差评 num
	private Integer num;
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
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Date getEdittime() {
		return edittime;
	}
	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
		return "evaluate [id=" + id + ", userid=" + userid + ", addtime=" + addtime + ", edittime=" + edittime
				+ ", status=" + status + ", content=" + content + ", companyId=" + companyId + ", auditor=" + auditor
				+ ", picture=" + picture + ", num=" + num + ", pages=" + pages + ", totals=" + totals + "]";
	}
	
	
	
	
	
	
}
