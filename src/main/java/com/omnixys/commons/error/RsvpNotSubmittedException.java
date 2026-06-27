package com.omnixys.commons.error;

public class RsvpNotSubmittedException extends BaseOmnixysException {
    public RsvpNotSubmittedException(String message) {
        super(ErrorCode.RSVP_NOT_SUBMITTED, message);
    }
    public RsvpNotSubmittedException(String message, Throwable cause) {
        super(ErrorCode.RSVP_NOT_SUBMITTED, message, cause);
    }

    public RsvpNotSubmittedException(String message, ExceptionContext context) {
        super(ErrorCode.RSVP_NOT_SUBMITTED, message, context);
    }
    public RsvpNotSubmittedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.RSVP_NOT_SUBMITTED, message, cause, context);
    }
}
