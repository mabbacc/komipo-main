package kr.co.atg.apds.komipo_main.fend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.graph.GraphAxisObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getMultiTrend;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L302Mapper;

@Service
public class L302Service {

  @Autowired
  L302Mapper l302mapper;

  public List<GraphObject<Double>> getMultiTrend() {

    List<P_getMultiTrend> rawdata = l302mapper.getMultiTrend();

    List<GraphObject<Double>> goList = new ArrayList<>();

    GraphObject<Double> go = new GraphObject<>();
    GraphObject<Double> go2 = new GraphObject<>();

    // xaxis
    GraphAxisObject xaxisObject = new GraphAxisObject();
    ArrayList<String> xaxisCategories = new ArrayList<>();

    // data
    for (P_getMultiTrend item : rawdata) {

      // find same key and add it.
      boolean isFind = false;
      for (GraphDataListObject<Double> series : go.getSeriesList()) {
        if (series.getName().equals(item.getParamid())) {
          series.getDataList().add(item.getDatavalue());
          isFind = true;
        }
      }
      if (!isFind) {
        // if finding is failed, create new one.
        GraphDataListObject<Double> series = new GraphDataListObject<>(item.getParamid());
        series.getDataList().add(item.getDatavalue());
        go.getSeriesList().add(series);
      }

      if (!xaxisCategories.contains(item.getMeasdt()))
        xaxisCategories.add(item.getMeasdt());
    }

    xaxisObject.setCategories(xaxisCategories);
    List<GraphAxisObject> xaxisObjectList = new ArrayList<>();
    xaxisObjectList.add(xaxisObject);
    go.setXaxisObject(xaxisObjectList);

    // yaxis - opposite
    List<GraphAxisObject> yaxisObjectList = new ArrayList<>();
    go.getSeriesList().forEach(item -> {
      GraphAxisObject yaxisObject = new GraphAxisObject();
      if (item.getName().equalsIgnoreCase("Speed")) {
        yaxisObject.setOpposite(true);
        yaxisObject.setSeriesName("Speed");
      }
      yaxisObjectList.add(yaxisObject);
    });
    go.setYaxisObject(yaxisObjectList);

    goList.add(go);
    return goList;
  }

}
