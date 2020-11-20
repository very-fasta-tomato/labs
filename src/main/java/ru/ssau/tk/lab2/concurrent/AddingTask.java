package ru.ssau.tk.lab2.concurrent;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

public class AddingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    private Runnable postRunAction;

    public AddingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    public AddingTask(TabulatedFunction func, Runnable postRunAction) {
        this.tabulatedFunction = func;
        this.postRunAction = postRunAction;
    }

    public void run() {
        double currentX;
        double currentY;
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            currentX = tabulatedFunction.getX(i);
            synchronized (tabulatedFunction) {
                currentY = tabulatedFunction.getY(i);
                System.out.println(Thread.currentThread().getName() + "i =" + i + " x = " + currentX + " old y = " + currentY);
                tabulatedFunction.setY(i, currentY + 3);
            }
            System.out.println(Thread.currentThread().getName() + "i =" + i + " x = " + currentX + " new y = " + currentY);
        }
    }
}
