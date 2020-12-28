package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class ArrayCreatingFunction extends JDialog {
    JPanel panel = new JPanel();
    TabulatedFunction tabulatedFunction;
    int amountOfPoints;
    double[] xValues;
    double[] yValues;

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
        nextButton.addActionListener(e -> {
            try {
                amountOfPoints = Integer.parseInt(textField.getText());
                if (amountOfPoints <= 1) {
                    completeButton.addActionListener(e1 -> {
                        JOptionPane.showMessageDialog(ArrayCreatingFunction.this,
                                "Колличество точек должно быть больше 1",
                                "Eror", JOptionPane.INFORMATION_MESSAGE);
                    });
                };
            } catch (NumberFormatException exception) {
                new ExceptionWindow(new ExceptionPanel(exception));
            } catch (NullPointerException exception) {
                new ExceptionWindow(new ExceptionPanel(exception));
            } catch (IllegalArgumentException exception) {
                new ExceptionWindow(new ExceptionPanel(exception));
            }
            xValues = new double[amountOfPoints];
            yValues = new double[amountOfPoints];
            table.setVisible(true);
            completeButton.setVisible(true);
        });
        completeButton.addActionListener(e -> {
            tabulatedFunction = Index.factory.create(xValues, yValues);
            this.dispose();
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
                    case 0 -> xValues[rowIndex];
                    case 1 -> yValues[rowIndex];
                    default -> "not stated";
                };
            } else {
                return 0;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0 -> xValues[rowIndex] = Double.parseDouble(aValue.toString());
                case 1 -> yValues[rowIndex] = Double.parseDouble(aValue.toString());
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

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }
    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }
}