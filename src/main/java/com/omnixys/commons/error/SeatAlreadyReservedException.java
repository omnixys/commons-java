package com.omnixys.commons.error;

public class SeatAlreadyReservedException extends BaseOmnixysException {
    public SeatAlreadyReservedException(String message) {
        super(ErrorCode.SEAT_ALREADY_RESERVED, message);
    }
    public SeatAlreadyReservedException(String message, Throwable cause) {
        super(ErrorCode.SEAT_ALREADY_RESERVED, message, cause);
    }

    public SeatAlreadyReservedException(String message, ExceptionContext context) {
        super(ErrorCode.SEAT_ALREADY_RESERVED, message, context);
    }
    public SeatAlreadyReservedException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.SEAT_ALREADY_RESERVED, message, cause, context);
    }
}
