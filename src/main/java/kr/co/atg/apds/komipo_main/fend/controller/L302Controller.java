package kr.co.atg.apds.komipo_main.fend.controller;

import java.util.List;

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
import kr.co.atg.apds.komipo_main.fend.service.L302Service;

@RestController
@RequestMapping("/api/v1/front")
public class L302Controller {

  L302Service l302Service;

  @Autowired
  public L302Controller(L302Service _l302service) {
    this.l302Service = _l302service;
  }

  @GetMapping("/detail-analysis/multi-trend")
  public ResponseEntity<List<GraphObject<Double>>> getMultilTrend(HttpServletRequest req, HttpServletResponse res,
  @RequestParam("mptkey") Integer mptkey,
  @RequestParam(name="itv", required = false) String itv,
  @RequestParam(name="start_dt", required = false) String sdt,
  @RequestParam("end_dt") String edt) {
    if ( null == itv && null == sdt) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(l302Service.getMultiTrend(mptkey, itv, sdt, edt), HttpStatus.OK);
  }

}
