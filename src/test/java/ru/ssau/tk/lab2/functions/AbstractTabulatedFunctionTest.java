package ru.ssau.tk.lab2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import ru.ssau.tk.lab2.exceptions.*;
import ru.ssau.tk.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.lab2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.lab2.functions.factory.TabulatedFunctionFactory;

public class AbstractTabulatedFunctionTest {

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] valuesX = new double[]{-3, 5};
            double[] valuesY = new double[]{9};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
        });
        double[] valuesX = new double[]{-3};
        double[] valuesY = new double[]{9};
        AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);

    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-3, 5, 7, 9, 0};
            AbstractTabulatedFunction.checkSorted(valuesX);
        });
        double[] valuesX = new double[]{-3, 5, 7, 9};
        AbstractTabulatedFunction.checkSorted(valuesX);
    }

    @Test
    public void testToString(){
        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction listFunction = listFactory.create(new double[]{-1., 0., 1., 2.}, new double[]{-5., 0., 5., 12.});
        TabulatedFunction arrayFunction = arrayFactory.create(new double[]{-7., -2., 1.}, new double[]{-49., -8., 2.});
        assertEquals(listFunction.toString(), "LinkedListTabulatedFunction; size = 4\n[-1.0, -5.0]\n[0.0, 0.0]\n[1.0, 5.0]\n[2.0, 12.0]");
        assertEquals(arrayFunction.toString(), "ArrayTabulatedFunction; size = 3\n[-7.0, -49.0]\n[-2.0, -8.0]\n[1.0, 2.0]");
    }
}