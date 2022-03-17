package kr.co.atg.apds.komipo_main.entity.pobject;

import lombok.Data;

@Data
public class P_getMeasptOverallStatus {
  String mptid;
  Integer mptkey;
  String measdt;
  Double overall;
  Integer units;
  String unitstr;
}