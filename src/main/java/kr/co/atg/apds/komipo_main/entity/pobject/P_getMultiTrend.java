package kr.co.atg.apds.komipo_main.entity.pobject;

import lombok.Data;

@Data
public class P_getMultiTrend {
  String paramid;
  Integer paramidx;
  Integer paramtype;
  String measdt;
  Double datavalue;
}
