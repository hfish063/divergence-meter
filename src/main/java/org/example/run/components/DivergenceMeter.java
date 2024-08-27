package org.example.run.components;

import javax.swing.*;
import java.awt.*;

public class DivergenceMeter extends JPanel {
    private static final int numOfTubes = 8;
    private JLabel[] tubes;

    public DivergenceMeter() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(0);
        setLayout(layout);

        tubes = new JLabel[numOfTubes];

        setInitialValues();
        addLabelsToComponent();
    }

    private void setInitialValues() {
        initLabels();

        drawPoints();

        reset();
    }

    private void initLabels() {
        for(int i = 0; i < tubes.length; i++) {
            tubes[i] = new JLabel();
        }
    }

    private void addLabelsToComponent() {
        for (JLabel tube : tubes) {
            add(tube);
        }
    }

    public void reset() {
        setHours(0);
        setMinutes(0);
        setSeconds(0);
    }

    public void setHours(int hours) {
        int value = hours / 10;
        tubes[0].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/" + value + ".png")));

        value = hours % 10;
        tubes[1].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/" + value + ".png")));
    }

    public void setMinutes(int minutes) {
        int value = minutes / 10;
        tubes[3].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/" + value + ".png")));

        value = minutes % 10;
        tubes[4].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/" + value + ".png")));
    }

    public void setSeconds(int seconds) {
        int value = seconds / 10;
        tubes[6].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/" + value + ".png")));

        value = seconds % 10;
        tubes[7].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/" + value + ".png")));
    }

    private void drawPoints() {
        tubes[2].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/point.png")));
        tubes[5].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/point.png")));
    }
}
