package com.omnixys.commons.error;

public class TicketAlreadyScannedException extends BaseOmnixysException {
    public TicketAlreadyScannedException(String message) {
        super(ErrorCode.TICKET_ALREADY_SCANNED, message);
    }
    public TicketAlreadyScannedException(String message, Throwable cause) {
        super(ErrorCode.TICKET_ALREADY_SCANNED, message, cause);
    }

    public TicketAlreadyScannedException(String message, ExceptionContext context) {
        super(ErrorCode.TICKET_ALREADY_SCANNED, message, context);
    }
    public TicketAlreadyScannedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.TICKET_ALREADY_SCANNED, message, cause, context);
    }
}
