package org.example.run.components;

import org.example.run.controller.TimeListener;
import org.example.run.pomodoro.Pomodoro;
import org.example.run.pomodoro.PomodoroState;
import org.example.run.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DivergenceMeter extends JPanel implements ActionListener {
    private static final int NUM_OF_TUBES = 8;
    private static final int ONE_SECOND = 1;

    private Pomodoro pomodoro;
    private ImageUtils imageUtils;

    private JLabel[] tubes;
    private Timer timer;

    private TimeListener timeListener;

    private Color borderColor;

    private int timeElapsed;

    public DivergenceMeter() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(0);
        layout.setVgap(0);
        setLayout(layout);

        pomodoro = new Pomodoro();
        tubes = new JLabel[NUM_OF_TUBES];
        imageUtils = new ImageUtils();
        timer = new Timer(ONE_SECOND, this);
        borderColor = Color.BLACK;

        setBorder(BorderFactory.createLineBorder(borderColor));

        timeElapsed = 0;

        setInitialValues();

        for (JLabel tube : tubes) {
            add(tube);
        }
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

    public void start() {
        timer.start();
    }

    public void pause() {
        timer.stop();
    }

    public void reset() {
        setHours(0);
        setMinutes(0);
        setSeconds(0);

        timeElapsed = 0;

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        timer.stop();
    }

    private void drawPoints() {
        tubes[2].setIcon(imageUtils.findImage("point"));
        tubes[5].setIcon(imageUtils.findImage("point"));
    }

    /**
     * Sets the hour tubes (denoted by 1st and 2nd timer digits) to the provided hour
     * @param hours this integer value is not verified, assumed to be of valid format 0-24
     */
    public void setHours(int hours) {
        int value = hours / 10;
        tubes[0].setIcon(imageUtils.findImage(String.valueOf(value)));

        value = hours % 10;
        tubes[1].setIcon(imageUtils.findImage(String.valueOf(value)));
    }

    /**
     * Sets the minute tubes (denoted by 3rd and 4th timer digits) to the provided minute(s)
     * @param minutes this integer value is not verified, assumed to be of valid format 0-60
     */
    public void setMinutes(int minutes) {
        int value = minutes / 10;
        tubes[3].setIcon(imageUtils.findImage(String.valueOf(value)));

        value = minutes % 10;
        tubes[4].setIcon(imageUtils.findImage(String.valueOf(value)));
    }

    /**
     * Sets the second tubes (denoted by 5th and 6th timer digits) to the provided second(s)
     * @param seconds this integer value is not verified, assumed to be of valid format 0-60
     */
    public void setSeconds(int seconds) {
        int value = seconds / 10;
        tubes[6].setIcon(imageUtils.findImage(String.valueOf(value)));

        value = seconds % 10;
        tubes[7].setIcon(imageUtils.findImage(String.valueOf(value)));
    }

    public void setTimeListener(TimeListener listener) {
        timeListener = listener;
    }

    /**
     * Performs pomodoro logic when timer state changes
     * This is where we handle the looping between different states, and update the times accordingly
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int timeRemaining = pomodoro.getDurationOfCurrentState() - timeElapsed;
        timeListener.stateChangeOccurred(pomodoro.getCurrentState());
        changeBorderColor(pomodoro.getCurrentState());

        // when remaining time runs out, the pomodoro state advances
        if (timeRemaining <= 0) {
            timeElapsed = 1; // Important: set to 1 in order to account for current tick
            pomodoro.advanceCurrentState();

            int temp = pomodoro.getDurationOfCurrentState();

            int minutes = pomodoro.getDurationOfCurrentState() / 60;
            temp -= minutes * 60;

            int seconds = temp;

            setMinutes(minutes);
            setSeconds(seconds);
        } else {
            timeElapsed++;

            setMinutes((pomodoro.getDurationOfCurrentState() - timeElapsed) / 60);
            setSeconds((pomodoro.getDurationOfCurrentState() - timeElapsed) % 60);
        }
    }

    private void changeBorderColor(PomodoroState state) {
        borderColor = pomodoro.getColorForState(state);

        // new border object is created and assigned to this JPanel, not able to change color in place
        setBorder(BorderFactory.createLineBorder(borderColor, 3));
    }

    public Color getBorderColor() {
        return borderColor;
    }
}
