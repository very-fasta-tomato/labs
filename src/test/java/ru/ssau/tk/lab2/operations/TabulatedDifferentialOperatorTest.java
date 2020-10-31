package ru.ssau.tk.lab2.operations;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.lab2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.lab2.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperatorTest {
    static TabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
    static TabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
    private static final double[] xValues = new double[]{-3., -2., -1., 0., 1., 2., 3.};
    private static final double[] yValues = new double[]{9., 4., 1., 0., 1., 4., 9.};
    private static final double[] derivedYValues = new double[]{-6., -4., -2., 0., 2., 4., 9.};
    private static final double[] newXValues = new double[]{4., 9., 16., 25., 36.};
    private static final double[] newYValues = new double[]{8., 27., 64., 125., 216.};
    private static final double[] newDerivedYValues = new double[]{3., 4.5, 6., 7.5, 216.};
    final static TabulatedFunction firstFunction = arrayTabulatedFunctionFactory.create(xValues, yValues);
    final static TabulatedFunction secondFunction = linkedListTabulatedFunctionFactory.create(xValues, yValues);
    final static TabulatedFunction newFirstFunction = arrayTabulatedFunctionFactory.create(newXValues, newYValues);
    final static TabulatedFunction newSecondFunction = linkedListTabulatedFunctionFactory.create(newXValues, newYValues);
    static TabulatedDifferentialOperator firstOperator = new TabulatedDifferentialOperator();
    static TabulatedDifferentialOperator secondOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
    static TabulatedDifferentialOperator thirdOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

    @Test
    public void testGetFactory() {
        assertTrue(firstOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(secondOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(thirdOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        firstOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        secondOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        thirdOperator.setFactory(new ArrayTabulatedFunctionFactory());
        assertTrue(firstOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(secondOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(thirdOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    public void testDerive() {
        TabulatedFunction derivedArrayFunction = firstOperator.derive(firstFunction);
        TabulatedFunction derivedLinkedListFunction = thirdOperator.derive(secondFunction);
        assertTrue(firstOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(thirdOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        int count = derivedArrayFunction.getCount();
        for (int i = 0; i < count; i++) {
            assertEquals(derivedArrayFunction.getY(i), derivedYValues[i], 1.);
            assertEquals(derivedLinkedListFunction.getY(i), derivedYValues[i], 1.);
        }
        derivedArrayFunction = firstOperator.derive(newFirstFunction);
        derivedLinkedListFunction = thirdOperator.derive(newSecondFunction);
        assertTrue(firstOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(thirdOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        count = derivedArrayFunction.getCount();
        for (int i = 0; i < count; i++) {
            assertEquals(derivedArrayFunction.getY(i), newDerivedYValues[i], 1.);
            assertEquals(derivedLinkedListFunction.getY(i), newDerivedYValues[i], 1.);
        }
    }
}