package kr.co.atg.apds.komipo_main.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import kr.co.atg.apds.komipo_main.common.ErrorCode;
import lombok.Data;

@Data
public class FendResponseObject<T> {
    String message;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    String errorMessage;

    T data;

    @JsonInclude(value = Include.NON_NULL)
    ErrorCode errorCode;

    public FendResponseObject() {
    }

    public FendResponseObject(String msg) {
        this.message = msg;
    }
}
