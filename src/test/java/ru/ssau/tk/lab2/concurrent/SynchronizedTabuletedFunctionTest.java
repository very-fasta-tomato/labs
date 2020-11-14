package ru.ssau.tk.lab2.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.lab2.functions.*;

import static org.testng.Assert.*;

public class SynchronizedTabuletedFunctionTest {
    private static final MathFunction sqrFunction = new SqrFunction();
    private static final TabulatedFunction linkedlistTabulatedFunction = new LinkedListTabulatedFunction(sqrFunction, -5, 5, 11);
    private static final TabulatedFunction synchronizedLinkedList = new SynchronizedTabuletedFunction(linkedlistTabulatedFunction);
    private static final TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(sqrFunction, -5, 5, 11);
    private static final TabulatedFunction synchronizedArray = new SynchronizedTabuletedFunction(arrayTabulatedFunction);

    @Test
    public void testGetCount() {
        assertEquals(synchronizedArray.getCount(), 11);
        assertEquals(synchronizedLinkedList.getCount(), 11);
    }

    @Test
    public void testGetX() {
        assertEquals(synchronizedArray.getX(0), -5.);
        assertEquals(synchronizedArray.getX(5), 0.);
        assertEquals(synchronizedLinkedList.getX(0), -5.);
        assertEquals(synchronizedLinkedList.getX(5), 0.);
    }

    @Test
    public void testGetY() {
        assertEquals(synchronizedArray.getY(0), 25.);
        assertEquals(synchronizedArray.getY(3), 4.);
        assertEquals(synchronizedArray.getY(10), 25.);
        assertEquals(synchronizedLinkedList.getY(0), 25.);
        assertEquals(synchronizedLinkedList.getY(3), 4.);
        assertEquals(synchronizedLinkedList.getY(10), 25.);
    }

    @Test
    public void testSetY() {
        synchronizedArray.setY(0, 1.);
        assertEquals(synchronizedArray.getY(0), 1.);
        synchronizedLinkedList.setY(10, 1.);
        assertEquals(synchronizedLinkedList.getY(10), 1.);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(synchronizedArray.indexOfX(-4.), 1);
        assertEquals(synchronizedArray.indexOfX(3.), 8);
        assertEquals(synchronizedLinkedList.indexOfX(-4.), 1);
        assertEquals(synchronizedLinkedList.indexOfX(3.), 8);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(synchronizedArray.indexOfY(25.), 0);
        assertEquals(synchronizedArray.indexOfY(0.), 5);
        assertEquals(synchronizedLinkedList.indexOfY(25.), 0);
        assertEquals(synchronizedLinkedList.indexOfY(0.), 5);
    }

    @Test
    public void testLeftBound() {
        assertEquals(synchronizedArray.leftBound(), -5.);
        assertEquals(synchronizedLinkedList.leftBound(), -5.);
    }

    @Test
    public void testRightBound() {
        assertEquals(synchronizedArray.rightBound(), 5.);
        assertEquals(synchronizedLinkedList.rightBound(), 5.);
    }

    @Test
    public void testIterator() {
    }

    @Test
    public void testApply() {
    }
}