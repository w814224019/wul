package com.select.wuliu.entity;
/**
 * 把计划表中的数字换成汉字显示
 * @author Admin
 *
 */
public class sunTask extends BaseEntity {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//序号id
	private Integer xid;
	//物流或公司id
	private Integer id;
	//物流币
	private Integer money;
	public Integer getXid() {
		return xid;
	}
	public void setXid(Integer xid) {
		this.xid = xid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "sunTask [xid=" + xid + ", id=" + id + ", money=" + money + "]";
	}
	
}
