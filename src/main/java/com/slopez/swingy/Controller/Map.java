package com.slopez.swingy.Controller;

import com.slopez.swingy.Model.MapModel;

public class Map {

	private MapModel model;

	public Map() {
		this.model = new MapModel();
	}

	public MapModel getModel() {
		return this.model;
	}

	public Foe getAt(int x, int y) {
		return this.model.getMap()[x * MapModel.MAP_SIZE + y];
	}
}
