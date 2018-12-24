package mmp.gps.monitor.aop;

import mmp.gps.domain.security.IdentityDto;
import mmp.gps.domain.security.OperateLog;
import mmp.gps.monitor.configuration.AppConfig;
import mmp.gps.monitor.service.SecurityService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
        this.operateLogs.add(log);
        if (this.operateLogs.size() >= appConfig.getOperateLogCacheRows()) {
            securityService.saveOperateLogs(this.operateLogs);
            this.operateLogs = Collections.synchronizedList(new ArrayList<>());
        }
    }

    @Before("execution(* mmp.gps..*.*(..)) && @annotation(mmp.gps.monitor.aop.Log)")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        Assert.isTrue(method.isAnnotationPresent(Log.class), "...Log isAnnotationPresent False");


        Log sm = method.getAnnotation(Log.class);
        String description = sm.name();


        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Assert.notNull(identity, "identity is null");


        OperateLog log = new OperateLog();
        log.setAction(description);
        log.setCompanyId(identity.getCompanyId());
        log.setTime(new Date());
        log.setUser(identity.getName());
        log.setUserId(identity.getId());

        this.addOperateLog(log);
    }


    @After("execution(* mmp.gps..*.*(..)) && @annotation(mmp.gps.monitor.aop.Log)")
    public void doAfterInServiceLayer(JoinPoint joinPoint) throws Exception {

        List<OperateLog> logs = new ArrayList<OperateLog>(1);
        OperateLog log = new OperateLog();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        log.setAction("用户登录:" + request.getRemoteAddr());


        IdentityDto dto = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        log.setCompanyId(dto.getCompanyId());
        log.setTime(new Date());
        log.setUser(dto.getName());
        log.setUserId(dto.getId());
        logs.add(log);

        securityService.saveOperateLogs(logs);
    }
}
