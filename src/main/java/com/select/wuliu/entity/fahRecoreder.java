package com.select.wuliu.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 发货人发货记录
 * @author Admin
 *
 */
public class fahRecoreder implements Serializable {
	private static final long serialVersionUID = 1L;
	//订单id
	private Integer id;
	//发货人
	private String fhuser;
	//发货人手机号
	private String fhphone;
	//发货地址-省市县
	private String fhaddress;
	//发货地址
	private String fhaddress1;
	//收货人
	private String shuser;
	//收货人电话
	private String shphone;
	//shaddress收货地址-省市县
	private String shaddress;
	//收货地址
	private String shaddress1;
	//货物名称
	private String goodsname;
	//货物数量
	private Integer goodsnum;
	//重量
	private Double weight;
	//体积
	private String volume;
	//预约时间
	private String time;
	//预约车型
	private String chetype;
	//车长
	private String chang;
	//备注
	private String remark;
	//companyId物流公司id
	private Integer companyId;
	//运单号
	private String tracking;
	//下单人
	private Integer userid;
	//状态
	private String status;
	//信息有效期
	private Date timeout;
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
	public String getFhuser() {
		return fhuser;
	}
	public void setFhuser(String fhuser) {
		this.fhuser = fhuser;
	}
	public String getFhphone() {
		return fhphone;
	}
	public void setFhphone(String fhphone) {
		this.fhphone = fhphone;
	}
	public String getFhaddress() {
		return fhaddress;
	}
	public void setFhaddress(String fhaddress) {
		this.fhaddress = fhaddress;
	}
	public String getFhaddress1() {
		return fhaddress1;
	}
	public void setFhaddress1(String fhaddress1) {
		this.fhaddress1 = fhaddress1;
	}
	public String getShuser() {
		return shuser;
	}
	public void setShuser(String shuser) {
		this.shuser = shuser;
	}
	public String getShphone() {
		return shphone;
	}
	public void setShphone(String shphone) {
		this.shphone = shphone;
	}
	public String getShaddress() {
		return shaddress;
	}
	public void setShaddress(String shaddress) {
		this.shaddress = shaddress;
	}
	public String getShaddress1() {
		return shaddress1;
	}
	public void setShaddress1(String shaddress1) {
		this.shaddress1 = shaddress1;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public Integer getGoodsnum() {
		return goodsnum;
	}
	public void setGoodsnum(Integer goodsnum) {
		this.goodsnum = goodsnum;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getChetype() {
		return chetype;
	}
	public void setChetype(String chetype) {
		this.chetype = chetype;
	}
	public String getChang() {
		return chang;
	}
	public void setChang(String chang) {
		this.chang = chang;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTimeout() {
		return timeout;
	}
	public void setTimeout(Date timeout) {
		this.timeout = timeout;
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
		return "fahRecoreder [id=" + id + ", fhuser=" + fhuser + ", fhphone=" + fhphone + ", fhaddress=" + fhaddress
				+ ", fhaddress1=" + fhaddress1 + ", shuser=" + shuser + ", shphone=" + shphone + ", shaddress="
				+ shaddress + ", shaddress1=" + shaddress1 + ", goodsname=" + goodsname + ", goodsnum=" + goodsnum
				+ ", weight=" + weight + ", volume=" + volume + ", time=" + time + ", chetype=" + chetype + ", chang="
				+ chang + ", remark=" + remark + ", companyId=" + companyId + ", tracking=" + tracking + ", userid="
				+ userid + ", status=" + status + ", timeout=" + timeout + ", pages=" + pages + ", totals=" + totals
				+ "]";
	}
	
	
	
}
