package kr.co.atg.apds.komipo_main.entity.graph;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraphDataListObject<T> {
    @JsonProperty("name")
    String name;

    @JsonProperty("data")
    List<T> dataList;

    public GraphDataListObject() {}
    public GraphDataListObject(String _name) {
        this.name = _name;
        this.dataList = new ArrayList<>();
    }
}
