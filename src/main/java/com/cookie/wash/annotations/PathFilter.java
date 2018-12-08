package com.cookie.wash.annotations;

/**
 * Created by cxq on 2018/06/22.
 *  如果加上该标识，则访问路径不需要可过滤登录访问
 */

import java.lang.annotation.*;

@Documented     // 注解是否将包含在JavaDoc中
@Retention(RetentionPolicy.RUNTIME) //什么时候使用该注解 : 始终存在
@Target(ElementType.METHOD)     // 注解用于什么地方 ：方法级别
@Inherited  // 是否允许子类继承该注解
public @interface PathFilter {
}