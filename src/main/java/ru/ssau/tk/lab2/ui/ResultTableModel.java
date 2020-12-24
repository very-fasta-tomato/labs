package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.table.AbstractTableModel;

public class ResultTableModel extends AbstractTableModel {
    int rowCount;
    TabulatedFunction tabulatedFunction;

    public ResultTableModel (){
        this.rowCount = 0;
    }

    public ResultTableModel (TabulatedFunction tabulatedFunction){
        this.tabulatedFunction = tabulatedFunction;
        this.rowCount = tabulatedFunction.getCount();
    }

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
        if (rowCount != 0){
            return switch (columnIndex){
                case 0 -> rowIndex;
                case 1 -> tabulatedFunction.getX(rowIndex);
                case 2 -> tabulatedFunction.getY(rowIndex);
                default -> "not stated";
            };
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
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
