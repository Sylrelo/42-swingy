package com.slopez.swingy.Model.Hero;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.slopez.swingy.Vector2;
import com.slopez.swingy.Controller.GameMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroModel {

	@NotBlank
	private String name;

	@NotBlank
	private String heroClass;

	@Min(1)
	private int level;

	@Min(0)
	private int experience;

	@Min(0)
	private int attack;

	@Min(0)
	private int defense;

	@Min(0)
	private int hitpoint;

	@Min(0)
	private int maxHitPoint;

	private Vector2 position;

	public HeroModel() {
		this.hitpoint = 100;
		this.maxHitPoint = 100;
		this.attack = 10;
		this.defense = 10;
		this.level = 1;

		double mapSize = GameMap.getMaxSize(1);
		this.position = new Vector2(mapSize * 0.5, mapSize * 0.5);
	}

	public void increaseHitPoint(int value) {
		this.hitpoint += value;
		this.hitpoint = Math.min(this.hitpoint, this.maxHitPoint);
	}

	public void increaseMaxHitPoint(int value) {
		this.maxHitPoint += value;
	}

	public void increaseDefense(int value) {
		this.defense += value;
	}

	public void increaseAttack(int value) {
		this.attack += value;
	}

	public void increaseExperience(int value) {
		this.experience += value;
	}

	public void increaseLevel(int value) {
		this.level += value;
	}

	public void decreaseHitPoint(int value) {
		this.hitpoint -= value;
	}

	public void setMaxHitPoint() {
		this.hitpoint = this.maxHitPoint;
	}

	public void addPostion(Vector2 position) {
		this.position.add(position);
	}

}
