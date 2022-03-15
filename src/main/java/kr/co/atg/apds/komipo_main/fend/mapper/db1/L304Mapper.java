package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getSpectrum;

@Mapper
public interface L304Mapper {

  List<P_getSpectrum> getSpectrum();

}
