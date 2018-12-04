package mmp.gps.common.util;

import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static final String token = "token";
    public static final String name = "name";


    public static Map readCookieMap(HttpServletRequest request) {
        HashMap cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

    public static Cookie getCookie(Object key, HttpServletRequest request) {
        Map map = readCookieMap(request);
        Cookie cookie = (Cookie) map.get(key);
        return cookie;
    }

    public static String getValue(Object key, HttpServletRequest request) {
        Cookie cookie = getCookie(key, request);
        return cookie == null ? null : cookie.getValue();
    }

    public static void clearCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            int i = 0;

            for (int len = cookies.length; i < len; ++i) {
                Cookie cookie = new Cookie(cookies[i].getName(), "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

        }
    }

    public static String makeToken(String account, String id, String name, long timestamp, String roles) {
        StringBuilder sb = new StringBuilder(100);
        sb.append(account).append("\t").append(id).append("\t").append(name).append("\t").append(timestamp).append("\t").append(roles);
        String token = "";

        try {
            token = RSA.encrypt(sb.toString());
        } catch (IllegalBlockSizeException var9) {
            var9.printStackTrace();
        } catch (BadPaddingException var10) {
            var10.printStackTrace();
        }

        return token;
    }
}
