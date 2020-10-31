package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {
    LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x) - function.apply(x - step)) / step;
    }
}
