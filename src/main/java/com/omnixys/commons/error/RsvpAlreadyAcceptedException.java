package com.omnixys.commons.error;

public class RsvpAlreadyAcceptedException extends BaseOmnixysException {
    public RsvpAlreadyAcceptedException(String message) {
        super(ErrorCode.RSVP_ALREADY_ACCEPTED, message);
    }
    public RsvpAlreadyAcceptedException(String message, Throwable cause) {
        super(ErrorCode.RSVP_ALREADY_ACCEPTED, message, cause);
    }

    public RsvpAlreadyAcceptedException(String message, ExceptionContext context) {
        super(ErrorCode.RSVP_ALREADY_ACCEPTED, message, context);
    }
    public RsvpAlreadyAcceptedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.RSVP_ALREADY_ACCEPTED, message, cause, context);
    }
}
