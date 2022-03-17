package kr.co.atg.apds.komipo_main.fend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.pobject.P_getMeasptAlarmLimit;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getMeasptOverallStatus;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L300Mapper;

@Service
public class L300Service {

  @Autowired
  L300Mapper l300mapper;

  public List<P_getMeasptOverallStatus> getMeasptOverallStatus(Integer equipmentkey) {
    return l300mapper.getMeasptOverallStatus(equipmentkey);
  }

  public List<P_getMeasptAlarmLimit> getMeasptAlarmLimit(Integer equipmentkey) {
    return l300mapper.getMeasptAlarmLimit(equipmentkey);
  }
}
