package ru.kpfu.itis.safiullin.walletspringboot.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.logging.Logger;

@Aspect
@Configurable
public class MyLogger {

    static Logger logger = Logger.getLogger(MyLogger.class.getName());

    @Pointcut("within(ru.kpfu.itis.safiullin.walletspringboot.controllers.*)")
    public void execute() {
    }

    @Pointcut("@annotation(ru.kpfu.itis.safiullin.walletspringboot.aspects.Loggable)")
    public void logAnnotation() {
    }

    @Before("execute()")
    public void before(JoinPoint joinPoint) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("METHOD: " + joinPoint.getSignature().getName() + " User: " + name);
    }

}

