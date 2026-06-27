package com.omnixys.commons.error;

public class InvitationNotFoundException extends BaseOmnixysException {
    public InvitationNotFoundException(String message) {
        super(ErrorCode.INVITATION_NOT_FOUND, message);
    }
    public InvitationNotFoundException(String message, Throwable cause) {
        super(ErrorCode.INVITATION_NOT_FOUND, message, cause);
    }

    public InvitationNotFoundException(String message, ExceptionContext context) {
        super(ErrorCode.INVITATION_NOT_FOUND, message, context);
    }
    public InvitationNotFoundException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.INVITATION_NOT_FOUND, message, cause, context);
    }
}
