package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import javax.swing.*;

public class FunctionOperationService extends JDialog {
    JPanel panel = new JPanel();
    TabulatedFunctionOperationService functionOperationService = new TabulatedFunctionOperationService(Index.factory);
    static TabulatedFunction firstTabulatedFunction;
    static TabulatedFunction secondTabulatedFunction;
    TabulatedFunction resultTabulatedFunction;
    TypeOfCreatingFunction type;
    CalculateOperation calculateOperation;

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

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(780, 400, Index.buttonWidth, Index.buttonHeight);
        exitButton.addActionListener(e -> this.dispose());

        JButton createButton1 = new JButton("Create");
        createButton1.setBounds(85, 320, Index.buttonWidth, Index.buttonHeight);
        createButton1.addActionListener(e -> {
            if (type == TypeOfCreatingFunction.ARRAY) {
                JDialog arrayCreatingFunctionDialig = new ArrayCreatingFunction(owner);
                arrayCreatingFunctionDialig.setVisible(true);
                firstTabulatedFunction = ArrayCreatingFunction.tabulatedFunction;
            } else {
                JDialog mathFunctionCreatingFunctionDialig = new MathFunctionCreatingFunction(owner);
                mathFunctionCreatingFunctionDialig.setVisible(true);
                firstTabulatedFunction = MathFunctionCreatingFunction.tabulatedFunction;
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
                JDialog arrayCreatingFunctionDialig = new ArrayCreatingFunction(owner);
                arrayCreatingFunctionDialig.setVisible(true);
                secondTabulatedFunction = ArrayCreatingFunction.tabulatedFunction;
            } else {
                JDialog mathFunctionCreatingFunctionDialig = new MathFunctionCreatingFunction(owner);
                mathFunctionCreatingFunctionDialig.setVisible(true);
                secondTabulatedFunction = MathFunctionCreatingFunction.tabulatedFunction;
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
                case SUM -> resultTabulatedFunction =
                        functionOperationService.sum(firstTabulatedFunction, secondTabulatedFunction);
                case SUBTRACT -> resultTabulatedFunction =
                        functionOperationService.subtract(firstTabulatedFunction, secondTabulatedFunction);
                case MULTIPLY -> resultTabulatedFunction =
                        functionOperationService.multiply(firstTabulatedFunction, secondTabulatedFunction);
                case DIVISION -> resultTabulatedFunction =
                        functionOperationService.division(firstTabulatedFunction, secondTabulatedFunction);
            }
        });

        JButton safeButton3 = new JButton("Safe");
        safeButton3.setBounds(605, 360, Index.buttonWidth, Index.buttonHeight);

        JTable firstFunctionTable = new JTable(new OperationTableModel());
        firstFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane firstTableScrollPane = new JScrollPane(firstFunctionTable);
        firstTableScrollPane.setBounds(10, 50, 250, 260);

        JTable secondFunctionTable = new JTable(new OperationTableModel());
        secondFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane secondTableScrollPane = new JScrollPane(secondFunctionTable);
        secondTableScrollPane.setBounds(270, 50, 250, 260);

        JTable resultFunctionTable = new JTable(new ResultTableModel());
        resultFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane resultTableScrollPane = new JScrollPane(resultFunctionTable);
        resultTableScrollPane.setBounds(530, 50, 250, 260);

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
        this.setJMenuBar(menuBar);
        getContentPane().add(panel);
    }

}
