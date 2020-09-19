package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    MathFunction X = new PowFunction();
    MathFunction sqrX = new SqrFunction();
    MathFunction one = new UnitFunction();
    MathFunction testFunction = sqrX.andThen(X);

    @Test
    public void testAndThen() {
        assertEquals(testFunction.apply(3), 27.0, 0.001);
        assertEquals(testFunction.andThen(one).apply(10), 1.0, 0.001);
        assertEquals(testFunction.andThen(sqrX).apply(2), 64.0, 0.001);
        assertEquals(testFunction.andThen(sqrX).apply(2.5), 244.140, 0.001);
    }
}