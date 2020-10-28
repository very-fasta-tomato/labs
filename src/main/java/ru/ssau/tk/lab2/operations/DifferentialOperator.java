package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;

public interface DifferentialOperator<T> extends MathFunction {
    T derive(T function);
}
