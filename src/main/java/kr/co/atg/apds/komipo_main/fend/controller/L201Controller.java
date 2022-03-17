package kr.co.atg.apds.komipo_main.fend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.fend.service.L201Service;

@RestController
@RequestMapping("/api/v1/front")
public class L201Controller {

  L201Service l201Service;

  @Autowired
  public L201Controller(L201Service _l201service) {
    this.l201Service = _l201service;
  }
  
  @GetMapping("/comparative/status")
  public ResponseEntity<GraphObject<Double>> getComparativeStatus(HttpServletRequest req, HttpServletResponse res,
  @RequestParam("equipmenttype") String equipmenttype) {
    return new ResponseEntity<>(l201Service.getComparativeStatus(equipmenttype), HttpStatus.OK);
  }

  @GetMapping("/comparative/trend")
  public ResponseEntity<GraphObject<Double>> getComparativeTrend(HttpServletRequest req, HttpServletResponse res,
  @RequestParam("equipmenttype") String equipmenttype,
  @RequestParam(name="itv", required = false) String itv,
  @RequestParam(name="start_dt", required = false) String sdt,
  @RequestParam("end_dt") String edt
  ) {
    return new ResponseEntity<>(l201Service.getComparativeTrend(equipmenttype, itv, sdt, edt), HttpStatus.OK);
  }


}
