package ru.ssau.tk.lab2.functions;

public class SqrFunction implements MathFunction {
    double number;

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    @Override
    public double apply(double x) {
        return java.lang.Math.pow(number, 2);
    }
}
