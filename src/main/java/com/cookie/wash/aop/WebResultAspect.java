package com.cookie.wash.aop;


import com.alibaba.fastjson.JSON;
import com.cookie.wash.enums.ReturnInfoEnum;
import com.cookie.wash.exceptions.ResultException;
import com.cookie.wash.result.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Aspect
@Component
public class WebResultAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebResultAspect.class);

    private Long take ;

    @Pointcut("execution(public * com.cookie.wash..controller..*.*(..))")
    public void controllerAspect(){}

    @Around("controllerAspect()")
    public Result proceed(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            logger.info("Request Method : " + joinPoint.getSignature().getDeclaringTypeName() + "_____" + joinPoint.getSignature().getName());
//            getMethodAnnotation(joinPoint,request);

//            logger.info("IP : " + request.getRemoteAddr());
//            logger.info("user-agent : "+request.getHeader("user-agent"));
//            getMethodAnnotation(joinPoint,request);

            //获取所有参数方法一：
            List<String> parameterList = new ArrayList<>();
            Enumeration<String> enu=request.getParameterNames();
            while(enu.hasMoreElements()){
                String paraName = enu.nextElement();
                parameterList.add(paraName+": "+request.getParameter(paraName));
            }
            logger.info("Request Parameters : " + JSON.toJSONString(parameterList));
            Result result = (Result) joinPoint.proceed();
            result.setCode(ReturnInfoEnum.Success.getCode());
            result.setSubcode(ReturnInfoEnum.Success.getSubcode());
            result.setMessage(ReturnInfoEnum.Success.getDesc());
            result.setTimestamp(System.currentTimeMillis());
            return result;
        } catch (ResultException e) {
            //通过反射拿到返回的对象类型，在new它的父类
            Result result = (Result)((MethodSignature)joinPoint.getSignature()).getReturnType().newInstance();

            result.setSuccess(false);
            result.setCode(e.getCode());
            result.setSubcode(e.getSubcode());
            result.setMessage(e.getMessage());
            result.setTimestamp(System.currentTimeMillis());
            return result;
        }
    }


    @Before("controllerAspect()")
    public void before(){
        take = System.currentTimeMillis();
    }


    @AfterReturning("controllerAspect()")
    public void returnString(){
        logger.info("this method takes :  "+(System.currentTimeMillis() - take ));
    }



}