package ru.ssau.tk.lab2.operations;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import ru.ssau.tk.lab2.functions.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        final double step = 0.001;
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2, 0.01);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4, 0.01);
    }
}