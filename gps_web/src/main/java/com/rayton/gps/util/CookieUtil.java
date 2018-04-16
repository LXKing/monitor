package com.rayton.gps.util;

import com.rayton.gps.dao.security.Identity;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {


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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String decoding(String code) {
        try {
            return RSA.decrypt(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    // identity to user
    public static String makeToken(Identity user) {
        String role = StringUtils.arrayToDelimitedString(user.getRoles(), ",");
        StringBuilder sb = new StringBuilder(100);
        sb.append(user.getId()).append("\t").append(user.getCompanyId()).append("\t").append(user.getUnid()).append
                ("\t").append(user.getAccount()).append("\t").append(user.getName()).append("\t").append(user.getKind
                ()).append("\t").append(role);
        String token = "";
        try {
            String info = sb.toString();
            token = RSA.encrypt(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    // cookie to identity
    public static Identity parseToken(String token) throws Exception {
        String info = RSA.decrypt(token);
        String[] list = info.split("\t", 7);
        Identity i = new Identity();
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
