package com.naukma.ticketsservice.train;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class NonUniqueWagonNameException extends RuntimeException {

    public NonUniqueWagonNameException() {
        super();
    }
    public NonUniqueWagonNameException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonUniqueWagonNameException(String message) {
        super(message);
    }
    public NonUniqueWagonNameException(Throwable cause) {
        super(cause);
    }
}

