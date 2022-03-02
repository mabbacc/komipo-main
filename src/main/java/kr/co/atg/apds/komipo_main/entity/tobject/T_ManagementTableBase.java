package kr.co.atg.apds.komipo_main.entity.tobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class T_ManagementTableBase {
    Integer id;
    Timestamp crt_dtm;
    Integer   crt_uid;
    Timestamp upd_dtm;
    Integer   upd_uid;
}
