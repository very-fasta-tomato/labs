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

        OperationTableModel functionTableModel = new OperationTableModel(tabulatedFunction);
        JTable functionTable = new JTable(functionTableModel);
        functionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane functionTableScrollPane = new JScrollPane(functionTable);
        functionTableScrollPane.setBounds(10, 50, 250, 260);

        ResultTableModel resultTableModel = new ResultTableModel(derivedTabulatedFunction);
        JTable resultTable = new JTable(resultTableModel);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane resultTableScrollPane = new JScrollPane(resultTable);
        resultTableScrollPane.setBounds(270, 50, 250, 260);

        JLabel functionTableLabel = new JLabel("Tabulated function");
        functionTableLabel.setBounds(10, 10, 250, 30);
        functionTableLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel resultTableLabel = new JLabel("Derived tabulated function");
        resultTableLabel.setBounds(270, 10, 250, 30);
        resultTableLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(480, 420, Index.buttonWidth, Index.buttonHeight);
        exitButton.addActionListener(e -> this.dispose());

        panel.add(functionTableScrollPane);
        panel.add(resultTableScrollPane);
        panel.add(functionTableLabel);
        panel.add(resultTableLabel);
        panel.add(exitButton);
        getContentPane().add(panel);
    }
}
