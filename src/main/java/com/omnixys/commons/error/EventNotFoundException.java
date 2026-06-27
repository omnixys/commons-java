package com.omnixys.commons.error;

public class EventNotFoundException extends BaseOmnixysException {
    public EventNotFoundException(String message) {
        super(ErrorCode.EVENT_NOT_FOUND, message);
    }
    public EventNotFoundException(String message, Throwable cause) {
        super(ErrorCode.EVENT_NOT_FOUND, message, cause);
    }

    public EventNotFoundException(String message, ExceptionContext context) {
        super(ErrorCode.EVENT_NOT_FOUND, message, context);
    }
    public EventNotFoundException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.EVENT_NOT_FOUND, message, cause, context);
    }
}
