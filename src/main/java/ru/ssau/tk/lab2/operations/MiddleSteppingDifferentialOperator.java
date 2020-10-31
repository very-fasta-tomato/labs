package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {
    public MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x + step) - function.apply(x - step)) / (2 * step);
    }
    @Override
    public double apply(double x) {
        return x;
    }
}
