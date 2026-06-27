package com.omnixys.commons.error;

public class SeatCapacityExceededException extends BaseOmnixysException {
    public SeatCapacityExceededException(String message) {
        super(ErrorCode.SEAT_CAPACITY_EXCEEDED, message);
    }
    public SeatCapacityExceededException(String message, Throwable cause) {
        super(ErrorCode.SEAT_CAPACITY_EXCEEDED, message, cause);
    }

    public SeatCapacityExceededException(String message, ExceptionContext context) {
        super(ErrorCode.SEAT_CAPACITY_EXCEEDED, message, context);
    }
    public SeatCapacityExceededException(String message, Throwable cause, ExceptionContext context) {
        super(ErrorCode.SEAT_CAPACITY_EXCEEDED, message, cause, context);
    }
}
