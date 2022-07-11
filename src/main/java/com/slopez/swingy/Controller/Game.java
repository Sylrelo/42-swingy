package com.slopez.swingy.Controller;

import java.util.Scanner;

import com.slopez.swingy.Vector2;
import com.slopez.swingy.View.MapView;

public class Game {
	private Hero hero;
	private GameMap map;
	private Foe currentFoe;

	private int lastState = 0x0;

	public Game() {
		this.map = new GameMap();
		this.hero = new Hero();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();

			Vector2 position = hero.getPosition();

			int mapSize = GameMap.getMaxSize(hero.getLevel());

			MapView mapViewCli = new MapView(position, mapSize);

			if (position.x <= 0 || position.x > mapSize || position.y <= 0 || position.y > mapSize) {
				System.out.println("Game finished ! Congrats.");
				System.exit(0);
			}

			if (map.hasFoundEnnemy(position)) {
				currentFoe = Foe.generateFoe(this.hero.getLevel());
			}

			System.out.printf("Position : %.0f %.0f\n", position.x, position.y);
			System.out.printf("Health : %d / %d\n", this.hero.getHitPoints(), this.hero.getModel().getMaxHitPoint());
			System.out.printf("Current Level : %d\n", this.hero.getLevel());
			System.out.printf("Currently Fighting : %s\n", currentFoe != null ? "yes" : "no");
			System.out.flush();

			if (currentFoe != null && lastState == 1) {
				System.out.printf("Fight Interface");
				if (this.simulateFight())
					continue;
				else
					break;
			}
			else {
				mapViewCli.GetLines();
			}

			if (currentFoe != null && lastState == 0) {
				System.out.println("Enemy Encounter : FIGHT or RUN ?");
			}

			String input = scanner.next();
			input = input.toLowerCase();

			if (input.equals("x")) break;
			
			if (currentFoe == null) this.handleCliMove(input);
			else this.handleCliCombat(input);
		}

		scanner.close();
	}

	private void tryRun() {

	}

	private boolean simulateFight() {
		while (true) {
			try {
				Thread.sleep(500);

				System.out.println(currentFoe.getHitpoint());

				this.currentFoe.receiveDamage(this.hero.getLevel(), this.hero.getAttackDamage());
				this.hero.receiveDamage(currentFoe.getLevel(), currentFoe.getAttack());
				

				if (this.currentFoe.getHitpoint() <= 0 || this.hero.getHitPoints() <= 0) {
					
					System.out.println("Someone died"); 
					break;
				}
				continue;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.hero.addExperience(1400);
		this.currentFoe = null;
		this.lastState = 0;
		return true;
	}

	private void handleCliCombat(String input) {
		switch (input) {
			case "f" :
				this.lastState = 1;
				// this.simulateFight();
			break;
			case "r" :
				this.tryRun();
			break;
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
