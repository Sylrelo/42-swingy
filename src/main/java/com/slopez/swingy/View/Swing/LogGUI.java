package com.slopez.swingy.View.Swing;

import java.util.List;

import javax.swing.*;
import java.awt.*;

public class LogGUI extends JPanel {

    private JTextArea logArea;

    public LogGUI() {

        GridLayout layout = new GridLayout(0, 1);
        setLayout(layout);

        logArea = new JTextArea(24, 24);
        logArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll);
        setBounds(20, 140, 460, 100);
    }

    public void update(List<String> logs) {

        StringBuffer tmplogs = new StringBuffer();

        for (String str : logs) {
            tmplogs.append(str + "\n");
        }

        logArea.setText(tmplogs.toString());
    }
}
