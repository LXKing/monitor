package mmp.gps.monitor;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import mmp.gps.monitor.dao.IDeviceDao;
import mmp.gps.monitor.godp.IGodpDao;
import mmp.gps.monitor.listener.PropertiesListener;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;

// 该 @SpringBootApplication 注解等价于以默认属性使用:
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan

// @SpringBootApplication
@SpringBootApplication
// mapper类的路径
@MapperScan("mmp.gps.monitor.dao")
@EnableCaching(proxyTargetClass = true) // 开启缓存功能
public class GpsWebApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpsWebApplication.class);


    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(GpsWebApplication.class);
        // 注册监听器
        application.addListeners(new PropertiesListener("properties/gps.properties"));

        application.run(args);

        // SpringApplication.run(GpsWebApplication.class, args);
    }


    // // Java EE应用服务器配置
    // // 如果要使用tomcat来加载jsp的话就必须继承SpringBootServletInitializer类并且重写其中configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GpsWebApplication.class);
    }

    // Springboot运行后此方法首先被调用
    // 实现CommandLineRunner抽象类中的run方法
    @Override
    public void run(String... args) throws Exception {
        LOGGER.warn("Spring Boot 启动完成！");
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {

        // FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //
        // FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //
        // // fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //
        //
        // JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        //
        // fastConverter.setFastJsonConfig(fastJsonConfig);
        //
        // HttpMessageConverter<?> converter = fastConverter;
        //
        //
        //
        //
        // return new HttpMessageConverters(converter);

        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                //    输出key是包含双引号
                //                SerializerFeature.QuoteFieldNames,
                //    是否输出为null的字段,若为null 则显示该字段
                //                SerializerFeature.WriteMapNullValue,
                //    数值字段如果为null，则输出为0
                // SerializerFeature.WriteNullNumberAsZero,
                //     List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //    字符类型字段如果为null,输出为"",而非null
                // SerializerFeature.WriteNullStringAsEmpty,
                //    Boolean字段如果为null,输出为false,而非null
                // SerializerFeature.WriteNullBooleanAsFalse,
                //    Date的日期转换器

                SerializerFeature.WriteDateUseDateFormat,
                //    循环引用
                SerializerFeature.DisableCircularReferenceDetect,};

        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));

        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //4、将convert添加到converters中
        HttpMessageConverter<?> converter = fastConverter;

        return new HttpMessageConverters(converter);


    }

}
