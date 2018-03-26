package com.edata.monitor.util;

import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSqlDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source == null || source.length() <= 0)
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            java.util.Date date = dateFormat.parse(source);
            return new Date(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}
