package ru.ssau.tk.lab2.concurrent;

import ru.ssau.tk.lab2.functions.Point;
import ru.ssau.tk.lab2.functions.TabulatedFunction;

import java.util.Iterator;

public class SynchonizedTabuletedFunction implements TabulatedFunction {
    private final TabulatedFunction tabulatedFunction;

    public SynchonizedTabuletedFunction(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }


    @Override
    synchronized public int getCount() {
        return tabulatedFunction.getCount();
    }

    @Override
    synchronized public double getX(int index) {
        return tabulatedFunction.getX(index);
    }

    @Override
    synchronized public double getY(int index) {
        return tabulatedFunction.getY(index);
    }

    @Override
    synchronized public void setY(int index, double value) {
        tabulatedFunction.setY(index, value);
    }

    @Override
    synchronized public int indexOfX(double x) {
        return tabulatedFunction.indexOfX(x);
    }

    @Override
    synchronized public int indexOfY(double y) {
        return tabulatedFunction.indexOfY(y);
    }

    @Override
    synchronized public double leftBound() {
        return tabulatedFunction.leftBound();
    }

    @Override
    synchronized public double rightBound() {
        return tabulatedFunction.rightBound();
    }

    @Override
    synchronized public Iterator<Point> iterator() {
        return null;
    }

    @Override
    synchronized public double apply(double x) {
        return tabulatedFunction.apply(x);
    }
}
