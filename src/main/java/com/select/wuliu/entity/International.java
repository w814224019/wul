package com.select.wuliu.entity;
/**
 * 国际线路实体
 * @author Admin
 *
 */
public class International{


	private static final long serialVersionUID = 1L;
	//主键 国际公司id
	private Integer id;
	// 国际物流公司名
	private String companyName;
	//联系人
	private String content;
	//联系电话
	private String phone;
	//传真
	private String chuanzhen;
	//地址
	private String companyAddress;
	//邮编
	private String youbian;
	//主页
	private String zhuye;
	//是否删除
	private Integer isDel;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getChuanzhen() {
		return chuanzhen;
	}
	public void setChuanzhen(String chuanzhen) {
		this.chuanzhen = chuanzhen;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getYoubian() {
		return youbian;
	}
	public void setYoubian(String youbian) {
		this.youbian = youbian;
	}
	public String getZhuye() {
		return zhuye;
	}
	public void setZhuye(String zhuye) {
		this.zhuye = zhuye;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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
		return "International [id=" + id + ", companyName=" + companyName + ", content=" + content + ", phone=" + phone
				+ ", chuanzhen=" + chuanzhen + ", companyAddress=" + companyAddress + ", youbian=" + youbian
				+ ", zhuye=" + zhuye + ", isDel=" + isDel + ", pages=" + pages + ", totals=" + totals + "]";
	}
	
	

}
