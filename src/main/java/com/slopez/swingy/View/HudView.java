package com.slopez.swingy.View;

import com.slopez.swingy.Controller.Foe;
import com.slopez.swingy.Controller.Hero;

public class HudView {
	private Hero hero;
	// private Foe foe;

	public HudView(Hero hero, Foe foe) {
		this.hero = hero;
		// this.foe = foe;
	}

	public void aled() {
		getHealth();
		p("[%-8s %12d]\n", "Damages", hero.getAttackDamage());
		p("[%-8s %12d]\n", "Defense", hero.getDefense());
	}

	private void getHealth() {
		int percentage = (int) Math.round(((double) hero.getHitPoints() / (double) hero.getMaxHitPoints()) * 22);

		String red = "\033[48;2;155;50;155m";
		String reset = "\033[0;00m";

		String healthHud = String.format("[%-8s %12d]", "Health", hero.getHitPoints());
		StringBuffer buffer = new StringBuffer(healthHud);

		buffer.insert(1, red);
		buffer.insert(percentage + red.length(), reset);

		p("%s\n", buffer);

	}

	private void p(String format, Object... args) {
		System.out.printf(format, args);
	}
}
