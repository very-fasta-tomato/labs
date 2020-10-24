package ru.ssau.tk.lab2.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.lab2.functions.*;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    private final double[] valuesX = new double[]{-27, -8, -1, 0, 1, 8, 27};
    private final double[] valuesY = new double[]{-3, -2, -1, -0, 1, 2, 3};
    ArrayTabulatedFunction ArrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
    LinkedListTabulatedFunction ListFunction = new LinkedListTabulatedFunction(valuesX, valuesY);

    @Test
    public void testAsPoints() {
        Point[] Points = TabulatedFunctionOperationService.asPoints(ArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, ArrayFunction.getX(i), 0.1);
            assertEquals(myPoint.y, ArrayFunction.getY(i++), 0.1);
        }
        assertEquals(ArrayFunction.getCount(), i);

        Points = TabulatedFunctionOperationService.asPoints(ListFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, ListFunction.getX(i), 0.1);
            assertEquals(myPoint.y, ListFunction.getY(i++), 0.1);
        }
        assertEquals(ListFunction.getCount(), i);
    }
}