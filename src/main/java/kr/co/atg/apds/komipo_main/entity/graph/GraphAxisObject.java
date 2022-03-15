package kr.co.atg.apds.komipo_main.entity.graph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraphAxisObject {

  @JsonProperty("opposite")
  Boolean opposite;

  @JsonProperty("title")
  GrapeAxisTitleObject title;

  @JsonProperty("seriesName")
  String seriesName;

  @JsonProperty("categories")
  List<String> categories;

  @JsonProperty("min")
  String min;

  @JsonProperty("max")
  String max;

  @JsonProperty("reserved")
  String reserved;

}
