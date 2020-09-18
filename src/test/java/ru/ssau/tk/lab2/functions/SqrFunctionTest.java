package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        assertEquals(java.lang.Math.pow(2, 2),4.0);
        assertNotEquals(java.lang.Math.pow(2, 2),2.0);
    }
}