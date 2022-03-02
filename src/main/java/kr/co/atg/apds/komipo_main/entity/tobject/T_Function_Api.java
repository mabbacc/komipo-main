package kr.co.atg.apds.komipo_main.entity.tobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class T_Function_Api extends T_ManagementTableBase {
    Integer t_functionid;
    String uri;
    String method;
    String description;

    public T_Function_Api(){}

    public T_Function_Api(Integer t_functionid, String uri, String method) {
        this.t_functionid = t_functionid;
        this.uri = uri;
        this.method = method;
    }
}