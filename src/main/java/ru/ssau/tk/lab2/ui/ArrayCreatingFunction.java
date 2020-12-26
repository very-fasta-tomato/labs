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
    private JTable table;


    public ArrayCreatingFunction(JFrame owner) {
        super(owner, "array", true);
        setSize(800, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);
        JTextField textField = new JTextField("amount of points", 10);
        textField.setBounds(10, 10, 200, 30);
        JButton nextButton = new JButton("OK");
        nextButton.setBounds(60, 60, 80, 20);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountOfPoints = Integer.parseInt(textField.getText());
                JTable table = new JTable(new Model());
                table.setBounds(70, 200, 300, 400);
            }
        });
        panel.add(textField);
        panel.add(nextButton);
        panel.add(new JScrollPane(table));
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
                    case 0 -> rowIndex;
                    case 1 -> 0;
                    case 2 -> 0;
                    default -> "not stated";
                };
            } else {
                return 0;
            }
        }

        @Override
        public String getColumnName(int columnIndex) {
            return switch (columnIndex) {
                case 0 -> "№";
                case 1 -> "X";
                case 2 -> "Y";
                default -> "not stated";
            };
        }


    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }
}
