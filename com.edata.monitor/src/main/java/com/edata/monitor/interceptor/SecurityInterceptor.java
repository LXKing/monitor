package com.edata.monitor.interceptor;

import com.edata.monitor.dao.security.Identity;
import com.edata.monitor.util.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class SecurityInterceptor implements HandlerInterceptor {
    private List<String> excludedUrls;

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.endsWith(url) || requestUri.matches(url)) {
                return true;
            }
        }
        Map<String, Cookie> map = CookieUtil.readCookieMap(request);

        if (!map.containsKey("token"))
            throw new AuthorizationException();

        Identity identity = CookieUtil.parseToken(map.get("token").getValue());
        request.setAttribute("user", identity);
        request.setAttribute("name", identity.getName());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            e) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
            throws Exception {
    }
}
