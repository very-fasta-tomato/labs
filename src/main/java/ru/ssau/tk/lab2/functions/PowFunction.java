package ru.ssau.tk.lab2.functions;

public class PowFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return java.lang.Math.pow(x, 1.5);
    }
}
