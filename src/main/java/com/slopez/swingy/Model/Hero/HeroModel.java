package com.slopez.swingy.Model.Hero;

import com.slopez.swingy.Model.Items.ArmorModel;
import com.slopez.swingy.Model.Items.HelmModel;
import com.slopez.swingy.Model.Items.WeaponModel;

public class HeroModel {

	private String name;
	private String heroClass;
	private int level;
	private int experience;
	
	private int attack;
	private int defense;
	private int hitpoint;

	private HelmModel helm;
	private ArmorModel armor;
	private WeaponModel weapon;

	public HeroModel() {

	}

	public String getName() {
		return this.name;
	}

	public String getHeroClass() {
		return this.heroClass;
	}

	public int getLevel() {
		return this.level;
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

	public void setHitpoint(int hitpoint) {
		this.hitpoint = hitpoint;
	}

	public HelmModel getHelm() {
		return this.helm;
	}

	public ArmorModel getArmor() {
		return this.armor;
	}

	public WeaponModel getWeapon() {
		return this.weapon;
	}

	public void  setHelm(HelmModel helm) {
		this.helm = helm;
	}

	public void  setArmor(ArmorModel armor) {
		this.armor = armor;
	}

	public void  setWeapon(WeaponModel weapon) {
		this.weapon = weapon;
	}
}
