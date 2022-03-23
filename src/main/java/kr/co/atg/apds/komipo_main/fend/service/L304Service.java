package kr.co.atg.apds.komipo_main.fend.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.entity.graph.GraphAxisObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphDataListObject;
import kr.co.atg.apds.komipo_main.entity.graph.GraphObject;
import kr.co.atg.apds.komipo_main.entity.pobject.C_selectDate;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getSpectrum;
import kr.co.atg.apds.komipo_main.entity.pobject.P_getSpectrumList;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.L304Mapper;

@Service
public class L304Service {

  @Autowired
  L304Mapper l304mapper;

  public List<GraphObject<Object>> getSpectrum(int mptkey, String measdt) {

    List<P_getSpectrum> rawdata = l304mapper.getSpectrum(mptkey, measdt);
    /* rawdata */
    /*
     * |------|-------|--------------------|--------|-----|-----------|-------------
     * ------|---------------------|------------------|------|--------|---------|---
     * ----------------|
     * |mptid |mptkey |measdt |spctype |fmax |windowfunc |deltafreq |deltaorder |rpm
     * |units |unitstr |rawdata |overall |
     * |------|-------|--------------------|--------|-----|-----------|-------------
     * ------|---------------------|------------------|------|--------|---------|---
     * ----------------|
     * |MOX |9 |2019-10-29 08:13:39 |3 |625 |1 |0.7812500174622987
     * |0.052164824347261364 |898.5940551757812 |3 |um |[unread] |11.909932694158938
     * |
     * |MOY |10 |2019-10-29 08:13:39 |3 |625 |1 |0.7812500174622987
     * |0.052164824347261364 |898.5940551757812 |3 |um |[unread] |13.199470410210854
     * |
     * |------|-------|--------------------|--------|-----|-----------|-------------
     * ------|---------------------|------------------|------|--------|---------|---
     * ----------------|
     */

    List<GraphObject<Object>> goList = new ArrayList<>();

    // xaxis instance
    GraphAxisObject xaxisObject = new GraphAxisObject();
    ArrayList<String> xaxisCategories = new ArrayList<>();

    // xy graphObject instance
    // GraphObject<Object> goxy = new GraphObject<>();
    // GraphDataListObject<Object> gdloxy = new GraphDataListObject<>("XY");

    // data
    for (int i = 0; i < rawdata.size(); i++) {
      P_getSpectrum item = rawdata.get(i);
      System.out.println("item name [" + item.getMptid() + "]");

      GraphObject<Object> go = new GraphObject<>();

      GraphDataListObject<Object> gdlo = new GraphDataListObject<>(item.getMptid());
      List<Object> data = new ArrayList<>();

      byte[] rawdata_hex = item.getRawdata();
      DoubleBuffer doubleBuffer = ByteBuffer.wrap(rawdata_hex).order(ByteOrder.LITTLE_ENDIAN).asDoubleBuffer();
      double[] dst = new double[doubleBuffer.capacity()];
      doubleBuffer.get(dst);

      for (int j = 0; j < dst.length; j++) {
        data.add(dst[j]);
        if (0 == i)
          xaxisCategories.add("" + j); // i 가 '0' 이라는 건 첫번째 루프라는 것임. 이때 Xaxis 만듦.

        /*
         * if (0 == i) {
         * XY<Double> xy = new XY<>(dst[j], null);
         * gdloxy.getDataList().add(xy);
         * } else if (1 == i) {
         * 
         * @SuppressWarnings("unchecked")
         * XY<Double> xy = (XY<Double>) gdloxy.getDataList().get(j);
         * xy.setY(dst[j]);
         * }
         */
      }

      gdlo.setDataList(data);
      go.getSeriesList().add(gdlo);

      xaxisObject.setCategories(xaxisCategories);
      List<GraphAxisObject> xaxisObjectList = new ArrayList<>();
      xaxisObjectList.add(xaxisObject);
      go.setXaxisObject(xaxisObjectList);

      goList.add(go);
    }

    // goxy.getSeriesList().add(gdloxy);
    // goList.add(goxy);

    return goList;
  }

  public List<P_getSpectrumList> getSpectrumList(int mptkey) {
    return l304mapper.getSpectrumList(mptkey);
  }

  public C_selectDate getSpectrumListDt(int mptkey, String dt) {
    List<P_getSpectrumList> spectrumLists = l304mapper.getSpectrumListDt(mptkey, dt);

    C_selectDate ret = null;
    String latestDt = dt;
    if ( null == dt ) {
      latestDt = spectrumLists.get(0).getMeasdt().substring(0, 10);
    }
    ret = new C_selectDate(latestDt);
    for ( P_getSpectrumList item: spectrumLists) {
      if (item.getMeasdt().startsWith(latestDt))  
        ret.getChild().add(item) ;
    };

    return ret;
  }
}
