package com.edata.monitor.util;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;

public class StringToTimestampConverter implements Converter<String, Timestamp> {

	@Override
	public Timestamp convert(String source) {
		long time = Long.parseLong(source);

		return new Timestamp(time);
	}

}
