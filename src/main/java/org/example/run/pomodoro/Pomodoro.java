package org.example.run.pomodoro;

import java.awt.*;
import java.util.Stack;

public class Pomodoro {
    private static final int ACTIVE = 1500; // 25 minutes
    private static final int BREAK = 300; // 5 minutes
    private static final int LONG_BREAK = 1500; // 25 minutes

    private PomodoroState currentState;

    private Stack<PomodoroState> stateQueue;

    public Pomodoro() {
        initStateQueue();

        currentState = stateQueue.pop();
    }

    /**
     * Goes to the next state, if stateQueue is empty then initialize again in order to restart the cycle
     */
    public void advanceCurrentState() {
        currentState = stateQueue.pop();

        if (stateQueue.isEmpty()) {
            initStateQueue();
        }
    }

    public int getDurationOfCurrentState() {
        int seconds = 0;

        if (currentState == PomodoroState.ACTIVE) {
            seconds = ACTIVE;
        } else if (currentState == PomodoroState.BREAK) {
            seconds = BREAK;
        } else if (currentState == PomodoroState.LONG_BREAK) {
            seconds = LONG_BREAK;
        }

        return seconds;
    }

    /**
     * This is where we define the default states of Pomodoro
     * Next state will always be at the top of stack, hence they must be initially appended in opposite order i.e. last-first
     */
    private void initStateQueue() {
        stateQueue = new Stack<>();

        stateQueue.push(PomodoroState.LONG_BREAK);
        stateQueue.push(PomodoroState.ACTIVE);
        stateQueue.push(PomodoroState.BREAK);
        stateQueue.push(PomodoroState.ACTIVE);
        stateQueue.push(PomodoroState.BREAK);
        stateQueue.push(PomodoroState.ACTIVE);
        stateQueue.push(PomodoroState.BREAK);
        stateQueue.push(PomodoroState.ACTIVE);
    }

    public PomodoroState getCurrentState() {
        return currentState;
    }

    public Color getColorForState(PomodoroState state) {
        Color result = Color.BLACK;

        if (state == PomodoroState.ACTIVE) {
            result = Color.RED;
        } else if (state == PomodoroState.BREAK) {
            result = Color.BLUE;
        } else {
            result = Color.GREEN;
        }

        return result;
    }
}
