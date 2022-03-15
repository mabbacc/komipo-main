package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getWaterfall;

@Mapper
public interface L305Mapper {

  List<P_getWaterfall> getWaterfall();

}
