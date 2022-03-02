package kr.co.atg.apds.komipo_main.common.util;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DoubleDeserializer extends JsonSerializer<Double> {

  @Override
  public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException, JsonProcessingException {
    String d = new DecimalFormat("###.###############").format(value);
    gen.writeString(d);
  }
}
