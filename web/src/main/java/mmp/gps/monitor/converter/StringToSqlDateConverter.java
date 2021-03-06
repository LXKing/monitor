package mmp.gps.monitor.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class StringToSqlDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        System.out.println(source);
        // Assert.hasLength(source, "text must be specified");
        if (source == null || source.length() <= 0)
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date date = null;
        try {
            date = new Date(dateFormat.parse(source).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
