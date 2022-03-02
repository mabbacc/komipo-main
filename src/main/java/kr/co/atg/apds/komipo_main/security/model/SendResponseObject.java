package kr.co.atg.apds.komipo_main.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.atg.apds.komipo_main.common.ErrorCode;
import lombok.Data;

@Data
public class SendResponseObject<T> {
    String message;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    String errorMessage;

    T data;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    ErrorCode errorCode;

    public SendResponseObject() {
    }

    public SendResponseObject(String msg) {
        this.message = msg;
    }
}
