package org.example.run.pomodoro;

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
}
