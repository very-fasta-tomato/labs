package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double[] valuesX = new double[]{-3., -2., -1., 0, 1., 2., 3.};
    private final double[] valuesY = new double[]{9., 4., 1., 0, 1., 4, 9.};
    private MathFunction sqrFunc = new SqrFunction();
    private ArrayTabulatedFunction definedThroughArrays = new ArrayTabulatedFunction(valuesX, valuesY);
    private ArrayTabulatedFunction definedThroughMathFunction = new ArrayTabulatedFunction(sqrFunc, 20, 0, 1000);

    @Test
    public void testGetCount() {
        assertEquals(definedThroughArrays.getCount(), 9, 0.001);
        assertEquals(definedThroughMathFunction.getCount(), 1000, 0.001);
    }

    @Test
    public void testGetX() {
        for (int i = 0; i < 9; i++) {
            assertEquals(definedThroughArrays.getX(i), i - 3., 0.001);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(definedThroughMathFunction.getX(i), i * 20. / 999,0.001);
        }
    }

    @Test
    public void testGetY() {
        for (int i = 0; i < 9; i++) {
            assertEquals(definedThroughArrays.getY(i), Math.pow(i - 3., 2), 0.001);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(definedThroughMathFunction.getY(i), Math.pow(i * 20. / 999, 2), 0.001);
        }
    }

    @Test
    public void testSetY() {
        definedThroughArrays.setY(5, 100500.);
        definedThroughMathFunction.setY(0, 1009.);
        assertEquals(definedThroughArrays.getY(5), 100500., 0.001);
        assertEquals(definedThroughMathFunction.getY(0), 1009., 0.001);
    }

    @Test
    public void testLeftBound() {
        assertEquals(definedThroughArrays.leftBound(), -3., 0.001);
        assertEquals(definedThroughMathFunction.leftBound(), 0., 0.001);
    }

    @Test
    public void testRightBound() {
        assertEquals(definedThroughArrays.rightBound(), 5., 0.001);
        assertEquals(definedThroughMathFunction.rightBound(), 20., 0.001);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(definedThroughArrays.indexOfX(1.), 4);
        assertEquals(definedThroughArrays.indexOfX(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfX(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfX(0.1), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(definedThroughArrays.indexOfY(1.), 2);
        assertEquals(definedThroughArrays.indexOfY(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfY(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfY(0.1), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(definedThroughArrays.floorIndexOfX(30.), 9);
        for (int i = -2; i < 5; i++) {
            assertEquals(definedThroughArrays.floorIndexOfX(i - 0.5), i + 2);
        }
        assertEquals(definedThroughMathFunction.floorIndexOfX(20.1), 1000);
        for (int i = 0; i < 999; i++) {
            assertEquals(definedThroughMathFunction.floorIndexOfX(20. * i / 999), i);
        }
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(definedThroughArrays.extrapolateLeft(-2.5), 11.5, 0.001);
        assertEquals(definedThroughMathFunction.extrapolateLeft());
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(definedThroughArrays.extrapolateRight(2.5),6.5, 0.001 );
        assertEquals(definedThroughMathFunction.extrapolateRight());
    }

    @Test
    public void testInterpolate() {
    }
}