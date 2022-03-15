package kr.co.atg.apds.komipo_main.fend.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.XY;
import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphXaxisObject;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getWaveform;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L303Mapper;

@Service
public class L303Service {

  @Autowired
  L303Mapper l303mapper;

  public List<GraphObject<Object>> getWaveform() {

    List<P_getWaveform> rawdata = l303mapper.getWaveform();
    /* rawdata */
/*
 ------|-------|--------------------|--------|---------------|---------------------|---------------------|------------------|------|--------|---------|-------------------|-------------------|-------------------|--------|--------|
|mptid |mptkey |measdt              |twftype |lineresolution |deltatime            |deltaorder           |rpm               |units |unitstr |rawdata  |overall            |onexamp            |onexphase          |syncrev |syncspr |
|------|-------|--------------------|--------|---------------|---------------------|---------------------|------------------|------|--------|---------|-------------------|-------------------|-------------------|--------|--------|
|MOX   |9      |2019-10-29 08:13:39 |3       |2048           |6.249999860301614E-4 |0.009360354532194156 |898.5940551757812 |3     |um      |[unread] |11.909932694158938 |10.264335237591308 |140.02496993611945 |[null]  |[null]  |
|MOY   |10     |2019-10-29 08:13:39 |3       |2048           |6.249999860301614E-4 |0.009360354532194156 |898.5940551757812 |3     |um      |[unread] |13.199470410210854 |11.9099285275269   |-98.2630616942917  |[null]  |[null]  |
|------|-------|--------------------|--------|---------------|---------------------|---------------------|------------------|------|--------|---------|-------------------|-------------------|-------------------|--------|--------|
*/

    List<GraphObject<Object>> goList = new ArrayList<>();

    // xaxis instance
    GraphXaxisObject xaxisObject = new GraphXaxisObject();
    ArrayList<String> xaxisCategories = new ArrayList<>();

    // xy graphObject instance
    GraphObject<Object> goxy = new GraphObject<>();
    GraphDataListObject<Object> gdloxy = new GraphDataListObject<>("XY");

    // data
    for (int i = 0 ; i < rawdata.size() ; i++ ) { 
      P_getWaveform item  = rawdata.get(i);
      System.out.println("item name [" + item.getMptid() + "]");

      GraphObject<Object> go = new GraphObject<>();

      GraphDataListObject<Object> gdlo = new GraphDataListObject<>(item.getMptid());
      List<Object> data = new ArrayList<>();

      byte[] rawdata_hex = item.getRawdata();
      DoubleBuffer doubleBuffer = ByteBuffer.wrap(rawdata_hex).order(ByteOrder.LITTLE_ENDIAN).asDoubleBuffer();
      double[] dst = new double[doubleBuffer.capacity()];
      doubleBuffer.get(dst);

      System.out.println("Length Compare [" + dst.length + ":" + item.getLineresolution() + "]");

      for (int j = 0; j < dst.length; j++) {
        data.add(dst[j]);
        if ( 0 == i ) xaxisCategories.add("" + j); // i 가 '0' 이라는 건 첫번째 루프라는 것임. 이때 Xaxis 만듦.

        if ( 0 == i ) {
          XY<Double> xy = new XY<>( dst[j], null);
          gdloxy.getDataList().add(xy);
        } else if ( 1 == i ) {
          @SuppressWarnings("unchecked") 
          XY<Double> xy = (XY<Double>)gdloxy.getDataList().get(j);
          xy.setY(dst[j]);
        }
      }

      gdlo.setDataList(data);
      go.getSeriesList().add(gdlo);

      xaxisObject.setCategories(xaxisCategories);
      go.setXaxisObject(xaxisObject);

      goList.add(go);
    }

    goxy.getSeriesList().add(gdloxy);
    goList.add(goxy);

    return goList;
  }

}
