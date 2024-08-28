package org.example.run.utils;

import javax.swing.*;

public class ImageUtils {
    public ImageIcon findImage(String imageName) {
        return new ImageIcon(getClass().getClassLoader().getResource("images/" + imageName + ".png"));
    }
}
