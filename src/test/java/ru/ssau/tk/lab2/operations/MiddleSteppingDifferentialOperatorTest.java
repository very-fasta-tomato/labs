package ru.ssau.tk.lab2.operations;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import ru.ssau.tk.lab2.functions.*;

public class MiddleSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        final double step = 0.01;
        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2, 0.1);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4, 0.1);
    }
}