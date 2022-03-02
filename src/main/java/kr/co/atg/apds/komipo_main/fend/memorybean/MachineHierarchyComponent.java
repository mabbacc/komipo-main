package kr.co.atg.apds.komipo_main.fend.memorybean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.co.atg.apds.komipo_main.entity.tobject.T_Facility;
import kr.co.atg.apds.komipo_main.fend.mapper.db2.MainDashboardMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MachineHierarchyComponent {

  MainDashboardMapper mainDashboardMapper;

  /*************************************************************************************
   * Private Instances *
   **************************************************************************************/
  private List<T_Facility> apdsAllObject; // All Authority Information Instance

  @Autowired
  MachineHierarchyComponent(MainDashboardMapper _mainDashboardMapper) {
    this.mainDashboardMapper = _mainDashboardMapper;
  }

  private final String cronTime = "0 0 0/1 * * *";

  @PostConstruct // Executing this method when the application will be run
  @Scheduled(cron = cronTime)
  @Transactional
  public synchronized void selectDashboardMachines() {

    if (null == this.apdsAllObject)
      this.apdsAllObject = new ArrayList<>();
    else
      this.apdsAllObject.clear();

    List<T_Facility> tFacilityList = mainDashboardMapper.selectFacilityTree();
    for (T_Facility tFacility : tFacilityList) {
      this.apdsAllObject.add(tFacility);
    }

  }

  /* Return FunctionName and T_Function_Api List Map */
  public List<T_Facility> getAllFacilityTree() {
    return apdsAllObject;
  }

  public T_Facility getFacility(Integer fid) {
    if (null == apdsAllObject)
      return null;
    return apdsAllObject.stream().filter(item -> item.getFacility_id() == fid).findAny().orElse(null);
  }
}
