package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PowFunctionTest {

    @Test
    public void testApply() {
        PowFunction testFunction= new PowFunction();
        assertEquals(testFunction.apply(9),27.0);
        assertNotEquals(testFunction.apply(9),9.0);
    }
}