package com.slopez.swingy.Model;

public class MapModel {

	final static int MAP_SIZE = 256;

	private int[] map;

	public MapModel() {
		this.map = new int[MapModel.MAP_SIZE * MapModel.MAP_SIZE];
	}

	public int[] getMap() {
		return this.map;
	}

	public int getAt(int x, int y) {
		return this.map[x * MapModel.MAP_SIZE + y];
	}
}
