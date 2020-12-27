package ru.ssau.tk.lab2.ui;

import javax.swing.*;

public class ExceptionWindow {
    public ExceptionWindow(ExceptionPanel exception) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                exception.getMessage(),
                "Ошибка :",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
