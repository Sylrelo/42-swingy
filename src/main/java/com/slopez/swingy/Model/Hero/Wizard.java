package com.slopez.swingy.Model.Hero;

public class Wizard extends HeroModel {
	public Wizard() {
		super();

		this.setMaxHitPoint(90);
		this.setHitpoint(90);
		this.setDefense(25);
		this.setAttack(60);

		this.perLevelAttack = 44;
		this.perLevelHealth = 30;
		this.perLevelDefense = 10;
	}

}
