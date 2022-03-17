package kr.co.atg.apds.komipo_main.entity.pobject;

import lombok.Data;

@Data
public class P_getComparativeStatus {
  String equipmenttype;
  String equipmentid;
  String mptid;
  String measdt;
  Double overall;
}
