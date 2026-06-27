package com.omnixys.commons.error;

public class TooManyRequestsException extends BaseOmnixysException {
    public TooManyRequestsException(String message) {
        super(ErrorCode.RATE_LIMIT_EXCEEDED, message);
    }
    public TooManyRequestsException(String message, Throwable cause) {
        super(ErrorCode.RATE_LIMIT_EXCEEDED, message, cause);
    }

    public TooManyRequestsException(String message, ExceptionContext context) {
        super(ErrorCode.RATE_LIMIT_EXCEEDED, message, context);
    }
    public TooManyRequestsException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.RATE_LIMIT_EXCEEDED, message, cause, context);
    }
}
