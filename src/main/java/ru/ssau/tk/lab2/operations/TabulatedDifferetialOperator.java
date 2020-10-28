package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;

public class TabulatedDifferetialOperator implements DifferentialOperator{
    DifferentialOperator<TabulatedFunction> function = new DifferentialOperator<TabulatedFunction>() {
        @Override
        public TabulatedFunction derive(TabulatedFunction function) {
            return null;
        }

        @Override
        public double apply(double x) {
            return 0;
        }
    };
}
