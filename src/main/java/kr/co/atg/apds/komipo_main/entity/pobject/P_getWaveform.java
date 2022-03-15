package kr.co.atg.apds.komipo_main.entity.pobject;

import lombok.Data;

@Data
public class P_getWaveform {
  String mptid;
  Integer mptkey;
  String measdt;
  Integer twftype;
  Integer lineresolution;
  Double deltatime;
  Double deltaorder;
  Double rpm;
  Integer units;
  String unitstr;
  byte[] rawdata;
  Double overall;
  Double onexamp ;
  Double onexphase;
  Integer syncrev;
  Integer syncspr;
}