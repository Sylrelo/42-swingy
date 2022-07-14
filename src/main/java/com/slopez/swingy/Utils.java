package com.slopez.swingy;

import com.slopez.swingy.Model.Hero.HeroModel;
import com.slopez.swingy.Model.Hero.Wizard;

public class Utils {
    public static int getPower(int attack, int defense, int level) {
        return (int) ((double) level * 0.5 + (attack * 0.5 + (defense * 0.25)));
    }

    public static String getClassName(HeroModel hero) {
        if (hero instanceof Wizard)
            return "Wizard";
        return null;
    }

    public static double getArmorReduction(int level, int defense) {
        return (double) ((double) defense
                / (100.0 + (100.0 * ((double) level * 0.22))));
    }

    public static int getDamageIncrease(int ownLevel, int opponentLevel, int damage) {
        return (int) (((opponentLevel - ownLevel) / 13.37) * damage);
    }
}
