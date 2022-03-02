package kr.co.atg.apds.komipo_main.common;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final kr.co.atg.apds.komipo_main.common.ErrorCode errorCode;

    private final Date timestamp;

    protected ErrorResponse(final String message, final kr.co.atg.apds.komipo_main.common.ErrorCode errorCode,
            HttpStatus status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new Date();
    }

    public static ErrorResponse of(final String message, final kr.co.atg.apds.komipo_main.common.ErrorCode errorCode,
            HttpStatus status) {
        return new ErrorResponse(message, errorCode, status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public kr.co.atg.apds.komipo_main.common.ErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
