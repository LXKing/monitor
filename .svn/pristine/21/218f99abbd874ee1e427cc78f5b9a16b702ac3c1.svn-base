package com.rayton.gps.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务方法注解
 *
 * @author 生
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 方法id
     */
    String id();

    /**
     * 上级方法
     */
    String pid() default "";

    /**
     * 前缀,用于日志记录
     */
    String prefix() default "";

    /**
     * 名称
     */
    String name();

    /**
     * 后缀,用于日志记录
     */
    String suffix() default "";
}
