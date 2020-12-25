package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MathFunctionCreatingFunction extends JDialog {
    static boolean isFirstFunction;
    JPanel panel = new JPanel();
    static TabulatedFunction tabulatedFunction;

    public MathFunctionCreatingFunction(JFrame owner){
        super(owner, "math function", true);
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
