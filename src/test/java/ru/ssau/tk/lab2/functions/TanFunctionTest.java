package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TanFunctionTest {

    @Test
    public void testTanFunction() {
        TanFunction testTanFunction = new TanFunction();
        assertEquals(testTanFunction.apply(0), 0.0);
        assertEquals(testTanFunction.apply(1.57), 1255.7656, 0.01);
        assertNotEquals(testTanFunction.apply(1), 2.4);
        assertNotEquals(testTanFunction.apply(1.57), 0.25, 0.1);
    }

}