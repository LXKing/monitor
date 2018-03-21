package com.edata.monitor.cache;

import com.edata.monitor.dao.security.Identity;
import com.edata.monitor.dao.security.RoleAuthorize;
import com.edata.monitor.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 权限缓存，用于授权检查
 *
 * @author yangzs
 */
@Component
public class AuthorizeCache {
    /**
     * Key:权限id,value:角色id列表
     */
    private static Map<String, List<String>> authorizes;

    private static SecurityService securityService;

    @Autowired(required = true)
    public AuthorizeCache(@Qualifier("securityService") SecurityService securityService) {
        AuthorizeCache.securityService = securityService;
        reload();
    }

    /**
     * 检测指定的权限项，是否授予给角色
     *
     * @param permissionId 权限项
     * @param roles        角色列表
     */
    public static boolean hasAuthorized(String permissionId, String[] roles) {
        if (authorizes == null)
            return false;
        List<String> list = authorizes.get(permissionId);
        if (list == null)
            return false;

        for (String role : roles) {
            if (list.contains(role))
                return true;
        }
        return false;
    }

    public static boolean hasAuthorized(String permissionId, HttpServletRequest request) {
        Identity identity = (Identity) request.getAttribute("user");
        if (identity == null)
            return false;
        // 系统管理员
        if (identity.getKind() == 0)
            return true;

        return hasAuthorized(permissionId, identity.getRoles());
    }

    public synchronized static void reload() {
        Map<String, List<String>> map = new Hashtable<String, List<String>>();
        try {
            List<RoleAuthorize> list = securityService.roleAuthorizes();
            // TODO
            list.forEach(roleAuthorize -> {
                List<String> roles = map.get(roleAuthorize.getPermissionId());
                if (roles == null) {
                    roles = new ArrayList<String>();
                    map.put(roleAuthorize.getPermissionId(), roles);
                }
                roles.add(roleAuthorize.getRoleId());
            });

            // for (RoleAuthorize ra : list) {
            //     List<String> roles = map.get(ra.getPermissionId());
            //     if (roles == null) {
            //         roles = new ArrayList<String>();
            //         map.put(ra.getPermissionId(), roles);
            //     }
            //     roles.add(ra.getRoleId());
            // }
            authorizes = map;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
