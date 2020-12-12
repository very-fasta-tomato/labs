package ru.ssau.tk.lab2.ui;

import javax.swing.*;

public class Index extends JFrame {
    final private static int buttonWidth = 100;
    final private static int buttonHeight = 30;

    public static void main(String[] args) {
        JFrame index = new JFrame("Tabulated functions calculator");
        index.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        index.setSize(640, 480);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(520, 410, buttonWidth, buttonHeight);
        exitButton.addActionListener(e -> index.dispose());
        panel.add(exitButton);
        index.getContentPane().add(panel);
        index.setVisible(true);
    }
}
