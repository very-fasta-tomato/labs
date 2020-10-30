package ru.ssau.tk.lab2.operations;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.lab2.functions.factory.LinkedListTabulatedFunctionFactory;

public class TabulatedDifferetialOperatorTest {
    static ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
    static LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
    private static final double[] xValues = new double[]{-3., -2., -1., 0., 1., 2., 3.};
    private static final double[] yValues = new double[]{9., 4., 1., 0., 1., 4., 9.};
    private static final double[] derivedYValues = new double[]{-6., -4., -2., 0., 2., 4., 6.};
    private static final double[] newXValues = new double[]{4., 9., 16., 25., 36.};
    private static final double[] newYValues = new double[]{8., 27., 64., 125., 216.};
    private static final double[] newDerivedYValues = new double[]{3., 4.5, 6., 7.5, 9.};
    final static TabulatedFunction firstFunction = arrayTabulatedFunctionFactory.create(xValues, yValues);
    final static TabulatedFunction secondFunction = linkedListTabulatedFunctionFactory.create(xValues, yValues);
    final static TabulatedFunction newFirstFunction = arrayTabulatedFunctionFactory.create(newXValues, newYValues);
    final static TabulatedFunction newSecondFunction = linkedListTabulatedFunctionFactory.create(newXValues, newYValues);
    static TabulatedDifferetialOperator firstOperator = new TabulatedDifferetialOperator();
    static TabulatedDifferetialOperator secondOperator = new TabulatedDifferetialOperator(new ArrayTabulatedFunctionFactory());
    static TabulatedDifferetialOperator thirdOperator = new TabulatedDifferetialOperator(new LinkedListTabulatedFunctionFactory());

    @Test
    public void testGetFactory() {
        assertEquals(firstOperator.getFactory(), ArrayTabulatedFunctionFactory.class);
        assertEquals(secondOperator.getFactory(), ArrayTabulatedFunctionFactory.class);
        assertEquals(thirdOperator.getFactory(), LinkedListTabulatedFunctionFactory.class);
    }

    @Test
    public void testSetFactory() {
        firstOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        secondOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        thirdOperator.setFactory(new ArrayTabulatedFunctionFactory());
        assertEquals(firstOperator.getFactory(), LinkedListTabulatedFunctionFactory.class);
        assertEquals(secondOperator.getFactory(), LinkedListTabulatedFunctionFactory.class);
        assertEquals(thirdOperator.getFactory(), ArrayTabulatedFunctionFactory.class);
    }

    @Test
    public void testDerive() {
        TabulatedFunction derivedArrayFunction = firstOperator.derive(firstFunction);
        TabulatedFunction derivedLinkedListFunction = thirdOperator.derive(secondFunction);
        int count = derivedArrayFunction.getCount();
        for (int i = 0; i < count; i++) {
            assertEquals(derivedArrayFunction.getY(i), derivedYValues[i], 1.);
            assertEquals(derivedLinkedListFunction.getY(i), derivedYValues[i], 1.);
        }
        derivedArrayFunction = firstOperator.derive(newFirstFunction);
        derivedLinkedListFunction = thirdOperator.derive(newSecondFunction);
        count = derivedArrayFunction.getCount();
        for (int i = 0; i < count; i++) {
            assertEquals(derivedArrayFunction.getY(i), newDerivedYValues[i], 1.);
            assertEquals(derivedLinkedListFunction.getY(i), newDerivedYValues[i], 1.);
        }
    }
}