package ru.ssau.tk.lab2.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.lab2.functions.*;

import java.util.Iterator;

import static org.testng.Assert.*;

public class SynchronizedTabuletedFunctionTest {
    private static final MathFunction sqrFunction = new SqrFunction();
    private static final TabulatedFunction linkedlistTabulatedFunction = new LinkedListTabulatedFunction(sqrFunction, -5, 5, 11);
    private static final TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedlistTabulatedFunction);
    private static final TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(sqrFunction, -5, 5, 11);
    private static final TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);

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
    public void testIteratorThroughWile() {
        Iterator<Point> iteratorOfArray = synchronizedArray.iterator();
        int i = 0;
        while (iteratorOfArray.hasNext()) {
            Point currentPoint = iteratorOfArray.next();
            assertEquals(synchronizedArray.getX(i), currentPoint.x, 0.001);
            assertEquals(synchronizedArray.getY(i++), currentPoint.y, 0.001);
        }
        assertEquals(synchronizedArray.getCount(), i);
        Iterator<Point> iteratorOfLinkedList = synchronizedArray.iterator();
        i = 0;
        while (iteratorOfLinkedList.hasNext()) {
            Point currentPoint = iteratorOfLinkedList.next();
            assertEquals(synchronizedLinkedList.getX(i), currentPoint.x, 0.001);
            assertEquals(synchronizedLinkedList.getY(i++), currentPoint.y, 0.001);
        }
        assertEquals(synchronizedLinkedList.getCount(), i);
    }

    @Test
    public void testIteratorThroughForEach(){
        int i = 0;
        for (Point currentPoint : synchronizedArray) {
            assertEquals(currentPoint.x, synchronizedArray.getX(i), 0.001);
            assertEquals(currentPoint.y, synchronizedArray.getY(i++), 0.001);
        }
        assertEquals(synchronizedArray.getCount(), i);
        i = 0;
        for (Point currentPoint : synchronizedLinkedList) {
            assertEquals(currentPoint.x, synchronizedLinkedList.getX(i), 0.001);
            assertEquals(currentPoint.y, synchronizedLinkedList.getY(i++), 0.001);
        }
        assertEquals(synchronizedLinkedList.getCount(), i);
    }

    @Test
    public void testApply() {
    }
}