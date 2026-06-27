package com.omnixys.commons.error;

public class NotificationNotFoundException extends BaseOmnixysException {
    public NotificationNotFoundException(String message) {
        super(ErrorCode.NOTIFICATION_NOT_FOUND, message);
    }
    public NotificationNotFoundException(String message, Throwable cause) {
        super(ErrorCode.NOTIFICATION_NOT_FOUND, message, cause);
    }

    public NotificationNotFoundException(String message, ExceptionContext context) {
        super(ErrorCode.NOTIFICATION_NOT_FOUND, message, context);
    }
    public NotificationNotFoundException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.NOTIFICATION_NOT_FOUND, message, cause, context);
    }
}
