package mmp.gps.monitor.configuration;


import mmp.gps.monitor.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;


@Configuration
public class BeanConfig implements WebMvcConfigurer {
    // 跨域
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }


    @Bean
    public ConversionService conversionService() {

        FormattingConversionServiceFactoryBean conversionService = new FormattingConversionServiceFactoryBean();

        Set<Converter> set = new HashSet<>();
        set.add(new StringToDateConverter());
        set.add(new DateToStringConverter());
        set.add(new StringToSqlDateConverter());
        set.add(new SqlDateToStringConverter());
        set.add(new StringToTimestampConverter());
        set.add(new TimestampToStringConverter());

        conversionService.setConverters(set);
        conversionService.afterPropertiesSet();
        return conversionService.getObject();

    }

    // @Bean
    // public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
    //     MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    //     // 设置日期格式
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
    //     objectMapper.setDateFormat(smt);
    //     mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
    //     //设置中文编码格式
    //     List<MediaType> list = new ArrayList<>();
    //     list.add(MediaType.APPLICATION_JSON_UTF8);
    //     mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
    //     return mappingJackson2HttpMessageConverter;
    // }

}
