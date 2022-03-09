package kr.co.atg.apds.komipo_main.entity.tobject;

import java.util.List;

import lombok.Data;

@Data
public class C_Area {
  Integer areakey;
  String areaid;
  String description;

  List<C_Equipment> child;
}
