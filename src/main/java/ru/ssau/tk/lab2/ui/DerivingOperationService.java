package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.*;

public class DerivingOperationService extends JDialog {
    JPanel panel = new JPanel();

    static TabulatedFunction tabulatedFunction;
    static TabulatedFunction derivedTabulatedFunction;

    public DerivingOperationService(JFrame owner){
        super(owner, "Deriving operation service", true);
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);
    }
}
