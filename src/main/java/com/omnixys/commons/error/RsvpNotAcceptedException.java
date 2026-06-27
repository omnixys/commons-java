package com.omnixys.commons.error;

public class RsvpNotAcceptedException extends BaseOmnixysException {
    public RsvpNotAcceptedException(String message) {
        super(ErrorCode.RSVP_NOT_ACCEPTED, message);
    }
    public RsvpNotAcceptedException(String message, Throwable cause) {
        super(ErrorCode.RSVP_NOT_ACCEPTED, message, cause);
    }

    public RsvpNotAcceptedException(String message, ExceptionContext context) {
        super(ErrorCode.RSVP_NOT_ACCEPTED, message, context);
    }
    public RsvpNotAcceptedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.RSVP_NOT_ACCEPTED, message, cause, context);
    }
}
