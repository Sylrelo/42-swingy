package com.slopez.swingy.Controller;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.slopez.swingy.Vector2;
import com.slopez.swingy.View.HudView;
import com.slopez.swingy.View.MapView;

public class Game {

	private static int S_IDLE = 0x00;
	private static int S_ATTACKING = 0x01;
	private static int S_FIGHT_WON = 0x02;
	private static int S_FIGHT_LOST = 0x04;

	private Hero hero;
	private GameMap map;
	private Foe currentFoe;

	private int lastState = S_IDLE;

	private List<String> fightLog;

	public Game() {
		this.map = new GameMap();
		this.hero = new Hero();
		this.fightLog = new ArrayList<String>();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();

			Vector2 position = hero.getPosition();

			int mapSize = GameMap.getMaxSize(hero.getLevel());

			MapView mapViewCli = new MapView(position, mapSize);
			HudView hudViewCli = new HudView(this.hero, this.currentFoe);

			if (position.x <= 0 || position.x > mapSize || position.y <= 0 || position.y > mapSize) {
				System.out.println("Game finished ! Congrats.");
				System.exit(0);
			}

			if (map.hasFoundEnnemy(position)) {
				currentFoe = Foe.generateFoe(this.hero.getLevel());
			}

			// System.out.printf("Position : %.0f %.0f\n", position.x, position.y);
			// System.out.printf("Health : %d / %d\n", this.hero.getHitPoints(),
			// this.hero.getModel().getMaxHitPoint());
			// System.out.printf("Current Level : %d\n", this.hero.getLevel());
			// System.out.printf("Currently Fighting : %s\n", currentFoe != null ? "yes" :
			// "no");
			// System.out.flush();

			hudViewCli.aled();

			if (currentFoe != null && (lastState & S_ATTACKING) == S_ATTACKING) {
				System.out.printf("Fight Interface");
				if (this.simulateFight())
					continue;
				else
					break;
			} else {
				mapViewCli.GetLines();
			}

			if (currentFoe != null && lastState == S_IDLE) {
				System.out.println("Enemy Encounter : FIGHT or RUN ?");
			}

			for (String msg : this.fightLog) {
				System.out.println(msg);
			}

			String input = scanner.next();
			input = input.toLowerCase();

			if (input.equals("x"))
				break;

			if (currentFoe == null)
				this.handleCliMove(input);
			else
				this.handleCliCombat(input);
		}

		scanner.close();
	}

	private void tryRun() {

	}

	private boolean simulateFight() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int inflictedDamage = this.currentFoe.receiveDamage(this.hero.getLevel(), this.hero.getAttackDamage());
		int receivedDamage = this.hero.receiveDamage(currentFoe.getLevel(), currentFoe.getAttack());

		insertLog("Inflicted %d damages.", inflictedDamage);
		insertLog("Received %d damages.", receivedDamage);

		if (this.currentFoe.getHitpoint() <= 0) {
			this.hero.addExperience(this.currentFoe.getGivenExperience());

			insertLog("Gained %d experiences.", this.currentFoe.getGivenExperience());

			this.lastState = S_FIGHT_WON;
		}

		if (this.hero.getHitPoints() <= 0) {
			this.lastState = S_FIGHT_LOST;
		}

		if (this.currentFoe.getHitpoint() <= 0 || this.hero.getHitPoints() <= 0) {
			return false;
		}

		return true;
	}

	private void handleCliCombat(String input) {
		switch (input) {
			case "f":
				this.lastState = 1;
				// this.simulateFight();
				break;
			case "r":
				this.tryRun();
				break;
		}
	}

	private void handleCliMove(String input) {
		switch (input) {
			case "w":
				this.hero.moveBy(0, 1);
				System.out.println("Up");
				break;
			case "s":
				this.hero.moveBy(0, -1);
				System.out.println("Down");
				break;
			case "a":
				this.hero.moveBy(-1, 0);
				System.out.println("Left");
				break;
			case "d":
				this.hero.moveBy(1, 0);
				System.out.println("Right");
				break;
		}
	}

	private void insertLog(String format, Object... args) {
		this.fightLog.add(0, String.format(format, args));

		if (this.fightLog.size() >= 5) {
			this.fightLog.remove(5);
		}
	}
}
