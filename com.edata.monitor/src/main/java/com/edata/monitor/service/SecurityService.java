package com.edata.monitor.service;

import com.edata.common.PackageUtil;
import com.edata.monitor.AppConfig;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.baseinfo.company.ICompanyDao;
import com.edata.monitor.dao.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 安用服务类
 *
 * @author 生
 */
@Service
public class SecurityService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private ISecurityDao securityDao;
    @Autowired
    private ICompanyDao companyDao;

    /**
     * 用户登录认证
     *
     * @throws Exception
     */
    public Identity login(String account, String password) throws Exception {
        IdentifyDto dto = securityDao.login(account, password);
        if (dto == null) {
            throw new Exception("用户名或密码输入错误！");
        }
        // 检测密码
        if (!dto.password.equals(password))
            throw new Exception("用户名或密码输入错误！");
        // 检查用户服务到期
        if (dto.serviceEndTime != null) {
            Date now = new Date();
            long from = dto.serviceEndTime.getTime();
            long to = now.getTime();
            if (from < to)
                throw new Exception("用户已过期！");
        }
        // 检查用户禁用否
        if (!dto.isEnable()) {
            throw new Exception("用户已停用！");
        }

        // 检查企业服务到期
        checkCompany(dto.companyId);

        Identity identity = new Identity();
        identity.setAccount(dto.account);
        identity.setId(dto.id);
        identity.setCompanyId(dto.companyId);
        identity.setUnid(dto.unid);
        identity.setName(dto.name);
        identity.setKind(dto.kind);
        if (dto.kind == 1) {
            identity.setRoles(new String[]{dto.companyId});
        } else {
            List<String> roles = securityDao.getRoles(dto.id);
            identity.setRoles(roles.toArray(new String[0]));
        }

        return identity;
    }

    private void checkCompany(String companyId) throws Exception {
        ParentDto parent = securityDao.getParentCompany(companyId);
        if (parent == null)
            return;
        // 检查企业服务到期
        if (parent.serviceEndTime != null) {
            Date now = new Date();
            long from = parent.serviceEndTime.getTime();
            long to = now.getTime();
            if (from < to)
                throw new Exception("企业服务已过期！");
        }
        // 检查上级禁用否
        if (!parent.isEnable())
            throw new Exception("企业服务已停用！");

        checkCompany(parent.companyId);
    }

    public MyInfo getMyInfo(String id) throws Exception {
        MyInfoDto dto = securityDao.getMyInfo(id);
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

    @ServiceMethod(id = "security.saveMyinfo", pid = "security", name = "修改用户信息")
    public void saveMyinfo(MyInfo info) throws Exception {
        // 验证密码
        String pwd = securityDao.getPasswodById(info.getId());
        if (!pwd.equals(info.getPwd())) {
            throw new Exception(Errors.passwordMistake);
        }
        MyInfoDto dto = new MyInfoDto();
        dto.account = info.getAccount();
        dto.contact = info.getContact();
        dto.editTime = info.getEditTime();
        dto.email = info.getEmail();
        dto.id = info.getId();
        dto.name = info.getName();
        dto.phone = info.getPhone();
        dto.pwd = info.getPwd();

        int rows = securityDao.saveMyInfo(dto);
        if (rows != 1) {
            throw new Exception(Errors.anotherEdited);
        }
    }

    @ServiceMethod(id = "security.saveMyKey", pid = "security", name = "修改用户密码")
    public void saveMyKey(String id, String oldKey, String newKey, String confirmKey) throws Exception {
        // 验证密码
        String pwd = securityDao.getPasswodById(id);
        if (!pwd.equals(oldKey)) {
            throw new Exception(Errors.oldPasswordMistake);
        }
        if (!newKey.equals(confirmKey)) {
            throw new Exception(Errors.passwordNotMatch);
        }
        int rows = securityDao.saveMyKey(id, newKey);
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
            return new ArrayList<Permission>(all.values());
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
        List<Class<?>> controller = PackageUtil.getAnnotationClass("com.edata.monitor.controller", Controller.class);
        for (Class<?> c : controller) {
            Method[] methods = c.getMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(ServiceMethod.class)) {
                    ServiceMethod sm = m.getAnnotation(ServiceMethod.class);
                    Permission per = new Permission();
                    per.setId(sm.id());
                    per.setPid(sm.pid());
                    per.setName(sm.name());

                    permissions.put(per.getId(), per);
                }
            }
        }
        List<Class<?>> services = PackageUtil.getAnnotationClass("com.edata.monitor.service", Service.class);
        for (Class<?> c : services) {
            Method[] methods = c.getMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(ServiceMethod.class)) {
                    ServiceMethod sm = m.getAnnotation(ServiceMethod.class);
                    Permission per = new Permission();
                    per.setId(sm.id());
                    per.setPid(sm.pid());
                    per.setName(sm.name());

                    permissions.put(per.getId(), per);
                }
            }
        }

        return permissions;
    }

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
}
