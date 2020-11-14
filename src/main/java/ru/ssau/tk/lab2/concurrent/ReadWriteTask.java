package ru.ssau.tk.lab2.concurrent;

import ru.ssau.tk.lab2.functions.TabulatedFunction;

public class ReadWriteTask implements Runnable{
    private final TabulatedFunction tabulatedFunction;

    public ReadWriteTask (TabulatedFunction tabulatedFunction){
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++){
            double currentX = tabulatedFunction.getX(i);
            double currentY = tabulatedFunction.getY(i);
            synchronized (Thread.currentThread()){
                System.out.println(Thread.currentThread().getName() + " before write: i = " + i + " x = " + currentX + " y = " + currentY);
                tabulatedFunction.setY(i, currentY + 1);
                currentY = tabulatedFunction.getY(i);
            }
            System.out.println(Thread.currentThread().getName() + " after write: i = " + i + " x = " + currentX + " y = " + currentY);
        }
    }
}
