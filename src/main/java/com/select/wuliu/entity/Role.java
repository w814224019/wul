package com.select.wuliu.entity;

public class Role extends BaseEntity {
/**
	 * 角色实体类
	 */
	private static final long serialVersionUID = 1L;
	//用户rid
private Integer rid;
//角色
private String roleName;
//用户名
private String userName;
public Integer getRid() {
	return rid;
}
public void setRid(Integer rid) {
	this.rid = rid;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
@Override
public String toString() {
	return "Role [rid=" + rid + ", roleName=" + roleName + ", userName=" + userName + "]";
}


}
