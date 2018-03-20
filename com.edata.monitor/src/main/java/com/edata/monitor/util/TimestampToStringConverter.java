package com.edata.monitor.util;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;

public class TimestampToStringConverter implements Converter<Timestamp, String> {

	@Override
	public String convert(Timestamp source) {
		return source.getTime() + "";
	}

}
