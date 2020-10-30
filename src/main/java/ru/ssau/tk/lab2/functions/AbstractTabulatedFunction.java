package ru.ssau.tk.lab2.functions;

import ru.ssau.tk.lab2.exceptions.*;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

public abstract class AbstractTabulatedFunction extends Object implements TabulatedFunction {
    protected int count;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX));
    }

    public double apply(double x) {
        int index = indexOfX(x);

        if (x < leftBound()) {
            return (extrapolateLeft(x));
        }
        if (x > rightBound()) {
            return (extrapolateRight(x));
        }
        if (index != -1) {
            return (getY(index));
        }
        return (interpolate(x, floorIndexOfX(x)));
    }

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Lengths of arrays are different");
        }
    }

    protected static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i] > xValues[i + 1]) {
                throw new ArrayIsNotSortedException("xValues array isn't sorted");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName());
        stringBuilder.append("; size = ");
        stringBuilder.append(this.count);
        for (Point currentPoint : TabulatedFunctionOperationService.asPoints(this)) {
            stringBuilder.append("\n");
            stringBuilder.append('[');
            stringBuilder.append(currentPoint.x);
            stringBuilder.append(',');
            stringBuilder.append(' ');
            stringBuilder.append(currentPoint.y);
            stringBuilder.append("]");
        }
        return (stringBuilder.toString());
    }
}
