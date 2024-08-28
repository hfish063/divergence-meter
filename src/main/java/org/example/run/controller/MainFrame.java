package org.example.run.controller;

import org.example.run.components.ControlBar;
import org.example.run.components.DivergenceMeter;
import org.example.run.pomodoro.PomodoroState;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainFrame extends JFrame implements DrawListener, TimeListener {
    private DivergenceMeter divergenceMeter;
    private ControlBar controlBar;

    public MainFrame() {
        super("Timer");

        setupFrame();
    }

    private void setupFrame() {
        setLayout(new BorderLayout());

        divergenceMeter = new DivergenceMeter();
        controlBar = new ControlBar();

        divergenceMeter.setTimeListener(this);
        controlBar.setDrawListener(this);

        add(divergenceMeter, BorderLayout.NORTH);
        add(controlBar, BorderLayout.SOUTH);

        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Important: size window based off preferred sizes of components
        setVisible(true);
    }

    @Override
    public void stateChangeOccurred(PomodoroState state) {
        controlBar.setStateLabel(state);
    }

    @Override
    public void randomEventOccurred() {
        Random rand = new Random();

        divergenceMeter.reset();

        divergenceMeter.setHours(rand.nextInt(24) + 1);
        divergenceMeter.setMinutes(rand.nextInt(60) + 1);
        divergenceMeter.setSeconds(rand.nextInt(60) + 1);
    }

    @Override
    public void startEventOccurred() {
        divergenceMeter.start();
    }

    @Override
    public void pauseEventOccurred() {
        divergenceMeter.pause();
    }

    @Override
    public void resetEventOccurred() {
        divergenceMeter.reset();
    }
}
