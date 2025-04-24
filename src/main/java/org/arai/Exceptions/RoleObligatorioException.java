package org.arai.Exceptions;


/**
 * Se usa cuando el usuario no cumple con el requisito de tener un Rol
 */
public class RoleObligatorioException extends RuntimeException{
    public RoleObligatorioException(String mensaje,  Exception callException){
        super(mensaje, callException);
    }
    public RoleObligatorioException(String mensaje){
        super(mensaje);
    }

}