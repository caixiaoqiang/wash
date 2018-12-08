package com.cookie.wash.annotations;

/**
 * Created by cxq on 2017/6/2.
 */

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface WithoutAspect {
}