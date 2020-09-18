package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    UnitFunction testFunction = new UnitFunction();

    @Test
    public void testGetConstant() {
        assertEquals(testFunction.apply(10), 1.0);
    }

    @Test
    public void testApply() {
        assertEquals(testFunction.getConstant(), 1.0);
    }
}