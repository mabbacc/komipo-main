package kr.co.atg.apds.komipo_main.entity.tobject;

import java.util.List;

import lombok.Data;

@Data
public class C_Component {
  Integer areakey;
  Integer equipmentkey;
  Integer componentkey;
  String componentid;
  String description;
  Integer speedloadtype;
  Double referencerpm;
  Integer comptype;
  Integer orientation;
  Integer mount;
  String componentinfo;

  List<C_Equipment> child;
}
