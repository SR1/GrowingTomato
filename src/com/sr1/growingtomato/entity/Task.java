package com.sr1.growingtomato.entity;

import java.io.Serializable;

public class Task implements Serializable{

	private static final long serialVersionUID = -7453662248653278513L;
	int id;
	String name;
	String remark;
	String addDate;
	String endDate;
	boolean isFinished;
	int classId;
	String reward;

	public int getId() {
		return id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", addDate=" + addDate
				+ ", endDate=" + endDate + ", isFinished=" + isFinished
				+ ", classId=" + classId + ", reward=" + reward + "]";
	}

}
