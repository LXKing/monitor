package com.rayton.gps.configuration;


import com.rayton.gps.util.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.HashSet;
import java.util.Set;


@Configuration
public class BeanConfig {


    @Bean
    public CommonsMultipartResolver multipartResolver() {

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

        multipartResolver.setMaxUploadSize(107374182400L);

        return multipartResolver;
    }

    @Bean
    public FormattingConversionServiceFactoryBean conversionService() {

        FormattingConversionServiceFactoryBean conversionService = new FormattingConversionServiceFactoryBean();

        Set<Object> set = new HashSet<>();
        set.add(new StringToDateConverter());
        set.add(new DateToStringConverter());
        set.add(new StringToSqlDateConverter());
        set.add(new SqlDateToStringConverter());
        set.add(new StringToTimestampConverter());
        set.add(new TimestampToStringConverter());

        conversionService.setConverters(set);

        return conversionService;
    }


}
