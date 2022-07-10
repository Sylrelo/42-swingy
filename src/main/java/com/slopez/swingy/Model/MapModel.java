package com.slopez.swingy.Model;

import com.slopez.swingy.Controller.Foe;

public class MapModel {

	final static public int MAP_SIZE = 256;

	private Foe[] map;

	public MapModel() {
		this.map = new Foe[MapModel.MAP_SIZE * MapModel.MAP_SIZE];
		
		for (int i = 0; i < MapModel.MAP_SIZE * MapModel.MAP_SIZE; i++) {
			this.map[i] = null;
		}
	}

	public Foe[] getMap() {
		return this.map;
	}

}
