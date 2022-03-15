package kr.co.atg.apds.komipo_main.fend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public GraphObject<Double> getOverallTrend(HttpServletRequest req, HttpServletResponse res) {

    return l301Service.getOverallTrend();
  }
  
}
