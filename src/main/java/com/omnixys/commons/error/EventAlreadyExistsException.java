package com.omnixys.commons.error;

public class EventAlreadyExistsException extends BaseOmnixysException {
    public EventAlreadyExistsException(String message) {
        super(ErrorCode.EVENT_ALREADY_EXISTS, message);
    }
    public EventAlreadyExistsException(String message, Throwable cause) {
        super(ErrorCode.EVENT_ALREADY_EXISTS, message, cause);
    }

    public EventAlreadyExistsException(String message, ExceptionContext context) {
        super(ErrorCode.EVENT_ALREADY_EXISTS, message, context);
    }
    public EventAlreadyExistsException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.EVENT_ALREADY_EXISTS, message, cause, context);
    }
}
