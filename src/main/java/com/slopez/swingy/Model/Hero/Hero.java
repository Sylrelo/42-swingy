package com.slopez.swingy.Model.Hero;

public class Hero {

	private String name;
	private String heroClass;
	private Number level;
	private Number experience;
	
	private Number attack;
	private Number defense;
	private Number hitpoint;

	public Hero() {

	}

	public String getName() {
		return this.name;
	}

	public String getHeroClass() {
		return this.heroClass;
	}

	public Number getLevel() {
		return this.level;
	}

	public Number getExperience() {
		return this.experience;
	}

	public void setExperience(Number experience) {
		this.experience = experience;
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
