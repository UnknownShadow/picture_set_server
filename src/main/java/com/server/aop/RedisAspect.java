package com.server.aop;

import com.server.annotation.RedisCache;
import com.server.entity.api.Ajax;
import com.server.exceptions.AuthException;
import com.server.utils.JedisUtil;
import com.server.utils.MD5Util;
import com.server.utils.SerializeUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * TODO Redis切面处理类
 */
@Aspect
@Component
public class RedisAspect {

    private static final Logger logger = LoggerFactory.getLogger(RedisAspect.class);


    /**
     * 分隔符 生成key 格式为 类全类名:方法名:参数所属类全类名
     **/
    private static final String DELIMITER = ":";


    /**
     * Service层切点 使用到了我们定义的 RedisCache 作为切点表达式。
     * 而且我们可以看出此表达式基于 annotation。
     * 并且用于内建属性为查询的方法之上
     */
    @Pointcut("@annotation(com.server.annotation.RedisCache)")
    public void redisCacheAspect() {

    }


    @Around("redisCacheAspect()")
    public Object cache(ProceedingJoinPoint joinPoint) throws Exception {
        // 得到类名、方法名和参数
        String clazzName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        /**
         * 根据类名、方法名和参数生成Key 格式为 类全类名|方法名|参数所属类全类名
         */
        logger.info("key参数: " + clazzName + "." + methodName);
        String key = getKey(clazzName, methodName, args);
        logger.info("生成key: {}", key);


        // 得到被代理的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 得到被代理的方法上的注解 <注解上的参数>
        long timeOut = method.getAnnotation(RedisCache.class).timeOut();
        TimeUnit timeUnit = method.getAnnotation(RedisCache.class).timeUnit();

        logger.info("timeOut：（" + timeOut + "），timeUnit（" + timeUnit + "）");

        //得到过期时间
        int time = (int) timeUnit.toSeconds(timeOut);


        // 检查Redis中是否有缓存
        Jedis jedis = JedisUtil.getJedis();
        byte[] value = jedis.get(key.getBytes());


        // 得到被代理方法的返回值类型
        Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();


        // result是方法的最终返回结果
        Object result = null;
        try {
            if (null == value) {
                if (logger.isInfoEnabled()) {
                    logger.info("缓存未命中");
                }

                // 调用数据库查询方法
                result = joinPoint.proceed(args);

                // 序列化后放入缓存
                byte[] serialize = SerializeUtil.serialize(result);

                jedis.set(key.getBytes(), serialize);
                jedis.expire(key, time);
            } else {

                // 反序列化  从缓存中拿到的字符串
                result = SerializeUtil.unSerialize(value);

                // 缓存命中
                logger.info("缓存命中 value值：{}", result);

                //查询该 Key 的剩余过期时间 (单位：秒)
                Long remainder = jedis.pttl(key.getBytes()) / 1000;

                logger.info("key值（" + key + "）的剩余过期时间：" + remainder + "s");
            }
        } catch (Throwable e) {
            logger.error("解析异常{}", e);
            throw new AuthException(e.getMessage(), -9);
        } finally {
            JedisUtil.returnResource(jedis);
        }

        return result;
    }


    /**
     * * 根据类名、方法名和参数生成Key
     * * @param clazzName   类名
     * * @param methodName  方法名
     * * @param args
     * * @return key格式：全类名|方法名｜参数类型
     */
    private String getKey(String clazzName, String methodName, Object[] args) {
        StringBuilder key = new StringBuilder(clazzName);
        key.append(DELIMITER);
        key.append(methodName);
        key.append(DELIMITER);

        for (Object obj : args) {
            //请求的类名称
            String reqClassName = obj.getClass().getSimpleName();
            key.append(MD5Util.MD5Encode(reqClassName, "UTF-8"));
            key.append(DELIMITER);
        }

        //去掉最后一个符号
        key.deleteCharAt(key.length() - 1);

        return key.toString();
    }


/**
 * Service层切点 使用到了我们定义的 RedisCache 作为切点表达式。
 * 而且我们可以看出此表达式基于 annotation。
 * 并且用于内建属性为查询的方法之上
 *//*

    @Pointcut("@annotation(com.server.annotation.RedisCache)")
    public void redisCacheAspect() {

    }

    @Qualifier("redisTemplateForString")
    StringRedisTemplate srt;

    */
/**
 * Service层切点 使用到了我们定义的 RedisEvict 作为切点表达式。
 * 而且我们可以看出此表达式是基于 annotation 的。
 * 并且用于内建属性为非查询的方法之上，用于更新表
 *//*

  */
/*  @Pointcut("@annotation(com.fxmms.common.rediscache.redisannotation.RedisEvict)")
    public void redisCacheEvict() {
    }
*//*

    @Around("redisCacheAspect()")
    public Object cache(ProceedingJoinPoint joinPoint) throws Exception {
        // 得到类名、方法名和参数
        String clazzName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // 根据类名、方法名和参数生成Key
        logger.info("key参数: " + clazzName + "." + methodName);
        //System.out.println("key参数: " + clazzName + "." + methodName);
        String key = getKey(clazzName, methodName, args);
        if (logger.isInfoEnabled()) {
            logger.info("生成key: " + key);
        }

        // 得到被代理的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 得到被代理的方法上的注解
        Class modelType = method.getAnnotation(RedisCache.class).type();


//        Jedis jedis = JedisConnectUtil.getJedis();

        // 检查Redis中是否有缓存
        String value = (String) srt.opsForHash().get(modelType.getName(), key);

        // 得到被代理方法的返回值类型
        Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();

        // result是方法的最终返回结果
        Object result = null;
        try {
            if (null == value) {
                if (logger.isInfoEnabled()) {
                    logger.info("缓存未命中");
                }

                // 调用数据库查询方法
                result = joinPoint.proceed(args);

                // 序列化查询结果
                String json = JSONObject.toJSONString(result);
                System.out.println("打印："+json);

                // 序列化结果放入缓存
                srt.opsForHash().put(modelType.getName(), key, json);
            } else {

                // 缓存命中
                if (logger.isInfoEnabled()) {
                    logger.info("缓存命中, value = " + value);
                }

                result = value;
                // 反序列化 从缓存中拿到的json字符串
                result = JSONObject.parseObject(value, returnType);
                System.out.println(result.toString());

                if (logger.isInfoEnabled()) {
                    logger.info("gson反序列化结果 = " + result);
                }
            }
        } catch (Throwable e) {
            logger.error("解析异常",e);
        }
        return result;
    }


    */
/**
 *      * 根据类名、方法名和参数生成Key
 *      * @param clazzName
 *      * @param methodName
 *      * @param args
 *      * @return key格式：全类名|方法名｜参数类型
 *
 *//*

    private String getKey(String clazzName, String methodName, Object[] args) {
        StringBuilder key = new StringBuilder(clazzName);
        key.append(DELIMITER);
        key.append(methodName);
        key.append(DELIMITER);

        for (Object obj : args) {
            key.append(obj.getClass().getSimpleName());
            key.append(DELIMITER);
        }

        return key.toString();
    }







    */
/**
 * 根据类名、方法名和参数生成filed
 * @param clazzName
 * @param methodName
 * @param args 方法参数
 * @return
 *//*

    private String genFiled(String clazzName, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder(clazzName).append(".").append(methodName);
        for (Object obj : args) {
            if (obj != null)
                sb.append(".").append(obj.toString());
        }
        return sb.toString();
    }

    */
/**
 * 根据类名；来源生成key
 * @param source
 * @return
 *//*

 */
/*   private String genKey(Class[] keyClasses,String methodName,CacheSource source){
        StringBuilder sb = new StringBuilder(source.getDes()).append(".").append(methodName);
        for (Class clazz : keyClasses){
            sb.append(".").append(clazz.getSimpleName());
        }
        return sb.toString();
    }*//*


    */
/*@Around("redisCash()")
    public void sdf(ProceedingJoinPoint joinPoint) {

        System.out.println("进入redisCash---");


        String name = joinPoint.getSignature().getName();
        System.out.println("name: " + name);

        //获取执行方法的参数列表
        Object[] args = joinPoint.getArgs();

        //得到所有参数的值
        StringBuffer params = new StringBuffer();

        System.out.println("所有参数值：" + args.toString());


        *//*
*/
/*try {
            getCallMethodParams(args, params);
        } catch (Exception e) {
            logger.error("", e);
        }

        String className = cls.getSimpleName();

        //采用访问的类加上访问的方法名加上参数值作为唯一的key
        String key = className + "_" + methodName + "_" + params;
        logger.info("Cache key:"+key);
*//*
*/
/*


        long start = System.currentTimeMillis();

        //是否要缓存
        boolean isCache = false;

        //缓存时间
        Long timeOut = 0L;

        //缓存单位
        TimeUnit timeUnit = null;

        //访问的类对象
        Class<?> cls = joinPoint.getTarget().getClass();

        //正在执行的方法名
        String methodName = joinPoint.getSignature().getName();

        Map<String, Object> map = isCache(cls, methodName);
        isCache = (boolean) map.get("isCache");
        if(isCache){
            timeOut = (long) map.get("timeOut");
            timeUnit = (TimeUnit) map.get("timeUnit");
            //获取执行方法的参数列表
            Object[] args = joinPoint.getArgs();
            //得到所有参数的值
            StringBuffer params = new StringBuffer();
            try {
                getCallMethodParams(args, params);
            } catch (Exception e) {
                logger.error("", e);
            }
            String className = cls.getSimpleName();
            //采用访问的类加上访问的方法名加上参数值作为唯一的key
            String key = className + "_" + methodName + "_" + params;
            logger.info("Cache key:"+key);
            try {
                if(stringRedisTemplate.hasKey(key)){
                    try {
                        return queryCache(start, methodName, key);
                    } catch (Exception e) {
                        logger.error("获取redis的缓存数据异常", e);
                    }
                } else {
                    Object result = joinPoint.proceed();
                    try {
                        setCache(timeOut, timeUnit, key, result);
                    } catch (Exception e) {
                        logger.error("缓存数据到redis异常", e);
                    }
                    return result;
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return joinPoint.proceed();

    }*/


}
