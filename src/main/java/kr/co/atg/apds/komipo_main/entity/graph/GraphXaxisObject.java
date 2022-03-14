package kr.co.atg.apds.komipo_main.entity.graph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraphXaxisObject {

  @JsonProperty("title")
  GrapeXaxisTitleObject title;

  @JsonProperty("categories")
  List<String> categories;

  @JsonProperty("min")
  String min;

  @JsonProperty("max")
  String max;

  @JsonProperty("reserved")
  String reserved; 

}
