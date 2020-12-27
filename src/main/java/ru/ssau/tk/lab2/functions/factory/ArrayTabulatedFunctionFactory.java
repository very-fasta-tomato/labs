package ru.ssau.tk.lab2.functions.factory;

import ru.ssau.tk.lab2.functions.*;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Override
    public TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count){
        return new ArrayTabulatedFunction(source, xFrom, xTo, count);
    }
}
