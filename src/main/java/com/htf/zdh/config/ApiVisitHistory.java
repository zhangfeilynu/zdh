package com.htf.zdh.config;


import com.htf.zdh.utils.AtomicCounter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangfei
 * @version 1.0.0
 * @className ApiVisitHistory.java
 * @description api访问历史统计
 * @createTime 2020/12/14 14:31
 */
//@Component
//@Aspect
public class ApiVisitHistory {

    private Logger log = LoggerFactory.getLogger(ApiVisitHistory.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切面
     * - 此处代表com.smile.demo.controller包下的所有接口都会被统计
     */
   @Pointcut("execution(* com.htf.zdh.controller..*.*(..))")
  //  @Pointcut("execution(* com.htf.zdh.controller.AppController.getQrCode(..))")
    public void pointCut(){

    }

    /**
     * 在接口原有的方法执行前，将会首先执行此处的代码
     */
   // @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        log.info("类名：{}", joinPoint.getSignature().getDeclaringType().getSimpleName());
        log.info("方法名:{}", joinPoint.getSignature().getName() );
        // 计数
        AtomicCounter.getInstance().increase();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        log.info("URI:[{}], 耗费时间:[{}] ms, 访问次数:{}", request.getRequestURI(), System.currentTimeMillis() - startTime.get(), AtomicCounter.getInstance().getValue());
    }

    /**
     * 只有正常返回才会执行此方法
     * 如果程序执行失败，则不执行此方法
     */
   //@AfterReturning(returning = "returnVal", pointcut = "pointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnVal) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("URI:[{}], 耗费时间:[{}] ms, 访问次数:{}", request.getRequestURI(), System.currentTimeMillis() - startTime.get(), AtomicCounter.getInstance().getValue());
    }

    /**
     * 当接口报错时执行此方法
     */
    //@AfterThrowing(pointcut = "pointCut()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("接口访问失败，URI:[{}], 耗费时间:[{}] ms", request.getRequestURI(), System.currentTimeMillis() - startTime.get());
    }
}
