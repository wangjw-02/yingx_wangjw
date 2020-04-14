package com.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @Author: wangjiangwei
 * @Date 2020/4/9 22:27
 * @Version 1.0
 */
@Aspect
@Configuration
public class MyCache {
    @Resource
    RedisTemplate redisTemplate;

    @Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object addCahce(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = null;
        // 设置redisTemplate 的 键的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        StringBuilder stringBuilder = new StringBuilder();
        // 获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        stringBuilder.append(methodName);
        // 获取参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            stringBuilder.append(arg);
        }
        // 小key
        String key = stringBuilder.toString();
        // 查看className  key 是否存在
        Boolean aBoolean = redisTemplate.opsForHash().hasKey(className, key);
        if (aBoolean){
            // 存在，直接在缓存中找
            result = redisTemplate.opsForHash().get(className, key);
        }else {
            // 不存在，放行，在数据库中查找
            result = joinPoint.proceed();
            // 再放入缓存
            redisTemplate.opsForHash().put(className,key,result);
        }


        return result;
    }

    @After("@annotation(com.baizhi.annotation.DelCache)")
    public void delCache(JoinPoint joinPoint){
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();
        redisTemplate.delete(className);
    }
}
