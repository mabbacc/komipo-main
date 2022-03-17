package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getMeasptAlarmLimit;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getMeasptOverallStatus;

@Mapper
public interface L300Mapper {

  List<P_getMeasptOverallStatus> getMeasptOverallStatus( @Param("equipmentkey") Integer equipmentkey);
  List<P_getMeasptAlarmLimit> getMeasptAlarmLimit( @Param("equipmentkey") Integer equipmentkey);

}
