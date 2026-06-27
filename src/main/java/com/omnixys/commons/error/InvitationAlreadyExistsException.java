package com.omnixys.commons.error;

public class InvitationAlreadyExistsException extends BaseOmnixysException {
    public InvitationAlreadyExistsException(String message) {
        super(ErrorCode.INVITATION_ALREADY_EXISTS, message);
    }
    public InvitationAlreadyExistsException(String message, Throwable cause) {
        super(ErrorCode.INVITATION_ALREADY_EXISTS, message, cause);
    }

    public InvitationAlreadyExistsException(String message, ExceptionContext context) {
        super(ErrorCode.INVITATION_ALREADY_EXISTS, message, context);
    }
    public InvitationAlreadyExistsException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.INVITATION_ALREADY_EXISTS, message, cause, context);
    }
}
