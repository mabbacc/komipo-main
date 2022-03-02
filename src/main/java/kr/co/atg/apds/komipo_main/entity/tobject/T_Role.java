package kr.co.atg.apds.komipo_main.entity.tobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class T_Role extends T_ManagementTableBase {
    String authority;
    String description;
}
