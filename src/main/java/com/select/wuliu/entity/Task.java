package com.select.wuliu.entity;
/**
 * 自动刷新计划表实体类
 * @author Admin
 *
 */
public class Task extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	//公司或者网点id
	private Integer lineId;
	//时间点分钟
	private String time;
	//状态
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", lineId=" + lineId + ", time=" + time + ", status=" + status + "]";
	}
	
	


}
