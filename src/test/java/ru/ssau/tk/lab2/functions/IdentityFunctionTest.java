package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        IdentityFunction testFunction = new IdentityFunction();
        assertEquals(testFunction.apply(5.0),5.0);
        assertEquals(testFunction.apply(1.5),1.5);
        assertEquals(testFunction.apply(10.8557),10.8557);
        assertNotEquals(testFunction.apply(5),5);
    }

}