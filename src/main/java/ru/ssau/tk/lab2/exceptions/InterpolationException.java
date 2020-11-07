package ru.ssau.tk.lab2.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 8879044447056659103L;

    public InterpolationException() {
        super();
    }

    public InterpolationException(String message) {
        super(message);
    }
}
