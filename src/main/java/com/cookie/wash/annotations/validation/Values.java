package com.cookie.wash.annotations.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 自定义扩展数据校验
 */
@Constraint(validatedBy = ValuesValidator.class) //具体的实现
@Target( { java.lang.annotation.ElementType.METHOD,
	java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface Values {
	String message() default "参数值错误,{attr}参数取值区间为{values}"; //提示信息
	
	String[] values(); //取值范围,该属性值取值范围必须为数组中的一个
	
	String attr(); //注解时变量的属性名,用于提示信息输出
	
	
	//下面这两个属性必须添加
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}