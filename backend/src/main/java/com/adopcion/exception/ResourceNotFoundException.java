package com.adopcion.exception;

// Herencia de RuntimeException para manejo personalizado de errores
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}