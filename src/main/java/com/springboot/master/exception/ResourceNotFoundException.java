package com.springboot.master.exception;

/**
 * Custom Exception for Resource Not Found scenarios
 * 
 * Demonstrates:
 * - Custom exception classes
 * - Runtime exception extension
 * - Meaningful error messages
 */
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
