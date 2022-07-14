package com.slopez.swingy.Controller;

import java.util.Set;

import com.slopez.swingy.Vector2;
import com.slopez.swingy.Model.Hero.HeroModel;
import com.slopez.swingy.Model.Items.ArmorModel;
import com.slopez.swingy.Model.Items.HelmModel;
import com.slopez.swingy.Model.Items.ItemModel;
import com.slopez.swingy.Model.Items.WeaponModel;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Hero {
	private static Validator validator;

	private HeroModel hero;

	@Getter
	private HelmModel helm;
	@Getter
	private ArmorModel armor;
	@Getter
	private WeaponModel weapon;

	public Hero() {
		this.hero = new HeroModel();

		this.helm = new HelmModel("The face of a newbie", 0);
		this.armor = new ArmorModel("The body of a weakling", 0);
		this.weapon = new WeaponModel("The hands of a weirdo", 0);
	}

	public void equipItem(ItemModel item) {
		if (item instanceof ArmorModel) {
			this.armor = (ArmorModel) item;
		} else if (item instanceof HelmModel) {
			this.helm = (HelmModel) item;
		} else if (item instanceof WeaponModel) {
			this.weapon = (WeaponModel) item;
		}
	}

	public int getAttackDamage() {
		return this.hero.getAttack() + this.weapon.getModifier();
	}

	public int getDefense() {
		return this.hero.getDefense() + this.armor.getModifier();
	}

	public int getHitPoints() {
		return this.hero.getHitpoint();
	}

	public int getMaxHitPoints() {
		return this.hero.getMaxHitPoint() + this.helm.getModifier();
	}

	public int getLevel() {
		return this.hero.getLevel();
	}

	public HeroModel getModel() {
		return this.hero;
	}

	public Vector2 getPosition() {
		return this.hero.getPosition();
	}

	public void moveBy(Vector2 move) {
		this.hero.addPostion(move);
	}

	public void moveBy(double x, double y) {
		this.hero.addPostion(new Vector2(x, y));
	}

	/**
	 * @return Return TRUE if player has leveled up, false otherwise.
	 */
	public boolean addExperience(int experience) {
		this.hero.setExperience(this.hero.getExperience() + experience);

		if (this.hero.getExperience() > Hero.getExperienceForLevel(this.hero.getLevel())) {
			this.leveUp();
			return true;
		}

		return false;
	}

	public int receiveDamage(int foeLevel, int foeDamage) {
		int damageIncrease = (int) (((foeLevel - this.getLevel()) / 13.37) * foeDamage);

		double armorReduction = (double) ((double) hero.getDefense()
				/ (100.0 + (100.0 * ((double) hero.getLevel() * 0.12))));

		int damages = (int) ((double) (foeDamage + damageIncrease));
		int reducedDamages = (int) Math.max(damages * 0.2, (damages - (damages * (armorReduction))));

		this.hero.decreaseHitPoint(reducedDamages);

		// System.out.printf("==> %f\n", Math.log(hero.getLevel()) * 0.80);

		return (reducedDamages);
	}

	/**
	 * @return Healed value
	 */
	public int healPercentage(int percentage) {
		int healValue = (int) (this.hero.getMaxHitPoint() * (1.0 / percentage));
		this.hero.increaseHitPoint(healValue);
		return healValue;
	}

	private void leveUp() {

		this.hero.increaseLevel(1);
		this.hero.increaseMaxHitPoint(60);
		this.hero.increaseAttack(2);
		this.hero.increaseDefense(4);

		this.hero.setMaxHitPoint();
	}

	static public int getExperienceForLevel(int level) {
		return (level * 1000 + ((level - 1) * (level - 1)) * 450);
	}

	private <T> Boolean validate(T validatedClass) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		Set<ConstraintViolation<T>> constraintViolations = validator.validate(validatedClass);

		System.out.println(constraintViolations);

		return constraintViolations.size() > 0;
	}
}
