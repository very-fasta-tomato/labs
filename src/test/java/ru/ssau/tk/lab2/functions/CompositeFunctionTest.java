package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testCompositeFunction() {
        MathFunction firstFunction = new IdentityFunction();
        MathFunction secondFunction = new TanFunction();
        MathFunction thirdFunction = new PowFunction();
        CompositeFunction firstCompositeFunction = new CompositeFunction(secondFunction, thirdFunction);
        assertEquals(firstCompositeFunction.apply(0.0), 0);
        CompositeFunction secondCompositeFunction = new CompositeFunction(firstFunction, thirdFunction);
        assertEquals(secondCompositeFunction.apply(9), 27);
    }

}