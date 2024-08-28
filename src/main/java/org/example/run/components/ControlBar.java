package org.example.run.components;

import org.example.run.controller.DrawListener;
import org.example.run.pomodoro.PomodoroState;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.run.utils.PomodoroUtils.getColorForPomodoroState;

public class ControlBar extends JPanel implements ActionListener {
    private JButton startBtn;
    private JButton pauseBtn;
    private JButton resetBtn;
    private JButton randomBtn;
    private JLabel stateLabel;

    private DrawListener drawListener;

    public ControlBar() {
        startBtn = new JButton("Start");
        pauseBtn = new JButton("Pause");
        resetBtn = new JButton("Reset");
        randomBtn = new JButton("Random");
        stateLabel = new JLabel();

        startBtn.addActionListener(this);
        pauseBtn.addActionListener(this);
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

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(stateLabel, constraints);
    }

    public void setDrawListener(DrawListener listener) {
        drawListener = listener;
    }

    public void setStateLabel(PomodoroState state) {
        stateLabel.setText(state.toString());

        Color textColor = getColorForPomodoroState(state);

        stateLabel.setForeground(textColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = (JButton) e.getSource();

        if (source.equals(randomBtn)) {
            drawListener.randomEventOccurred();
        } else if (source.equals(resetBtn)) {
            drawListener.resetEventOccurred();
        } else if (source.equals(startBtn)) {
            drawListener.startEventOccurred();
        } else if (source.equals(pauseBtn)) {
            drawListener.pauseEventOccurred();
        }
    }
}
