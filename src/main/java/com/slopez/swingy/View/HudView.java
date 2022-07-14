package com.slopez.swingy.View;

import com.slopez.swingy.Controller.Foe;
import com.slopez.swingy.Controller.Hero;
import com.slopez.swingy.Model.Items.ArmorModel;
import com.slopez.swingy.Model.Items.HelmModel;
import com.slopez.swingy.Model.Items.ItemModel;
import com.slopez.swingy.Model.Items.WeaponModel;

public class HudView {
	private Hero hero;

	public static String SUPERIOR = "\033[38;2;50;255;155m";
	public static String INFERIOR = "\033[38;2;255;50;155m";
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

		int previousLevelExperience = hero.getLevel() > 1 ? Hero.getExperienceForLevel(hero.getLevel() - 1)
				: 0;

		double percentage = ((double) (hero.getModel().getExperience() - previousLevelExperience)
				/ (double) (Hero.getExperienceForLevel(hero.getLevel()) - previousLevelExperience));

		StringBuffer buffer = new StringBuffer(
				String.format("[Level %-10d %3d%%]", hero.getLevel(), (int) (percentage * 100)));

		if ((int) (percentage * 21) > 0 && percentage < 1) {
			buffer.insert(1, RED);
			buffer.insert((int) (percentage * 21) + RED.length(), RESET);
		}

		p("%s %s\n", buffer, foe != null ? String.format("[Level %-15d]", foe.getLevel()) : "");
	}

	public void displayItemProperties(ItemModel item) {
		String itemType = "";
		String modifierType = "";
		ItemModel oldItem = null;

		if (item instanceof ArmorModel) {
			itemType = "Armor";
			modifierType = "Defense";
			oldItem = this.hero.getArmor();
		} else if (item instanceof HelmModel) {
			oldItem = this.hero.getHelm();
			modifierType = "Health";
			itemType = "Helm";
		} else if (item instanceof WeaponModel) {
			oldItem = this.hero.getWeapon();
			modifierType = "Damages";
			itemType = "Weapom";
		}

		boolean isSuperior = item.getModifier() - oldItem.getModifier() > 0;

		p("You looted a new %s\n", itemType);
		p("%s\n", item.getName());
		p("%16s : %+d (%s%+d%s)\n", modifierType, item.getModifier(), isSuperior ? SUPERIOR : INFERIOR,
				item.getModifier() - oldItem.getModifier(), RESET);

	}

	private String hudDisplay(String title, int value) {
		return String.format("[%-8s %12d]", title, value);
	}

	private String getHealth(int current, int max) {
		int percentage = (int) Math.round(((double) current / (double) max) * 22);

		String healthHud = String.format("[%-8s %12d]", "Health", current);
		StringBuffer buffer = new StringBuffer(healthHud);

		if (percentage > 0) {
			buffer.insert(1, RED);
			buffer.insert(percentage + RED.length(), RESET);
		}
		// p("%s\n", buffer);
		return (buffer.toString());
	}

	private void p(String format, Object... args) {
		System.out.printf(format, args);
	}
}
