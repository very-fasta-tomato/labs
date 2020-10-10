package ru.ssau.tk.lab2.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class MockTabulatedFunctionTest {
    private final MockTabulatedFunction mockTabulatedFunctionTest = new MockTabulatedFunction();
    private final double delta = 0.1;

    @Test
    public void abstractTabulatedFunctionTest() {
        assertEquals(mockTabulatedFunctionTest.getCount(), 2);
        assertEquals(mockTabulatedFunctionTest.interpolate(0.5, 0), 0.5, delta);
        assertEquals(mockTabulatedFunctionTest.interpolate(0.7, 0), 0.7, delta);
        assertEquals(mockTabulatedFunctionTest.interpolate(0.9, 0), 0.9, delta);

        assertEquals(mockTabulatedFunctionTest.apply(0), 0.0, delta);
        assertEquals(mockTabulatedFunctionTest.apply(1), 1.0, delta);
        assertEquals(mockTabulatedFunctionTest.apply(0.7), 0.7, delta);
        assertEquals(mockTabulatedFunctionTest.apply(-0.5), -0.5, delta);
        assertEquals(mockTabulatedFunctionTest.apply(1.5), 1.5, delta);
    }
}