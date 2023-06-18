package com.delicious.interceptor;

import com.delicious.annotation.AddLog;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Log;
import com.delicious.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-11 11:37
 **/
@Aspect
@Order(2)
@Component
public class LogInterceptor {

    @Resource
    private HttpServletRequest request;

    @Resource
    private LogService logService;

    @After("@annotation(com.delicious.annotation.AddLog)")
    public void AddLog(JoinPoint joinPoint) throws Throwable {
        // 获取目标方法的方法签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取目标方法对象
        Method method = signature.getMethod();
        // 获取目标方法上的注解
        AddLog addLogAnnotation = method.getAnnotation(AddLog.class);
        String logValue = null;
        if (addLogAnnotation != null) {
            // 获取注解的value值
            logValue = addLogAnnotation.value();
            System.out.println("Log Value: " + logValue);
        }
        Object userId = request.getAttribute("userId");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date().getTime());
        System.out.println(time);
        Log log = new Log(null,(Integer) userId, logValue, time);
        logService.save(log);
        System.out.println("========================================添加了Log==================================================");
    }
}
