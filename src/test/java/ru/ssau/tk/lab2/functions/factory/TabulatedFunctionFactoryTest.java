package ru.ssau.tk.lab2.functions.factory;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk.lab2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lab2.functions.LinkedListTabulatedFunction;

public class TabulatedFunctionFactoryTest {
    final static double[] xValuesOne = new double[]{-2, -1, 0, 1, 2};
    final static double[] xValuesTwo = new double[]{0, 0, 0};
    final static double[] yValuesOne = new double[]{-2, -1, 0, 1, 2};
    final static double[] yValuesTwo = new double[]{0, 0, 0};
    ArrayTabulatedFunctionFactory factory1 = new ArrayTabulatedFunctionFactory();
    LinkedListTabulatedFunctionFactory factory2 = new LinkedListTabulatedFunctionFactory();

    @Test
    public void arrayFactoryTest(){
        assertEquals(factory1.create(xValuesOne, yValuesOne).getClass(), ArrayTabulatedFunction.class);
        assertEquals(factory1.create(xValuesTwo, yValuesTwo).getClass(), ArrayTabulatedFunction.class);
        assertEquals(factory2.create(xValuesOne, yValuesOne).getClass(), LinkedListTabulatedFunction.class);
        assertEquals(factory2.create(xValuesTwo, yValuesTwo).getClass(), LinkedListTabulatedFunction.class);
    }
}