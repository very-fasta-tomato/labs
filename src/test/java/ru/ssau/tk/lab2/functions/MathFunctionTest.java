package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    private MathFunction X = new PowFunction();
    private MathFunction sqrX = new SqrFunction();
    private MathFunction one = new UnitFunction();
    private MathFunction testFunction = sqrX.andThen(X);
    private MathFunction tanFunction = new TanFunction();

    @Test
    public void testAndThen() {
        assertEquals(testFunction.apply(3), 27.0, 0.001);
        assertEquals(testFunction.andThen(one).apply(10), 1.0, 0.001);
        assertEquals(testFunction.andThen(sqrX).apply(2), 64.0, 0.001);
        assertEquals(testFunction.andThen(sqrX).apply(2.5), 244.140, 0.001);
    }

    @Test
    public void tabulatedCombinedFunctionsTest(){
        final double[] valuesX = new double[]{0., 1., 2., 3., 4., 5., 6., 7., 8., 9.};
        final double[] valuesY = new double[10];
        for (int i = 0; i < 10; i++) {
            valuesY[i] = tanFunction.apply(valuesX[i]);
        }
        LinkedListTabulatedFunction combinedFunctionList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(tanFunction, -9.0, 9.0, 100);
        //ArrayTabulatedFunction combinedFunctionArray = ArrayTabulatedFunction.
    }

}