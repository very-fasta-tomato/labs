package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    private final MathFunction X = new PowFunction();
    private final MathFunction sqrX = new SqrFunction();
    private final MathFunction one = new UnitFunction();
    private final MathFunction testFunction = sqrX.andThen(X);
    private final MathFunction tanFunction = new TanFunction();
    private final MathFunction sqrFunction = new SqrFunction();
    private final double delta = 0.1;

    @Test
    public void testAndThen() {
        assertEquals(testFunction.apply(3), 27.0, 0.001);
        assertEquals(testFunction.andThen(one).apply(10), 1.0, 0.001);
        assertEquals(testFunction.andThen(sqrX).apply(2), 64.0, 0.001);
        assertEquals(testFunction.andThen(sqrX).apply(2.5), 244.140, 0.001);
    }

    @Test
    public void tabulatedCombinedFunctionsTest() {
        final double[] valuesX = new double[]{0., 1., 2., 3., 4., 5., 6., 7., 8., 9.};
        final double[] valuesY = new double[10];
        for (int i = 0; i < 10; i++) {
            valuesY[i] = sqrX.apply(valuesX[i]);
        }
        TabulatedFunction combinedFunctionList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunction, -9.0, 9.0, 100);
        assertEquals(combinedFunctionList.andThen(sqrX).apply(-8.56), 1.43, delta);
        assertEquals(combinedFunctionList.andThen(sqrX).apply(-3.45), 0.10, delta);
        assertEquals(combinedFunctionList.andThen(sqrX).apply(1), 2.42, delta);
        assertEquals(combinedFunctionList.andThen(sqrX).apply(2.35), 1.02, delta);
        assertEquals(combinedFunctionList.andThen(sqrX).apply(9), 0.2, delta);
        TabulatedFunction combinedFunctionArray = ArrayTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrFunction, -9.0, 9.0, 100);
        assertEquals(combinedFunctionArray.andThen(combinedFunctionList).andThen(sqrX).apply(-8.56), 7215.9, delta);
        assertEquals(combinedFunctionArray.andThen(combinedFunctionList).andThen(sqrX).apply(-3.45), 11.6, delta);
        assertEquals(combinedFunctionArray.andThen(combinedFunctionList).andThen(sqrX).apply(1), 2.4, delta);
        assertEquals(combinedFunctionArray.andThen(combinedFunctionList).andThen(sqrX).apply(2.35), 0.8, delta);
        assertEquals(combinedFunctionArray.andThen(combinedFunctionList).andThen(sqrX).apply(9), 9063.2, delta);
    }

}