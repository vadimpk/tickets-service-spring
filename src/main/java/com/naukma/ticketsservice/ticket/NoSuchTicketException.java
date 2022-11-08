package com.naukma.ticketsservice.ticket;

public class NoSuchTicketException extends RuntimeException {
    public NoSuchTicketException() {
        super();
    }
    public NoSuchTicketException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoSuchTicketException(String message) {
        super(message);
    }
    public NoSuchTicketException(Throwable cause) {
        super(cause);
    }
}
