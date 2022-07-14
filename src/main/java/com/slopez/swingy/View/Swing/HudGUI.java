package com.slopez.swingy.View.Swing;

import java.awt.*;
import javax.swing.*;

import com.slopez.swingy.Utils;
import com.slopez.swingy.Controller.Foe;
import com.slopez.swingy.Controller.Hero;

import lombok.Setter;

public class HudGUI {
	private JFrame frame;

	@Setter
	private Hero hero;
	@Setter
	private Foe foe;

	private JLabel heroNameLabel;
	private JLabel heroDefenseLabel;
	private JLabel heroAttackLevel;

	private JProgressBar heroLevelBar;
	private JProgressBar heroHealthBar;

	private JProgressBar foeHealthBar;

	public HudGUI(JFrame frame) {
		this.frame = frame;

	}

	public void displayHero() {
		JPanel panel = new JPanel();

		frame.setLayout(null);

		GridLayout layout = new GridLayout(0, 1);

		panel.setLayout(layout);

		heroHealthBar = new JProgressBar(0, 100);
		heroHealthBar.setPreferredSize(new Dimension(200, 40));
		// heroHealthBar.setBounds(0, 0, 200, 40);
		heroHealthBar.setStringPainted(true);
		panel.add(heroHealthBar);

		heroNameLabel = new JLabel("Name");
		panel.add(heroNameLabel);

		heroAttackLevel = new JLabel("Attack");
		panel.add(heroAttackLevel);

		heroDefenseLabel = new JLabel("Defense");
		panel.add(heroDefenseLabel);

		heroLevelBar = new JProgressBar(0, 100);
		heroLevelBar.setPreferredSize(new Dimension(200, 40));
		// heroLevelBar.setBounds(0, 80, 200, 40);
		heroLevelBar.setStringPainted(true);
		panel.add(heroLevelBar);

		// frame.setBounds(0, 0, 400, 300);
		frame.getContentPane().add(panel);
		panel.setBounds(20, 20, 200, 100);

		// frame.setLocationRelativeTo(null);
		// frame.setVisible(true);

		/*
		 * Dimension preferredSize = new Dimension();
		 * for (int i = 0; i < panel.getComponentCount(); i++) {
		 * Rectangle bounds = panel.getComponent(i).getBounds();
		 * preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
		 * preferredSize.height = Math.max(bounds.y + bounds.height,
		 * preferredSize.height);
		 * }
		 * Insets insets = panel.getInsets();
		 * preferredSize.width += insets.right;
		 * preferredSize.height += insets.bottom;
		 * panel.setMinimumSize(preferredSize);
		 * panel.setPreferredSize(preferredSize);
		 */
	}

	public void update() {
		heroNameLabel.setText(hero.getModel().getName());
		heroAttackLevel.setText(String.format("%d", hero.getAttackDamage()));
		heroDefenseLabel.setText(String.format("%d", hero.getDefense()));

		double percentage = Utils.getLevelPercentage(hero.getLevel(), hero.getModel().getExperience());

		heroLevelBar.setValue((int) (percentage * 100));
		heroLevelBar.setString(String.format("%d (%.0f %%)", hero.getLevel(), (percentage * 100)));

		heroHealthBar.setMaximum(hero.getMaxHitPoints());
		heroHealthBar.setValue(hero.getHitPoints());
		heroHealthBar.setString(String.format("%d / %d", hero.getHitPoints(), hero.getMaxHitPoints()));
		// heroLevelBar.setMaximum(((int) percentage * 100));
		// heroExperienceLabel.setText(String.format("%d",
		// hero.getModel().getExperience()));
	}

}
