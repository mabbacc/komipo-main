package kr.co.atg.apds.komipo_main.security.auth.ajax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {

    private long idx;
    private String token;

    private String country_code = "82";
    private String auth_number;

    private String login_type;

    private String user_id; // phone_number
    private String user_pw; // auth_number

    private String user_name;
    private String email;
    private String phone_number;
    private String user_status;

    private String authorities;
    private String scope;

    private String description;

    private String social_cd;
    private String social_id;
    private Integer login_fail_cnt;
    private Boolean is_lock;
    private OffsetDateTime last_login_ts;

    private OffsetDateTime reg_ts;
    private OffsetDateTime update_ts;

    private String auth_type;


//    @JsonCreator
//    public LoginRequest(@JsonProperty("login_type") String login_type
//                        , @JsonProperty("idx") Integer idx
//                        , @JsonProperty("user_id") String user_id
//                        , @JsonProperty("user_pw") String user_pw
//                        , @JsonProperty("user_name") String user_name
//                        , @JsonProperty("phone_number") String phone_number
//                        , @JsonProperty("user_status") String user_status
//    ) {
//        this.login_type = login_type;
//        this.idx = idx;
//
//        this.user_id = user_id;
//        this.user_pw = user_pw;
//
//        this.user_name = user_name;
//        this.phone_number = phone_number;
//        this.user_status = user_status;
//    }

    @JsonCreator
    public LoginRequest(@JsonProperty("login_type") String login_type
            , @JsonProperty("idx") int idx
            , @JsonProperty("token") String token
            , @JsonProperty("phone_number") String phone_number
            , @JsonProperty("auth_number") String auth_number
            , @JsonProperty("user_status") String user_status
            , @JsonProperty("social_cd") String social_cd
            , @JsonProperty("social_id") String social_id
            , @JsonProperty("user_id") String user_id
            , @JsonProperty("user_pw") String user_pw
            , @JsonProperty("login_fail_cnt") Integer login_fail_cnt
            , @JsonProperty("is_lock") Boolean is_lock
            , @JsonProperty("last_login_ts") OffsetDateTime last_login_ts
            // , @JsonProperty("jb_user_info") C_jb_user_info jb_user_info
            , @JsonProperty("reg_ts") OffsetDateTime reg_ts
            , @JsonProperty("update_ts") OffsetDateTime update_ts
            , @JsonProperty("auth_type") String auth_type

    ) {
        this.login_type = login_type;
        this.idx = idx;
        this.token = token;

        this.user_id = user_id;
        this.user_pw = user_pw;

        this.phone_number = phone_number;
        this.auth_number = auth_number;
        this.user_status = user_status;
        this.social_cd = social_cd;
        this.social_id = social_id;
        this.login_fail_cnt = login_fail_cnt;
        this.is_lock = is_lock;
        this.last_login_ts = last_login_ts;

        // this.jb_user_info = jb_user_info;

        this.reg_ts = reg_ts;
        this.update_ts = update_ts;

        this.auth_type = auth_type;
    }
}
