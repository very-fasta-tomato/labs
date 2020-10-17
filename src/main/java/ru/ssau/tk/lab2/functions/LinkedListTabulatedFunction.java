package ru.ssau.tk.lab2.functions;

import ru.ssau.tk.lab2.exceptions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    private Node head;

    private void addNode(double x, double y) {
        Node newNode = new Node();
        if (head == null) {
            newNode.x = x;
            newNode.y = y;
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
        } else {
            newNode.next = head;
            newNode.prev = head.prev;
            newNode.x = x;
            newNode.y = y;
            head.prev.next = newNode;
            head.prev = newNode;
        }
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if ((xValues.length < 2) || (yValues.length < 2)) {
            throw new IllegalArgumentException("List length less, than minimal");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        this.count = xValues.length;
        for (int iterator = 0; iterator < count; iterator++) {
            this.addNode(xValues[iterator], yValues[iterator]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Max X is less, than min X");
        }
        if (count < 2) {
            throw new IllegalArgumentException("List length less, than minimal");
        }
        this.count = count;
        double step = (xTo - xFrom) / (count - 1);
        double xMomentValue = xFrom;
        for (int iterator = 0; iterator < count; iterator++) {
            this.addNode(xMomentValue, source.apply(xMomentValue));
            xMomentValue += step;
        }
    }

    private Node getNode(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        Node currentNode = head;
        for (int iterator = 0; iterator < index; iterator++) {
            currentNode = currentNode.next;
            if (currentNode == head) {
                throw new IllegalArgumentException();
            }
        }
        return currentNode;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        int iterator = 0;
        Node currentNode = head;
        while (iterator < count) {
            if (currentNode.x == x) {
                return iterator;
            }
            iterator++;
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        int iterator = 0;
        Node currentNode = head;
        while (iterator < count) {
            if (currentNode.y == y) {
                return iterator;
            }
            iterator++;
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException();
        }
        Node currentNode = head;
        for (int iterator = 0; iterator + 1 < count; iterator++) {
            if (currentNode.next.x > x) {
                return iterator;
            }
            currentNode = currentNode.next;
        }
        return count;
    }

    @Override
    public double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    public double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (x < head.x || x > head.prev.x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, getNode(floorIndex).x, getNode(floorIndex + 1).x, getNode(floorIndex).y, getNode(floorIndex + 1).y);
    }

    public static LinkedListTabulatedFunction createTabulatedFunctionDefinedThroughList(double[] valuesX, double[] valuesY) {
        return new LinkedListTabulatedFunction(valuesX, valuesY);
    }

    public static LinkedListTabulatedFunction createTabulatedFunctionDefinedThroughMathFunction(MathFunction source, double xFrom, double xTo, int count) {
        return new LinkedListTabulatedFunction(source, xFrom, xTo, count);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private Node node = head;

            public boolean hasNext() {
                return (node != null);
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(node.x, node.y);
                    node = (node != head.prev) ? node.next : null;
                    return point;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

}
