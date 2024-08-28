package org.example.run.controller;

import org.example.run.pomodoro.PomodoroState;

public interface DrawListener {
    public void randomEventOccurred();
    public void startEventOccurred();
    public void pauseEventOccurred();
    public void resetEventOccurred();
}
