package com.cabInvoiceGenerator.Exceptions;

public class InvoiceException extends Exception {
    public enum ExceptionType {
        USER_NOT_EXIST, USER_ALREADY_EXISTS
    }

    public ExceptionType type;

    public InvoiceException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
