package com.omnixys.commons.error;

public class UserNotFoundException extends BaseOmnixysException {
    public UserNotFoundException(String message) {
        super(ErrorCode.USER_NOT_FOUND, message);
    }
    public UserNotFoundException(String message, Throwable cause) {
        super(ErrorCode.USER_NOT_FOUND, message, cause);
    }

    public UserNotFoundException(String message, ExceptionContext context) {
        super(ErrorCode.USER_NOT_FOUND, message, context);
    }
    public UserNotFoundException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.USER_NOT_FOUND, message, cause, context);
    }
}
