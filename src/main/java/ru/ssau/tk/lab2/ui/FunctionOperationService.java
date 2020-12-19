package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.functions.TypeOfCreatingFunction;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import javax.swing.*;

public class FunctionOperationService extends JDialog {
    JPanel panel = new JPanel();
    TabulatedFunctionOperationService functionOperationService = new TabulatedFunctionOperationService(Index.factory);
    TabulatedFunction firstTabulatedFunction;
    TabulatedFunction secondTabulatedFunction;
    TabulatedFunction resultTabulatedFunction;
    TypeOfCreatingFunction type;


    public FunctionOperationService(JFrame owner){
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
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuCreating);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(780, 400, Index.buttonWidth, Index.buttonHeight);
        exitButton.addActionListener(e -> this.dispose());

        JButton createButton1 = new JButton("Create");
        createButton1.setBounds(85, 320, Index.buttonWidth, Index.buttonHeight);
        createButton1.addActionListener(e -> {
            if (type == TypeOfCreatingFunction.ARRAY){
                JDialog arrayCreatingFunctionDialig = new ArrayCreatingFunction(owner);
                arrayCreatingFunctionDialig.setVisible(true);
            }
            else {
                JDialog mathFunctionCreatingFunctionDialig = new MathFunctionCreatingFunction(owner);
                mathFunctionCreatingFunctionDialig.setVisible(true);
            }
        });

        JButton loadButton1 = new JButton("Load");
        loadButton1.setBounds(85, 360, Index.buttonWidth, Index.buttonHeight);

        JButton safeButton1 = new JButton("Safe");
        safeButton1.setBounds(85, 400, Index.buttonWidth, Index.buttonHeight);

        panel.add(exitButton);
        panel.add(createButton1);
        panel.add(safeButton1);
        panel.add(loadButton1);
        this.setJMenuBar(menuBar);
        getContentPane().add(panel);
    }

}
