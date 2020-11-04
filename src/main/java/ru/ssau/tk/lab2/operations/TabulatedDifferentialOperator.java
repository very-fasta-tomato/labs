package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.functions.factory.*;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        int count = function.getCount();
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count - 1; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
            xValues[i] = points[i].x;
        }
        yValues[count - 1] = (points[count -2].y - points[count - 1].y) / (points[count - 2].x - points[count - 1].x);
        xValues[count - 1] = points[count - 1].x;
        return factory.create(xValues, yValues);
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

}
