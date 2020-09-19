package ru.ssau.tk.lab2.functions;

public class TanFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return java.lang.Math.tan(x);
    }
}
