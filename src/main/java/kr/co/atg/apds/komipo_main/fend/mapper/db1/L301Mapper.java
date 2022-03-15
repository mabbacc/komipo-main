package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getOverallTrend;

@Mapper
public interface L301Mapper {

  List<P_getOverallTrend> getOverallTrend();
  
}
