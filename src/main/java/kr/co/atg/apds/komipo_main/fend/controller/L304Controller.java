package kr.co.atg.apds.komipo_main.fend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.fend.service.L304Service;

@RestController
@RequestMapping("/api/v1/front")
public class L304Controller {

  L304Service l304Service;

  @Autowired
  public L304Controller(L304Service _l304service) {
    this.l304Service = _l304service;
  }

  @GetMapping("/detail-analysis/spectrum")
  public List<GraphObject<Object>> getSpectrum(HttpServletRequest req, HttpServletResponse res) {

    return l304Service.getSpectrum();
  }

}
