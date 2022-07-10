package com.slopez.swingy.Model.Hero;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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


	public HeroModel() {}

	

}
