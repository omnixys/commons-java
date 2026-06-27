package com.omnixys.commons.error;

public class NotificationChannelUnavailableException extends BaseOmnixysException {
    public NotificationChannelUnavailableException(String message) {
        super(ErrorCode.NOTIFICATION_CHANNEL_UNAVAILABLE, message);
    }
    public NotificationChannelUnavailableException(String message, Throwable cause) {
        super(ErrorCode.NOTIFICATION_CHANNEL_UNAVAILABLE, message, cause);
    }

    public NotificationChannelUnavailableException(String message, ExceptionContext context) {
        super(ErrorCode.NOTIFICATION_CHANNEL_UNAVAILABLE, message, context);
    }
    public NotificationChannelUnavailableException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.NOTIFICATION_CHANNEL_UNAVAILABLE, message, cause, context);
    }
}
