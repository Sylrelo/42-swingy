package com.slopez.swingy.Model.Items;

public class ItemModel {
	private String name;
	private int modifier;

	public ItemModel(String name, int modifier) {
		this.name = name;
		this.modifier = modifier;
	}

	public String getName() {
		return this.name;
	}

	public int getModifier() {
		return this.modifier;
	}
}
