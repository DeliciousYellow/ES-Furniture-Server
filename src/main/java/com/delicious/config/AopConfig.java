package com.delicious.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-05-21 18:52
 **/
@Configuration
@EnableAspectJAutoProxy //让springAOP参与到目标对象的生命周期中,在这里就是加了指定注解的类或者方法
public class AopConfig {
}
