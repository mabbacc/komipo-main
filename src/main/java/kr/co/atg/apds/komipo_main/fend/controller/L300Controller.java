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

import kr.co.atg.apds.komipo_main.entity.pobject.P_getMeasptAlarmLimit;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getMeasptOverallStatus;
import kr.co.atg.apds.komipo_main.fend.service.L300Service;

@RestController
@RequestMapping("/api/v1/front")
public class L300Controller {

  L300Service l300Service;

  @Autowired
  public L300Controller(L300Service _l300service) {
    this.l300Service = _l300service;
  }

  @GetMapping("/detail-machine/overall")
  public ResponseEntity<List<P_getMeasptOverallStatus>> getMeasptOverallStatus(HttpServletRequest req, HttpServletResponse res,
      @RequestParam("equipmentkey") Integer equipmentkey) {

    return new ResponseEntity<>(l300Service.getMeasptOverallStatus(equipmentkey), HttpStatus.OK);
  }

  @GetMapping("/detail-machine/alarm-limit")
  public ResponseEntity<List<P_getMeasptAlarmLimit>> getMeasptAlarmLimit(HttpServletRequest req, HttpServletResponse res,
      @RequestParam("equipmentkey") Integer equipmentkey) {

    return new ResponseEntity<>(l300Service.getMeasptAlarmLimit(equipmentkey), HttpStatus.OK);
  }
}
