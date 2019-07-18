package com.select.wuliu.entity;

import java.io.Serializable;

public class userToCorbcompany implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//公司或者网点id
	private Integer companyid;
	//员工id
	private Integer userid;
	//是否绑定，0为绑定，1为解除
	private Integer isDel;
	//是否绑定，0网点，1为公司
		private Integer mark;
		public Integer getCompanyid() {
			return companyid;
		}
		public void setCompanyid(Integer companyid) {
			this.companyid = companyid;
		}
		public Integer getUserid() {
			return userid;
		}
		public void setUserid(Integer userid) {
			this.userid = userid;
		}
		public Integer getIsDel() {
			return isDel;
		}
		public void setIsDel(Integer isDel) {
			this.isDel = isDel;
		}
		public Integer getMark() {
			return mark;
		}
		public void setMark(Integer mark) {
			this.mark = mark;
		}
		@Override
		public String toString() {
			return "userToCorbcompany [companyid=" + companyid + ", userid=" + userid + ", isDel=" + isDel + ", mark="
					+ mark + "]";
		}
	
	


}
