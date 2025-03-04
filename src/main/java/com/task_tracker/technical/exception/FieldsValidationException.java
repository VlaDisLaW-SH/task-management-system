package com.task_tracker.technical.exception;

public class FieldsValidationException extends RuntimeException {
    public FieldsValidationException(String message) {
        super(message);
    }
}
