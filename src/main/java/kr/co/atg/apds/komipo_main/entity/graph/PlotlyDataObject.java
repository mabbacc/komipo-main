package kr.co.atg.apds.komipo_main.entity.graph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotlyDataObject<T> {
  List<String> x;
  List<Integer> y;
  List<T> z;
  String type;
  String mode;
  String name;

  List<List<String>> colorscale;
}
