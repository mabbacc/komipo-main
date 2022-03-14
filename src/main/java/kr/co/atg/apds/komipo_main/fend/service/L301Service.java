package kr.co.atg.apds.komipo_main.fend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphXaxisObject;
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
    GraphXaxisObject xaxisObject = new GraphXaxisObject();
    ArrayList<String> xaxisCategories = new ArrayList<>();


    // data
    GraphDataListObject<Double> mox = new GraphDataListObject<>("MOX");
    GraphDataListObject<Double> moy = new GraphDataListObject<>("MOY");

    for ( P_getOverallTrend item : rawdata) {
      if (item.getMptid().equals("MOX"))       mox.getDataList().add(item.getOverall());
      else if (item.getMptid().equals("MOY"))  moy.getDataList().add(item.getOverall());

      if (!xaxisCategories.contains(item.getMeasdt())) xaxisCategories.add(item.getMeasdt());
    }

    xaxisObject.setCategories(xaxisCategories);
    go.setXaxisObject(xaxisObject);

    if ( null == go.getSeriesList() ) { go.setSeriesList(new ArrayList<>());}
    go.getSeriesList().add(mox);
    go.getSeriesList().add(moy);

    return go;
  }
  
}
