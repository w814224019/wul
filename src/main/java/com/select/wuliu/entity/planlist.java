package com.select.wuliu.entity;
/**
 * 存放开启自动刷新后计划表
 * @author Admin
 *
 */
public class planlist extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//序号
	private Integer id;
	//定时网点或者公司序号
	private Integer linedId;
	//定时时间
	private String time;
	//0等待，1已经刷新
	private String status;
	//起始地址
	private String sta;
	//送达地址
	private String end;
	//wl_id 物流/网点公司名称
	private String  wname;
	//总数
	private Integer pages;
	//页数
	private long totals;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLinedId() {
		return linedId;
	}
	public void setLinedId(Integer linedId) {
		this.linedId = linedId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
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
		return "planlist [id=" + id + ", linedId=" + linedId + ", time=" + time + ", status=" + status + ", sta=" + sta
				+ ", end=" + end + ", wname=" + wname + ", pages=" + pages + ", totals=" + totals + "]";
	}

	





}
