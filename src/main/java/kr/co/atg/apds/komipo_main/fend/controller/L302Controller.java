package kr.co.atg.apds.komipo_main.fend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.fend.service.L302Service;

@RestController
@RequestMapping("/api/v1/front")
public class L302Controller {

  L302Service l302Service;

  @Autowired
  public L302Controller( L302Service _l302service ) {
    this.l302Service = _l302service;
  }

  
  @GetMapping("/detail-analysis/multi-trend")
  public GraphObject<Double> getMultilTrend(HttpServletRequest req, HttpServletResponse res) {

    return l302Service.getOverallTrend();
  }
   
}
