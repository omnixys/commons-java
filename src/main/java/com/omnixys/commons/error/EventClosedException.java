package com.omnixys.commons.error;

public class EventClosedException extends BaseOmnixysException {
    public EventClosedException(String message) {
        super(ErrorCode.EVENT_CLOSED, message);
    }
    public EventClosedException(String message, Throwable cause) {
        super(ErrorCode.EVENT_CLOSED, message, cause);
    }

    public EventClosedException(String message, ExceptionContext context) {
        super(ErrorCode.EVENT_CLOSED, message, context);
    }
    public EventClosedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.EVENT_CLOSED, message, cause, context);
    }
}
