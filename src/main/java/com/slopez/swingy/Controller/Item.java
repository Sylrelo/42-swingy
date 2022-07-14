package com.slopez.swingy.Controller;

import java.util.Random;

import com.slopez.swingy.Model.Items.ArmorModel;
import com.slopez.swingy.Model.Items.HelmModel;
import com.slopez.swingy.Model.Items.ItemModel;
import com.slopez.swingy.Model.Items.WeaponModel;

public class Item {

    public static String[] type = { "armor", "helm", "weapon" };

    private static String[] armorNames = { "na" };
    private static String[] weaponNames = { "me" };
    private static String[] helmNames = { "eh" };

    public static ItemModel Create(String type, int modifier) {
        Random rand = new Random();

        switch (type) {
            case "armor":
                return new ArmorModel(Item.armorNames[rand.nextInt(Item.armorNames.length)], modifier);
            case "helm":
                return new HelmModel(Item.helmNames[rand.nextInt(Item.helmNames.length)], modifier);
            case "weapon":
                return new WeaponModel(Item.weaponNames[rand.nextInt(Item.weaponNames.length)], modifier);
        }

        return null;
    }
}
