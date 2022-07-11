package com.slopez.swingy.View;

import java.util.Map;

import com.slopez.swingy.Vector2;
import com.slopez.swingy.Controller.GameMap;

public class MapView {

	private final int MAX_VIEW_X = 30;
	private final int MAX_VIEW_Y = 5;

	private Vector2 heroPosition;
	private int mapSize;

	public MapView(Vector2 heroPosition, int mapSize) {
		this.heroPosition = heroPosition;
		this.mapSize = mapSize;
	}
	
	public void GetLines() {
		for (int y = (int) heroPosition.y + MAX_VIEW_Y; y >=  (int) heroPosition.y - MAX_VIEW_Y; y--) {
			for (int x = (int) heroPosition.x - MAX_VIEW_X; x < (int) heroPosition.x + MAX_VIEW_X; x++) {
				double schneider = GameMap.schneiderRandom(new Vector2(x, y));
				int color = range((int) (schneider * 255.0), 0, 255, 100, 255);

				if ((int) heroPosition.x == x && (int) heroPosition.y == y) {
					System.out.printf("\033[48;2;155;50;155mP\033[0;00m");
				}
				else if (x < 0 || x > mapSize || y < 0 || y > mapSize) {
					System.out.printf(" ");
				}
				else if (x == 0 || x == mapSize || y == 0 || y == mapSize) {
					System.out.printf("\033[48;2;155;0;0m \033[0;00m");
				}
				else {
					System.out.printf("\033[48;2;%d;%d;%dm \033[0;00m", color, color, color);
				}
			}
			System.out.printf("\n");
		}
	}

	private int range(int oldValue, int oldMax, int oldMin, int newMax, int newMin) {
		int oldRange = (oldMax - oldMin) ;
		int newRange = (newMax - newMin) ;
		int newValue = (((oldValue - oldMin) * newRange) / oldRange) + newMin;

		return newValue;
	}
}
