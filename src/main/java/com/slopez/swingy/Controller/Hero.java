package com.slopez.swingy.Controller;

import java.util.Set;

import com.slopez.swingy.Vector2;
import com.slopez.swingy.Model.Hero.HeroModel;
import com.slopez.swingy.Model.Items.ArmorModel;
import com.slopez.swingy.Model.Items.HelmModel;
import com.slopez.swingy.Model.Items.WeaponModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Hero {
	private static Validator validator;

	private HeroModel hero;
	private HelmModel helm;
	private ArmorModel armor;
	private WeaponModel weapon;

	public Hero() {
		this.hero = new HeroModel();

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

	public int getLevel() {
		return this.hero.getLevel();
	}

	// private void setPosition(Vector2 position) {
	// 	this.hero.setPosition(position);
	// }

	public void moveBy(Vector2 move) {
		this.hero.addPostion(move);
	}

	public void moveBy(double x, double y) {
		this.hero.addPostion(new Vector2(x, y));
	}

	public void addExperience(int experience) {
		this.hero.setExperience(this.hero.getExperience() + experience);
		
		if (this.hero.getExperience() > this.getExperienceForLevel(this.hero.getLevel() + 1)) {
			this.leveUp();
		}
	}

	public HeroModel getModel() {
		return this.hero;
	}

	public Vector2 getPosition() {
		return this.hero.getPosition();
	}

	private void leveUp() {

		this.hero.increaseLevel(1);
		this.hero.increaseMaxHitPoint(60);
		this.hero.increaseAttack(2);
		this.hero.increaseDefense(4);

		this.hero.setMaxHitPoint();
	}

	public void test() {
		System.out.printf("%d %d | %d %d %d\n", this.hero.getExperience(), this.hero.getLevel(), this.hero.getAttack(), this.hero.getDefense(), this.hero.getMaxHitPoint());
	}

	private <T> Boolean validate(T validatedClass) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

		Set<ConstraintViolation<T>> constraintViolations = validator.validate( validatedClass );

		System.out.println(constraintViolations);

		return constraintViolations.size() > 0;
	}
}
