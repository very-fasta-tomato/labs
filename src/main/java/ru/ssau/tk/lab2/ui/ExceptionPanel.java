package ru.ssau.tk.lab2.ui;

import ru.ssau.tk.lab2.exceptions.*;

public class ExceptionPanel extends Exception{
    public ExceptionPanel() {
    }

    public ExceptionPanel(NumberFormatException message) {
        super("Введите число!");
    }

    public ExceptionPanel(NullPointerException message) {
        super(message);
    }
    public ExceptionPanel(IllegalArgumentException message){
        super("Введите число!");
    }
}
