package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.postgresql.util.PGInterval;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getWaterfall;

@Mapper
public interface L305Mapper {

  List<P_getWaterfall> getWaterfall(
      @Param("mptkey") Integer mptkey,
      @Param("itv") PGInterval itv,
      @Param("sdt") String std,
      @Param("edt") String edt);

}
