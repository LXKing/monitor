package com.rayton.gps.util;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public class StringToTimestampConverter implements Converter<String, Timestamp> {

    @Override
    public Timestamp convert(String source) {
        long time = Long.parseLong(source);

        return new Timestamp(time);
    }

}
