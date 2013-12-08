package com.sr1.growingtomato.entity;

public class Reward {

	int position;
	String name;
	Double chance;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getChance() {
		return chance;
	}

	public void setChance(Double chance) {
		this.chance = chance;
	}

	@Override
	public String toString() {
		return "Reward [name=" + name + ", chance=" + chance + "]";
	}

}
