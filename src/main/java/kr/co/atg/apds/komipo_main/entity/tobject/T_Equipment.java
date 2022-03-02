package kr.co.atg.apds.komipo_main.entity.tobject;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class T_Equipment {
  Integer equipment_id;
  String equipment_name;
  Long area_id;
  Integer uid;

  Instant insertdt;
  Long insertid;
  Instant updatedt;
  Long updateid;
  String use_yn;
  String remark;
  String facility_description;

  List<T_Machine> child;
}
