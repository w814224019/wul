package com.select.wuliu.entity;

import java.io.Serializable;

public class carLine implements Serializable{
	     /**
	      * 车辆线路实体类
	      */
	private static final long serialVersionUID = 1L;
		//主键id
		private Integer id;
		//起始地址
		private String sta;
		//送达地址
		private String end;
		//车辆id
		private Integer carId;
		//权重，影响排序',
		private String qz;
		//DEFAULT '0' COMMENT '是否废除，0为否，1为废除'
		private Integer isDel;
		//1为网点 2为物流公司
		private String type;
		//1推广，
		private String tui;
		//直达 中转
		private String direct;
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
		public Integer getCarId() {
			return carId;
		}
		public void setCarId(Integer carId) {
			this.carId = carId;
		}
		public String getQz() {
			return qz;
		}
		public void setQz(String qz) {
			this.qz = qz;
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
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "carLine [id=" + id + ", sta=" + sta + ", end=" + end + ", carId=" + carId + ", qz=" + qz
					+ ", isDel=" + isDel + ", type=" + type + ", tui=" + tui + ", direct=" + direct + ", pages=" + pages
					+ ", totals=" + totals + "]";
		}
		
}
