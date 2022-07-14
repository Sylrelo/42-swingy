package com.slopez.swingy;

import com.slopez.swingy.Controller.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        for (int i = 1; i < 25; i++) {
            double lg = i * 0.1;

            int attack = 14;

            double p = Math.max(0.0, (40 / (100 + (100 * lg))));

            int a2 = (int) ((double) attack - (attack * p));

            // System.out.printf("%.4f - %.4f > %.1f\n", lg, 100 + (100 * lg), (30 / (100 +
            // (100 * lg))) * 100);
            System.out.printf(" %f | %d => %d\n", p, attack, a2);
        }
    }
}
