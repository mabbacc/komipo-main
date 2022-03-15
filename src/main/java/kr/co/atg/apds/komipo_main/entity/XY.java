package kr.co.atg.apds.komipo_main.entity;

import lombok.Data;

@Data
public class XY<T> {
  T x;
  T y;
  public XY() {}
  public XY(T _x, T _y) {
    this.x = _x;
    this.y = _y;
  }
}
