package com.omnixys.commons.error;

public class MissingContactMethodException extends BaseOmnixysException {
    public MissingContactMethodException(String message) {
        super(ErrorCode.MISSING_CONTACT_METHOD, message);
    }
    public MissingContactMethodException(String message, Throwable cause) {
        super(ErrorCode.MISSING_CONTACT_METHOD, message, cause);
    }

    public MissingContactMethodException(String message, ExceptionContext context) {
        super(ErrorCode.MISSING_CONTACT_METHOD, message, context);
    }
    public MissingContactMethodException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.MISSING_CONTACT_METHOD, message, cause, context);
    }
}
