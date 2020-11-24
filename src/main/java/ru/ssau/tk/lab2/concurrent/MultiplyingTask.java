package ru.ssau.tk.lab2.concurrent;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;

    public MultiplyingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    public void run() {
        double currentX;
        double currentY;
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            currentX = tabulatedFunction.getX(i);
            synchronized (tabulatedFunction) {
                currentY = tabulatedFunction.getY(i);
                System.out.println(Thread.currentThread().getName() + "i =" + i + " x = " + currentX + " old y = " + currentY);
                tabulatedFunction.setY(i, currentY * 10);
            }
            System.out.println(Thread.currentThread().getName() + "i =" + i + " x = " + currentX + " new y = " + currentY);
        }
    }
}

