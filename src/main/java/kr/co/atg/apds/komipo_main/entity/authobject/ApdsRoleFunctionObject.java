package kr.co.atg.apds.komipo_main.entity.authobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.atg.apds.komipo_main.entity.tobject.T_ManagementTableBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApdsRoleFunctionObject extends T_ManagementTableBase {
    @JsonProperty("authority")
    Integer roleId;

    @JsonProperty("functions")
    List<Integer> functionIdList;
}
