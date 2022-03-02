package kr.co.atg.apds.komipo_main.entity.tobject;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class T_Measurementpt {
  Integer measurementpt_id;
  String measurementpt_name;
  Long area_id;
  Long facility_id;
  Long machine_id;
  String type_inout;
  String fmax;
  String rpm;
  Long uid;

  Instant insertdt;
  Long insertid;
  Instant updatedt;
  Long updateid;

  String use_yn;
  String remark;

  String rawdata;
}
