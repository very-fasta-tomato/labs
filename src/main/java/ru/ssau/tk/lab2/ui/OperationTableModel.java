package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

import javax.swing.table.AbstractTableModel;

public class OperationTableModel extends AbstractTableModel {
    int rowCount;
    TabulatedFunction tabulatedFunction;

    public OperationTableModel (TabulatedFunction tabulatedFunction){
        this.tabulatedFunction = tabulatedFunction;
        this.rowCount = tabulatedFunction.getCount();
    }

    public OperationTableModel (){
        this.rowCount = 0;
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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if ((columnIndex == 0) || (columnIndex == 1)){
            return;
        }
        tabulatedFunction.setY(rowIndex, (double) aValue);
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
