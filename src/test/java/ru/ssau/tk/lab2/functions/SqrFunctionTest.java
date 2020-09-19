package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        SqrFunction testFunction = new SqrFunction();
        assertEquals(testFunction.apply(2), 4.0, 0.001);
        assertEquals(testFunction.apply(-2), 4.0, 0.001);
        assertNotEquals(testFunction.apply(2), 2.0, 0.001);
    }
}