package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final double[] valuesX = new double[]{-3., -2., -1., 0., 1., 2., 3.};
    private final double[] valuesY = new double[]{0.143, 2.185, -1.557, 0., 1.557, -2.185, -0.143};
    private MathFunction tanFunc = new TanFunction();
    private final LinkedListTabulatedFunction definedThroughList = new LinkedListTabulatedFunction(valuesX, valuesY);
    private final LinkedListTabulatedFunction definedThroughMathFunction = new LinkedListTabulatedFunction(tanFunc, 0, 20, 15);

    private final double delta = 0.1;

    @Test
    public void testGetCount() {
        assertEquals(definedThroughList.getCount(), 7, delta);
        assertEquals(definedThroughMathFunction.getCount(), 15, delta);
    }

    @Test
    public void testGetX() {
        for (int i = 0; i < 7; i++) {
            assertEquals(definedThroughList.getX(i), i - 3, delta);
        }
        for (int i = 0; i < 15; i++) {
            assertEquals(definedThroughMathFunction.getX(i), i * 20. / 14, delta);
        }
    }

    @Test
    public void testGetY() {
        for (int i = 0; i < 7; i++) {
            assertEquals(definedThroughList.getY(i), Math.tan(i - 3), delta);
        }
        for (int i = 0; i < 15; i++) {
            assertEquals(definedThroughMathFunction.getY(i), Math.tan(i * 20. / 14), delta);
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
        for (int i = 1; i < 20; i++){
            //assertEquals(definedThroughMathFunction.extrapolateLeft(i), sqrFunc.apply(i));
            System.out.println(i);
            System.out.println(definedThroughMathFunction.extrapolateLeft(i));
            System.out.println(tanFunc.apply(i));
        }
    }

    @Test
    public void testExtrapolateRight() {
        for (int i = 1; i < 20; i++){
            //assertEquals(definedThroughMathFunction.extrapolateLeft(i), sqrFunc.apply(i));
            System.out.println(i);
            System.out.println(definedThroughMathFunction.extrapolateRight(i));
            System.out.println(tanFunc.apply(i));
        }
    }
}