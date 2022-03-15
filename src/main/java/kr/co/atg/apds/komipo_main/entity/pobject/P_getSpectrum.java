package kr.co.atg.apds.komipo_main.entity.pobject;

import lombok.Data;

@Data
public class P_getSpectrum {
  String mptid;
  Integer mptkey;
  String measdt;
  Integer spctype;
  Integer fmax;
  Integer windowfunc;
  Double deltafreq;
  Double deltaorder;
  Double rpm;
  Integer units;
  String unitstr;
  byte[] rawdata;
  Double overall;
}
