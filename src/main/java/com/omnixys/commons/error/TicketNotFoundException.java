package com.omnixys.commons.error;

public class TicketNotFoundException extends BaseOmnixysException {
    public TicketNotFoundException(String message) {
        super(ErrorCode.TICKET_NOT_FOUND, message);
    }
    public TicketNotFoundException(String message, Throwable cause) {
        super(ErrorCode.TICKET_NOT_FOUND, message, cause);
    }

    public TicketNotFoundException(String message, ExceptionContext context) {
        super(ErrorCode.TICKET_NOT_FOUND, message, context);
    }
    public TicketNotFoundException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.TICKET_NOT_FOUND, message, cause, context);
    }
}
