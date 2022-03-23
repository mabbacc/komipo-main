package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getSpectrum;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getSpectrumList;

@Mapper
public interface L304Mapper {

  List<P_getSpectrum> getSpectrum(@Param("mptkey") Integer mptkey, @Param("measdt") String measdt);
  List<P_getSpectrumList> getSpectrumList(@Param("mptkey") Integer mptkey);
  List<P_getSpectrumList> getSpectrumListDt(@Param("mptkey") Integer mptkey, @Param("dt") String dt);

}
