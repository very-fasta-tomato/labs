package ru.ssau.tk.lab2.operations;

import ru.ssau.tk.lab2.functions.*;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            points[i++] = newPoint;
        }
        return points;
    }
}
