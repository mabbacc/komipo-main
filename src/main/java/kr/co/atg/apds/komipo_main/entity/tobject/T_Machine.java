package kr.co.atg.apds.komipo_main.entity.tobject;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class T_Machine {
  Integer machine_id;
  String machine_name;
  Integer facility_id;
  Integer equipment_id;
  String type;
  String reference_rpm;
  Integer uid;

  Instant insertdt;
  Long insertid;
  Instant updatedt;
  Long updateid;
  String use_yn;
  String remark;
  String orientation;

  List<T_Measurementpt> child;
}
