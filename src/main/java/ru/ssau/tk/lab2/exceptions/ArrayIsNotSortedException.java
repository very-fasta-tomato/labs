package ru.ssau.tk.lab2.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    public ArrayIsNotSortedException() {

    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
