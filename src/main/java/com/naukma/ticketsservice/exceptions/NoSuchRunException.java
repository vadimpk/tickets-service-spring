package com.naukma.ticketsservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchRunException extends RuntimeException {

    public NoSuchRunException() {
        super();
    }
    public NoSuchRunException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoSuchRunException(String message) {
        super(message);
    }
    public NoSuchRunException(Throwable cause) {
        super(cause);
    }
}
