package com.slopez.swingy;

public class Utils {
    public static int getPower(int attack, int defense, int level) {
        return (int) ((double) level * 0.5 + (attack * 0.5 + (defense * 0.25)));
    }
}
