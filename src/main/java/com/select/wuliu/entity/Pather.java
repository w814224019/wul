package com.select.wuliu.entity;

import java.io.Serializable;

/**
 * 线路实体类
 * @author Admin
 *
 */
public class Pather  implements Serializable {


	private static final long serialVersionUID = 1L;
	//主键id
	private Integer id;
	//起始地址
	private Integer sta;
	//送达地址
	private Integer end;
	//wl_id 物流公司id
	private Integer wlId;
	//权重，影响排序',
	private long qz;
	//DEFAULT '0' COMMENT '是否废除，0为否，2为废除'
	private Integer delFlag;
	//1为网点 2为物流公司
	private Integer type;
	//1推广，
	private String tui;
	//直达 中转
	private String direct;
	//总页码数
	private Integer pages;
	//总条数
	private long totals;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSta() {
		return sta;
	}
	public void setSta(Integer sta) {
		this.sta = sta;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getWlId() {
		return wlId;
	}
	public void setWlId(Integer wlId) {
		this.wlId = wlId;
	}
	public long getQz() {
		return qz;
	}
	public void setQz(long qz) {
		this.qz = qz;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTui() {
		return tui;
	}
	public void setTui(String tui) {
		this.tui = tui;
	}
	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
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
		return "Pather [id=" + id + ", sta=" + sta + ", end=" + end + ", wlId=" + wlId + ", qz=" + qz + ", delFlag="
				+ delFlag + ", type=" + type + ", tui=" + tui + ", direct=" + direct + ", pages=" + pages + ", totals="
				+ totals + "]";
	}
	
	
}
