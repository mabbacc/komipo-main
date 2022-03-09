package kr.co.atg.apds.komipo_main.fend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.atg.apds.komipo_main.entity.tobject.C_Area;
import kr.co.atg.apds.komipo_main.fend.memorybean.MachineHierarchyComponent;
import kr.co.atg.apds.komipo_main.security.model.FendResponseObject;

@RestController
@RequestMapping("/api/v1/front")
public class MainDashboardController {

  MachineHierarchyComponent machineHierarchyComponent;

  @Autowired
  public MainDashboardController(
      MachineHierarchyComponent _machineHierarchyComponent
  ) {
      this.machineHierarchyComponent = _machineHierarchyComponent;
  }

  int ______________________________________real________________________________________ = 0;
  @GetMapping("/dashboard/hierarchy")
  public ResponseEntity<FendResponseObject<List<C_Area>>> getHierarchy(
      HttpServletRequest req, HttpServletResponse res
  ){

      try {
          // List<T_Facility> tAreaList = mainDashboardService.selectDashboardMachines();
          List<C_Area> cAreaList = machineHierarchyComponent.getAllFacilityTree();
          if ( null == cAreaList || cAreaList.isEmpty() ) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
          FendResponseObject<List<C_Area>> ro = new FendResponseObject<>("Success");
          ro.setData(cAreaList);

          return new ResponseEntity<>(ro, HttpStatus.OK);

      }catch(Exception e){
          e.printStackTrace();
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }

  }
  
}
