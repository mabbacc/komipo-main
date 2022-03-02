package kr.co.atg.apds.komipo_main.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    GLOBAL(2),

    AUTHENTICATION(10),
    JWT_TOKEN_EXPIRED(11),
    SOCIAL_ID_AUTH_FAIL(12),
    SOCIAL_ID_NOT_FOUND(13),
    AUTHORIZATION(14),
    PASSWORD_RESET(15),

    /* Data Object Related */
    DATA_RET_OBJECT_EMPTY(100),
    OBJECT_NOT_FOUND(101),
    INSUFFICIENT_KEY_COLUMN(102),
    INVALID_PARAMETERS(103),
    INVALID_MULTIPART_FILE(104),
    REQUEST_BODY_NULL(105),
    BLANK_USER(106),

    /* URL Related */
    URL_NOT_FOUND(201),

    /* DataBase Query Related */
    INSERT_DATA_FAIL(301),
    UPDATE_DATA_FAIL(302),
    QUERY_DATA_FAIL(303),

    // Transaction Exception
    TRANSACTION_ROLLBACK_BY_EXCEPTION(701),
    ;

    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
