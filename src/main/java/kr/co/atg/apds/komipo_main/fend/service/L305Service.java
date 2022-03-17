package kr.co.atg.apds.komipo_main.fend.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PGInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.graph.PlotlyDataObject;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getWaterfall;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L305Mapper;

@Service
public class L305Service {

  @Autowired
  L305Mapper l305mapper;

  public List<List<PlotlyDataObject<Object>>> getWaterfall(Integer mptkey, String itv, String sdt, String edt) {

    PGInterval pgitv = null;
    if (null != itv)
      try {
        pgitv = new PGInterval(itv);
      } catch (Exception e) {
        e.printStackTrace();
      }

    List<P_getWaterfall> rawdata = l305mapper.getWaterfall(mptkey, pgitv, sdt, edt);

    List<List<PlotlyDataObject<Object>>> all = new ArrayList<>();

    List<PlotlyDataObject<Object>> goList = new ArrayList<>();

    // data
    for (int i = 0; i < rawdata.size(); i++) {
      P_getWaterfall item = rawdata.get(i);

      PlotlyDataObject<Object> go = new PlotlyDataObject<>();

      List<String> x = new ArrayList<>();
      List<Integer> y = new ArrayList<>();
      List<Object> z = new ArrayList<>();

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

    // create heatmap
    List<PlotlyDataObject<Object>> heatmapList = new ArrayList<>();
    PlotlyDataObject<Object> heatmap = new PlotlyDataObject<>();

    List<Object> heatmapXData = new ArrayList<>();
    for ( PlotlyDataObject<Object> go : goList) {
      heatmapXData.add(go.getZ());
    }
    heatmap.setZ(heatmapXData);
    heatmap.setType("heatmap");
    List<List<String>> colorMap = new ArrayList<>();
    List<String> acolor = new ArrayList<>(); acolor.add("0.0"); acolor.add("rgb(165,0,38)"); colorMap.add(acolor);
    List<String> bcolor = new ArrayList<>(); bcolor.add("0.111111111111"); bcolor.add("rgb(215,48,39)"); colorMap.add(bcolor);
    List<String> ccolor = new ArrayList<>(); ccolor.add("0.222222222222"); ccolor.add("rgb(244,109,67)"); colorMap.add(ccolor);
    List<String> dcolor = new ArrayList<>(); dcolor.add("0.333333333333"); dcolor.add("rgb(253,174,97)"); colorMap.add(dcolor);
    List<String> ecolor = new ArrayList<>(); ecolor.add("0.444444444444"); ecolor.add("rgb(254,224,144)"); colorMap.add(ecolor);
    List<String> fcolor = new ArrayList<>(); fcolor.add("0.555555555555"); fcolor.add("rgb(224,243,248)"); colorMap.add(fcolor);
    List<String> gcolor = new ArrayList<>(); gcolor.add("0.666666666666"); gcolor.add("rgb(171,217,233)"); colorMap.add(gcolor);
    List<String> hcolor = new ArrayList<>(); hcolor.add("0.777777777777"); hcolor.add("rgb(116,173,209)"); colorMap.add(hcolor);
    List<String> icolor = new ArrayList<>(); icolor.add("0.888888888888"); icolor.add("rgb(69,117,180)'"); colorMap.add(icolor);
    List<String> jcolor = new ArrayList<>(); jcolor.add("1.0"); jcolor.add("rgb(49,54,149)"); colorMap.add(jcolor);
    heatmap.setColorscale(colorMap);

    heatmapList.add(heatmap);





    // goxy.getSeriesList().add(gdloxy);
    // goList.add(goxy);

    all.add(goList);
    all.add(heatmapList);
    return all;
  }

}
