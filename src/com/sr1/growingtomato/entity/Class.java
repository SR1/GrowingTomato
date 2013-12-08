package com.sr1.growingtomato.entity;

public class Class {
	int id;
	String name;
	String addDate;
	String endDate;
	boolean isFinished;

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

	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + ", addDate=" + addDate
				+ ", endDate=" + endDate + ", isFinished=" + isFinished + "]";
	}

}
