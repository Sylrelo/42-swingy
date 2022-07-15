package com.slopez.swingy.View.Swing;

import java.awt.*;
import javax.swing.*;

import com.slopez.swingy.Utils;
import com.slopez.swingy.Controller.Hero;

public class HeroGUI extends JPanel {
    private JLabel heroNameLabel;
    private JLabel heroDefenseLabel;
    private JLabel heroAttackLevel;

    private JProgressBar heroLevelBar;
    private JProgressBar heroHealthBar;

    public HeroGUI() {
        GridLayout layout = new GridLayout(0, 1);
        setLayout(layout);

        heroHealthBar = new JProgressBar(0, 100);
        heroHealthBar.setPreferredSize(new Dimension(200, 40));
        heroHealthBar.setStringPainted(true);
        add(heroHealthBar);

        heroNameLabel = new JLabel("Name");
        add(heroNameLabel);

        heroAttackLevel = new JLabel("Attack");
        add(heroAttackLevel);

        heroDefenseLabel = new JLabel("Defense");
        add(heroDefenseLabel);

        heroLevelBar = new JProgressBar(0, 100);
        heroLevelBar.setPreferredSize(new Dimension(200, 40));
        heroLevelBar.setStringPainted(true);
        add(heroLevelBar);

        setBounds(20, 20, 200, 100);
    }

    public void update(Hero hero) {

        heroNameLabel.setText(hero.getModel().getName());
        heroAttackLevel.setText(String.format("%d", hero.getAttackDamage()));
        heroDefenseLabel.setText(String.format("%d", hero.getDefense()));

        double percentage = Utils.getLevelPercentage(hero.getLevel(), hero.getModel().getExperience());

        heroLevelBar.setValue((int) (percentage * 100));
        heroLevelBar.setString(String.format("%d (%.0f %%)", hero.getLevel(), (percentage * 100)));

        heroHealthBar.setMaximum(hero.getMaxHitPoints());
        heroHealthBar.setValue(hero.getHitPoints());
        heroHealthBar.setString(String.format("%d / %d", hero.getHitPoints(), hero.getMaxHitPoints()));

    }
}
