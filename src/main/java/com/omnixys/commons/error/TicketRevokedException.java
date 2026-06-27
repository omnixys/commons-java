package com.omnixys.commons.error;

public class TicketRevokedException extends BaseOmnixysException {
    public TicketRevokedException(String message) {
        super(ErrorCode.TICKET_REVOKED, message);
    }
    public TicketRevokedException(String message, Throwable cause) {
        super(ErrorCode.TICKET_REVOKED, message, cause);
    }

    public TicketRevokedException(String message, ExceptionContext context) {
        super(ErrorCode.TICKET_REVOKED, message, context);
    }
    public TicketRevokedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.TICKET_REVOKED, message, cause, context);
    }
}
