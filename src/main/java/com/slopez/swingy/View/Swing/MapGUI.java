package com.slopez.swingy.View.Swing;

import javax.swing.*;

import com.slopez.swingy.Utils;
import com.slopez.swingy.Vector2;
import com.slopez.swingy.Controller.GameMap;

import java.awt.*;

public class MapGUI extends JPanel {

	final private int SIZE = 10;

	private JLabel[][] map;

	public MapGUI() {
		GridLayout layout = new GridLayout(SIZE, SIZE);
		setLayout(layout);

		map = new JLabel[SIZE][SIZE];

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				JLabel label = new JLabel();

				label.setSize(20, 20);
				label.setLayout(new BorderLayout());

				label.setOpaque(true);

				label.setBackground(new Color(0, 0, 0, 0));

				map[y][x] = label;
				add(map[y][x]);
			}

		}
		// table = new JTable();

		// add(table);
		setBounds(230, 20, 300, 300);
	}

	public void update(Vector2 heroPosition, int mapSize) {

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				map[y][x].setBackground(new Color(255, 255, 255, 255));
				map[y][x].setText("");

				int dx = x + (int) (heroPosition.x - 5);
				int dy = (int) (heroPosition.y + 5) - y;

				System.out.printf("%d %d\n", dx, dy);

				double schneider = GameMap
						.schneiderRandom(new Vector2(dx, dy));

				int color = Utils.range((int) (schneider * 255.0), 0, 255, 100, 255);

				map[y][x].setBackground(new Color(color, color, color, 255));

				if (dx == -1 || dy == -1 || dy == mapSize || dx == mapSize) {
					map[y][x].setBackground(new Color(255, 0, 0, 255));
				}

				if (dx < -1 || dy < -1 || dx > mapSize || dy > mapSize) {
					map[y][x].setBackground(new Color(255, 255, 255, 255));
				}

				if (dx == (int) heroPosition.x && dy == (int) heroPosition.y) {
					map[y][x].setBackground(new Color(0, 255, 0, 255));
					map[y][x].setText("Hero");
				}
			}
		}

		repaint();
	}
}
