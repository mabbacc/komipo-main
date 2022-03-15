package kr.co.atg.apds.komipo_main.fend.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.XY;
import kr.co.atg.apds.komipo_main.entity.graph.GraphAxisObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.graph.PlotlyDataObject;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getSpectrum;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getWaterfall;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L305Mapper;

@Service
public class L305Service {

  @Autowired
  L305Mapper l305mapper;

  public List<PlotlyDataObject<Double>> getWaterfall() {

    List<P_getWaterfall> rawdata = l305mapper.getWaterfall();

    List<PlotlyDataObject<Double>> goList = new ArrayList<>();

    // data
    for (int i = 0; i < rawdata.size(); i++) {
      P_getWaterfall item = rawdata.get(i);

      PlotlyDataObject<Double> go = new PlotlyDataObject<>();

      List<String> x = new ArrayList<>();
      List<Integer> y = new ArrayList<>();
      List<Double> z = new ArrayList<>();

      byte[] rawdata_hex = item.getRawdata();
      DoubleBuffer doubleBuffer = ByteBuffer.wrap(rawdata_hex).order(ByteOrder.LITTLE_ENDIAN).asDoubleBuffer();
      double[] dst = new double[doubleBuffer.capacity()];
      doubleBuffer.get(dst);

      for (int j = 0; j < dst.length; j++) {
        x.add(item.getMeasdt());
        y.add(j);
        z.add(dst[j]);
      }

      go.setX(x);
      go.setY(y);
      go.setZ(z);
      go.setType("scatter3d");
      go.setMode("lines");
      go.setName(item.getMeasdt());

      goList.add(go);
    }

    // goxy.getSeriesList().add(gdloxy);
    // goList.add(goxy);

    return goList;
  }

}
