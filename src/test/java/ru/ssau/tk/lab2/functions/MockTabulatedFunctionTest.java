package ru.ssau.tk.lab2.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class MockTabulatedFunctionTest {
    private final MockTabulatedFunction mockTabulatedFunctionTest = new MockTabulatedFunction();

    @Test
    public void abstractTabulatedFunctionTest(){
        assertEquals(mockTabulatedFunctionTest.getCount(), 2);
    }
}