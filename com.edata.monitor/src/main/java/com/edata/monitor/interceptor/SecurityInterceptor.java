package com.edata.monitor.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.util.CookieUtil;

public class SecurityInterceptor implements HandlerInterceptor {
	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				return true;
			} else if (requestUri.matches(url))
				return true;
		}
		Map<String, Cookie> map = CookieUtil.readCookieMap(request);

		if (!map.containsKey("token"))
			throw new AuthorizationException();
		
		Identify identify = CookieUtil.parseToken(map.get("token").getValue());
		request.setAttribute("user", identify);
		request.setAttribute("name", identify.getName());
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
	}
}
