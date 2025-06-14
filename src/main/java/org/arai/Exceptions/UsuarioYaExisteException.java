package org.arai.Exceptions;

public class UsuarioYaExisteException extends  RuntimeException{
    public UsuarioYaExisteException(String message) {
        super(message);
    }

    public UsuarioYaExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}
