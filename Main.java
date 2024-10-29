package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Swing GUI'yi başlat
        SwingUtilities.invokeLater(() -> {
            new InputArrayListGUI();
        });
    }
}
