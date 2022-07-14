package com.slopez.swingy.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.slopez.swingy.Vector2;
import com.slopez.swingy.Model.Items.ItemModel;
import com.slopez.swingy.View.HudView;
import com.slopez.swingy.View.MapView;

public class Game {

	private static int S_IDLE = 0x00;
	private static int S_ATTACKING = 0x01;
	private static int S_FIGHT_WON = 0x02;
	private static int S_FIGHT_LOST = 0x04;
	private static int S_ACCEPT_ITEM = 0x08;
	private static int S_REFUSE_ITEM = 0x10;

	private Hero hero;
	private GameMap map;
	private Foe currentFoe;
	private ItemModel droppedItem;

	private int lastState = S_IDLE;

	private List<String> fightLog;

	boolean handled = false;

	public Game() {
		this.map = new GameMap();
		this.hero = new Hero();
		this.droppedItem = null;

		this.fightLog = new ArrayList<String>();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();

			Vector2 position = hero.getPosition();

			int mapSize = GameMap.getMaxSize(hero.getLevel());

			this.handleDroppedItemState();

			MapView mapViewCli = new MapView(position, mapSize);
			HudView hudViewCli = new HudView(this.hero, this.currentFoe);

			if (position.x <= 0 || position.x > mapSize || position.y <= 0 || position.y > mapSize) {
				System.out.println("Game finished ! Congrats.");
				System.exit(0);
			}

			if (map.hasFoundEnnemy(position)) {
				currentFoe = Foe.generateFoe(this.hero.getLevel());
			}

			hudViewCli.aled();
			hudViewCli.displayExperienceBar();

			for (String msg : this.fightLog) {
				System.out.println(msg);
			}

			if (currentFoe != null && (lastState & S_ATTACKING) == S_ATTACKING) {
				if (this.simulateFight())
					continue;
				else {
					// this.currentFoe = null;
					// this.lastState = S_IDLE;
					continue;
				}
			} else if (lastState == S_IDLE) {
				mapViewCli.GetLines();
			}

			if (lastState == S_FIGHT_WON) {
				if (!handled) {
					this.generateLoot();
					int healed = this.hero.healPercentage(10);
					insertLog("You've been healed for 10%% health point (%d)\n", healed);
					hudViewCli.displayItemProperties(this.droppedItem);
					handled = true;
				}
				System.out.println("[y] Equip new item | [n] Leave");
			}

			if (currentFoe != null && lastState == S_IDLE) {
				System.out.println("[f] Fight | [r] Try to run away");
				System.out.println("Enemy Encounter : FIGHT or RUN ?");
			}

			String input = scanner.next();
			input = input.toLowerCase();

			if (input.equals("x"))
				break;

			if (lastState == S_FIGHT_WON)
				this.handleCliItem(input);
			else if (currentFoe == null)
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
		insertLog("Inflicted %d damages.", inflictedDamage);

		if (this.currentFoe.getHitpoint() <= 0) {
			boolean hasLeveledUp = this.hero.addExperience(this.currentFoe.getGivenExperience());

			insertLog("You won and gained %d experiences.", this.currentFoe.getGivenExperience());
			if (hasLeveledUp)
				insertLog("You leveled up ! Congratulation.");

			this.lastState = S_FIGHT_WON;
			return false;
		}

		int receivedDamage = this.hero.receiveDamage(currentFoe.getLevel(), currentFoe.getAttack());
		insertLog("Received %d damages.", receivedDamage);

		if (this.hero.getHitPoints() <= 0) {
			this.lastState = S_FIGHT_LOST;
			return true;
		}

		return true;
	}

	private void handleCliItem(String input) {
		switch (input) {
			case "y":
				this.lastState = S_ACCEPT_ITEM;
				insertLog("You equiped the item !");
				break;
			case "n":
				insertLog("You left the item :(");
				this.lastState = S_REFUSE_ITEM;
				break;
		}
	}

	private void handleCliCombat(String input) {
		switch (input) {
			case "f":
				this.lastState = S_ATTACKING;
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
		LocalDateTime time = LocalDateTime.now();

		String log = String.format("[%d:%d:%d] %s", time.getHour(), time.getMinute(), time.getSecond(),
				String.format(format, args));

		this.fightLog.add(0, log);

		if (this.fightLog.size() > 5) {
			this.fightLog.remove(5);
		}
	}

	private void handleDroppedItemState() {
		if (lastState == S_ACCEPT_ITEM) {
			this.hero.equipItem(this.droppedItem);
			this.droppedItem = null;
		}

		if (lastState == S_REFUSE_ITEM || lastState == S_ACCEPT_ITEM) {
			lastState = S_IDLE;
			this.currentFoe = null;
			handled = false;
		}

	}

	private void generateLoot() {
		this.hero.getLevel();

		System.out.println(this.currentFoe.getLevel());
		System.out.println(this.currentFoe.getPower());

		Random rnd = new Random();

		int maxModifier = rnd.nextInt((int) (21 * ((double) currentFoe.getLevel() * 0.85)));
		int minModifier = rnd.nextInt((int) (10 * ((double) currentFoe.getLevel() * 0.70)));

		int modifier = (int) (minModifier + (10.0 * (double) currentFoe.getLevel() * 0.2));

		this.droppedItem = Item.Create(Item.type[rnd.nextInt(3)], modifier + maxModifier);
	}
}
