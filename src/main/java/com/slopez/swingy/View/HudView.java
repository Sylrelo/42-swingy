package com.slopez.swingy.View;

import com.slopez.swingy.Controller.Foe;
import com.slopez.swingy.Controller.Hero;
import com.slopez.swingy.Model.Items.ArmorModel;
import com.slopez.swingy.Model.Items.HelmModel;
import com.slopez.swingy.Model.Items.ItemModel;
import com.slopez.swingy.Model.Items.WeaponModel;

public class HudView {
	private Hero hero;

	public static String RED = "\033[48;2;155;50;155m";
	public static String RESET = "\033[0;00m";

	private Foe foe;

	public HudView(Hero hero, Foe foe) {
		this.hero = hero;
		this.foe = foe;
	}

	public void aled() {

		p("%s %s\n",
				getHealth(hero.getHitPoints(), hero.getMaxHitPoints()),
				foe != null ? getHealth(foe.getModel().getHitpoint(), foe.getModel().getMaxHitPoint()) : "");

		String foeDamage = foe != null ? hudDisplay("Damages", foe.getAttack()) : "";
		String foeDefense = foe != null ? hudDisplay("Defense", foe.getModel().getDefense()) : "";

		p("%s %s\n", hudDisplay("Damages", hero.getAttackDamage()), foeDamage);
		p("%s %s\n", hudDisplay("Defense", hero.getDefense()), foeDefense);
	}

	public void displayExperienceBar() {
		int percentage = (int) Math.round(
				((double) hero.getModel().getExperience() / (double) Hero.getExperienceForLevel(hero.getLevel()))
						* 100);

		StringBuffer buffer = new StringBuffer(String.format("[Level %-10d %3d%%]\n", hero.getLevel(), percentage));

		if (percentage > 0) {
			buffer.insert(1, RED);
			buffer.insert(percentage + RED.length(), RESET);
		}

		p("%s\n", buffer);
	}

	public void displayItemProperties(ItemModel item) {
		String itemType = "";
		if (item instanceof ArmorModel) {
			itemType = "Armor";
		} else if (item instanceof HelmModel) {
			itemType = "Helm";
		} else if (item instanceof WeaponModel) {
			itemType = "Weapom";
		}
		p("You looted a new %s : %s (%d)\n", itemType, item.getName(), item.getModifier());
	}

	private String hudDisplay(String title, int value) {
		return String.format("[%-8s %12d]", title, value);
	}

	private String getHealth(int current, int max) {
		int percentage = (int) Math.round(((double) current / (double) max) * 22);

		String healthHud = String.format("[%-8s %12d]", "Health", current);
		StringBuffer buffer = new StringBuffer(healthHud);

		buffer.insert(1, RED);
		buffer.insert(percentage + RED.length(), RESET);
		// p("%s\n", buffer);
		return (buffer.toString());
	}

	private void p(String format, Object... args) {
		System.out.printf(format, args);
	}
}
