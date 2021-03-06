package ru.ssau.tk.lab2.functions.factory;

import ru.ssau.tk.lab2.functions.*;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
}
