package org.udesa.unoback.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegal(IllegalArgumentException exception){
        return ResponseEntity.badRequest().body("Argumento invalido: " + exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException exception){
        return ResponseEntity.badRequest().body("Error: " + exception.getMessage());
    }
}
