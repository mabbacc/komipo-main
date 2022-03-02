package kr.co.atg.apds.komipo_main.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class T_user {
    Long id;
    String user_id;
    String user_pw;
    String user_nm;
    String email;
    String phone_number;
    String user_status;
    String authorities;
    String scope;
    String description;

    Timestamp crt_dtm;
    Integer crt_uid;
    Timestamp upd_dtm;
    Integer upd_uid;
}
