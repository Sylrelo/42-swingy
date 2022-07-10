package com.slopez.swingy.Controller;

import com.slopez.swingy.Model.MapModel;
import com.slopez.swingy.Model.Hero.HeroModel;
import com.slopez.swingy.Model.Items.ArmorModel;
import com.slopez.swingy.Model.Items.HelmModel;
import com.slopez.swingy.Model.Items.WeaponModel;

public class Hero {
	private HeroModel hero;
	private HelmModel helm;
	private ArmorModel armor;
	private WeaponModel weapon;

	private int[] position;

	public Hero() {
		this.hero = new HeroModel();

		this.hero.setLevel(1);
		this.hero.setHitpoint(100);
		this.hero.setMaxHitpoint(100);
		this.hero.setAttack(10);
		this.hero.setDefense(10);

		this.position = new int[2];
		this.position[0] = MapModel.MAP_SIZE / 2;
		this.position[1] = MapModel.MAP_SIZE / 2;

		this.helm = new HelmModel("The face of a newbie", 0);
		this.armor = new ArmorModel("The body of a weakling", 0);
		this.weapon = new WeaponModel("The hands of a weirdo", 0);
	}

	public int getAttackDamage() {
		return this.hero.getAttack() + this.weapon.getModifier();
	}

	public int getDefense() {
		return this.hero.getDefense() + this.armor.getModifier();
	}

	public int getHitPoints() {
		return this.hero.getHitpoint() + this.helm.getModifier();
	}

	private int getExperienceForLevel(int level) {
		return (level * 1000 + ((level - 1) * (level - 1)) * 450);
	}

	public void test() {
		System.out.printf("%d %d | %d %d %d\n", this.hero.getExperience(), this.hero.getLevel(), this.hero.getAttack(), this.hero.getDefense(), this.hero.getMaxHitPoint());
	}

	public void addExperience(int experience) {
		this.hero.setExperience(this.hero.getExperience() + experience);
		
		if (this.hero.getExperience() > this.getExperienceForLevel(this.hero.getLevel() + 1)) {
			this.leveUp();
		}
	}

	private void leveUp() {
		this.hero.setLevel(this.hero.getLevel() + 1);
		this.hero.setMaxHitpoint(this.hero.getMaxHitPoint() + 60);
		this.hero.setHitpoint(this.hero.getMaxHitPoint());
		this.hero.setAttack(this.hero.getAttack() + 2);
		this.hero.setDefense(this.hero.getDefense() + 4);
	}
}
