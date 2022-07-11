package com.slopez.swingy.Model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FoeModel {

	private String name;

	private int level;
	private int attack;
	private int defense;
	private int hitpoint;

	public FoeModel() {
		this.name = "Random name";
		this.level = 1;
	}

	public void decreaseHitPoint(int value) {
		this.hitpoint -= value;
	}

}