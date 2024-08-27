package org.example.run.components;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    private DivergenceMeter divergenceMeter;
    private int timeElapsed;
    private boolean active;

    public TimerPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        setBorder(BorderFactory.createEtchedBorder());

        add(new DivergenceMeter());
    }
}
