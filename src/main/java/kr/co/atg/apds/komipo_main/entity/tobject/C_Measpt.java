package kr.co.atg.apds.komipo_main.entity.tobject;

import lombok.Data;

@Data
public class C_Measpt {
  Integer areakey;
  Integer equipmentkey;
  Integer componentkey;
  Integer mptkey;
  String mptid;
  String description;
  Integer companionmptkey;
  Double referencerpm;
  Integer signaltype;
  Integer orientation;
  Integer position;
  Boolean activealarm;
  Integer alarmtype;
  Double alerthigh;
  Double faulthigh;
  Double alertlow;
  Double faultlow;
}
