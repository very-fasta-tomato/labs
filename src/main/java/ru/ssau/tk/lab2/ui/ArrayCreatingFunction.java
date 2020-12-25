package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ArrayCreatingFunction extends JDialog {
    static boolean isFirstFunction;
    JPanel panel = new JPanel();
    static TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(new double[] {1., 2., 3.}, new double[] {1., 5., 6.});

    public ArrayCreatingFunction(JFrame owner){
        super(owner, "array", true);
        setSize(100, 100);
        panel.setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (isFirstFunction){
                    FunctionOperationService.firstTabulatedFunction = tabulatedFunction;
                }
                else {
                    FunctionOperationService.secondTabulatedFunction = tabulatedFunction;
                }
            }
        });
    }
}
