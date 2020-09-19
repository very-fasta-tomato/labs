package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    ZeroFunction testFunction = new ZeroFunction();

    @Test
    public void testGetConstant() {
        assertEquals(testFunction.apply(10), 0.0);
        assertEquals(testFunction.apply(0), 0.0);
        assertEquals(testFunction.apply(-10), 0.0);
    }

    @Test
    public void testApply() {
        assertEquals(testFunction.getConstant(), 0.0);
    }
}