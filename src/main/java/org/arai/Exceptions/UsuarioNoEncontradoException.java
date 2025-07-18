package org.arai.Exceptions;


/**
 * Se lanza cuando no se encuentra un usuario
 */

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(String mensage){
        super(mensage);
    }

    public UsuarioNoEncontradoException(String mensaje, Exception callException){
        super(mensaje, callException);
    }

}
