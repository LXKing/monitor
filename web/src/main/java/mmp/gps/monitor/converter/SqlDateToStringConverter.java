package mmp.gps.monitor.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class SqlDateToStringConverter implements Converter<Date, String> {

    @Override
    public String convert(Date source) {

        return (source == null) ? "" : new SimpleDateFormat("yyyy-MM-dd").format(source);

    }

}
