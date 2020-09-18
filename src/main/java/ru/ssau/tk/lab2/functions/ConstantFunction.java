package ru.ssau.tk.lab2.functions;

public class ConstantFunction implements MathFunction{
    private final double constant;

    public double getConstant() {
        return constant;
    }

    public ConstantFunction (double constant){
        this.constant=constant;
    }

    @Override
    public double apply(double x) {
        return constant;
    }
}
