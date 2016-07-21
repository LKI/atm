package com.liriansu.atm.exception;

/**
 * {@link ATMException} is an abstract class for all Exceptions in this project
 */
public abstract class ATMException extends Exception {
    public ATMException() {
        super();
    }

    public ATMException(String message) {
        super(message);
    }

    public ATMException(Throwable throwable) {
        super(throwable);
    }

    public ATMException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
