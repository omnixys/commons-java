package com.omnixys.commons.error;

public class MissingRsvpContactDetailsException extends BaseOmnixysException {
    public MissingRsvpContactDetailsException(String message) {
        super(ErrorCode.MISSING_RSVP_CONTACT_DETAILS, message);
    }
    public MissingRsvpContactDetailsException(String message, Throwable cause) {
        super(ErrorCode.MISSING_RSVP_CONTACT_DETAILS, message, cause);
    }

    public MissingRsvpContactDetailsException(String message, ExceptionContext context) {
        super(ErrorCode.MISSING_RSVP_CONTACT_DETAILS, message, context);
    }
    public MissingRsvpContactDetailsException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.MISSING_RSVP_CONTACT_DETAILS, message, cause, context);
    }
}
