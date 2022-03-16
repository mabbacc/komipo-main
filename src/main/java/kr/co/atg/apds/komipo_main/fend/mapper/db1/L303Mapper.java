package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getWaveform;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getWaveformList;

@Mapper
public interface L303Mapper {

  List<P_getWaveform> getWaveform(@Param("mptkey") Integer mptkey, @Param("measdt") String measdt);
  List<P_getWaveformList> getWaveformList(@Param("mptkey") Integer mptkey);

}
