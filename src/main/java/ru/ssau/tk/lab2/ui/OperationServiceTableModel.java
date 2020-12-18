package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class OperationServiceTableModel extends AbstractTableModel {
    private int rowCount;
    private TabulatedFunction tabulatedFunction;

    /*public OperationServiceTableModel (TabulatedFunction tabulatedFunction){
        this.tabulatedFunction = tabulatedFunction;
        this.rowCount = tabulatedFunction.getCount();
    }*/

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> rowIndex;
            case 1, 2 -> new JTextField();
            default -> null;
        };
    }
}
