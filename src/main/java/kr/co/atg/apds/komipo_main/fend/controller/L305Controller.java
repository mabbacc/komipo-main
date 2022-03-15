package kr.co.atg.apds.komipo_main.fend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.graph.PlotlyDataObject;
import kr.co.atg.apds.komipo_main.fend.service.L305Service;

@RestController
@RequestMapping("/api/v1/front")
public class L305Controller {

  L305Service l305Service;

  @Autowired
  public L305Controller(L305Service _l305service) {
    this.l305Service = _l305service;
  }

  @GetMapping("/detail-analysis/waterfall")
  public List<PlotlyDataObject<Double>> getSpectrum(HttpServletRequest req, HttpServletResponse res) {

    return l305Service.getWaterfall();
  }

}
