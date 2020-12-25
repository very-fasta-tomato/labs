package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.*;

public class MathFunctionCreatingFunction extends JDialog {
    JPanel panel = new JPanel();
    TabulatedFunction tabulatedFunction;

    public MathFunctionCreatingFunction(JFrame owner){
        super(owner, "math function", true);
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);
    }

    public TabulatedFunction getTabulatedFunction(){
        return tabulatedFunction;
    }
}
