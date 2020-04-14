package com.baizhi.aspect;


import com.baizhi.annotation.MyAnnotation;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: wangjiangwei
 * @Date 2020/4/6 20:08
 * @Version 1.0
 */
@Aspect
@Configuration
public class MyAspect {

    @Resource
    HttpSession session;

    @Around("@annotation(com.baizhi.annotation.MyAnnotation)")
    public Object addLogs(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed=null;
        Admin admin = (Admin) session.getAttribute("admin");
        // 操作的哪个方法
        String signature = joinPoint.getSignature().getName();
        // 获取方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 获取方法上的注解
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        // 获取注解中的值
        String value = annotation.value();
        String message="error";
        try {
             proceed = joinPoint.proceed();
             message="success";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }finally {
            Log log=new Log(UUID.randomUUID().toString().replace("-",""),admin.getName(),new Date(),value,message);
            System.out.println(log);
        }
        return proceed;
    }
}
