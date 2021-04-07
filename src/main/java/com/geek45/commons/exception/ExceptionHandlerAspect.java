package com.geek45.commons.exception;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author qian
 */
@Component
public class ExceptionHandlerAspect {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAspect.class);


    @Pointcut("@annotation(com.geek45.commons.exception.ExceptionHandler)")
    public void aroundCatchException() {}

    @Around("aroundCatchException")
    public Object aroundLogic(ProceedingJoinPoint proceedingJoinPoint) {
        String methodName = null;
        Class<?> returnClass = null;
        try {
            methodName = proceedingJoinPoint.getSignature().getName();
            Object proceed = proceedingJoinPoint.proceed();
            return proceed;
        } catch (BizException ex) {
            log.error("process {} exception. args is :{},", methodName, JSON.toJSONString(proceedingJoinPoint.getArgs()), ex);
            return buildResult(ex.getErrCode(), ex.getErrMsg(), proceedingJoinPoint);
        } catch (Exception ex) {
            log.error("process {} exception. args is :{},", methodName, JSON.toJSONString(proceedingJoinPoint.getArgs()), ex);
            return buildResult("system", "系统异常", proceedingJoinPoint);
        } catch (Throwable th) {
            log.error("process {} exception. args is :{},", methodName, JSON.toJSONString(proceedingJoinPoint.getArgs()), th);
            return buildResult("system", "系统异常", proceedingJoinPoint);
        }
    }

    private Object buildResult(String errCode, String errMsg, ProceedingJoinPoint proceedingJoinPoint) {
        //TODO result
        return null;
    }


}
