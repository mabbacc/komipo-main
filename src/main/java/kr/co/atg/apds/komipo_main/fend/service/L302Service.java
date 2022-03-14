package kr.co.atg.apds.komipo_main.fend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphXaxisObject;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getMultiTrend;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getOverallTrend;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L302Mapper;

@Service
public class L302Service {

  @Autowired
  L302Mapper l302mapper;

  public GraphObject<Double> getOverallTrend() {

    List<P_getMultiTrend> rawdata = l302mapper.getMultiTrend();



    GraphObject<Double> go = new GraphObject<>();

    // xaxis
    GraphXaxisObject xaxisObject = new GraphXaxisObject();
    ArrayList<String> xaxisCategories = new ArrayList<>();


    // data
    for ( P_getMultiTrend item : rawdata) {

      // find same key
      boolean isFind = false;
      for ( GraphDataListObject<Double> series : go.getSeriesList() ) {
        if ( series.getName().equals(item.getParamid()) ) {
          series.getDataList().add(item.getDatavalue());
          isFind = true;
        }
      }
      if ( !isFind ) {
        GraphDataListObject<Double> series = new GraphDataListObject<>(item.getParamid());
        series.getDataList().add(item.getDatavalue());
        go.getSeriesList().add(series);
      }


      if (!xaxisCategories.contains(item.getMeasdt())) xaxisCategories.add(item.getMeasdt());
    }

    xaxisObject.setCategories(xaxisCategories);
    go.setXaxisObject(xaxisObject);

    return go;
  }
  
}
