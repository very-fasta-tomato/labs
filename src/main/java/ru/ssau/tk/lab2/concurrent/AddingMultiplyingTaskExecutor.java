package ru.ssau.tk.lab2.concurrent;

import ru.ssau.tk.lab2.functions.*;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);

        Thread thread1 = new Thread(new MultiplyingTask(function));
        Thread thread2 = new Thread(new MultiplyingTask(function));
        Thread thread3 = new Thread(new AddingTask(function));

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(3000);

        System.out.println(function.toString());
    }
}
