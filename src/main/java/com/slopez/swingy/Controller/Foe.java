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
		int damageIncrease = (int) (((heroLevel - this.getLevel()) / 13.37) * heroDamage);

		int diff = Math.max((heroDamage + damageIncrease) - this.foe.getDefense(), 1);

		this.foe.decreaseHitPoint(diff);

		return (diff);
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

		if (rngLevel > 0.9)
			foeLevel = heroLevel + 2;
		else if (rngLevel > 0.8)
			foeLevel = heroLevel + 1;
		else if (rngLevel > .2)
			foeLevel = heroLevel;
		else
			foeLevel = Math.max(1, heroLevel - 1);

		foe.setLevel(foeLevel);

		foe.setHitpoint(foeLevel * 64);
		foe.setMaxHitPoint(foeLevel * 64);

		foe.setAttack(foeLevel * 4);
		foe.setDefense(foeLevel * 4);

		return new Foe(foe);
	}
}
