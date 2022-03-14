package kr.co.atg.apds.komipo_main.fend.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphXaxisObject;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getMultiTrend;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getOverallTrend;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getWaveform;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L303Mapper;

@Service
public class L303Service {

  @Autowired
  L303Mapper l303mapper;

  public List<GraphObject<Double>> getWaveform() {

    List<P_getWaveform> rawdata = l303mapper.getWaveform();

    List<GraphObject<Double>> goList = new ArrayList<>();

    // xaxis
    GraphXaxisObject xaxisObject = new GraphXaxisObject();
    ArrayList<String> xaxisCategories = new ArrayList<>();

    // data
    for (P_getWaveform item : rawdata) {
      System.out.println("item name [" + item.getMptid() + "]");

      GraphObject<Double> go = new GraphObject<>();

      GraphDataListObject<Double> gdlo = new GraphDataListObject<>(item.getMptid());
      List<Double> data = new ArrayList<>();


      byte[] rawdata_hex = item.getRawdata();
      DoubleBuffer doubleBuffer = ByteBuffer.wrap(rawdata_hex).order(ByteOrder.LITTLE_ENDIAN).asDoubleBuffer();
      double[] dst = new double[doubleBuffer.capacity()];
      doubleBuffer.get(dst);

      System.out.println("Length Compare [" + dst.length + ":" + item.getLineresolution() + "]");

      for (int i = 0; i < dst.length; i++) {
        data.add(dst[i]);
        if ( 0 == goList.size() ) xaxisCategories.add("" + i); // goList 의 size 가 '0' 이라는 건 첫번째 루프라는 것임. 이때 Xaxis 만듦.
      }
      gdlo.setDataList(data);
      go.getSeriesList().add(gdlo);

      xaxisObject.setCategories(xaxisCategories);
      go.setXaxisObject(xaxisObject);

      goList.add(go);
    }

    return goList;
  }

}
