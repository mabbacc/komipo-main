package kr.co.atg.apds.komipo_main.entity.tobject;

import java.util.List;

import lombok.Data;

@Data
public class C_Equipment {
  Integer areakey;
  Integer equipmentkey;
  String equipmentid;
  String description;
  Double referencerpm;
  String equipmenttype;

  List<C_Component> child;
}
