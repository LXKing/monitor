package com.edata.monitor.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

public class SqlDateToStringConverter implements Converter<Date, String> {

	@Override
	public String convert(Date source) {
		if (source == null)
			return "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(source);
	}

}
