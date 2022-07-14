package com.slopez.swingy;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.slopez.swingy.Controller.Game;
import com.slopez.swingy.Controller.Hero;

public class Main {

    private static String[] classes = { "Wizard", "Warrior", "Tank", "Necromancer", "Assassin" };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Hero hero = null;

        hero = Hero.Create("patate", "Wizard");

        new Game(hero);

        /*
         * System.out.println("[c] Create hero | [l] Load hero");
         * 
         * String input = getInput(scanner);
         * if (input.equals("c")) {
         * 
         * String heroClass = chooseClass(scanner);
         * String heroName = chooseName(scanner, heroClass);
         * 
         * hero = Hero.Create(heroName, heroClass);
         * 
         * new Game(hero);
         * 
         * } else if (input.equals("l")) {
         * System.out.println("Load !");
         * }
         */
    }

    static public JFrame createWindow() {
        JFrame frame = new JFrame("Swingy");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setVisible(false);

        return frame;
    }

    static private String getInput(Scanner scanner) {
        String input = scanner.next();
        input = input.toLowerCase();
        return input;
    }

    static private String chooseClass(Scanner scanner) {
        System.out.printf("Choose a class : \n");
        System.out.printf("[0] Wizard\n");
        System.out.printf("[1] Warrior\n");
        System.out.printf("[2] Tank\n");
        System.out.printf("[3] Necromancer\n");
        System.out.printf("[4] Assassin\n");

        String input = getInput(scanner);
        if (Integer.parseInt(input) < 0 || Integer.parseInt(input) > 4) {
            return chooseClass(scanner);
        }

        return classes[Integer.parseInt(input)];
    }

    static private String chooseName(Scanner scanner, String heroClass) {
        System.out.printf("Choose your %s name : \n", heroClass);
        String input = getInput(scanner);

        if (input.length() < 3) {
            System.out.println("");
        }

        return input;
    }
    // private Hero createHero() {

    // }
}
