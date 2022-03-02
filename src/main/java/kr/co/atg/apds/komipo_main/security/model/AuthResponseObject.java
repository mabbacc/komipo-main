package kr.co.atg.apds.komipo_main.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.atg.apds.komipo_main.common.ErrorCode;
import lombok.Data;

@Data
public class AuthResponseObject<T> {
    String message;
    T data;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    ErrorCode errorCode;

    public AuthResponseObject() {
    }

    public AuthResponseObject(String msg) {
        this.message = msg;
    }
}
