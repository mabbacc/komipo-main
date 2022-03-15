package kr.co.atg.apds.komipo_main.entity.graph;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraphObject<T> {
  @JsonProperty("series")
  List<GraphDataListObject<T>> seriesList;

  @JsonProperty("xaxis")
  List<GraphAxisObject> xaxisObject;

  @JsonProperty("yaxis")
  List<GraphAxisObject> yaxisObject;

  public GraphObject() {
    this.seriesList = new ArrayList<>();
  }
}
