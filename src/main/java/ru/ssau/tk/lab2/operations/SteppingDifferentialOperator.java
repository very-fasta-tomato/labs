package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step)) {
            throw new IllegalArgumentException("Step is wrong");
        }
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }
}

