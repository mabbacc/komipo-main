package kr.co.atg.apds.komipo_main.entity.pobject;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class C_selectDate {
  String dt;
  List<P_getMeasdtList> child;

  public C_selectDate() {}

  public C_selectDate(String _dt) {
    this.dt = _dt;
    this.child = new ArrayList<>();
  }
}
