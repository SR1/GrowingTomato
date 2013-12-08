package com.sr1.growingtomato.entity;

public class Task {

	int id;
	String name;
	String addDate;
	String endDate;
	boolean isFinished;
	int classId;
	String Reward;

	public int getId() {
		return id;
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
		return Reward;
	}

	public void setReward(String reward) {
		Reward = reward;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", addDate=" + addDate
				+ ", endDate=" + endDate + ", isFinished=" + isFinished
				+ ", classId=" + classId + ", Reward=" + Reward + "]";
	}

}
