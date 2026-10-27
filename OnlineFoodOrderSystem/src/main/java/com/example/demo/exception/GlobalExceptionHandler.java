package com.example.demo.exception;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidation(
	            MethodArgumentNotValidException exception) {

	        Map<String, String> errors = new java.util.LinkedHashMap<>();

	        exception.getBindingResult().getFieldErrors().forEach(error ->
	                errors.put(error.getField(), error.getDefaultMessage()));

	        return ResponseEntity.badRequest().body(errors);
	    }

	    @ExceptionHandler(OrderNotFoundException.class)
	    public ResponseEntity<Map<String, String>> handleOrderNotFound(
	            OrderNotFoundException exception) {

	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", exception.getMessage()));
	    }

	    @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<Map<String, String>> handleInvalidInput(
	            IllegalArgumentException exception) {

	        return ResponseEntity.badRequest()
	                .body(Map.of("message", exception.getMessage()));
	    }
}
