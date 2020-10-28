package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.functions.factory.*;

public class TabulatedDifferetialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        int count = function.getCount();
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
            xValues[i] = points[i].x;
        }
        TabulatedFunction newFunction = factory.create(xValues, yValues);
        return newFunction;
    }

    @Override
    public double apply(double x) {
        return 0;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public Class getFactory() {
        return factory.getClass();
    }

    public TabulatedDifferetialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferetialOperator() {
        factory = new ArrayTabulatedFunctionFactory();
    }


}
