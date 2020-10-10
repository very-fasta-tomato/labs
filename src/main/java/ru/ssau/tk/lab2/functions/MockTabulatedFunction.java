package ru.ssau.tk.lab2.functions;

public class MockTabulatedFunction extends AbstractTabulatedFunction {
    private final double x0 = 0.;
    private final double x1 = 1.;
    private final double y0 = 0.;
    private final double y1 = 1.;

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        return index;
    }

    @Override
    public double getY(int index) {
        return index;
    }

    @Override
    public void setY(int index, double value) {
        System.out.println("Fields are final, you can't change them.");
    }

    @Override
    public int indexOfX(double x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int indexOfY(double y) {
        if (y == 0) {
            return 0;
        }
        if (y == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public double leftBound() {
        return 0;
    }

    @Override
    public double rightBound() {
        return 1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < x1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public double extrapolateLeft(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public double extrapolateRight(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (floorIndex < x1) {
            return interpolate(x, x0, x1, y0, y1);
        } else {
            return interpolate(x, x1, x0, y1, y0);
        }
    }
}
