package com.select.wuliu.entity;

import java.io.Serializable;

public class arealike implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String sheng;
  private String shi;
  private String qu;
public String getSheng() {
	return sheng;
}
public void setSheng(String sheng) {
	this.sheng = sheng;
}
public String getShi() {
	return shi;
}
public void setShi(String shi) {
	this.shi = shi;
}
public String getQu() {
	return qu;
}
public void setQu(String qu) {
	this.qu = qu;
}
@Override
public String toString() {
	return "arealike [sheng=" + sheng + ", shi=" + shi + ", qu=" + qu + "]";
}
  
  
}
