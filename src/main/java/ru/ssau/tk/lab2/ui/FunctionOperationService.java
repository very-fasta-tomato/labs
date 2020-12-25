package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FunctionOperationService extends JDialog {
    JPanel panel = new JPanel();
    static TabulatedFunctionOperationService functionOperationService;
    static TabulatedFunction firstTabulatedFunction;
    static TabulatedFunction secondTabulatedFunction;
    static TabulatedFunction resultTabulatedFunction;
    TypeOfCreatingFunction type;
    CalculateOperation calculateOperation = CalculateOperation.SUM;

    public FunctionOperationService(JFrame owner) {
        super(owner, "Tabulated function operation service", true);
        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);

        JMenu menuFile = new JMenu("File");
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(e -> this.dispose());
        menuFile.add(menuExit);
        JMenu menuCreating = new JMenu("Creating options");
        JMenuItem mathFunction = new JMenuItem("Create from math function");
        JMenuItem array = new JMenuItem("Create from array");
        mathFunction.addActionListener(e -> type = TypeOfCreatingFunction.FUNCTION);
        array.addActionListener(e -> type = TypeOfCreatingFunction.ARRAY);
        menuCreating.add(mathFunction);
        menuCreating.add(array);
        JMenu menuCalculatingOperation = new JMenu("Calculating operation");
        JMenuItem sum = new JMenuItem("Sum");
        sum.addActionListener(e -> calculateOperation = CalculateOperation.SUM);
        JMenuItem subtract = new JMenuItem("Subtract");
        subtract.addActionListener(e -> calculateOperation = CalculateOperation.SUBTRACT);
        JMenuItem multiply = new JMenuItem("Multiply");
        multiply.addActionListener(e -> calculateOperation = CalculateOperation.MULTIPLY);
        JMenuItem division = new JMenuItem("Division");
        division.addActionListener(e -> calculateOperation = CalculateOperation.DIVISION);
        menuCalculatingOperation.add(sum);
        menuCalculatingOperation.add(subtract);
        menuCalculatingOperation.add(multiply);
        menuCalculatingOperation.add(division);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuCreating);
        menuBar.add(menuCalculatingOperation);

        AbstractTableModel firstTableModel = new OperationTableModel(firstTabulatedFunction);
        JTable firstFunctionTable = new JTable(firstTableModel);
        firstFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane firstTableScrollPane = new JScrollPane(firstFunctionTable);
        firstTableScrollPane.setBounds(10, 50, 250, 260);

        AbstractTableModel secondTableModel = new OperationTableModel(secondTabulatedFunction);
        JTable secondFunctionTable = new JTable(secondTableModel);
        secondFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane secondTableScrollPane = new JScrollPane(secondFunctionTable);
        secondTableScrollPane.setBounds(270, 50, 250, 260);

        AbstractTableModel resultTableModel = new ResultTableModel(resultTabulatedFunction);
        JTable resultFunctionTable = new JTable(resultTableModel);
        resultFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane resultTableScrollPane = new JScrollPane(resultFunctionTable);
        resultTableScrollPane.setBounds(530, 50, 250, 260);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(780, 400, Index.buttonWidth, Index.buttonHeight);
        exitButton.addActionListener(e -> this.dispose());

        JButton createButton1 = new JButton("Create");
        createButton1.setBounds(85, 320, Index.buttonWidth, Index.buttonHeight);
        createButton1.addActionListener(e -> {
            if (type == TypeOfCreatingFunction.ARRAY) {
                ArrayCreatingFunction arrayCreatingFunctionDialog = new ArrayCreatingFunction(owner);
                arrayCreatingFunctionDialog.setVisible(true);
                arrayCreatingFunctionDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        firstTabulatedFunction = arrayCreatingFunctionDialog.getTabulatedFunction();
                        
                        firstTableModel.fireTableDataChanged();
                    }
                });
            } else {
                MathFunctionCreatingFunction mathFunctionCreatingFunctionDialog = new MathFunctionCreatingFunction(owner);
                mathFunctionCreatingFunctionDialog.setVisible(true);
                mathFunctionCreatingFunctionDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        firstTabulatedFunction = mathFunctionCreatingFunctionDialog.getTabulatedFunction();
                        firstTableModel.fireTableDataChanged();
                    }
                });
            }
        });

        JButton loadButton1 = new JButton("Load");
        loadButton1.setBounds(85, 360, Index.buttonWidth, Index.buttonHeight);

        JButton safeButton1 = new JButton("Safe");
        safeButton1.setBounds(85, 400, Index.buttonWidth, Index.buttonHeight);

        JButton createButton2 = new JButton("Create");
        createButton2.setBounds(345, 320, Index.buttonWidth, Index.buttonHeight);
        createButton2.addActionListener(e -> {
            if (type == TypeOfCreatingFunction.ARRAY) {
                ArrayCreatingFunction arrayCreatingFunctionDialog = new ArrayCreatingFunction(owner);
                arrayCreatingFunctionDialog.setVisible(true);
                arrayCreatingFunctionDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        secondTabulatedFunction = arrayCreatingFunctionDialog.getTabulatedFunction();
                        secondTableModel.fireTableDataChanged();
                    }
                });
            } else {
                MathFunctionCreatingFunction mathFunctionCreatingFunctionDialog = new MathFunctionCreatingFunction(owner);
                mathFunctionCreatingFunctionDialog.setVisible(true);
                mathFunctionCreatingFunctionDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        secondTabulatedFunction = mathFunctionCreatingFunctionDialog.getTabulatedFunction();
                        secondTableModel.fireTableDataChanged();
                    }
                });
            }
        });

        JButton loadButton2 = new JButton("Load");
        loadButton2.setBounds(345, 360, Index.buttonWidth, Index.buttonHeight);

        JButton safeButton2 = new JButton("Safe");
        safeButton2.setBounds(345, 400, Index.buttonWidth, Index.buttonHeight);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(605, 320, Index.buttonWidth, Index.buttonHeight);
        calculateButton.addActionListener(e -> {
            switch (calculateOperation) {
                case SUM -> {
                    resultTabulatedFunction =
                            functionOperationService.sum(firstTabulatedFunction, secondTabulatedFunction);
                    resultTableModel.fireTableDataChanged();
                }
                case SUBTRACT -> {
                    resultTabulatedFunction =
                            functionOperationService.subtract(firstTabulatedFunction, secondTabulatedFunction);
                    resultTableModel.fireTableDataChanged();
                }
                case MULTIPLY -> {
                    resultTabulatedFunction =
                            functionOperationService.multiply(firstTabulatedFunction, secondTabulatedFunction);
                    resultTableModel.fireTableDataChanged();
                }
                case DIVISION -> {
                    resultTabulatedFunction =
                            functionOperationService.division(firstTabulatedFunction, secondTabulatedFunction);
                    resultTableModel.fireTableDataChanged();
                }
            }
        });

        JButton safeButton3 = new JButton("Safe");
        safeButton3.setBounds(605, 360, Index.buttonWidth, Index.buttonHeight);

        panel.add(exitButton);
        panel.add(createButton1);
        panel.add(safeButton1);
        panel.add(loadButton1);
        panel.add(createButton2);
        panel.add(safeButton2);
        panel.add(loadButton2);
        panel.add(safeButton3);
        panel.add(calculateButton);
        panel.add(firstTableScrollPane);
        panel.add(secondTableScrollPane);
        panel.add(resultTableScrollPane);
        setLocationRelativeTo(null);
        this.setJMenuBar(menuBar);
        getContentPane().add(panel);
    }

}
