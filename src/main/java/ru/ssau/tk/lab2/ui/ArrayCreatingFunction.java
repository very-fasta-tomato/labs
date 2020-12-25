package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.*;

public class ArrayCreatingFunction extends JDialog {
    JPanel panel = new JPanel();
    TabulatedFunction tabulatedFunction;

    public ArrayCreatingFunction(JFrame owner){
        super(owner, "array", true);
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);
    }

    public TabulatedFunction getTabulatedFunction(){
        return tabulatedFunction;
    }
}
