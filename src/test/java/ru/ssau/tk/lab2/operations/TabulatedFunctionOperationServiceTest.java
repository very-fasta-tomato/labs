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
    private TabulatedFunction arrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
    private TabulatedFunction listFunction = new LinkedListTabulatedFunction(valuesX, valuesYSecond);
    private final TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService arrayService = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
    private final TabulatedFunctionOperationService listService = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());


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
    public void testException() {
        final double[] errorX = new double[]{0, 1, 2};
        final double[] errorY = new double[]{0, 1, 2};
        TabulatedFunction errorTest = new ArrayTabulatedFunction(errorX, errorY);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().sum(listFunction, errorTest));

        final double[] errorX1 = new double[]{-27, -8, -1, 0, 1, 8, 28};
        final double[] errorY1 = new double[]{-3, -2, -1, -0, 1, 2, 3};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(errorX1, errorY1);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().sum(listFunction, errorTest1));
    }

    @Test
    public void testSum() {
        TabulatedFunction arrayTestSumOfArrays =arrayService.sum(arrayFunction, arrayFunction);
        TabulatedFunction listTestSumOfArrays = listService.sum(arrayFunction, arrayFunction);
        int i = 0;
        assertTrue(arrayTestSumOfArrays instanceof ArrayTabulatedFunction);
        assertTrue(listTestSumOfArrays instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }
        i = 0;
        for (Point point : listTestSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }

        TabulatedFunction arrayTestSumOfLists = arrayService.sum(listFunction, listFunction);
        TabulatedFunction listTestSumOfLists = listService.sum(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestSumOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestSumOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] + valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] + valuesYSecond[i++]);
        }

        TabulatedFunction arrayTestSumOfArrayAndList = arrayService.sum(arrayFunction, listFunction);
        TabulatedFunction listTestSumOfArrayAndList = listService.sum(arrayFunction, listFunction);
        i = 0;
        assertTrue(arrayTestSumOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(listTestSumOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYSecond[i++]);
        }
    }

    @Test
    public void testMultiply() {
        TabulatedFunction arrayTestMultiplyOfArrays = arrayService.multiply(arrayFunction, arrayFunction);
        TabulatedFunction listTestMultiplyOfArrays = listService.multiply(arrayFunction, arrayFunction);
        int i = 0;
        assertTrue(arrayTestMultiplyOfArrays instanceof ArrayTabulatedFunction);
        assertTrue(listTestMultiplyOfArrays instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestMultiplyOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesY[i++]);
        }
        i = 0;
        for (Point point : listTestMultiplyOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesY[i++]);
        }

        TabulatedFunction arrayTestMultiplyOfLists = arrayService.multiply(listFunction, listFunction);
        TabulatedFunction listTestMultiplyOfLists = listService.multiply(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestMultiplyOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestMultiplyOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestMultiplyOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] * valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestMultiplyOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] * valuesYSecond[i++]);
        }

        TabulatedFunction arrayTestMultiplyOfArrayAndList = arrayService.multiply(arrayFunction, listFunction);
        TabulatedFunction listTestMultiplyOfArrayAndList = listService.multiply(arrayFunction, listFunction);
        i = 0;
        assertTrue(arrayTestMultiplyOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(listTestMultiplyOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestMultiplyOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestMultiplyOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesYSecond[i++]);
        }
    }

    @Test
    public void testDivision() {
        TabulatedFunction arrayTestDivisionOfArrays = arrayService.division(arrayFunction, arrayFunction);
        TabulatedFunction listTestDivisionOfArrays = listService.division(arrayFunction, arrayFunction);
        int i = 0;
        assertTrue(arrayTestDivisionOfArrays instanceof ArrayTabulatedFunction);
        assertTrue(listTestDivisionOfArrays instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestDivisionOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesY[i++]);
        }
        i = 0;
        for (Point point : listTestDivisionOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesY[i++]);
        }

        TabulatedFunction arrayTestDivisionOfLists = arrayService.division(listFunction, listFunction);
        TabulatedFunction listTestDivisionOfLists = listService.division(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestDivisionOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestDivisionOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestDivisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] / valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestDivisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] / valuesYSecond[i++]);
        }

        TabulatedFunction arrayTestDivisionOfArrayAndList = arrayService.division(arrayFunction, listFunction);
        TabulatedFunction listTestDivisionOfArrayAndList = listService.division(arrayFunction, listFunction);
        i = 0;
        assertTrue(arrayTestDivisionOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(listTestDivisionOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestDivisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestDivisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesYSecond[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction arrayTestSubtractOfArrays = arrayService.subtract(arrayFunction, arrayFunction);
        TabulatedFunction listTestSubtractOfArrays = listService.subtract(arrayFunction, arrayFunction);
        int i = 0;
        assertTrue(arrayTestSubtractOfArrays instanceof ArrayTabulatedFunction);
        assertTrue(listTestSubtractOfArrays instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }
        i = 0;
        for (Point point : listTestSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }

        TabulatedFunction arrayTestSubtractOfLists = arrayService.subtract(listFunction, listFunction);
        TabulatedFunction listTestSubtractOfLists = listService.subtract(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestSubtractOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestSubtractOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] - valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] - valuesYSecond[i++]);
        }

        TabulatedFunction arrayTestSubtractOfArrayAndList = arrayService.subtract(arrayFunction, listFunction);
        TabulatedFunction listTestSubtractOfArrayAndList = listService.subtract(arrayFunction, listFunction);
        i = 0;
        assertTrue(arrayTestSubtractOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(listTestSubtractOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYSecond[i++]);
        }
    }
}