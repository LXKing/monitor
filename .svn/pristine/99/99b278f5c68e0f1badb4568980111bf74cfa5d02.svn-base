package com.rayton.gps.aop;

import com.rayton.gps.configuration.AppConfig;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.dao.security.OperateLog;
import com.rayton.gps.service.SecurityService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private SecurityService securityService;

    private List<OperateLog> operateLogs = Collections.synchronizedList(new ArrayList<>());

    private void addOperateLog(OperateLog log) throws Exception {
        operateLogs.add(log);
        if (operateLogs.size() >= appConfig.getOperateLogCacheRows()) {
            securityService.saveOperateLogs(operateLogs);
            operateLogs = Collections.synchronizedList(new ArrayList<>());
        }
    }

    @Before("execution(* com.rayton.gps..*.*(..)) && @annotation(com.rayton.gps.aop.Log)")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        Assert.isTrue(method.isAnnotationPresent(Log.class), "...Log isAnnotationPresent False");
        // if (!method.isAnnotationPresent(Log.class))
        //     return;
        Log sm = method.getAnnotation(Log.class);
        String description = sm.prefix() + sm.name() + sm.suffix();

        // RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        // ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        // HttpServletRequest request = sra.getRequest();
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Assert.notNull(identity, "identity is null");
        // if (identity == null)
        //     return;

        // 系统管理员
        // if (!identity.getId().equals(appConfig.getTopCompanyId())) {
        //     // 授权检查
        //     if (!AuthorizeCache.hasAuthorized(sm.id(), identity.getRoles()))
        //         throw new Exception("此操作未授权！");
        // }

        OperateLog log = new OperateLog();
        log.setAction(description);
        log.setCompanyId(identity.getCompanyId());
        log.setTime(new Date());
        log.setUser(identity.getName());
        log.setUserId(identity.getId());

        this.addOperateLog(log);
    }


    // @After("execution(* com.rayton.gps..*.*(..)) && @annotation(com.rayton.gps.aop.Log)")
    // public void shit(JoinPoint joinPoint) throws Exception {
    //
    //     List<OperateLog> logs = new ArrayList<OperateLog>(1);
    //     OperateLog log = new OperateLog();
    //     RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    //     ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    //     HttpServletRequest request = sra.getRequest();
    //
    //     log.setAction("用户录登:" + request.getRemoteAddr());
    //
    //
    //     IdentifyDto dto = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
    //     log.setCompanyId(dto.getCompanyId());
    //     log.setTime(new Date());
    //     log.setUser(dto.getName());
    //     log.setUserId(dto.getId());
    //     logs.add(log);
    //
    //     securityService.saveOperateLogs(logs);
    // }
}
