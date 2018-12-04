package mmp.gps.monitor.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public class TimestampToStringConverter implements Converter<Timestamp, String> {

    @Override
    public String convert(Timestamp source) {

        return String.valueOf(source.getTime());
    }

}
