package com.omnixys.commons.error;

public class MissingPendingContactException extends BaseOmnixysException {
    public MissingPendingContactException(String message) {
        super(ErrorCode.MISSING_PENDING_CONTACT, message);
    }
    public MissingPendingContactException(String message, Throwable cause) {
        super(ErrorCode.MISSING_PENDING_CONTACT, message, cause);
    }

    public MissingPendingContactException(String message, ExceptionContext context) {
        super(ErrorCode.MISSING_PENDING_CONTACT, message, context);
    }
    public MissingPendingContactException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.MISSING_PENDING_CONTACT, message, cause, context);
    }
}
