package com.delicious.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})//加在类上
@Retention(RetentionPolicy.RUNTIME)//运行时仍然保留
public @interface AdminInterceptor {
}
