package kr.co.atg.apds.komipo_main.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class T_user_config {
    Integer idx;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String user_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String user_pw;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String user_name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String phone_number;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String user_status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String authorities;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String scope;
}
