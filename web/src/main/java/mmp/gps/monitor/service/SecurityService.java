package mmp.gps.monitor.service;

import mmp.gps.common.util.Errors;
import mmp.gps.domain.permission.Permission;
import mmp.gps.domain.security.*;
import mmp.gps.domain.user.User;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.configuration.AppConfig;
import mmp.gps.monitor.dao.ICompanyDao;
import mmp.gps.monitor.dao.ISecurityDao;
import mmp.gps.monitor.dao.PermissionMapper;
import mmp.gps.monitor.util.MD5;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 安用服务类
 */
@Service
public class SecurityService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private ISecurityDao securityDao;
    @Autowired
    private ICompanyDao companyDao;


    @Autowired
    private PermissionMapper permissionMapper;


    public IdentityDto get(String account) throws Exception {
        return securityDao.get(account);
    }


    public List<RoleAuthorize> getAllShit() {
        List<RoleAuthorize> list = null;
        try {
            list = roleAuthorizes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getAllRoles(IdentityDto user) {
        List<String> roles = null;


        // 根据身份信息从数据库中获取权限数据
        if (user.kind == 1) {

            try {
                roles = securityDao.getRoles(user.companyId);
            } catch (Exception e) {
                e.printStackTrace();
            }

            user.setRoles(new String[]{user.companyId});
        } else {

            try {
                roles = securityDao.getRoles(user.id);
            } catch (Exception e) {
                e.printStackTrace();
            }


            user.setRoles(roles.toArray(new String[0]));
        }

        return roles;
    }

    // @Autowired
    // private SecurityService securityService;

    public boolean hasAuthorized(String permissionId, List<RoleAuthorize> list, List<String> roles, IdentityDto user) {


        if (user == null)
            return false;
        // 系统管理员
        if (user.getKind() == 0)
            return true;


        List<String> permissionStringList = new ArrayList<>();


        // 系统管理员
        if (user.getKind() == 0) {
            list.forEach(roleAuthorize -> permissionStringList.add(roleAuthorize.getPermissionId()));
        } else {
            for (String role : roles) {
                list.stream()
                        .filter(roleAuthorize -> roleAuthorize.getRoleId().equals(role))
                        .forEach(roleAuthorize -> permissionStringList.add(roleAuthorize.getPermissionId()));
            }
        }
        for (String permission : permissionStringList) {
            if (permission.equals(permissionId)) {
                return true;
            }
        }


        return false;

    }

    /**
     * 用户登录认证
     *
     * @throws Exception
     */
    // public Identity login(String account, String password) throws Exception {
    //     IdentityDto dto = securityDao.login(account, password);
    //     // if (dto == null) {
    //     //     throw new Exception("用户名或密码输入错误！");
    //     // }
    //     // // 检测密码
    //     // if (!dto.password.equals(password))
    //     //     throw new Exception("用户名或密码输入错误！");
    //     // // 检查用户服务到期
    //     // if (dto.serviceEndTime != null) {
    //     //     Date now = new Date();
    //     //     long from = dto.serviceEndTime.getTime();
    //     //     long to = now.getTime();
    //     //     if (from < to)
    //     //         throw new Exception("用户已过期！");
    //     // }
    //     // // 检查用户禁用否
    //     // if (!dto.isEnable()) {
    //     //     throw new Exception("用户已停用！");
    //     // }
    //
    //     // 检查企业服务到期
    //     // checkCompany(dto.companyId);
    //
    //     Identity identity = new Identity();
    //     identity.setAccount(dto.account);
    //     identity.setId(dto.id);
    //     identity.setCompanyId(dto.companyId);
    //     identity.setUnid(dto.unid);
    //     identity.setName(dto.name);
    //     identity.setKind(dto.kind);
    //     if (dto.kind == 1) {
    //         identity.setRoles(new String[]{dto.companyId});
    //     } else {
    //         List<String> roles = securityDao.getRoles(dto.id);
    //         identity.setRoles(roles.toArray(new String[0]));
    //     }
    //
    //     return identity;
    // }
    public void checkCompany(String companyId) throws AuthenticationException {
        ParentDto parent = null;

        try {
            parent = securityDao.getParentCompany(companyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (parent == null)
            return;
        // 检查企业服务到期
        if (parent.serviceEndTime != null) {
            Date now = new Date();
            long from = parent.serviceEndTime.getTime();
            long to = now.getTime();
            if (from < to)
                throw new AuthenticationException("企业服务已过期！");
        }
        // 检查上级禁用否
        if (!parent.isEnable())
            throw new AuthenticationException("企业服务已停用！");

        checkCompany(parent.companyId);
    }

    public MyInfo getMyInfo(String id) throws Exception {
        MyinfoDto dto = securityDao.getMyInfo(id);
        MyInfo info = new MyInfo();
        info.setAccount(dto.account);
        info.setContact(dto.contact);
        info.setEditTime(dto.editTime);
        info.setEmail(dto.email);
        info.setId(id);
        info.setName(dto.name);
        info.setPhone(dto.phone);

        return info;
    }

    @RequiresPermissions("security.saveMyinfo")
    @Log(name = "修改用户信息")
    public void saveMyinfo(MyInfo info, IdentityDto u) throws Exception {
        // 验证密码
        String pwd = securityDao.getPasswodById(info.getId());

        User user = new User();
        user.setAccount(u.getAccount());
        user.setPassword(info.getPwd());

        if (!pwd.equals(MD5.doMD5(user))) {
            throw new Exception(Errors.passwordMistake);
        }
        MyinfoDto dto = new MyinfoDto();
        dto.account = info.getAccount();

        dto.editTime = info.getEditTime();
        dto.email = info.getEmail();
        dto.id = info.getId();
        dto.name = info.getName();
        dto.phone = info.getPhone();
        dto.contact = info.getContact();

        User www = new User();
        www.setAccount(info.getContact());
        www.setPassword(info.getPwd());
        dto.pwd = MD5.doMD5(www);

        int rows = securityDao.saveMyInfo(dto);
        if (rows != 1) {
            throw new Exception(Errors.anotherEdited);
        }
    }

    @RequiresPermissions("security.saveMyKey")
    @Log(name = "修改用户密码")
    public void saveMyKey(String id, String account, String oldKey, String newKey, String confirmKey) throws Exception {
        // 验证密码
        String pwd = securityDao.getPasswodById(id);
        User user = new User();
        user.setAccount(account);
        user.setPassword(oldKey);
        MD5.doMD5(user);

        if (!pwd.equals(MD5.doMD5(user))) {
            throw new Exception(Errors.oldPasswordMistake);
        }
        if (!newKey.equals(confirmKey)) {
            throw new Exception(Errors.passwordNotMatch);
        }
        user.setPassword(newKey);

        int rows = securityDao.saveMyKey(id, MD5.doMD5(user));
        if (rows != 1) {
            throw new Exception(Errors.anotherEdited);
        }
    }

    public void saveOperateLogs(List<OperateLog> operateLogs) throws Exception {
        securityDao.saveOperateLogs(operateLogs);
    }

    public List<Permission> authorizes(String companyId) throws Exception {
        Map<String, Permission> all = loadPermissions();
        if (companyId.equals(appConfig.getTopCompanyId())) {
            return new ArrayList<>(all.values());
        }
        List<Permission> permissions = new ArrayList<Permission>();
        List<String> list = companyDao.authorizes(companyId);

        list.stream().filter(id -> all.get(id) != null).forEach(id -> permissions.add(all.get(id)));


        // for (String id : list) {
        //     Permission per = all.get(id);
        //     if (per == null)
        //         continue;
        //     permissions.add(per);
        // }

        return permissions;
    }

    private Map<String, Permission> loadPermissions() {
        Map<String, Permission> permissions = new HashMap<String, Permission>();
        // List<Class<?>> controller = PackageUtil.getAnnotationClass("mmp.gps.monitor.controller", Controller.class);

        // doShit(controller, permissions);
        // List<Class<?>> services = PackageUtil.getAnnotationClass("mmp.gps.monitor.service", Service.class);
        List<Permission> permissionList = permissionMapper.listPermission();


        permissionList.forEach(permission -> {
            Permission per = new Permission();
            per.setId(permission.getPermissionid());
            per.setPid(permission.getPid());
            per.setName(permission.getName());

            permissions.put(per.getId(), per);
        });
        // doShit(services, permissions);
        return permissions;
    }


    // private void doShit(List<Class<?>> classes, Map<String, Permission> permissionMap) {
    //     for (Class<?> c : classes) {
    //         Method[] methods = c.getMethods();
    //         for (Method m : methods) {
    //             if (m.isAnnotationPresent(Log.class)) {
    //                 Log sm = m.getAnnotation(Log.class);
    //                 Permission per = new Permission();
    //                 per.setId(sm.id());
    //                 per.setPid(sm.pid());
    //                 per.setName(sm.name());
    //
    //                 permissionMap.put(per.getId(), per);
    //             }
    //         }
    //     }
    // }

    public List<RoleAuthorize> roleAuthorizes() throws Exception {
        List<RoleAuthorizeDto> list = securityDao.roleAuthorizes();
        List<RoleAuthorize> authorizes = new ArrayList<RoleAuthorize>();
        for (RoleAuthorizeDto dto : list) {
            RoleAuthorize ra = new RoleAuthorize();
            ra.setPermissionId(dto.permissionId);
            ra.setRoleId(dto.roleId);

            authorizes.add(ra);
        }
        return authorizes;
    }


    public Integer createPermission(Permission permission) {
        return companyDao.createPermission(permission);
    }

    public Integer deletePermission(Permission permission) {
        return companyDao.deletePermission(permission);
    }

    public Integer updatePermission(Permission permission) {
        return companyDao.updatePermission(permission);
    }
}
