package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double[] valuesX = new double[]{-3., -2., -1., 0, 1., 2., 3.};
    private final double[] valuesY = new double[]{9., 4., 1., 0, 1., 4, 9.};
    private final MathFunction sqrFunc = new SqrFunction();

    @Test
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(new double[]{1}, new double[]{1}));
        assertThrows(IllegalArgumentException.class, () -> ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(new double[]{}, new double[]{}));
        assertThrows(IllegalArgumentException.class, () -> ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(new double[]{1, 5}, new double[]{1}));
        assertThrows(IllegalArgumentException.class, () -> ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 10, 5, 15));
        assertThrows(IllegalArgumentException.class, () -> ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 5, 6, 1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 5, 5, 2));
    }

    @Test
    public void testGetCount() {
        TabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.getCount(), 7, 0.001);
        assertEquals(definedThroughMathFunction.getCount(), 21, 0.001);
    }

    @Test
    public void testGetX() {
        TabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        for (int i = 0; i < 7; i++) {
            assertEquals(definedThroughArrays.getX(i), i - 3., 0.001);
        }
        for (int i = 0; i < 21; i++) {
            assertEquals(definedThroughMathFunction.getX(i), i * 20. / 20, 0.001);
        }
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.getX(9));
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.getX(-1));
        assertThrows(IllegalArgumentException.class, () -> definedThroughMathFunction.getX(18));
        assertThrows(IllegalArgumentException.class, () -> definedThroughMathFunction.getX(-1));
    }

    @Test
    public void testGetY() {
        TabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        for (int i = 0; i < 7; i++) {
            assertEquals(definedThroughArrays.getY(i), Math.pow(i - 3., 2), 0.001);
        }
        for (int i = 0; i < 21; i++) {
            assertEquals(definedThroughMathFunction.getY(i), Math.pow(i * 20. / 20, 2), 0.001);
        }
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.getY(9));
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.getY(-1));
        assertThrows(IllegalArgumentException.class, () -> definedThroughMathFunction.getY(18));
        assertThrows(IllegalArgumentException.class, () -> definedThroughMathFunction.getY(-1));
    }

    @Test
    public void testSetY() {
        TabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        definedThroughArrays.setY(5, 100500.);
        definedThroughMathFunction.setY(0, 1009.);
        assertEquals(definedThroughArrays.getY(5), 100500., 0.001);
        assertEquals(definedThroughMathFunction.getY(0), 1009., 0.001);
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.setY(9, 100500));
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.setY(-1, 100500));
        assertThrows(IllegalArgumentException.class, () -> definedThroughMathFunction.setY(18, 100500));
        assertThrows(IllegalArgumentException.class, () -> definedThroughMathFunction.setY(-1, 100500));
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.leftBound(), -3., 0.001);
        assertEquals(definedThroughMathFunction.leftBound(), 0., 0.001);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.rightBound(), 3., 0.001);
        assertEquals(definedThroughMathFunction.rightBound(), 20., 0.001);
    }

    @Test
    public void testIndexOfX() {
        TabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.indexOfX(1.), 4);
        assertEquals(definedThroughArrays.indexOfX(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfX(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfX(0.1), -1);
    }

    @Test
    public void testIndexOfY() {
        TabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        TabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.indexOfY(1.), 2);
        assertEquals(definedThroughArrays.indexOfY(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfY(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfY(0.1), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        ArrayTabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        ArrayTabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.floorIndexOfX(30.), 7);
        assertEquals(definedThroughMathFunction.floorIndexOfX(20.1), 21);
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.floorIndexOfX(-4.0));
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.floorIndexOfX(-18.98));
        assertThrows(IllegalArgumentException.class, () -> definedThroughMathFunction.floorIndexOfX(-13.69));
        assertThrows(IllegalArgumentException.class, () -> definedThroughMathFunction.floorIndexOfX(-1));
    }

    @Test
    public void testExtrapolateLeft() {
        ArrayTabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        ArrayTabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.extrapolateLeft(-4), 14., 0.001);
        assertEquals(definedThroughArrays.extrapolateLeft(-4.5), 16.5, 0.001);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-4), -4., 0.001);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-4.3), -4.3, 0.001);
    }

    @Test
    public void testExtrapolateRight() {
        ArrayTabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        ArrayTabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.extrapolateRight(4.), 14., 0.001);
        assertEquals(definedThroughArrays.extrapolateRight(4.8), 18., 0.001);
        assertEquals(definedThroughMathFunction.extrapolateRight(4.), -224., 0.001);
        assertEquals(definedThroughMathFunction.extrapolateRight(4.8), -192.799, 0.001);
    }

    @Test
    public void testInterpolate() {
        ArrayTabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        ArrayTabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.interpolate(-1.5, 1), 2.5, 0.001);
        assertEquals(definedThroughArrays.interpolate(0.5, 3), 0.5, 0.001);
        assertEquals(definedThroughMathFunction.interpolate(14.9, 14), 222.1, 0.001);
        assertEquals(definedThroughMathFunction.interpolate(1.3, 1), 1.9, 0.001);
        assertEquals(definedThroughMathFunction.interpolate(10.8, 8), 111.6, 0.001);
    }

    @Test
    public void testApply() {
        ArrayTabulatedFunction definedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        ArrayTabulatedFunction definedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunc, 0, 20, 21);
        assertEquals(definedThroughArrays.apply(-1.5), 2.5, 0.001);
        assertEquals(definedThroughMathFunction.apply(14.9), 222.1, 0.001);
        assertEquals(definedThroughArrays.apply(4.0), 14.0, 0.001);
        assertEquals(definedThroughMathFunction.apply(22.0), 478.0, 0.001);
        assertEquals(definedThroughArrays.apply(-4.0), 14.0, 0.001);
        assertEquals(definedThroughMathFunction.apply(-5.0), -5.0, 0.001);
    }

    @Test
    public void testIteratorWhile() {
        ArrayTabulatedFunction DefinedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        Iterator<Point> myIterator = DefinedThroughArrays.iterator();
        int i = 0;
        while (myIterator.hasNext()) {
            Point myPoint = myIterator.next();
            assertEquals(DefinedThroughArrays.getX(i), myPoint.x, 0.001);
            assertEquals(DefinedThroughArrays.getY(i++), myPoint.y, 0.001);
        }
        assertEquals(DefinedThroughArrays.getCount(), i);

        ArrayTabulatedFunction DefinedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        myIterator = DefinedThroughMathFunction.iterator();
        i = 0;
        while (myIterator.hasNext()) {
            Point myPoint = myIterator.next();
            assertEquals(DefinedThroughMathFunction.getX(i), myPoint.x, 0.001);
            assertEquals(DefinedThroughMathFunction.getY(i++), myPoint.y, 0.001);
        }
        assertEquals(DefinedThroughMathFunction.getCount(), i);
    }

    @Test
    public void testIteratorForEach() {
        ArrayTabulatedFunction DefinedThroughArrays = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        int i = 0;
        for (Point myPoint : DefinedThroughArrays) {
            assertEquals(myPoint.x, DefinedThroughArrays.getX(i), 0.001);
            assertEquals(myPoint.y, DefinedThroughArrays.getY(i++), 0.001);
        }
        assertEquals(DefinedThroughArrays.getCount(), i);

        ArrayTabulatedFunction testDefinedThroughMathFunction = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughArray(valuesX, valuesY);
        i = 0;
        for (Point myPoint : testDefinedThroughMathFunction) {
            assertEquals(myPoint.x, testDefinedThroughMathFunction.getX(i), 0.001);
            assertEquals(myPoint.y, testDefinedThroughMathFunction.getY(i++), 0.001);
        }
        assertEquals(testDefinedThroughMathFunction.getCount(), i);
    }
}
