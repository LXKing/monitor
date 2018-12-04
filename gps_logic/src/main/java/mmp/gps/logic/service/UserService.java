package mmp.gps.logic.service;

import mmp.gps.common.util.ObjectId;
import mmp.gps.domain.user.*;
import mmp.gps.logic.dao.IUserDao;
import mmp.gps.logic.push.Pushers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserDao userDao;


    @Transactional
    public void create(CreateUserRequest request) {
        if (this.userDao.exist(request.getAccount())) {
            throw new RuntimeException("用户已存在");
        } else {
            UserDto dto = new UserDto();
            dto.id = ObjectId.next();
            dto.account = request.getAccount();
            dto.contact = request.getContact();
            dto.email = request.getEmail();
            dto.enabled = request.isEnabled();
            dto.name = request.getName();
            dto.password = request.getPassword();
            dto.phone = request.getPhone();
            dto.pushUrl = request.getPushUrl();
            dto.remark = request.getRemark();
            dto.serviceEndDate = request.getServiceEndTime();
            dto.serviceStartDate = request.getServiceStartTime();
            this.userDao.create(dto);
            Pushers.getCurrent().addPusher(dto.id, dto.pushUrl);
        }
    }

    @Transactional
    public void update(EditUserRequest request) {
        if (this.userDao.existOutId(request.getAccount(), request.getId())) {
            throw new RuntimeException("用户已存在");
        } else {
            UserDto dto = new UserDto();
            dto.id = request.getId();
            dto.account = request.getAccount();
            dto.contact = request.getContact();
            dto.email = request.getEmail();
            dto.enabled = request.isEnabled();
            dto.name = request.getName();
            dto.password = request.getPassword();
            dto.phone = request.getPhone();
            dto.pushUrl = request.getPushUrl();
            dto.remark = request.getRemark();
            dto.serviceEndDate = request.getServiceEndTime();
            dto.serviceStartDate = request.getServiceStartTime();
            dto.editTime = request.getEditTime();
            int rows = this.userDao.update(dto);
            if (rows != 1) {
                throw new RuntimeException("数据已被其它用户修改！");
            } else {
                Pushers.getCurrent().updatePusher(dto.id, dto.pushUrl);
            }
        }
    }

    public FetchUserResponse fetch(String id) {
        UserDto dto = this.userDao.fetch(id);
        FetchUserResponse user = new FetchUserResponse();
        user.setId(dto.id);
        user.setAccount(dto.account);
        user.setContact(dto.contact);
        user.setCreateTime(dto.createTime);
        user.setEditTime(dto.editTime);
        user.setEmail(dto.email);
        user.setEnabled(dto.enabled);
        user.setName(dto.name);
        user.setPassword(dto.password);
        user.setPhone(dto.phone);
        user.setPushUrl(dto.pushUrl);
        user.setRemark(dto.remark);
        user.setServiceEndTime(dto.serviceEndDate);
        user.setServiceStartTime(dto.serviceStartDate);
        user.setEditTime(dto.editTime);
        return user;
    }

    public QueryUserResponse query(String nameFilter, int pageIndex, int pageSize) {
        int total = this.userDao.queryPageCount(nameFilter);
        QueryUserResponse response = new QueryUserResponse();
        response.setTotal(total);
        if (total > 0) {
            List rows = this.userDao.queryPageDetail(nameFilter, (pageIndex - 1) * pageSize, pageSize);
            Iterator var7 = rows.iterator();

            while (var7.hasNext()) {
                UserDto dto = (UserDto) var7.next();
                UserInfo user = new UserInfo();
                user.setId(dto.id);
                user.setAccount(dto.account);
                user.setCreateTime(dto.createTime);
                user.setEnabled(dto.enabled);
                user.setName(dto.name);
                user.setPushUrl(dto.pushUrl);
                user.setRemark(dto.remark);
                user.setServiceEndTime(dto.serviceEndDate);
                user.setServiceStartTime(dto.serviceStartDate);
                response.getUsers().add(user);
            }
        }

        return response;
    }

    @Transactional
    public void delete(String id) {
        this.userDao.deleteDeviceInUser(id);
        this.userDao.deleteRoleInUser(id);
        this.userDao.delete(id);
        Pushers.getCurrent().updatePusher(id, (String) null);
    }

    @Transactional
    public void authorize(String roleId, String userId, boolean allow) {
        if (allow) {
            this.userDao.assignRole(userId, roleId);
        } else {
            this.userDao.removeRole(userId, roleId);
        }

    }

    public boolean exists(String account, String id, boolean checkId) {
        return checkId ? this.userDao.existOutId(account, id) : this.userDao.exist(account);
    }

    public UserRolesResponse getRoles(String userId) {
        UserRolesResponse response = new UserRolesResponse();
        List roles = this.userDao.getRoles(userId);
        response.setRoles(roles);
        response.setUserId(userId);
        return response;
    }

    public HashMap getPushUrls() {
        HashMap map = new HashMap();
        List rows = this.userDao.getPushUrls();
        Iterator var3 = rows.iterator();

        while (var3.hasNext()) {
            UserPushUrlDto dto = (UserPushUrlDto) var3.next();
            map.put(dto.id, dto.pushUrl);
        }

        return map;
    }
}
