package kr.co.atg.apds.komipo_main.entity.authobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApdsRegisterApiFunctionObject {
    String uri;
    String method;
    String name; // function name

    @JsonProperty("id")
    Integer functionId; // functionId
}
