package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.*;

public class ArrayCreatingFunction extends JDialog {
    JPanel panel = new JPanel();
    static TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(new double[]{0, 1, 2, 3}, new double[]{1, 1, 1, 1});

    public ArrayCreatingFunction(JFrame owner){
        super(owner, "array", true);
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);

    }
}
