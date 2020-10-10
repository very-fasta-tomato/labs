package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double[] valuesX = new double[]{-3., -2., -1., 0, 1., 2., 3.};
    private final double[] valuesY = new double[]{9., 4., 1., 0, 1., 4, 9.};
    private MathFunction sqrFunc = new SqrFunction();
    private ArrayTabulatedFunction definedThroughArrays = new ArrayTabulatedFunction(valuesX, valuesY);
    private ArrayTabulatedFunction definedThroughMathFunction = new ArrayTabulatedFunction(sqrFunc, 0, 20, 21);

    @Test
    public void testGetCount() {
        assertEquals(definedThroughArrays.getCount(), 7, 0.001);
        assertEquals(definedThroughMathFunction.getCount(), 21, 0.001);
    }

    @Test
    public void testGetX() {
        for (int i = 0; i < 7; i++) {
            assertEquals(definedThroughArrays.getX(i), i - 3., 0.001);
        }
        for (int i = 0; i < 21; i++) {
            assertEquals(definedThroughMathFunction.getX(i), i * 20. / 20,0.001);
        }
    }

    @Test
    public void testGetY() {
        for (int i = 0; i < 7; i++) {
            assertEquals(definedThroughArrays.getY(i), Math.pow(i - 3., 2), 0.001);
        }
        for (int i = 0; i < 21; i++) {
            assertEquals(definedThroughMathFunction.getY(i), Math.pow(i * 20. / 20, 2), 0.001);
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
        assertEquals(definedThroughArrays.rightBound(), 3., 0.001);
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
        assertEquals(definedThroughArrays.floorIndexOfX(30.), 7);
        assertEquals(definedThroughMathFunction.floorIndexOfX(20.1), 21);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(definedThroughArrays.extrapolateLeft(-4), 14., 0.001);
        assertEquals(definedThroughArrays.extrapolateLeft(-4.5), 16.5, 0.001);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-4), -4., 0.001);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-4.3), -4.3, 0.001);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(definedThroughArrays.extrapolateRight(4.),14., 0.001 );
        assertEquals(definedThroughArrays.extrapolateRight(4.8),18., 0.001 );
        assertEquals(definedThroughMathFunction.extrapolateRight(4.), -224., 0.001);
        assertEquals(definedThroughMathFunction.extrapolateRight(4.8), -192.799, 0.001);
    }

    @Test
    public void testInterpolate() {
        assertEquals(definedThroughArrays.interpolate(-1.5, 1), 2.5, 0.001);
        assertEquals(definedThroughArrays.interpolate(0.5, 3), 0.5, 0.001);
        assertEquals(definedThroughMathFunction.interpolate(14.9, 14), 222.1, 0.001);
        assertEquals(definedThroughMathFunction.interpolate(1.3, 1), 1.9, 0.001);
        assertEquals(definedThroughMathFunction.interpolate(10.8, 8), 111.6, 0.001);
    }
    @Test
    public void testCombinedFunctions(){
        double xFrom = 5;
        double xTo = 10;
        int count = 64;
        MathFunction sqr = new SqrFunction();
        MathFunction sin = new PowFunction();
        MathFunction ten = new TanFunction();
        ArrayTabulatedFunction f = new ArrayTabulatedFunction(sin.andThen(sqr).andThen(ten), xFrom, xTo, count);
        LinkedListTabulatedFunction g = new LinkedListTabulatedFunction(sin.andThen(sqr).andThen(ten), xFrom, xTo, count);
        assertEquals(f.getY(0), g.getY(0), 0.001);
        assertEquals(f.getY(1), g.getY(1), 0.001);
        assertEquals(f.getY(2), g.getY(2), 0.001);
    }

}