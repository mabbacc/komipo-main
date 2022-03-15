package kr.co.atg.apds.komipo_main.entity.graph;

import java.util.List;

import lombok.Data;

@Data
public class PlotlyDataObject<T> {
  List<String> x;
  List<Integer> y;
  List<T> z;
  String type;
  String mode;
  String name;
}
