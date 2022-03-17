package kr.co.atg.apds.komipo_main.fend.service;

import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PGInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.graph.GraphAxisObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getComparativeStatus;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getComparativeTrend;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getOverallTrend;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L201Mapper;

@Service
public class L201Service {

  @Autowired
  L201Mapper l201mapper;

  public GraphObject<Double> getComparativeStatus(String equipmenttype) {

    GraphObject<Double> go = new GraphObject<>();

    List<P_getComparativeStatus> rawdata = l201mapper.getComparativeStatus(equipmenttype);
    for (P_getComparativeStatus item : rawdata) {

      boolean isFind = false;
      for (GraphDataListObject<Double> series : go.getSeriesList()) {
        if (series.getName().equals(item.getEquipmentid())) {
          series.getDataList().add(item.getOverall());
          isFind = true;
        }
      }
      ;
      if (!isFind) {
        GraphDataListObject<Double> gdlo = new GraphDataListObject<>(item.getEquipmentid());
        gdlo.getDataList().add(item.getOverall());
        go.getSeriesList().add(gdlo);
      }
    }

    return go;
  }

  public GraphObject<Double> getComparativeTrend(String equipmenttype, String itv, String sdt, String edt) {

    PGInterval pgitv = null;
    if (null != itv)
      try {
        pgitv = new PGInterval(itv);
      } catch (Exception e) {
        e.printStackTrace();
      }

    List<P_getComparativeTrend> rawdata = l201mapper.getComparativeTrend(equipmenttype, pgitv, sdt, edt);

    GraphObject<Double> go = new GraphObject<>();

    // xaxis
    GraphAxisObject xaxisObject = new GraphAxisObject();
    ArrayList<String> xaxisCategories = new ArrayList<>();

    // data
    for (P_getComparativeTrend item : rawdata) {

      // find same key
      boolean isFind = false;
      for (GraphDataListObject<Double> series : go.getSeriesList()) {
        if (series.getName().equals(item.getMptid())) {
          series.getDataList().add(item.getOverall());
          isFind = true;
        }
      }
      if (!isFind) {
        GraphDataListObject<Double> series = new GraphDataListObject<>(item.getMptid());
        series.getDataList().add(item.getOverall());
        go.getSeriesList().add(series);
      }

      if (!xaxisCategories.contains(item.getMeasdt()))
        xaxisCategories.add(item.getMeasdt());
    }

    xaxisObject.setCategories(xaxisCategories);
    List<GraphAxisObject> xaxisObjectList = new ArrayList<>();
    xaxisObjectList.add(xaxisObject);
    go.setXaxisObject(xaxisObjectList);

    return go;
  }

}
