package com.example.proyecto.exception;

/**
 * Se lanza cuando un recurso solicitado (por id) no existe en la base de datos.
 * El GlobalExceptionHandler la traduce automáticamente a un HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
