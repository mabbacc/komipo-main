package kr.co.atg.apds.komipo_main.security.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class T_admin {

    Long idx;
    String admin_id;
    String admin_pw;
    String admin_nm;
    String email;
    String phone_number;
    String user_status;
    String scope;
    String authorities;
    String description;

    LocalDateTime crt_dtm;
    Integer crt_uid;
    LocalDateTime upd_dtm;
    Integer upd_uid;
}
