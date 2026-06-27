package com.omnixys.commons.error;

public class UserAlreadyExistsException extends BaseOmnixysException {
    public UserAlreadyExistsException(String message) {
        super(ErrorCode.USER_ALREADY_EXISTS, message);
    }
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(ErrorCode.USER_ALREADY_EXISTS, message, cause);
    }

    public UserAlreadyExistsException(String message, ExceptionContext context) {
        super(ErrorCode.USER_ALREADY_EXISTS, message, context);
    }
    public UserAlreadyExistsException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.USER_ALREADY_EXISTS, message, cause, context);
    }
}
