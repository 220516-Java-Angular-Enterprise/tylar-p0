package com.revature.customPaint.util.custom_exceptions;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        //this call the parent class constructor with the message string
        super(message);
    }
}
