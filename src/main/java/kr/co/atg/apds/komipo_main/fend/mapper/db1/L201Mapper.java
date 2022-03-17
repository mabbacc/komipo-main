package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.postgresql.util.PGInterval;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getComparativeStatus;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getComparativeTrend;

@Mapper
public interface L201Mapper {

  List<P_getComparativeStatus> getComparativeStatus( @Param("equipmenttype") String equipmenttype);
  List<P_getComparativeTrend> getComparativeTrend(
      @Param("equipmenttype") String equipmenttype,
      @Param("itv") PGInterval itv,
      @Param("sdt") String sdt, 
      @Param("edt") String edt);

}
