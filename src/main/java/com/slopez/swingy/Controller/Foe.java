package com.slopez.swingy.Controller;

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
		return (int) ((double) foe.getLevel() * 0.25 + (foe.getAttack() + (foe.getDefense() * 0.25)));
	}

	public static Foe generateFoe(int heroLevel) {
		FoeModel foe = new FoeModel();

		foe.setLevel(heroLevel);
		foe.setHitpoint(10);
		foe.setMaxHitPoint(10);
		foe.setAttack(10);
		foe.setDefense(10);

		return new Foe(foe);
	}
}
