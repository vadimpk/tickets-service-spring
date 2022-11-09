package com.naukma.pricemanager;

public class NoSuchCurrencyException extends RuntimeException{
    public NoSuchCurrencyException() {
        super();
    }

    public NoSuchCurrencyException(String message) {
        super(message);
    }

    public NoSuchCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCurrencyException(Throwable cause) {
        super(cause);
    }
}
