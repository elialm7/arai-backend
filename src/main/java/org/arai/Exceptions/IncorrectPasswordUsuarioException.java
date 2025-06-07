package org.arai.Exceptions;

public class IncorrectPasswordUsuarioException extends Exception {

    public IncorrectPasswordUsuarioException() {

    }
    public IncorrectPasswordUsuarioException(String message) {
        super(message);
    }
    public IncorrectPasswordUsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
}
