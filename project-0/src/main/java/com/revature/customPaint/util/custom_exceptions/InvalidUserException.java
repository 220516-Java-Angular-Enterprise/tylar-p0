package com.revature.customPaint.util.custom_exceptions;

import javax.management.ObjectName;
import javax.management.relation.RoleUnresolved;
import java.util.List;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        //this call the parent class constructor with the message string
        super(message);
    }
}
