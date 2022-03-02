package kr.co.atg.apds.komipo_main.entity.tobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class T_User extends T_ManagementTableBase {
    String    user_id;
    String    user_pw;
    String    user_nm;
    String    email;
    String    phone_number;
    String    user_status;
    String    authorities;
    String    scope;
    String    description;
}
