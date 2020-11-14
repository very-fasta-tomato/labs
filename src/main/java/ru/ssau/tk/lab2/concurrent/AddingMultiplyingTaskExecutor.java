package ru.ssau.tk.lab2.concurrent;

import ru.ssau.tk.lab2.functions.*;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 1);

    }
}
