package com.omnixys.commons.error;

public class MissingGuestNameException extends BaseOmnixysException {
    public MissingGuestNameException(String message) {
        super(ErrorCode.MISSING_GUEST_NAME, message);
    }
    public MissingGuestNameException(String message, Throwable cause) {
        super(ErrorCode.MISSING_GUEST_NAME, message, cause);
    }

    public MissingGuestNameException(String message, ExceptionContext context) {
        super(ErrorCode.MISSING_GUEST_NAME, message, context);
    }
    public MissingGuestNameException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.MISSING_GUEST_NAME, message, cause, context);
    }
}
