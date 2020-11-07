package ru.ssau.tk.lab2.exceptions;

import java.io.Serializable;

public class DifferentLengthOfArraysException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -5650533779031228626L;

    public DifferentLengthOfArraysException() {
        super();
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
