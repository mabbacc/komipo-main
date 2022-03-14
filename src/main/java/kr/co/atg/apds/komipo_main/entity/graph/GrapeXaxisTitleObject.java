package kr.co.atg.apds.komipo_main.entity.graph;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GrapeXaxisTitleObject {
  String text;
}
