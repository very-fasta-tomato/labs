package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.lab2.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferetialOperator implements DifferentialOperator{
    private TabulatedFunctionFactory factory;
    private TabulatedFunction function;

    DifferentialOperator<TabulatedFunction> parametr = new DifferentialOperator<TabulatedFunction>() {
        @Override
        public TabulatedFunction derive(TabulatedFunction function) {
            return function;
        }

        @Override
        public double apply(double x) {
            return 0;
        }
    };

    public TabulatedDifferetialOperator(TabulatedFunctionFactory factory, TabulatedFunction function){
        this.factory = factory;
        this.function = function;
    }

    public TabulatedDifferetialOperator(){
        factory = new ArrayTabulatedFunctionFactory();
        function = new ArrayTabulatedFunction();
    }
}
