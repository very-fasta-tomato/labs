package ru.ssau.tk.lab2.ui;

import javax.swing.*;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.functions.factory.*;

public class Index extends JFrame {
    static int buttonWidth = 100;
    static int buttonHeight = 30;
    static TabulatedFunctionFactory factory;
    private static final TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
    private static final TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();

    public static void main(String[] args) {
        JFrame index = new JFrame("Tabulated functions calculator");
        index.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        index.setSize(640, 480);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(520, 380, buttonWidth, buttonHeight);
        exitButton.addActionListener(e -> index.dispose());

        JButton OperationServiceButton = new JButton("Tabulated function operation service");
        OperationServiceButton.setBounds(10, 100, 250, buttonHeight);
        OperationServiceButton.addActionListener(e -> {
            JDialog operationServiceDialog = new FunctionOperationService(index);
            operationServiceDialog.setVisible(true);
        });

        JMenu menuFile = new JMenu("File");
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(e -> index.dispose());
        menuFile.add(menuExit);

        JLabel factoryLabel = new JLabel("Array function factory");
        factoryLabel.setBounds(10, 10, 320, 30);

        JMenu menuOptions = new JMenu("Options");
        JMenu menuFactory = new JMenu("Factory");
        JMenuItem menuArrayFactory = new JMenuItem("Array");
        JMenuItem menuLinkedListFactory = new JMenuItem("Linked list");
        menuArrayFactory.addActionListener(e -> {
            factory = arrayFactory;
            factoryLabel.setText("Array function factory");
        });
        menuLinkedListFactory.addActionListener(e -> {
            factory = linkedListFactory;
            factoryLabel.setText("Linked list function factory");
        });
        menuFactory.add(menuArrayFactory);
        menuFactory.add(menuLinkedListFactory);
        menuOptions.add(menuFactory);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuOptions);

        panel.add(exitButton);
        panel.add(factoryLabel);
        panel.add(OperationServiceButton);
        index.getContentPane().add(panel);
        index.setJMenuBar(menuBar);
        index.setVisible(true);
    }
}
