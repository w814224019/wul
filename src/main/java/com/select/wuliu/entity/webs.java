package com.select.wuliu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 个人收藏实体类
 * @author Admin
 *
 */
public class webs implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Override
public String toString() {
	return "webs [id=" + id + ", rid=" + rid + ", webName=" + webName + ", webAddress=" + webAddress + ", type=" + type
			+ ", createTime=" + createTime + "]";
}
//主键	
private Integer id;			
//用户id
private Integer rid;
//网站名称
private String webName;
//网站地址
private String webAddress;
//网站类型
private String type;
//创建时间
private Date createTime;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getRid() {
	return rid;
}
public void setRid(Integer rid) {
	this.rid = rid;
}
public String getWebName() {
	return webName;
}
public void setWebName(String webName) {
	this.webName = webName;
}
public String getWebAddress() {
	return webAddress;
}
public void setWebAddress(String webAddress) {
	this.webAddress = webAddress;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}



}
