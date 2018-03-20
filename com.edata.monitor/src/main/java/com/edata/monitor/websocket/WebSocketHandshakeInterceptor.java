package com.edata.monitor.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.edata.monitor.domain.security.Identify;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	// private static Logger logger =
	// LoggerFactory.getLogger(HandshakeInterceptor.class);

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes)
			throws Exception {

		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			Identify identify = (Identify) servletRequest.getServletRequest().getAttribute("user");

			// HttpSession session =
			// servletRequest.getServletRequest().getSession(false);
			// /if (session != null) {
			// 使用userName区分WebSocketHandler，以便定向发送消息
			// String userName = (String) session.getAttribute("user");
			attributes.put("user", identify);
			// }
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

	}
}
