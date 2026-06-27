package com.omnixys.commons.error;

public class SeatNotFoundException extends BaseOmnixysException {
    public SeatNotFoundException(String message) {
        super(ErrorCode.SEAT_NOT_FOUND, message);
    }
    public SeatNotFoundException(String message, Throwable cause) {
        super(ErrorCode.SEAT_NOT_FOUND, message, cause);
    }

    public SeatNotFoundException(String message, ExceptionContext context) {
        super(ErrorCode.SEAT_NOT_FOUND, message, context);
    }
    public SeatNotFoundException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.SEAT_NOT_FOUND, message, cause, context);
    }
}
