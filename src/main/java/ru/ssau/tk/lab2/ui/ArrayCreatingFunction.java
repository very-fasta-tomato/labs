package ru.ssau.tk.lab2.ui;

import javax.swing.*;

public class ArrayCreatingFunction extends JDialog {
    JPanel panel = new JPanel();

    public ArrayCreatingFunction(JFrame owner){
        super(owner, "array", true);
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);
    }
}
