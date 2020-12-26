package ru.ssau.tk.lab2.ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import ru.ssau.tk.lab2.functions.factory.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Index extends JFrame {
    static int buttonWidth = 100;
    static int buttonHeight = 30;
    private static final TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
    private static final TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
    static TabulatedFunctionFactory factory = arrayFactory;


    public static void main(String[] args) {
        JFrame index = new JFrame("Tabulated functions calculator");
        index.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        index.setSize(640, 480);

        JPanel panel = new BgPanel();
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

        JButton derivingServiceButton = new JButton("Deriving operation service");
        derivingServiceButton.setBounds(10, 150, 250, buttonHeight);
        derivingServiceButton.addActionListener(e -> {
            JDialog derivingServiceDialog = new DifferentialOperationService(index);
            derivingServiceDialog.setVisible(true);
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
        panel.add(derivingServiceButton);
        index.getContentPane().add(panel);
        index.setJMenuBar(menuBar);
        index.setVisible(true);
    }

    static class BgPanel extends JPanel{
        public void paintComponent(Graphics g){
            Image im = null;
            try {
                im = ImageIO.read(new File("C:\\Users\\Admin\\Documents\\GitHub\\labs\\Shark.jpg"));
            } catch (IOException e) {}
            g.drawImage(im, 0, 0, null);
        }
    }
}
