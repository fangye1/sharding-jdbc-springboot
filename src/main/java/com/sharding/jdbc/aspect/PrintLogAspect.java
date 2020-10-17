package com.sharding.jdbc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jacky
 */
@Slf4j
@Component
@Aspect
public class PrintLogAspect {
    public PrintLogAspect() {
        log.info("PrintLogAspect aop init.");
    }

    @Around(value = "@annotation(printLog))")
    public Object printLog(ProceedingJoinPoint point, PrintLog printLog) throws Exception {
        long start = System.currentTimeMillis();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        try {
            String className = point.getTarget().getClass().getSimpleName();
            String methodName = point.getSignature().getName();
            StringBuilder argsString = new StringBuilder();
            Object[] args = point.getArgs();
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    if (arg != null) {
                        argsString.append(("".equals(argsString.toString())) ? arg.toString() : "," + arg.toString());
                    }
                }
            }
            String token = request.getHeader("token");
            log.info("[API-S] {}.{} Args: {} token: {} at {}", className, methodName, argsString.toString(),
                    token, start);
            Object result = point.proceed();
            long end = System.currentTimeMillis();
            log.info("[API-E] {}.{} Args: {} token: {} in {} ms", className, methodName,
                    argsString.toString(), token, end - start);
            return result;
        } catch (Throwable e) {
            log.info("[API-E] Error :{}", e.getMessage(), e);
            return new ResponseEntity<>("系统繁忙，请稍后再试", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
