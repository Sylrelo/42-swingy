package com.slopez.swingy.Model;

public class FoeModel {

	private String name;
	private int level;
	private int attack;
	private int defense;
	private int hitpoint;

	public FoeModel() {
		this.name = "Random name";
		this.level = 1;
	}

	public String getName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getDefense() {
		return this.defense;
	}


	public int getHitpoint() {
		return this.hitpoint;
	}

	public void setHitpoint(int hitpoint) {
		this.hitpoint = hitpoint;
	}
}