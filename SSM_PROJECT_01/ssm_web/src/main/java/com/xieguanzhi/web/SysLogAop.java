package com.xieguanzhi.web;


import com.xieguanzhi.domain.SysLog;
import com.xieguanzhi.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class SysLogAop {

    @Autowired
    SysLogService sysLogServiceImpl;

    @Autowired
    HttpServletRequest httpServletRequest;

    //获取调用的类名
    private Class clazz;

    //获取调用的方法
    private Method method;

    //获取起始时间
    private Date visitTime;

    //获取总体时长
    private Long executionTime;

    //获取访问URL
    private String URL;

    private String userName;

    private String ip;


    @Before("execution(* com.xieguanzhi.web.*.*(..))")
    public void init(JoinPoint joinPoint){
        clazz = joinPoint.getTarget().getClass();
        String name = joinPoint.getSignature().getName();
        Class[] argsClass = new Class[joinPoint.getArgs().length];
        for (int i = 0; i < argsClass.length; i++) {
            argsClass[i]=joinPoint.getArgs()[i].getClass();
        }
        try {
            method = clazz.getMethod(name,argsClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        visitTime = new Date();
        ip = httpServletRequest.getRemoteAddr();
    }

    @After("execution(* com.xieguanzhi.web.*.*(..))")
    public void after(JoinPoint joinPoint){
        executionTime = System.currentTimeMillis()-visitTime.getTime();
        try {
            RequestMapping classAnno = joinPoint.getTarget().getClass().getAnnotation(RequestMapping.class);
            RequestMapping methodAnno = method.getAnnotation(RequestMapping.class);
            URL = classAnno.value()[0]+methodAnno.value()[0];
        }catch (Exception e){
            e.printStackTrace();
        }
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userName = user.getUsername();
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod(clazz.getName() +"---"+ method.getName());
        sysLog.setUrl(URL);
        sysLog.setUsername(userName);
        sysLog.setVisitTime(visitTime);
        sysLogServiceImpl.save(sysLog);
    }

}
