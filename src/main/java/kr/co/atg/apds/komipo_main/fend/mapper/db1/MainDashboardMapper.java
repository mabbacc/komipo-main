package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.atg.apds.komipo_main.entity.tobject.C_Area;

// import kr.co.atg.apds.komipo_main.entity.dashboard.P_getAlarmCnt;
// import kr.co.atg.apds.komipo_main.entity.dashboard.P_getAlarmHistory;
// import kr.co.atg.apds.komipo_main.entity.dashboard.P_getAlarmPer;
// import kr.co.atg.apds.komipo_main.entity.tobject.T_Facility;

@Mapper
public interface MainDashboardMapper {

  // 4-1 Facility > Equipment > Machine > MPT
  List<C_Area> selectAreaTree();

  List<C_Area> selectAreas();

  /*
  // 4-2 Vibration Overall Graph
  // P_getAlarmCnt selectVibrationOverall(@Param("fid") Long fid, @Param("eid")
  // Long eid);
  P_getAlarmCnt selectVibrationOverall(@Param("fid") Integer fid);

  // 4-3 Vibration Overall Graph
  // P_getAlarmPer selectVibrationStatus(@Param("fid") Long fid, @Param("eid")
  // Long eid);
  P_getAlarmPer selectVibrationStatus(@Param("fid") Integer fid);

  // 4-4 AlarmHistory
  List<P_getAlarmHistory> selectAlarmHistory(
      @Param("pcnt") Integer pcnt, @Param("pidx") Integer pidx,
      @Param("fid") Integer fid, @Param("eid") Integer eid, 
      @Param("sdate") String sdate, @Param("edate") String edate,
      @Param("alarmtype") String atype);

  List<P_getAlarmHistory> selectAlarmHistorySearch(
      @Param("pcnt") Integer pcnt, @Param("pidx") Integer pidx,
      @Param("fid") Integer fid, @Param("eid") Integer eid, 
      @Param("sdate") String sdate, @Param("edate") String edate,
      @Param("alarmtype") String atype);

      */

}
