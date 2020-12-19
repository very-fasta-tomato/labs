package ru.ssau.tk.lab2.ui;

import javax.swing.table.AbstractTableModel;

public class OperationTableModel extends AbstractTableModel {
    static int rowCount = 0;

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return 0;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public String getColumnName(int columnIndex){
        return switch (columnIndex) {
            case 0 -> "№";
            case 1 -> "X";
            case 2 -> "Y";
            default -> "not stated";
        };
    }

}
