package ru.ssau.tk.lab2.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.lab2.functions.*;
import ru.ssau.tk.lab2.functions.factory.*;

import static org.testng.Assert.*;

import ru.ssau.tk.lab2.exceptions.*;

public class TabulatedFunctionOperationServiceTest {
    private final double[] valuesX = new double[]{-27, -8, -1, 0, 1, 8, 27};
    private final double[] valuesY = new double[]{-3, -2, -1, -0, 1, 2, 3};
    private final double[] valuesYSecond = new double[]{1, 2, 3, 4, 5, 6, 7};
    ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
    LinkedListTabulatedFunction listFunction = new LinkedListTabulatedFunction(valuesX, valuesYSecond);
    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

    @Test
    public void testAsPoints() {
        Point[] Points = TabulatedFunctionOperationService.asPoints(arrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, arrayFunction.getX(i), 0.1);
            assertEquals(myPoint.y, arrayFunction.getY(i++), 0.1);
        }
        assertEquals(arrayFunction.getCount(), i);

        Points = TabulatedFunctionOperationService.asPoints(listFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, listFunction.getX(i), 0.1);
            assertEquals(myPoint.y, listFunction.getY(i++), 0.1);
        }
        assertEquals(listFunction.getCount(), i);
    }

    @Test
    public void testGetFactory() {
        assertTrue(service.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        service.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(service.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        final double[] errorX = new double[]{0, 1, 2};
        final double[] errorY = new double[]{0, 1, 2};
        TabulatedFunction errorTest = new ArrayTabulatedFunction(errorX, errorY);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().sum(listFunction, errorTest));

        final double[] errorX1 = new double[]{-27, -8, -1, 0, 1, 8, 28};
        final double[] errorY1 = new double[]{-3, -2, -1, -0, 1, 2, 3};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(errorX1, errorY1);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().sum(listFunction, errorTest1));

        TabulatedFunction testSumOfArrays = new TabulatedFunctionOperationService().sum(arrayFunction, arrayFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }

        TabulatedFunction testSumOfLists = new TabulatedFunctionOperationService().sum(listFunction, listFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] + valuesYSecond[i++]);
        }

        TabulatedFunction testSumOfArrayAndList = new TabulatedFunctionOperationService().sum(arrayFunction, listFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYSecond[i++]);
        }

        TabulatedFunction testMultipyOfArrays = new TabulatedFunctionOperationService().multiply(arrayFunction, arrayFunction);
        i = 0;
        for (Point point : testMultipyOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction testSubtractOfArrays = new TabulatedFunctionOperationService().subtract(arrayFunction, arrayFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }

        TabulatedFunction testSubtractOfLists = new TabulatedFunctionOperationService().subtract(listFunction, listFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] - valuesYSecond[i++]);
        }

        TabulatedFunction testSubtractOfArrayAndList = new TabulatedFunctionOperationService().subtract(arrayFunction, listFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYSecond[i++]);
        }
    }
}