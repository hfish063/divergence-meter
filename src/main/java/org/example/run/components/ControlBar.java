package org.example.run.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBar extends JPanel implements ActionListener {
    private JButton startBtn;
    private JButton pauseBtn;
    private JButton resetBtn;
    private JButton randomBtn;
    private GenerateListener generateListener;

    public ControlBar() {
        startBtn = new JButton("Start");
        pauseBtn = new JButton("Pause");
        resetBtn = new JButton("Reset");
        randomBtn = new JButton("Random");

        resetBtn.addActionListener(this);
        randomBtn.addActionListener(this);

        Border innerBorder = BorderFactory.createLoweredBevelBorder();
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());
        setupLayout();
    }

    private void setupLayout() {
        GridBagConstraints constraints = new GridBagConstraints();

        // first row
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(startBtn, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.PAGE_START;
        add(pauseBtn, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        add(resetBtn, constraints);

        // second row
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(randomBtn, constraints);
    }

    public void setGenerateListener(GenerateListener listener) {
        generateListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = (JButton) e.getSource();

        if (source.equals(randomBtn)) {
            generateListener.randomEventOccured();
        } else if (source.equals(resetBtn)) {
            generateListener.resetEventOccured();
        }
    }
}
