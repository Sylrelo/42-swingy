package com.slopez.swingy.Model;

public class Foe {

	private String name;
	private Number level;
	private Number attack;
	private Number defense;
	private Number hitpoint;

	public Foe() {
		this.name = "Random name";
		this.level = 1;
	}

	public String getName() {
		return this.name;
	}

	public Number getLevel() {
		return this.level;
	}

	public Number getAttack() {
		return this.attack;
	}

	public Number getDefense() {
		return this.defense;
	}


	public Number getHitpoint() {
		return this.hitpoint;
	}

	public void setHitpoint(Number hitpoint) {
		this.hitpoint = hitpoint;
	}
}