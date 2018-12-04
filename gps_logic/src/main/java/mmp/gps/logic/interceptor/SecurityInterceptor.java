package mmp.gps.logic.interceptor;

import mmp.gps.logic.Identity;
import mmp.gps.common.util.CookieUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {

    private List excludedUrls;


    public void setExcludedUrls(List excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        Iterator map = this.excludedUrls.iterator();

        String identify;
        do {
            if (!map.hasNext()) {
                Map map1 = CookieUtil.readCookieMap(request);
                if (!map1.containsKey("token")) {
                    throw new AuthorizationException();
                }

                Identity identify1 = Identity.parse(((Cookie) map1.get("token")).getValue());
                request.setAttribute("user", identify1);
                request.setAttribute("name", identify1.getName());
                return true;
            }

            identify = (String) map.next();
            if (requestUri.endsWith(identify)) {
                return true;
            }
        } while (!requestUri.matches(identify));

        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
    }
}
