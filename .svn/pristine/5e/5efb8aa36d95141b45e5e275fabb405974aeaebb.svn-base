package com.edata.monitor.aop;

import com.edata.monitor.AppConfig;
import com.edata.monitor.cache.AuthorizeCache;
import com.edata.monitor.dao.security.Identity;
import com.edata.monitor.dao.security.OperateLog;
import com.edata.monitor.service.SecurityService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Aspect
public class ServiceAspect {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private SecurityService securityService;

    private List<OperateLog> operateLogs = new ArrayList<>();

    private synchronized void addOperateLog(OperateLog log) {
        operateLogs.add(log);
        if (operateLogs.size() >= appConfig.getOperateLogCacheRows()) {
            try {
                securityService.saveOperateLogs(operateLogs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            operateLogs = new ArrayList<>();
        }
    }

    @Before("execution(* com.edata.monitor..*.*(..)) && @annotation(com.edata.monitor.aop.ServiceMethod)")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (!method.isAnnotationPresent(ServiceMethod.class))
            return;
        ServiceMethod sm = method.getAnnotation(ServiceMethod.class);
        String description = sm.prefix() + sm.name() + sm.suffix();

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Identity identity = (Identity) request.getAttribute("user");
        if (identity == null)
            return;

        // 系统管理员
        if (!identity.getId().equals(appConfig.getTopCompanyId())) {
            // 授权检查
            if (!AuthorizeCache.hasAuthorized(sm.id(), identity.getRoles()))
                throw new Exception("此操作未授权！");
        }

        OperateLog log = new OperateLog();
        log.setAction(description);
        log.setCompanyId(identity.getCompanyId());
        log.setTime(new Date());
        log.setUser(identity.getName());
        log.setUserId(identity.getId());

        addOperateLog(log);
    }
}
