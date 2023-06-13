package com.delicious.interceptor;

import com.delicious.pojo.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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

    @Around("@annotation(com.delicious.annotation.AddLog)")
    public Result AddLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        @Addlog注解加一个value存操作类型，添加，删除，修改。
//        分3中情况来处理日志记录
        System.out.println(joinPoint.getThis());
        System.out.println("========================================添加了Log==================================================");

        //重新接收目标方法的返回值
        Result result = (Result) joinPoint.proceed();

        System.out.println(result);
        return result;
    }

}
