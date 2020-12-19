package ru.ssau.tk.lab2.ui;

import javax.swing.*;

public class MathFunctionCreatingFunction extends JDialog {
    JPanel panel = new JPanel();

    public MathFunctionCreatingFunction(JFrame owner){
        super(owner, "math function", true);
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);
    }
}
