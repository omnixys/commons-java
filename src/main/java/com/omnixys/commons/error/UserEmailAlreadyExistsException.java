package com.omnixys.commons.error;

public class UserEmailAlreadyExistsException extends BaseOmnixysException {
    public UserEmailAlreadyExistsException(String message) {
        super(ErrorCode.USER_EMAIL_ALREADY_EXISTS, message);
    }
    public UserEmailAlreadyExistsException(String message, Throwable cause) {
        super(ErrorCode.USER_EMAIL_ALREADY_EXISTS, message, cause);
    }

    public UserEmailAlreadyExistsException(String message, ExceptionContext context) {
        super(ErrorCode.USER_EMAIL_ALREADY_EXISTS, message, context);
    }
    public UserEmailAlreadyExistsException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.USER_EMAIL_ALREADY_EXISTS, message, cause, context);
    }
}
