package org.example.run.utils;

import org.example.run.pomodoro.PomodoroState;

import java.awt.*;

public class PomodoroUtils {
    public static Color getColorForPomodoroState(PomodoroState state) {
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
