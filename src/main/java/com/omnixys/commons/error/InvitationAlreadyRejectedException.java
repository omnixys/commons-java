package com.omnixys.commons.error;

public class InvitationAlreadyRejectedException extends BaseOmnixysException {
    public InvitationAlreadyRejectedException(String message) {
        super(ErrorCode.INVITATION_ALREADY_REJECTED, message);
    }
    public InvitationAlreadyRejectedException(String message, Throwable cause) {
        super(ErrorCode.INVITATION_ALREADY_REJECTED, message, cause);
    }

    public InvitationAlreadyRejectedException(String message, ExceptionContext context) {
        super(ErrorCode.INVITATION_ALREADY_REJECTED, message, context);
    }
    public InvitationAlreadyRejectedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.INVITATION_ALREADY_REJECTED, message, cause, context);
    }
}
