package com.slopez.swingy.Model.Hero;

public class HeroModel {

	private String name;
	private String heroClass;

	private int level;
	private int experience;
	
	private int attack;
	private int defense;
	private int hitpoint;
	private int maxHitPoint;

	public HeroModel() {}

	public String getName() {
		return this.name;
	}

	public String getHeroClass() {
		return this.heroClass;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		 this.level = level;
	}

	public int getExperience() {
		return this.experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
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

	public int getMaxHitPoint() {
		return this.maxHitPoint;
	}

	public void setHitpoint(int hitpoint) {
		this.hitpoint = hitpoint;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setMaxHitpoint(int maxHitPoint) {
		this.maxHitPoint = maxHitPoint;
	}

}
