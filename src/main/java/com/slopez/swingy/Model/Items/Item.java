package com.slopez.swingy.Model.Items;

public class Item {
	private String name;
	private Number modifier;

	public Item(String name, Number modifier) {
		this.name = name;
		this.modifier = modifier;
	}

	public String getName() {
		return this.name;
	}

	public Number getModifier() {
		return this.modifier;
	}
}
