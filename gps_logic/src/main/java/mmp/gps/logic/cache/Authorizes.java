package mmp.gps.logic.cache;

import mmp.gps.logic.service.SecurityService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public final class Authorizes {

    private static SecurityService securityService;
    private static Map permissionRoles;


    @Autowired(required = true)
    public Authorizes(@Qualifier("securityService") SecurityService securityService) {
        securityService = securityService;
        init();
    }

    public static boolean isInRole(String permission, String roles) {
        if (permissionRoles == null) {
            init();
        }

        List pr = (List) permissionRoles.get(permission);
        if (pr == null) {
            return true;
        } else if (roles != null && roles.length() > 0) {
            String[] list = roles.split(";");
            String[] var4 = list;
            int var5 = list.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                String role = var4[var6];
                if (pr.contains(role)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    private static synchronized void init() {
        permissionRoles = securityService.getPermissionRoles();
    }

    public static synchronized void authorize(String permission, String role, boolean allow) {
        if (permissionRoles != null) {
            Object pr = (List) permissionRoles.get(permission);
            if (pr == null) {
                pr = new ArrayList();
                permissionRoles.put(permission, pr);
            }

            if (allow) {
                ((List) pr).add(role);
            } else {
                ((List) pr).remove(role);
            }

        }
    }

    public static synchronized void updatePermission(String oldPermission, String newPermission) {
        if (permissionRoles != null) {
            List pr = (List) permissionRoles.get(oldPermission);
            permissionRoles.put(newPermission, pr);
            pr.remove(oldPermission);
        }
    }

    public static synchronized void removePermission(String permission) {
        if (permissionRoles != null) {
            permissionRoles.remove(permission);
        }
    }

    public static synchronized void updateRole(String oldRole, String newRole) {
        if (permissionRoles != null) {
            Iterator var2 = permissionRoles.values().iterator();

            while (var2.hasNext()) {
                List pr = (List) var2.next();
                if (pr.remove(oldRole)) {
                    pr.add(newRole);
                }
            }

        }
    }

    public static synchronized void removeRole(String role) {
        if (permissionRoles != null) {
            Iterator iterator = permissionRoles.values().iterator();

            while (iterator.hasNext()) {
                List pr = (List) iterator.next();
                pr.remove(role);
            }

        }
    }
}
