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
import kr.co.atg.apds.komipo_main.fend.service.L301Service;

@RestController
@RequestMapping("/api/v1/front")
public class L301Controller {

  L301Service l301Service;

  @Autowired
  public L301Controller( L301Service _l301service ) {
    this.l301Service = _l301service;
  }

  
  @GetMapping("/detail-analysis/trend")
  public ResponseEntity<GraphObject<Double>> getOverallTrend(HttpServletRequest req, HttpServletResponse res,
  @RequestParam("mptkey") Integer mptkey,
  @RequestParam(name="itv", required=false) String itv,
  @RequestParam(name="start_dt", required=false) String sdt,
  @RequestParam(name="end_dt") String edt) {
    if ( null == itv && null == sdt) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(l301Service.getOverallTrend(mptkey, itv, sdt, edt), HttpStatus.OK);
  }
  
}
