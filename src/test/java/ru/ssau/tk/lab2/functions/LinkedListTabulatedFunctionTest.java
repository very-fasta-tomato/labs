package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final double[] valuesX = new double[]{-3., -2., -1., 0., 1., 2., 3.};
    private final double[] valuesY = new double[]{0.143, 2.185, -1.557, 0., 1.557, -2.185, -0.143};
    private final MathFunction tanFunc = new TanFunction();
    private final MathFunction powFunc = new PowFunction();
    private final MathFunction sqrFunc = new SqrFunction();
    private final double delta = 0.1;

    @Test
    public void testGetCount() {
        TabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.getCount(), 7, delta);
        assertEquals(definedThroughMathFunction.getCount(), 15, delta);
    }

    @Test
    public void testGetX() {
        TabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        for (int i = 0; i < 7; i++) {
            assertEquals(definedThroughList.getX(i), i - 3, delta);
        }
        for (int i = 0; i < 15; i++) {
            assertEquals(definedThroughMathFunction.getX(i), i * 20. / 14, delta);
        }
    }

    @Test
    public void testGetY() {
        TabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        for (int i = 0; i < 7; i++) {
            assertEquals(definedThroughList.getY(i), Math.tan(i - 3), delta);
        }
        for (int i = 0; i < 15; i++) {
            assertEquals(definedThroughMathFunction.getY(i), Math.tan(i * 20. / 14), delta);
        }
    }

    @Test
    public void testSetY() {
        TabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        definedThroughList.setY(5, 100500.);
        definedThroughMathFunction.setY(0, 1009.);
        assertEquals(definedThroughList.getY(5), 100500., delta);
        assertEquals(definedThroughMathFunction.getY(0), 1009., delta);
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.leftBound(), -3., delta);
        assertEquals(definedThroughMathFunction.leftBound(), 0., delta);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.rightBound(), 3., delta);
        assertEquals(definedThroughMathFunction.rightBound(), 20., delta);
    }

    @Test
    public void testIndexOfX() {
        TabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.indexOfX(1.), 4);
        assertEquals(definedThroughList.indexOfX(-2.), 1);
        assertEquals(definedThroughList.indexOfX(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfX(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfX(10), 7);
        assertEquals(definedThroughMathFunction.indexOfX(0.1), -1);
    }

    @Test
    public void testIndexOfY() {
        TabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.indexOfY(2.185), 1);
        assertEquals(definedThroughList.indexOfY(5.0), -1);
        assertEquals(definedThroughList.indexOfY(-2.185), 5);
        assertEquals(definedThroughMathFunction.indexOfY(6.), -1);
        assertEquals(definedThroughMathFunction.indexOfY(0.1), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        LinkedListTabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.floorIndexOfX(30.), 7);
        assertEquals(definedThroughList.floorIndexOfX(-1.7), 1);
        assertEquals(definedThroughList.floorIndexOfX(2.5), 5);
        assertEquals(definedThroughMathFunction.floorIndexOfX(30.), 15);
        assertEquals(definedThroughMathFunction.floorIndexOfX(5.), 3);
        assertEquals(definedThroughMathFunction.floorIndexOfX(10.8), 7);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        LinkedListTabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.extrapolateLeft(-4.0), -1.9655, delta);
        assertEquals(definedThroughList.extrapolateLeft(-5.0), -3.853, delta);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-1.0), -4.924, delta);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-3.0), -14.657, delta);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-5.0), -24.439, delta);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        LinkedListTabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.extrapolateLeft(4.0), 14.439, delta);
        assertEquals(definedThroughList.extrapolateLeft(5.0), 16.486, delta);
        assertEquals(definedThroughMathFunction.extrapolateLeft(22.0), 107.542, delta);
        assertEquals(definedThroughMathFunction.extrapolateLeft(25.0), 122.296, delta);
        assertEquals(definedThroughMathFunction.extrapolateLeft(30.0), 146.637, delta);
    }

    @Test
    public void testInterlateRight() {
        LinkedListTabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        LinkedListTabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.interpolate(-1.5, 0), 3.2, delta);
        assertEquals(definedThroughList.interpolate(0.5, 3), 0.78, delta);
        assertEquals(definedThroughMathFunction.interpolate(14.9, 10), -3.81, delta);
        assertEquals(definedThroughMathFunction.interpolate(1.3, 0), 6.34, delta);
        assertEquals(definedThroughMathFunction.interpolate(10.8, 8), -3.19, delta);
    }

    @Test
    public void testApply(){
        LinkedListTabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(valuesX, valuesY);
        LinkedListTabulatedFunction definedThroughMathFunction = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunc, 0, 20, 15);
        assertEquals(definedThroughList.apply(-1.5), 0.31, delta);
        assertEquals(definedThroughMathFunction.apply(14.9), -3.81, delta);
        assertEquals(definedThroughList.apply(4.0), 1.89, delta);
        assertEquals(definedThroughMathFunction.apply(22.0), 5.76, delta);
        assertEquals(definedThroughList.apply(-4.0), -1.89, delta);
        assertEquals(definedThroughMathFunction.apply(-5.0), -24.439, delta);


    }
/*
    @Test
    public void testCombinedFunctions() {
        final double[] valuesX = new double[]{0., 1., 2., 3., 4., 5., 6., 7., 8., 9.};
        final double[] valuesY = new double[10];
        for (int i = 0; i < 10; i++) {
            valuesY[i] = tanFunc.apply(powFunc.apply(valuesX[i]));
        }
        final LinkedListTabulatedFunction combinedFunctionList = new LinkedListTabulatedFunction(valuesX, valuesY);
        assertEquals(combinedFunctionList.extrapolateLeft(-1.), -1.55, delta);
        assertEquals(combinedFunctionList.extrapolateLeft(-2.), -3.11, delta);
        assertEquals(combinedFunctionList.extrapolateLeft(-4.), -6.22, delta);
        assertEquals(combinedFunctionList.extrapolateRight(10.), -7.28, delta);
        assertEquals(combinedFunctionList.extrapolateRight(11.), -11.29, delta);
        assertEquals(combinedFunctionList.extrapolateRight(12.), -15.31, delta);
        assertEquals(combinedFunctionList.interpolate(2.5, 1), -1.26, delta);
        assertEquals(combinedFunctionList.interpolate(6.9, 4), -2.59, delta);
        assertEquals(combinedFunctionList.interpolate(8.2, 7), 1, delta);

        for (int i = 0; i < 10; i++) {
            combinedFunctionList.setY(i, sqrFunc.apply(tanFunc.apply(valuesX[i])));
            assertEquals(combinedFunctionList.getY(i), sqrFunc.apply(tanFunc.apply(valuesX[i])));
        }

        assertEquals(combinedFunctionList.extrapolateLeft(-1.), -2.42, delta);
        assertEquals(combinedFunctionList.extrapolateLeft(-2.), -4.85, delta);
        assertEquals(combinedFunctionList.extrapolateLeft(-4.), -9.71, delta);
        assertEquals(combinedFunctionList.extrapolateRight(10.), -45.82, delta);
        assertEquals(combinedFunctionList.extrapolateRight(11.), -91.85, delta);
        assertEquals(combinedFunctionList.extrapolateRight(12.), -137.88, delta);
        assertEquals(combinedFunctionList.interpolate(2.5, 1), 5.94, delta);
        assertEquals(combinedFunctionList.interpolate(6.9, 4), 30.59, delta);
        assertEquals(combinedFunctionList.interpolate(8.2, 7), 55.33, delta);
    }
    */

}