package ru.ssau.tk.lab2.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.functions.TypeOfCreatingFunction;
import ru.ssau.tk.lab2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.lab2.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.io.*;

public class GraphTabulatedFunctionService extends JDialog {

    JPanel panel = new JPanel();
    JMenuBar menuBar = new JMenuBar();
    JLabel functionTableLabel = new JLabel("Tabulated function");
    JLabel alarmLabel = new JLabel("");
    JButton createButton = new JButton("Create");
    JButton loadButton = new JButton("Load");
    JButton saveButton1 = new JButton("Save");
    JButton exitButton = new JButton("Exit");
    ResultTableModel functionTableModel;
    JTable functionTable;
    JScrollPane functionTableScrollPane;
    File file;
    boolean loadedFromFile = false;
    ChartPanel graphPanel;

    TabulatedFunction tabulatedFunction;
    TypeOfCreatingFunction type;
    TabulatedFunctionFactory factory;

    public GraphTabulatedFunctionService(JFrame owner){
        super(owner, "Graph Tabulated Function Service", true);
        setSize(1000, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);

        factory = Index.factory;

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

        functionTableModel = new ResultTableModel(tabulatedFunction);
        functionTable = new JTable(functionTableModel);
        functionTableScrollPane = new JScrollPane(functionTable);
        functionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        functionTableScrollPane.setBounds(10, 50, 250, 260);

        functionTableLabel.setBounds(10, 10, 250, 30);
        functionTableLabel.setHorizontalAlignment(JLabel.CENTER);

        alarmLabel.setBounds(270, 400, 250, 30);
        alarmLabel.setForeground(Color.RED);
        alarmLabel.setHorizontalAlignment(JLabel.CENTER);

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
                        loadedFromFile = false;
                        createGraph();
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
                        loadedFromFile = false;
                        createGraph();
                    }
                });
            }
        });

        loadButton.setBounds(85, 360, Index.buttonWidth, Index.buttonHeight);
        loadButton.addActionListener(e -> {
            fileChooserOpen.showOpenDialog(this);
            file = fileChooserOpen.getSelectedFile();
            if (file != null) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    tabulatedFunction = FunctionsIO.deserialize(in);
                } catch (IOException | ClassNotFoundException err) {
                    alarmLabel.setText("Error on loading");
                }
                loadedFromFile = true;
                functionTableModel.setTabulatedFunction(tabulatedFunction);
                functionTableModel.fireTableDataChanged();
                createGraph();
            }
        });

        saveButton1.setBounds(85, 400, Index.buttonWidth, Index.buttonHeight);
        saveButton1.addActionListener(e -> {
            fileChooserSave.showSaveDialog(this);
            file = fileChooserSave.getSelectedFile();
            if (file != null) {
                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file + ".bin"))) {
                    FunctionsIO.serialize(out, tabulatedFunction);
                } catch (IOException err) {
                    alarmLabel.setText("Error on saving");
                }
            }
        });

        exitButton.setBounds(870, 400, Index.buttonWidth, Index.buttonHeight);
        exitButton.addActionListener(e -> this.dispose());

        addToPanel();
    }

    private XYDataset createDataset() {
        XYSeries graph = new XYSeries("Graph");
        for (int i = 0; i < tabulatedFunction.getCount(); i++){
            graph.add(tabulatedFunction.getX(i), tabulatedFunction.getY(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(graph);
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart;
        if (loadedFromFile){
             chart = ChartFactory.createXYLineChart(file.getName(), "X", "Y", dataset);
        } else {
            chart = ChartFactory.createXYLineChart("Tabulated function", "X", "Y", dataset);
        }
        chart.removeLegend();
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
        renderer.setSeriesFillPaint(0, Color.RED);
        renderer.setUseFillPaint(true);
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    private void createGraph(){
        if (graphPanel != null){
            getContentPane().remove(graphPanel);
        }
        JFreeChart chart = createChart(createDataset());
        graphPanel = new ChartPanel(chart);
        graphPanel.setBounds(270, 50, 700, 300);
        getContentPane().add(graphPanel);
        addToPanel();
    }

    private void addToPanel(){
        panel.add(functionTableScrollPane);
        panel.add(functionTableLabel);
        panel.add(exitButton);
        panel.add(createButton);
        panel.add(loadButton);
        panel.add(saveButton1);
        panel.add(alarmLabel);
        this.setJMenuBar(menuBar);
        getContentPane().add(panel);
    }
}
