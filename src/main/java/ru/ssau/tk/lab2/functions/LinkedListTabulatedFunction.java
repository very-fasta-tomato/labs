package ru.ssau.tk.lab2.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;

    private void addNode(double x, double y){
        Node newNode = new Node();
        if (head==null){
            newNode.x=x;
            newNode.y=y;
            newNode.next=newNode;
            newNode.prev=newNode;
            head = newNode;
        }
        else {
            newNode.x=x;
            newNode.y=y;
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
        }
        count++;
    }
    LinkedListTabulatedFunction(double[] xValues, double[] yValues){
        for (int iterator=0; iterator<count; iterator++){
            addNode(xValues[iterator], yValues[iterator]);
        }
    }
    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        this.count=count;
        double step = (xTo - xFrom) / (count - 1);
        double xMomentValue = xFrom;
        for (int iterator=0; iterator<count; iterator++){
            addNode(xMomentValue, source.apply(xMomentValue));
            xMomentValue+=step;
        }
    }
    Node getNode(int index){
        Node currentNode=head;
        if (index<=count/2) {
            for (int iterator = 0; iterator < index; iterator++) {
                currentNode = currentNode.next;
            }
        }
        else{
            for (int iterator = 0; iterator <= count-index; iterator++) {
                currentNode = currentNode.prev;
            }
        }
        return currentNode;
    }

    @Override
    public int getCount(){
        return count;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public double leftBound(){
        return head.x;
    }

    @Override
    public double getX(int index){
        return getNode(index).x;
    }

    @Override
    public double getY(int index){
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value){
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        int iterator = 0;
        while (iterator<count){
            if(getNode(iterator).x==x){
                return iterator;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        int iterator = 0;
        while (iterator<count){
            if(getNode(iterator).y==y){
                return iterator;
            }
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < head.x) {
            return 0;
        }
        for (int iterator = 0; iterator + 1 < count; iterator++) {
            if (getNode(iterator).next.x > x) {
                return iterator;
            }
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
    public double interpolate (double x, int floorIndex){
        if (count == 1) {
            return x;
        }
        return interpolate(x, getNode(floorIndex).x, getNode(floorIndex+1).x, getNode(floorIndex).y, getNode(floorIndex+1).y);
    }

}
