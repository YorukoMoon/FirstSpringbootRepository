package com.example.springbootbootdemo.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//自定义注解，加在方法上，代表此方法不被拦截器拦截，能够直接访问。
//还要在拦截器中加上逻辑语句，让拦截器见到这个注解就不拦截。
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthAccess {


}
