package com.naukma.ticketsservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchWagonException extends RuntimeException {

    public NoSuchWagonException() {
        super();
    }
    public NoSuchWagonException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoSuchWagonException(String message) {
        super(message);
    }
    public NoSuchWagonException(Throwable cause) {
        super(cause);
    }
}
