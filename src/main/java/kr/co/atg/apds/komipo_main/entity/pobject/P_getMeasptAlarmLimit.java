package kr.co.atg.apds.komipo_main.entity.pobject;

import lombok.Data;

@Data
public class P_getMeasptAlarmLimit {
  String mptid; 
  Integer mptkey; 
  Double alerthigh;
  Double faulthigh; 
  Integer units; 
  String unitstr;
}
