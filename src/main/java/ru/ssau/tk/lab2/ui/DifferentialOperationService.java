package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.functions.TypeOfCreatingFunction;
import ru.ssau.tk.lab2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.lab2.io.FunctionsIO;
import ru.ssau.tk.lab2.operations.TabulatedDifferentialOperator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class DifferentialOperationService extends JDialog {
    JPanel panel = new JPanel();

    static TabulatedFunction tabulatedFunction;
    static TabulatedFunction derivedTabulatedFunction;
    TabulatedFunctionFactory factory;
    TabulatedDifferentialOperator differentialOperator;
    TypeOfCreatingFunction type;

    public DifferentialOperationService(JFrame owner){
        super(owner, "Deriving operation service", true);
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);

        factory = Index.factory;
        differentialOperator = new TabulatedDifferentialOperator(factory);

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

        OperationTableModel functionTableModel = new OperationTableModel(tabulatedFunction);
        JTable functionTable = new JTable(functionTableModel);
        functionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane functionTableScrollPane = new JScrollPane(functionTable);
        functionTableScrollPane.setBounds(10, 50, 250, 260);

        ResultTableModel resultTableModel = new ResultTableModel(derivedTabulatedFunction);
        JTable resultTable = new JTable(resultTableModel);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane resultTableScrollPane = new JScrollPane(resultTable);
        resultTableScrollPane.setBounds(270, 50, 250, 260);

        JLabel functionTableLabel = new JLabel("Tabulated function");
        functionTableLabel.setBounds(10, 10, 250, 30);
        functionTableLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel resultTableLabel = new JLabel("Derived tabulated function");
        resultTableLabel.setBounds(270, 10, 250, 30);
        resultTableLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel alarmLabel = new JLabel("");
        alarmLabel.setBounds(270, 400, 250, 30);
        alarmLabel.setForeground(Color.RED);
        alarmLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(480, 400, Index.buttonWidth, Index.buttonHeight);
        exitButton.addActionListener(e -> this.dispose());

        JButton createButton = new JButton("Create");
        createButton.setBounds(85, 320, Index.buttonWidth, Index.buttonHeight);
        createButton.addActionListener(e -> {
            if (type == TypeOfCreatingFunction.ARRAY) {
                ArrayCreatingFunction arrayCreatingFunctionDialog = new ArrayCreatingFunction(owner);
                arrayCreatingFunctionDialog.setVisible(true);
                arrayCreatingFunctionDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        tabulatedFunction = arrayCreatingFunctionDialog.getTabulatedFunction();
                        functionTableModel.setTabulatedFunction(tabulatedFunction);
                        functionTableModel.fireTableDataChanged();
                    }
                });
            } else {
                MathFunctionCreatingFunction mathFunctionCreatingFunctionDialog = new MathFunctionCreatingFunction(owner);
                mathFunctionCreatingFunctionDialog.setVisible(true);
                mathFunctionCreatingFunctionDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        tabulatedFunction = mathFunctionCreatingFunctionDialog.getTabulatedFunction();
                        functionTableModel.setTabulatedFunction(tabulatedFunction);
                        functionTableModel.fireTableDataChanged();
                    }
                });
            }
        });

        JButton loadButton = new JButton("Load");
        loadButton.setBounds(85, 360, Index.buttonWidth, Index.buttonHeight);
        loadButton.addActionListener(e -> {
            fileChooserOpen.showOpenDialog(this);
            File file = fileChooserOpen.getSelectedFile();
            if (file != null) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    tabulatedFunction = FunctionsIO.deserialize(in);
                } catch (IOException | ClassNotFoundException err) {
                    err.printStackTrace();
                }

                functionTableModel.setTabulatedFunction(tabulatedFunction);
                functionTableModel.fireTableDataChanged();
            }
        });

        JButton safeButton1 = new JButton("Safe");
        safeButton1.setBounds(85, 400, Index.buttonWidth, Index.buttonHeight);
        safeButton1.addActionListener(e -> {
            fileChooserSave.showSaveDialog(this);
            File file = fileChooserSave.getSelectedFile();
            if (file != null) {
                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file + ".bin"))) {
                    FunctionsIO.serialize(out, tabulatedFunction);
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

        JButton deriveButton = new JButton("Derive");
        deriveButton.setBounds(345, 320, Index.buttonWidth, Index.buttonHeight);
        deriveButton.addActionListener(e -> {
            if (tabulatedFunction == null){
                alarmLabel.setText("None tabulated function!");
            } else {
                derivedTabulatedFunction = differentialOperator.derive(tabulatedFunction);
                resultTableModel.setTabulatedFunction(derivedTabulatedFunction);
                resultTableModel.fireTableDataChanged();
            }
        });

        JButton safeButton2 = new JButton("Safe");
        safeButton2.setBounds(345, 360, Index.buttonWidth, Index.buttonHeight);
        safeButton2.addActionListener(e -> {
            fileChooserSave.showSaveDialog(this);
            File file = fileChooserSave.getSelectedFile();
            if (file != null) {
                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file + ".bin"))) {
                    FunctionsIO.serialize(out, derivedTabulatedFunction);
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

        panel.add(functionTableScrollPane);
        panel.add(resultTableScrollPane);
        panel.add(functionTableLabel);
        panel.add(resultTableLabel);
        panel.add(exitButton);
        panel.add(createButton);
        panel.add(loadButton);
        panel.add(safeButton1);
        panel.add(alarmLabel);
        panel.add(deriveButton);
        panel.add(safeButton2);
        this.setJMenuBar(menuBar);
        getContentPane().add(panel);
    }
}
