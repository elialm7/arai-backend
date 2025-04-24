package org.arai.Exceptions;

public class RoleNoEncontradoException extends RuntimeException{
    public RoleNoEncontradoException(String mensaje){
        super(mensaje);
    }
    public RoleNoEncontradoException(String mensaje, Throwable cause){
        super(mensaje, cause);
    }
}
