package kr.co.atg.apds.komipo_main.fend.memorybean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.co.atg.apds.komipo_main.entity.tobject.C_Area;
import kr.co.atg.apds.komipo_main.entity.tobject.C_Equipment;
import kr.co.atg.apds.komipo_main.entity.tobject.S_EquipmentTree;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.MainDashboardMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MachineHierarchyComponent {

  MainDashboardMapper mainDashboardMapper;

  /*************************************************************************************
   * Private Instances *
   **************************************************************************************/
  private List<C_Area> apdsAllObject; // All Authority Information Instance
  private List<S_EquipmentTree> equipmentTrees; // All Authority Information Instance

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

    List<C_Area> cAreaList = mainDashboardMapper.selectAreaTree();
    for (C_Area cArea : cAreaList) {
      this.apdsAllObject.add(cArea);
    }

    // Equipment Tree 만들기
    this.equipmentTrees = new ArrayList<>();
    for(C_Area area : apdsAllObject) {
      for ( C_Equipment equipment: area.getChild()) {

        boolean isFind = false;
        for (S_EquipmentTree eqTree: equipmentTrees) {
          if ( eqTree.getEquipmenttype().equals(equipment.getEquipmenttype()) ) {
            eqTree.getChild().add(equipment);
            isFind = true;
          }
        }
        if ( !isFind ) {
          S_EquipmentTree newEqTree = new S_EquipmentTree();
          newEqTree.setEquipmenttype(equipment.getEquipmenttype());
          newEqTree.setChild(new ArrayList<>());
          newEqTree.getChild().add(equipment);
          equipmentTrees.add(newEqTree);
        }
      }
    }

  }

  /* Return FunctionName and T_Function_Api List Map */
  public List<C_Area> getAllFacilityTree() {
    return apdsAllObject;
  }

  public C_Area getFacility(Integer aid) {
    if (null == apdsAllObject)
      return null;
    return apdsAllObject.stream().filter(item -> item.getAreakey() == aid).findAny().orElse(null);
  }


  public List<S_EquipmentTree> getEquipmentTree() {
    return equipmentTrees;
  }
}
