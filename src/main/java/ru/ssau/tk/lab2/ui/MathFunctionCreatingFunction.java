package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.functions.factory.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MathFunctionCreatingFunction extends JDialog {
    JPanel panel = new JPanel();
    TabulatedFunction tabulatedFunction;
    int count;
    double from;
    double to;

    public MathFunctionCreatingFunction(JFrame owner) {
        super(owner, "math function", true);
        setSize(350, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);
        JLabel labelCount = new JLabel("Количество точек: ");
        labelCount.setBounds(10, 40, 120, 20);
        panel.add(labelCount);
        labelCount.setVisible(true);
        JTextField textFieldCount = new JTextField(10);
        textFieldCount.setBounds(140, 40, 30, 20);
        panel.add(textFieldCount);
        textFieldCount.setVisible(true);
        JLabel labelBracket1 = new JLabel("от");
        labelBracket1.setBounds(130, 70, 40, 20);
        panel.add(labelBracket1);
        labelBracket1.setVisible(true);
        JLabel labelBracket2 = new JLabel("до");
        labelBracket2.setBounds(200, 70, 40, 20);
        panel.add(labelBracket2);
        labelBracket2.setVisible(true);
        JTextField textFieldFrom = new JTextField(10);
        textFieldFrom.setBounds(150, 70, 40, 20);
        panel.add(textFieldFrom);
        textFieldFrom.setVisible(true);
        JTextField textFieldTo = new JTextField(10);
        textFieldTo.setVisible(true);
        textFieldTo.setBounds(220, 70, 40, 20);
        panel.add(textFieldTo);
        comboBoxFunctions.setBounds(10, 10, 300, 20);
        panel.add(comboBoxFunctions);
        comboBoxFunctions.setVisible(true);
        JButton finalButton = new JButton("Создать");
        finalButton.setBounds(10, 100, 90, 30);
        finalButton.setVisible(true);
        panel.add(finalButton);
        finalButton.addActionListener(e -> {
            try {
                count = Integer.parseInt(textFieldCount.getText());
                from = Double.parseDouble(textFieldFrom.getText());
                to = Double.parseDouble(textFieldTo.getText());
            } catch (NumberFormatException exception) {
                new ExceptionWindow(new ExceptionPanel(exception));
            } catch (NullPointerException exception) {
                new ExceptionWindow(new ExceptionPanel(exception));
            } catch (IllegalArgumentException exception) {
                new ExceptionWindow(new ExceptionPanel(exception));
            }
            String str = comboBoxFunctions.getItemAt(comboBoxFunctions.getSelectedIndex());
            if (str.equals("Константная функция")) {
                String result = JOptionPane.showInputDialog("Введите значение константы");
                double constant = Double.parseDouble(result);
                tabulatedFunction = Index.factory.create(new ConstantFunction(constant), from, to, count);
            } else {
                MathFunction mathFunction = functionMap.get(str);
                tabulatedFunction = Index.factory.create(mathFunction, from, to, count);
            }
            this.dispose();
        });
        getContentPane().add(panel);
    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }

    Map<String, MathFunction> functionMap = new HashMap<>();
    JComboBox<String> comboBoxFunctions = showComboBox();

    private JComboBox<String> showComboBox() {
        functionMap.put("Единичная функция", new UnitFunction());
        functionMap.put("Квадратичная функция", new SqrFunction());
        functionMap.put("Константная функция", new ConstantFunction(10));
        functionMap.put("Нулевая функция", new ZeroFunction());
        functionMap.put("Тангенсоидная функция", new TanFunction());
        functionMap.put("Тождественная функция", new IdentityFunction());

        DefaultComboBoxModel<String> functions = new DefaultComboBoxModel<>();

        functions.addElement("Единичная функция");
        functions.addElement("Квадратичная функция");
        functions.addElement("Константная функция");
        functions.addElement("Нулевая функция");
        functions.addElement("Тангенсоидная функция");
        functions.addElement("Тождественная функция");

        return new JComboBox<>(functions);
    }
}
