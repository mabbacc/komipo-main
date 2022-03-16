package kr.co.atg.apds.komipo_main.entity.pobject;

import lombok.Data;

@Data
public class P_getWaterfall {
  String mptid;
  Integer mptkey;
  String measdt;
  Integer spctype;
  Double fmax;
  Integer windowfunc;
  Double deltafreq;
  Double deltaorder;
  Double rpm;
  Integer units;
  String unitstr;
  byte[] rawdata;
  Double overall;
}
