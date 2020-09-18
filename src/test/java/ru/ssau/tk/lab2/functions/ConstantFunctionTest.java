package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testApply() {
        ConstantFunction testFunction = new ConstantFunction(3);
        assertEquals(testFunction.apply(8),3.0);
    }
}