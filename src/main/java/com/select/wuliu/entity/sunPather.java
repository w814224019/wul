package com.select.wuliu.entity;
/**
 * 把线路表中的数字换成汉字显示表中
 * @author Admin
 *
 */
public class sunPather extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//主键id
	private Integer id;
	//wl_id 物流网点id
	private Integer wlId;
	//起始地址
	private String sta;
	//送达地址
	private String end;
	//wl_id 物流/网点公司名称
	private String  wname;
	//权重，影响排序',
	private long qz;
	//DEFAULT '0' COMMENT '是否废除，0为否，1为废除'
	private Integer delFlag;
	//增加其他功能 把物流币放进去
	private Integer money;
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
	public Integer getWlId() {
		return wlId;
	}
	public void setWlId(Integer wlId) {
		this.wlId = wlId;
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
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
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
		return "sunPather [id=" + id + ", wlId=" + wlId + ", sta=" + sta + ", end=" + end + ", wname=" + wname + ", qz="
				+ qz + ", delFlag=" + delFlag + ", money=" + money + ", tui=" + tui + ", direct=" + direct + ", pages="
				+ pages + ", totals=" + totals + "]";
	}
	
}
