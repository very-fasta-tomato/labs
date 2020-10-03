package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final double[] valuesX = new double[]{-3., -2., -1., 0, 1., 2., 3.};
    private final double[] valuesY = new double[]{9., 4., 1., 0, 1., 4, 9.};
    private MathFunction sqrFunc = new SqrFunction();
    private LinkedListTabulatedFunction definedThroughList = new LinkedListTabulatedFunction(valuesX, valuesY);
    private LinkedListTabulatedFunction definedThroughMathFunction = new LinkedListTabulatedFunction(sqrFunc, 20, 0, 1000);

    @Test
    public void testGetCount() {
        assertEquals(definedThroughList.getCount(), 9, 0.001);
        assertEquals(definedThroughMathFunction.getCount(), 1000, 0.001);
    }

    @Test
    public void testGetX() {
        for (int i = 0; i < 9; i++) {
            assertEquals(definedThroughList.getX(i), i - 3., 0.001);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(definedThroughMathFunction.getX(i), i * 20. / 999,0.001);
        }
    }

    @Test
    public void testGetY() {
        for (int i = 0; i < 9; i++) {
            assertEquals(definedThroughList.getY(i), Math.pow(i - 3., 2), 0.001);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(definedThroughMathFunction.getY(i), Math.pow(i * 20. / 999, 2), 0.001);
        }
    }

    @Test
    public void testSetY() {
        definedThroughList.setY(5, 100500.);
        definedThroughMathFunction.setY(0, 1009.);
        assertEquals(definedThroughList.getY(5), 100500., 0.001);
        assertEquals(definedThroughMathFunction.getY(0), 1009., 0.001);
    }

    @Test
    public void testLeftBound() {
        assertEquals(definedThroughList.leftBound(), -3., 0.001);
        assertEquals(definedThroughMathFunction.leftBound(), 0., 0.001);
    }

    @Test
    public void testRightBound() {
        assertEquals(definedThroughList.rightBound(), 5., 0.001);
        assertEquals(definedThroughMathFunction.rightBound(), 20., 0.001);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(definedThroughList.indexOfX(1.), 4);
        assertEquals(definedThroughList.indexOfX(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfX(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfX(0.1), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(definedThroughList.indexOfY(1.), 2);
        assertEquals(definedThroughList.indexOfY(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfY(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfY(0.1), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(definedThroughList.floorIndexOfX(30.), 9);
        for (int i = -2; i < 5; i++) {
            assertEquals(definedThroughList.floorIndexOfX(i - 0.5), i + 2);
        }
        assertEquals(definedThroughMathFunction.floorIndexOfX(20.1), 1000);
        for (int i = 0; i < 999; i++) {
            assertEquals(definedThroughMathFunction.floorIndexOfX(20. * i / 999), i);
        }
    }

    @Test
    public void testExtrapolateLeft() {
        for (int i = 0; i < 20; i++){
            assertEquals(definedThroughMathFunction.extrapolateLeft(i), sqrFunc.apply(i));
        }
    }

    @Test
    public void testExtrapolateRight() {
    }
}