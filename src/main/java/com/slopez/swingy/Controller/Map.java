package com.slopez.swingy.Controller;

import com.slopez.swingy.Vector2;

// import com.slopez.swingy.Model.MapModel;

public class Map {

	// private MapModel model;

	public Map() {
		// this.model = new MapModel();
	}

	// public MapModel getModel() {
	// 	return this.model;
	// }

	// public boolean getAt(int x, int y) {
	// 	return this.model.getMap()[x * MapModel.MAP_SIZE + y];
	// }

	static public int getMaxSize(int level) {
		return (level - 1) * 5 + 10 - (level % 2);
	}

	private double schneiderRandom(Vector2 position) {
		Vector2 k1 = new Vector2(23.14069263277926, 2.665144142690225);
		double schneider = Math.cos(position.dot(k1)) * 12345.6789;
		return schneider - Math.floor(schneider);
	}
}
