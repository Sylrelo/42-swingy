package com.slopez.swingy.Controller;

import java.util.ArrayList;
import java.util.List;

import com.slopez.swingy.Vector2;

public class GameMap {

	private List<Integer> alreadyVisited;

	public GameMap() {
		alreadyVisited = new ArrayList<Integer>();
	}

	static public int getMaxSize(int level) {
		return (level - 1) * 5 + 10 - (level % 2);
	}

	public Boolean hasFoundEnnemy(Vector2 position) {
		if (alreadyVisited.contains(position.hash())) {
			return false;
		}

		alreadyVisited.add(position.hash());

		double schneid = schneiderRandom(position);

		return schneid > 0.8;
	}

	static public double schneiderRandom(Vector2 position) {
		Vector2 k1 = new Vector2(23.14069263277926, 2.665144142690225);
		double schneider = Math.cos(position.dot(k1)) * 12345.6789;
		return schneider - Math.floor(schneider);
	}
}
