package com.slopez.swingy.Controller;

import java.util.Scanner;

import com.slopez.swingy.Vector2;

public class Game {
	private Hero hero;
	private Map map;

	public Game() {
		this.map = new Map();
		this.hero = new Hero();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();

			Vector2 position = this.hero.getPosition();
			double mapSize = Map.getMaxSize(this.hero.getLevel());

			if (position.x <= 0 || position.x > mapSize || position.y <= 0 || position.y > mapSize) {
				System.out.println("Game finished ! Congrats.");
				System.exit(0);
			}

			System.out.printf("Position : %.0f %.0f\n", position.x, position.y);
			System.out.flush();

			String input = scanner.next();
			input = input.toLowerCase();

			if (input.equals("x")) System.exit(0);
			

			this.handleCliMove(input);
		}
	}

	private void handleCliMove(String input) {
		switch (input) {
			case "w" :
				this.hero.moveBy(0, 1);
				System.out.println("Up");
			break;
			case "s" :
				this.hero.moveBy(0, -1);
				System.out.println("Down");
			break;
			case "a" :
				this.hero.moveBy(-1, 0);
				System.out.println("Left");
			break;
			case "d" :
				this.hero.moveBy(1, 0);
				System.out.println("Right");
			break;
		}
	}
}
