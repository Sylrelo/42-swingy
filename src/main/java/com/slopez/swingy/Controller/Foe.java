package com.slopez.swingy.Controller;

import java.util.Random;

import com.slopez.swingy.Utils;
import com.slopez.swingy.Model.FoeModel;

public class Foe {
	private FoeModel foe;

	public Foe(FoeModel foeModel) {
		this.foe = foeModel;
	}

	public FoeModel getModel() {
		return this.foe;
	}

	public int receiveDamage(int heroLevel, int heroDamage) {
		int damageIncrease = Utils.getDamageIncrease(foe.getLevel(), heroLevel, heroDamage);
		double armorReduction = Utils.getArmorReduction(foe.getLevel(), foe.getDefense());

		int damages = (heroDamage + damageIncrease);
		int reducedDamages = (int) Math.max(damages * 0.2, (damages - (damages * (armorReduction))));

		this.foe.decreaseHitPoint(reducedDamages);

		return (reducedDamages);
	}

	public int getLevel() {
		return this.foe.getLevel();
	}

	public int getAttack() {
		return this.foe.getAttack();
	}

	public int getHitpoint() {
		return this.foe.getHitpoint();
	}

	public int getGivenExperience() {
		return (int) (((double) foe.getLevel() * 0.5 + (foe.getAttack() * 0.5 + (foe.getDefense() * 0.25))) * 42.0
				+ (21.0 * (double) foe.getLevel() * 0.105));
	}

	public int getPower() {
		return Utils.getPower(foe.getAttack(), foe.getDefense(), foe.getLevel());
	}

	public static Foe generateFoe(int heroLevel) {
		FoeModel foe = new FoeModel();
		Random rnd = new Random();
		int foeLevel = heroLevel;

		double rngLevel = rnd.nextDouble();

		if (rngLevel > 0.95)
			foeLevel = heroLevel + 2;
		else if (rngLevel > 0.85)
			foeLevel = heroLevel + 1;
		else if (rngLevel > .2)
			foeLevel = heroLevel;
		else
			foeLevel = Math.max(1, heroLevel - 1);

		foe.setLevel(foeLevel);

		foe.setHitpoint(foeLevel * 64);
		foe.setMaxHitPoint(foeLevel * 64);

		foe.setAttack(foeLevel * 22);
		foe.setDefense(foeLevel * 22);

		return new Foe(foe);
	}
}
