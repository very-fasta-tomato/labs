package ru.ssau.tk.lab2.exceptions;

import java.io.Serializable;

public class InconsistentFunctionsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1411648393745750445L;

    public InconsistentFunctionsException() {
        super();
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
