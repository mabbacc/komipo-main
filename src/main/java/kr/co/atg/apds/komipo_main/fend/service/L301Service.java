package kr.co.atg.apds.komipo_main.fend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.graph.GraphAxisObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getOverallTrend;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L301Mapper;

@Service
public class L301Service {

  @Autowired
  L301Mapper l301mapper;

  public GraphObject<Double> getOverallTrend() {

    List<P_getOverallTrend> rawdata = l301mapper.getOverallTrend();

    GraphObject<Double> go = new GraphObject<>();

    // xaxis
    GraphAxisObject xaxisObject = new GraphAxisObject();
    ArrayList<String> xaxisCategories = new ArrayList<>();

    // data
    for (P_getOverallTrend item : rawdata) {

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
    List<GraphAxisObject>xaxisObjectList = new ArrayList<>();
    xaxisObjectList.add(xaxisObject);
    go.setXaxisObject(xaxisObjectList);

    return go;
  }

}
