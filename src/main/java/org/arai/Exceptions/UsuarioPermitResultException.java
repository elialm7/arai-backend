package org.arai.Exceptions;

public class UsuarioPermitResultException  extends RuntimeException{
    public UsuarioPermitResultException(String mensage){
        super(mensage);
    }
    public UsuarioPermitResultException(String mensaje, Throwable callException){
        super(mensaje, callException);
    }
}
