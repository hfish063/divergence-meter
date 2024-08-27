package org.example.run.controller;

import org.example.run.components.ControlBar;
import org.example.run.components.DivergenceMeter;
import org.example.run.components.GenerateListener;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainFrame extends JFrame implements GenerateListener {
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

        controlBar.setGenerateListener(this);

        add(divergenceMeter, BorderLayout.NORTH);
        add(controlBar, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Important: size window based off preferred sizes of components
        setVisible(true);
    }

    @Override
    public void randomEventOccured() {
        Random rand = new Random();

        divergenceMeter.setHours(rand.nextInt(24) + 1);
        divergenceMeter.setMinutes(rand.nextInt(60) + 1);
        divergenceMeter.setSeconds(rand.nextInt(60) + 1);
    }

    @Override
    public void resetEventOccured() {
        divergenceMeter.reset();
    }
}
