package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lab2.functions.TabulatedFunction;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class ArrayCreatingFunction extends JDialog {
    JPanel panel = new JPanel();
    TabulatedFunction tabulatedFunction;
    int amountOfPoints;


    public ArrayCreatingFunction(JFrame owner) {
        super(owner, "array", true);
        setSize(350, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);
        JTextField textField = new JTextField("", 10);
        textField.setBounds(10, 10, 50, 30);
        JButton nextButton = new JButton("OK");
        nextButton.setBounds(10, 60, 80, 20);
        JTable table = new JTable(new Model());
        JButton completeButton = new JButton("Finish");
        completeButton.setBounds(200, 60, 80, 20);
        table.setVisible(false);
        completeButton.setVisible(false);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountOfPoints = Integer.parseInt(textField.getText());
                table.setVisible(true);
                completeButton.setVisible(true);
            }
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(10, 80, 300, 400);
        panel.add(tableScrollPane);
        panel.add(completeButton);
        panel.add(textField);
        panel.add(nextButton);
        getContentPane().add(panel);
    }

    class Model extends AbstractTableModel {
        @Override
        public int getRowCount() {
            return amountOfPoints;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (amountOfPoints != 0) {
                return switch (columnIndex) {
                    case 1 -> "";
                    case 2 -> "";
                    default -> "not stated";
                };
            } else {
                return 0;
            }
        }

        @Override
        public String getColumnName(int columnIndex) {
            return switch (columnIndex) {
                case 0 -> "X";
                case 1 -> "Y";
                default -> "not stated";
            };
        }


    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }
}
