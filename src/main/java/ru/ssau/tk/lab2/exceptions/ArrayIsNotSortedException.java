package ru.ssau.tk.lab2.exceptions;

import java.io.Serializable;

public class ArrayIsNotSortedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 578396464927694662L;

    public ArrayIsNotSortedException() {
        super();
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
