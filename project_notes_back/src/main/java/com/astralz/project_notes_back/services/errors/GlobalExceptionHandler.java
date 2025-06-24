package com.astralz.project_notes_back.services.errors;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * 📦 GlobalExceptionHandler
 * 
 * @RestControllerAdvice: Indica que esta clase es un controlador de
 *                        excepciones.
 * @ExceptionHandler: Indica que este método maneja una excepción específica.
 * @ResponseEntity: Indica que este método retorna una respuesta HTTP.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura errores de validación (anotaciones @Valid).
     * 
     * @MethodArgumentNotValidException: Excepción que se lanza cuando un
     *                                   argumento no es válido.
     * @FieldError: Error de validación de un campo.
     * @getAllErrors: Obtiene todos los errores de validación.
     * @put: Agrega un error de validación al mapa de errores.
     * @new ResponseEntity: Crea una respuesta HTTP con el mapa de errores.
     * 
     * @param ex Excepción de validación.
     * @return Mapa de errores de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Obtiene todos los errores de validación.
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        // Retorna una respuesta HTTP con el mapa de errores.
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Captura errores generales (opcional, útil para debugging).
     * 
     * @ExceptionHandler: Indica que este método maneja una excepción específica.
     * @ResponseEntity: Indica que este método retorna una respuesta HTTP.
     * 
     * @param ex Excepción general.
     * @return Mapa de errores generales.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {

        // Crea un mapa de errores.   
        Map<String, String> error = new HashMap<>();

        // Agrega el mensaje de la excepción al mapa de errores.
        error.put("error", ex.getMessage());

        // Retorna una respuesta HTTP con el mapa de errores.
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
