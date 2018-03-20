package com.edata.monitor.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.edata.monitor.domain.security.Identify;

public class CookieUtil {

	public static final String token = "token";
	public static final String name = "name";

	public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	public static Cookie getCookie(Object key, HttpServletRequest request) {
		Map<String, Cookie> map = readCookieMap(request);
		Cookie cookie = map.get(key);
		return cookie;
	}

	public static String getValue(Object key, HttpServletRequest request) {
		Cookie cookie = getCookie(key, request);
		if (cookie == null)
			return null;
		return cookie.getValue();
	}

	public static String encoding(String code) {
		try {
			return RSA.encrypt(code);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return code;
	}

	public static String decoding(String code) {
		try {
			return RSA.decrypt(code);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return code;
	}

	public static String makeToken(Identify user) {
		String role = StringUtils.arrayToDelimitedString(user.getRoles(), ",");
		StringBuilder sb = new StringBuilder(100);
		sb.append(user.getId()).append("\t").append(user.getCompanyId()).append("\t").append(user.getUnid()).append("\t").append(user.getAccount())
				.append("\t").append(user.getName()).append("\t").append(user.getKind()).append("\t").append(role);
		String token = "";
		try {
			String info = sb.toString();
			token = RSA.encrypt(info);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
	}

	public static Identify parseToken(String token) throws Exception {
		String info = RSA.decrypt(token);
		String[] list = info.split("\t", 7);
		Identify i = new Identify();
		i.setId(list[0]);
		i.setCompanyId(list[1]);
		i.setUnid(list[2]);
		i.setAccount(list[3]);
		i.setName(list[4]);
		i.setKind(Integer.parseInt(list[5]));

		String[] roles = list[6].split(",");
		i.setRoles(roles);

		return i;
	}
}
