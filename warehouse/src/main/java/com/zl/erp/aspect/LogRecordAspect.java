package com.zl.erp.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: LogRecordAspect
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@Aspect
@Component
@Order(-1)
@Slf4j
public class LogRecordAspect {


    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切点
     */
    @Pointcut(value = "execution(* com.zl..controller..*.*(..))")
    public void executeService() {
    }

    /**
     * 通过连接点切入
     */
    @Before(value = "executeService()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求地址:{}", request.getRequestURL().toString());
        log.info("请求方式:{}", request.getMethod());
        log.info("请求IP:{}", request.getRemoteAddr());
        log.info("请求控制器名称：{}，请求接口名称：{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("请求参数:{}", joinPoint.getArgs());
    }

    @AfterReturning("executeService()")
    public void doAfterReturning() {
        // 处理完请求，返回内容
        log.info("接口耗时（毫秒）:{}", (System.currentTimeMillis() - startTime.get()));
    }
}
