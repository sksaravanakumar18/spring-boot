package com.springboot.master.exception;

/**
 * Custom Exception for Duplicate Resource scenarios
 * 
 * Demonstrates:
 * - Business logic exceptions
 * - Conflict handling
 */
public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message) {
        super(message);
    }
    
    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
