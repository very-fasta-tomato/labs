package ru.ssau.tk.lab2.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
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
        this.count = xValues.length;
        for (int iterator = 0; iterator < count; iterator++) {
            this.addNode(xValues[iterator], yValues[iterator]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        double step = (xTo - xFrom) / (count - 1);
        double xMomentValue = xFrom;
        for (int iterator = 0; iterator < count; iterator++) {
            this.addNode(xMomentValue, source.apply(xMomentValue));
            xMomentValue += step;
        }
    }

    private Node getNode(int index) {
        Node currentNode = head;
        for (int iterator = 0; iterator < index; iterator++) {
            currentNode = currentNode.next;
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
            return 0;
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
        if (count == 1) {
            return x;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    public double extrapolateRight(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, getNode(floorIndex).x, getNode(floorIndex + 1).x, getNode(floorIndex).y, getNode(floorIndex + 1).y);
    }

    public static LinkedListTabulatedFunction createTabulatedFunctionDefinedThroughList(double[] valuesX, double[] valuesY){
        return new LinkedListTabulatedFunction(valuesX, valuesY);
    }

    public static LinkedListTabulatedFunction createTabulatedFunctionDefinedThroughMathFunction(MathFunction source, double xFrom, double xTo, int count){
        return new LinkedListTabulatedFunction(source, xFrom, xTo, count);
    }

}
