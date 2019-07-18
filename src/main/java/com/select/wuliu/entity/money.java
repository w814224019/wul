package com.select.wuliu.entity;

import java.io.Serializable;

/**
 * 物流币实体类
 * @author Admin
 *
 */
public class money implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private Integer id;
	//公司或者网点ID
	private Integer companyId;
	//类型
	private Integer type;
	//物流币
	private Integer balance;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMoney() {
		return balance;
	}
	public void setMoney(Integer money) {
		this.balance = money;
	}
	@Override
	public String toString() {
		return "money [id=" + id + ", companyId=" + companyId + ", type=" + type + ", money=" + balance + "]";
	}




}
