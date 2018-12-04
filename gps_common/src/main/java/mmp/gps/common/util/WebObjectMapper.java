package mmp.gps.common.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

public class WebObjectMapper extends ObjectMapper {

    public WebObjectMapper() {
        CustomSerializerFactory factory = new CustomSerializerFactory();
        factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {
            public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
                if (value == null) {
                    jsonGenerator.writeString("");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    jsonGenerator.writeString(sdf.format(value));
                }
            }
        });
        factory.addGenericMapping(java.sql.Date.class, new JsonSerializer<java.sql.Date>() {
            public void serialize(java.sql.Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
                if (value == null) {
                    jsonGenerator.writeString("");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    jsonGenerator.writeString(sdf.format(value));
                }
            }
        });
        factory.addGenericMapping(Timestamp.class, new JsonSerializer<Timestamp>() {
            public void serialize(Timestamp value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
                if (value == null) {
                    jsonGenerator.writeString("");
                } else {
                    jsonGenerator.writeString(value.getTime() + "");
                }
            }
        });
        this.setSerializerFactory(factory);
    }
}
