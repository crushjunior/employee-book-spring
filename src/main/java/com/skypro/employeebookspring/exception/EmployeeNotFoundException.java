package com.skypro.employeebookspring.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
