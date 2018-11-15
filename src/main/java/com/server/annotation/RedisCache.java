package com.server.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * TODO 自定义 redis缓存数据 注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {
    /**
     * 过期时间
     * @return
     */
    public abstract long timeOut() default 0;
    /**
     * 过期单位
     * @return
     */
    public abstract TimeUnit timeUnit() default TimeUnit.HOURS;
}
