package mmp.gps.logic.service;

import mmp.gps.common.util.CookieUtil;
import mmp.gps.domain.permission.PermissionDto;
import mmp.gps.domain.security.*;
import mmp.gps.domain.user.UserDto;
import mmp.gps.logic.Identity;
import mmp.gps.logic.dao.ISecurityDao;
import mmp.gps.logic.dao.IUserDao;
import mmp.gps.logic.push.Pushers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class SecurityService {

    @Autowired
    private ISecurityDao securityDao;
    @Autowired
    private IUserDao userDao;


    public LoginResponse login(String account, String password) throws Exception {
        System.out.println("account" + account + "password" + password);
        IdentityInfoDto dto = this.securityDao.login(account, password);
        LoginResponse response = new LoginResponse();
        if (dto == null) {
            throw new Exception("用户不存在");
        } else if (!dto.password.equals(password)) {
            throw new Exception("密码输入错误");
        } else if (!dto.enabled) {
            throw new Exception("帐户已禁用");
        } else {
            Date start = dto.serviceStartDate;
            Date end = dto.serviceEndDate;
            Date now = new Date();
            if (end != null && end.before(now)) {
                throw new Exception("用户已过期");
            } else {
                response.setAccount(dto.account);
                response.setId(dto.id);
                response.setName(dto.name);
                response.setServiceStartTime(start);
                response.setServiceEndTime(end);
                String roles = this.getRoles(dto.id);
                String token = CookieUtil.makeToken(account, dto.id, dto.name, now.getTime(), roles);
                response.setToken(token);
                return response;
            }
        }
    }

    public String getRoles(String id) {
        List roles = this.securityDao.getRoleInUser(id);
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;

        for (Iterator var5 = roles.iterator(); var5.hasNext(); isFirst = false) {
            String role = (String) var5.next();
            if (!isFirst) {
                builder.append(";");
            }

            builder.append(role);
        }

        return builder.toString();
    }

    public HashMap getPermissionRoles() {
        HashMap map = new HashMap();
        List permissions = this.securityDao.loadPermissions();
        Iterator var3 = permissions.iterator();

        while (var3.hasNext()) {
            PermissionDto permission = (PermissionDto) var3.next();
            List roles = this.securityDao.loadRoleInPermission(permission.id);
            map.put(permission.name, roles);
        }

        return map;
    }

    public GetMyInfoResponse getMyinfo(Identity identity) {
        MyinfoDto dto = this.securityDao.getMyInfo(identity.getId());
        GetMyInfoResponse info = new GetMyInfoResponse();
        info.setAccount(dto.account);
        info.setContact(dto.contact);
        info.setEditTime(dto.editTime);
        info.setEmail(dto.email);
        info.setId(dto.id);
        info.setName(dto.name);
        info.setPhone(dto.phone);
        info.setPushUrl(dto.pushUrl);
        info.setPwd(dto.pwd);
        return info;
    }

    @Transactional
    public void saveMyInfo(SaveMyInfoRequest request) {
        if (this.userDao.existOutId(request.getAccount(), request.getId())) {
            throw new RuntimeException("用户已存在！");
        } else {
            UserDto user = this.userDao.fetch(request.getId());
            if (!user.password.equals(request.getPwd())) {
                throw new RuntimeException("密码输入错误");
            } else {
                MyinfoDto dto = new MyinfoDto();
                dto.account = request.getAccount();
                dto.contact = request.getContact();
                dto.editTime = request.getEditTime();
                dto.email = request.getEmail();
                dto.id = request.getId();
                dto.name = request.getName();
                dto.phone = request.getPhone();
                dto.pushUrl = request.getPushUrl();
                int rows = this.securityDao.saveMyInfo(dto);
                if (rows != 1) {
                    throw new RuntimeException("数据已被其它用户修改！");
                } else {
                    Pushers.getCurrent().updatePusher(dto.id, dto.pushUrl);
                }
            }
        }
    }

    @Transactional
    public void saveMyKey(Identity identity, SaveMyKeyRequest request) {
        UserDto user = this.userDao.fetch(identity.getId());
        if (!user.password.equals(request.getOldKey())) {
            throw new RuntimeException("旧密码错误！");
        } else if (!request.getNewKey().equals(request.getConfirmKey())) {
            throw new RuntimeException("确认密码与新密码不一致！");
        } else {
            this.securityDao.saveMyKey(user.id, request.getNewKey());
        }
    }
}
