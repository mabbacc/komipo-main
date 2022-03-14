package kr.co.atg.apds.komipo_main.fend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.fend.service.L303Service;

@RestController
@RequestMapping("/api/v1/front")
public class L303Controller {

  L303Service l303Service;

  @Autowired
  public L303Controller( L303Service _l303service ) {
    this.l303Service = _l303service;
  }

  
  @GetMapping("/detail-analysis/waveform")
  public List<GraphObject<Double>> getWaveform(HttpServletRequest req, HttpServletResponse res) {

    return l303Service.getWaveform();
  }
   
}
