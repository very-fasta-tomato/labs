package ru.ssau.tk.lab2.operations;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import ru.ssau.tk.lab2.functions.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        final double step = 0.001;
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.99, 0.1);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 3.99, 0.1);
    }
}