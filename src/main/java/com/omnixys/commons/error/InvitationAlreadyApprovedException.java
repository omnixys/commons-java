package com.omnixys.commons.error;

public class InvitationAlreadyApprovedException extends BaseOmnixysException {
    public InvitationAlreadyApprovedException(String message) {
        super(ErrorCode.INVITATION_ALREADY_APPROVED, message);
    }
    public InvitationAlreadyApprovedException(String message, Throwable cause) {
        super(ErrorCode.INVITATION_ALREADY_APPROVED, message, cause);
    }

    public InvitationAlreadyApprovedException(String message, ExceptionContext context) {
        super(ErrorCode.INVITATION_ALREADY_APPROVED, message, context);
    }
    public InvitationAlreadyApprovedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.INVITATION_ALREADY_APPROVED, message, cause, context);
    }
}
