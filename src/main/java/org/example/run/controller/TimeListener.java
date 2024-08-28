package org.example.run.controller;

import org.example.run.pomodoro.PomodoroState;

public interface TimeListener {
    public void stateChangeOccurred(PomodoroState state);
}
