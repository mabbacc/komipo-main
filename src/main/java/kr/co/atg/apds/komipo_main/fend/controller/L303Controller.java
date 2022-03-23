package kr.co.atg.apds.komipo_main.fend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.pobject.C_selectDate;
import kr.co.atg.apds.komipo_main.fend.service.L303Service;

@RestController
@RequestMapping("/api/v1/front")
public class L303Controller {

  L303Service l303Service;

  @Autowired
  public L303Controller(L303Service _l303service) {
    this.l303Service = _l303service;
  }

  @GetMapping("/detail-analysis/waveform")
  public List<GraphObject<Object>> getWaveform(HttpServletRequest req, HttpServletResponse res,
      @RequestParam(name = "filter", required = false) Boolean filter,
      @RequestParam("mptkey") Integer mptkey,
      @RequestParam("measdt") String measdt) {

    if (null == filter)
      filter = false;
    return l303Service.getWaveform(filter, mptkey, measdt);
  }

  /*
  @GetMapping("/detail-analysis/waveform-dt-list")
  public List<P_getWaveformList> getWaveformList(HttpServletRequest req, HttpServletResponse res,
      @RequestParam("mptkey") Integer mptkey) {
    return l303Service.getWaveformList(mptkey);

  }
  */

  @GetMapping("/detail-analysis/waveform-dt-list")
  public C_selectDate getWaveformList(HttpServletRequest req, HttpServletResponse res,
      @RequestParam("mptkey") Integer mptkey,
      @RequestParam(name="dt", required=false) String dt) {
    return l303Service.getWaveformListDt(mptkey, dt);

  }

}
