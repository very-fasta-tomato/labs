package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        SqrFunction testFunction= new SqrFunction();
        assertEquals(testFunction.apply(2),4.0);
        assertNotEquals(testFunction.apply(2),2.0);
    }
}