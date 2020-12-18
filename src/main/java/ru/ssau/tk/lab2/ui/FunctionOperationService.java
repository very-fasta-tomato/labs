package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import javax.swing.*;

public class FunctionOperationService extends JDialog {
    JPanel panel = new JPanel();
    TabulatedFunctionOperationService functionOperationService = new TabulatedFunctionOperationService(Index.factory);
    TabulatedFunction firstTabulatedFunction;
    TabulatedFunction secondTabulatedFunction;
    TabulatedFunction resultTabulatedFunction;

    public FunctionOperationService(JFrame owner){
        super(owner, "Tabulated function operation service", true);
        setSize(900, 480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(780, 410, Index.buttonWidth, Index.buttonHeight);
        exitButton.addActionListener(e -> this.dispose());

        JTable firstTable = new JTable(new OperationServiceTableModel());
        firstTable.setBounds(10, 50, 250, 300);

        JButton addButton1 = new JButton("add");
        addButton1.setBounds(85, 370, Index.buttonWidth, Index.buttonHeight);
        addButton1.addActionListener(e -> {

        });

        panel.add(firstTable);
        panel.add(exitButton);
        panel.add(addButton1);
        getContentPane().add(panel);
    }

}
