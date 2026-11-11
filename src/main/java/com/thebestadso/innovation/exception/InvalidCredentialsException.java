package com.thebestadso.innovation.exception;

/**
 * Indica un fallo de autenticación con un mensaje común para usuario inexistente
 * y contraseña incorrecta, evitando revelar si una cuenta está registrada.
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Usuario o contraseña incorrectos");
    }
}
