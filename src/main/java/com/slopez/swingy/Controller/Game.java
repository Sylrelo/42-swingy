package com.slopez.swingy.Controller;

public class Game {
	private Hero hero;
	private Map map;

	public Game() {
		this.map = new Map();

		this.hero = new Hero();
		this.hero.test();

		for (int i = 0; i < 20; i++) {
			this.hero.addExperience(400);
			this.hero.test();
		}
	}
}
