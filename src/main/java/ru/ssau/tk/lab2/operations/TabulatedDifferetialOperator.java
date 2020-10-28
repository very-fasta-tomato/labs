package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.functions.factory.*;

public class TabulatedDifferetialOperator implements DifferentialOperator<TabulatedFunction>{
    private TabulatedFunctionFactory factory;


    @Override
    public TabulatedFunction derive (TabulatedFunction function){
        return function;
    }

    @Override
    public double apply(double x){
        return 0;
    }

    public void setFactory(TabulatedFunctionFactory factory){
        this.factory = factory;
    }

    public Class getFactory(){
        return factory.getClass();
    }

    public TabulatedDifferetialOperator(TabulatedFunctionFactory factory){
        this.factory = factory;
    }

    public TabulatedDifferetialOperator(){
        factory = new ArrayTabulatedFunctionFactory();
    }


}
