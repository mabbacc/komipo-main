package kr.co.atg.apds.komipo_main.entity.tobject;

import java.util.List;

import lombok.Data;

@Data
public class S_EquipmentTree {
  String areaid;
  Integer areakey;

  String equipmenttype;

  List<C_Equipment> child;
}
