package org.arai.Exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(String mensaje, Exception callException){
        super(mensaje, callException);
    }

}
