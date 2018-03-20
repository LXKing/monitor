package com.edata.monitor.cache;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.domain.security.RoleAuthorize;
import com.edata.monitor.service.SecurityService;

/**
 * 权限缓存，用于授权检查
 * 
 * @author yangzs
 *
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
	 * @param permissionId
	 *            权限项
	 * @param roles
	 *            角色列表
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
		Identify identify = (Identify) request.getAttribute("user");
		if (identify == null)
			return false;
		// 系统管理员
		if (identify.getKind() == 0)
			return true;

		return hasAuthorized(permissionId, identify.getRoles());
	}

	public synchronized static void reload() {
		Map<String, List<String>> map = new Hashtable<String, List<String>>();
		try {
			List<RoleAuthorize> list = securityService.roleAuthorizes();
			for (RoleAuthorize ra : list) {
				List<String> roles = map.get(ra.getPermissionId());
				if (roles == null) {
					roles = new ArrayList<String>();
					map.put(ra.getPermissionId(), roles);
				}
				roles.add(ra.getRoleId());
			}
			authorizes = map;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
