package kr.co.atg.apds.komipo_main.entity.pobject;

import lombok.Data;

@Data
public class P_getComparativeTrend {
  String equipmenttype;
  String equipmentid;
  String mptid;
  String measdt;
  Double overall;
  Integer units;
  String unitstr;
}
