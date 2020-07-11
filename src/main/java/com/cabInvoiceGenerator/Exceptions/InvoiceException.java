package com.cabInvoiceGenerator.Exceptions;

public class InvoiceException extends Exception {
    public enum ExceptionType {
        USER_PROBLEM
    }

    public ExceptionType type;

    public InvoiceException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
