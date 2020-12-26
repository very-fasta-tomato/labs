package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.lab2.io.FunctionsIO;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class FunctionOperationService extends JDialog {
    JPanel panel = new JPanel();

    static TabulatedFunction firstTabulatedFunction;
    static TabulatedFunction secondTabulatedFunction;
    static TabulatedFunction resultTabulatedFunction;
    static TabulatedFunctionFactory factory;
    TabulatedFunctionOperationService functionOperationService;
    TypeOfCreatingFunction type;
    CalculateOperation calculateOperation = CalculateOperation.SUM;

    public FunctionOperationService(JFrame owner) {
        super(owner, "Tabulated function operation service", true);
        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);

        factory = Index.factory;
        functionOperationService = new TabulatedFunctionOperationService(factory);

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

        OperationTableModel firstTableModel = new OperationTableModel(firstTabulatedFunction);
        JTable firstFunctionTable = new JTable(firstTableModel);
        firstFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane firstTableScrollPane = new JScrollPane(firstFunctionTable);
        firstTableScrollPane.setBounds(10, 50, 250, 260);

        OperationTableModel secondTableModel = new OperationTableModel(secondTabulatedFunction);
        JTable secondFunctionTable = new JTable(secondTableModel);
        secondFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane secondTableScrollPane = new JScrollPane(secondFunctionTable);
        secondTableScrollPane.setBounds(270, 50, 250, 260);

        ResultTableModel resultTableModel = new ResultTableModel(resultTabulatedFunction);
        JTable resultFunctionTable = new JTable(resultTableModel);
        resultFunctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane resultTableScrollPane = new JScrollPane(resultFunctionTable);
        resultTableScrollPane.setBounds(530, 50, 250, 260);

        JLabel firstTableLabel = new JLabel("First tabulated function");
        firstTableLabel.setBounds(10, 10, 250, 30);
        firstTableLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel secondTableLabel = new JLabel("Second tabulated function");
        secondTableLabel.setBounds(270, 10, 250, 30);
        secondTableLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel resultTableLabel = new JLabel("Result tabulated function");
        resultTableLabel.setBounds(530, 10, 250, 30);
        resultTableLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel alarmLabel = new JLabel("");
        alarmLabel.setBounds(530, 400, 250, 30);
        alarmLabel.setForeground(Color.RED);
        alarmLabel.setHorizontalAlignment(JLabel.CENTER);

        JFileChooser fileChooserOpen = new JFileChooser();
        fileChooserOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooserOpen.setDialogTitle("Open file");
        fileChooserOpen.addChoosableFileFilter(new FileNameExtensionFilter("Serialized functions", "bin"));
        fileChooserOpen.setAcceptAllFileFilterUsed(false);

        JFileChooser fileChooserSave = new JFileChooser();
        fileChooserSave.setDialogTitle("Save file");
        fileChooserSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooserSave.addChoosableFileFilter(new FileNameExtensionFilter("Serialized functions", "bin"));
        fileChooserSave.setAcceptAllFileFilterUsed(false);

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
                        firstTableModel.setTabulatedFunction(firstTabulatedFunction);
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
                        firstTableModel.setTabulatedFunction(firstTabulatedFunction);
                        firstTableModel.fireTableDataChanged();
                    }
                });
            }
        });

        JButton loadButton1 = new JButton("Load");
        loadButton1.setBounds(85, 360, Index.buttonWidth, Index.buttonHeight);
        loadButton1.addActionListener(e -> {
            fileChooserOpen.showOpenDialog(this);
            File file = fileChooserOpen.getSelectedFile();
            if (file != null) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    firstTabulatedFunction = FunctionsIO.deserialize(in);
                } catch (IOException | ClassNotFoundException err) {
                    err.printStackTrace();
                }

                firstTableModel.setTabulatedFunction(firstTabulatedFunction);
                firstTableModel.fireTableDataChanged();
            }
        });

        JButton safeButton1 = new JButton("Safe");
        safeButton1.setBounds(85, 400, Index.buttonWidth, Index.buttonHeight);
        safeButton1.addActionListener(e -> {
            fileChooserSave.showSaveDialog(this);
            File file = fileChooserSave.getSelectedFile();
            if (file != null) {
                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file + ".bin"))) {
                    FunctionsIO.serialize(out, firstTabulatedFunction);
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

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
                        secondTableModel.setTabulatedFunction(secondTabulatedFunction);
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
                        secondTableModel.setTabulatedFunction(secondTabulatedFunction);
                        secondTableModel.fireTableDataChanged();
                    }
                });
            }
        });

        JButton loadButton2 = new JButton("Load");
        loadButton2.setBounds(345, 360, Index.buttonWidth, Index.buttonHeight);
        loadButton2.addActionListener(e -> {
            fileChooserOpen.showOpenDialog(this);
            File file = fileChooserOpen.getSelectedFile();
            if (file != null) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    secondTabulatedFunction = FunctionsIO.deserialize(in);
                } catch (IOException | ClassNotFoundException err) {
                    err.printStackTrace();
                }
                secondTableModel.setTabulatedFunction(secondTabulatedFunction);
                secondTableModel.fireTableDataChanged();
            }
        });

        JButton safeButton2 = new JButton("Safe");
        safeButton2.setBounds(345, 400, Index.buttonWidth, Index.buttonHeight);
        safeButton2.addActionListener(e -> {
            fileChooserSave.showSaveDialog(this);
            File file = fileChooserSave.getSelectedFile();
            if (file != null) {
                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file + ".bin"))) {
                    FunctionsIO.serialize(out, secondTabulatedFunction);
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(605, 320, Index.buttonWidth, Index.buttonHeight);
        calculateButton.addActionListener(e -> {
            if ((firstTabulatedFunction == null) && (secondTabulatedFunction == null)) {
                alarmLabel.setText("Incorrect operand tabulated function!");
            } else {
                if (firstTabulatedFunction.getCount() != secondTabulatedFunction.getCount()){
                    alarmLabel.setText("Different count of points!");
                } else {
                    alarmLabel.setText("");
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
                }
            }
            resultTableModel.setTabulatedFunction(resultTabulatedFunction);
            resultTableModel.fireTableDataChanged();
        });

        JButton safeButton3 = new JButton("Safe");
        safeButton3.setBounds(605, 360, Index.buttonWidth, Index.buttonHeight);
        safeButton3.addActionListener(e -> {
            fileChooserSave.showSaveDialog(this);
            File file = fileChooserSave.getSelectedFile();
            if (file != null) {
                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file + ".bin"))) {
                    FunctionsIO.serialize(out, resultTabulatedFunction);
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

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
        panel.add(firstTableLabel);
        panel.add(secondTableLabel);
        panel.add(resultTableLabel);
        panel.add(alarmLabel);
        setLocationRelativeTo(null);
        this.setJMenuBar(menuBar);
        getContentPane().add(panel);
    }

}
