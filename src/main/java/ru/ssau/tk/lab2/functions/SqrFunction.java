package ru.ssau.tk.lab2.functions;

public class SqrFunction implements MathFunction {
    double number;

    @Override
    public double apply(double x) {
        return java.lang.Math.pow(number, 2);
    }
}
