package mmp.gps.monitor.service;

import mmp.gps.common.util.*;
import mmp.gps.common.enums.UserKinds;
import mmp.gps.common.enums.UserOptions;
import mmp.gps.domain.monitor.MonitorInfo;
import mmp.gps.domain.role.Role;
import mmp.gps.domain.role.RoleInfo;
import mmp.gps.domain.user.User;
import mmp.gps.domain.user.UserInfo;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.dao.IUserDao;
import mmp.gps.monitor.util.MD5;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户服务类
 */
@Service
public class UserService {
    @Autowired
    private IUserDao userDao;


    public Page<UserInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
        Page<UserInfo> page = new Page<UserInfo>();
        int total = userDao.queryPageCount(companyId, filter);
        if (total > 0) {
            page.total = total;
            List<UserInfo> users = userDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
            page.rows.addAll(users);
            // for (UserInfoDto dto : users) {
            //     UserInfo info = new UserInfo();
            //     info.setServiceStartDate(dto.serviceStartDate);
            //     info.setServiceEndDate(dto.serviceEndDate);
            //     info.setEnable(dto.enable);
            //     info.setId(dto.id);
            //     info.setName(dto.name);
            //     info.setPhone(dto.phone);
            //     info.setRemark(dto.remark);
            //     info.setCreateTime(dto.createTime);
            //     page.rows.add(info);
            // }
        }

        return page;
    }

    @RequiresPermissions("baseinfo.user.create")
    @Log(name = "创建新的用户")
    @Transactional
    public void create(User user) {
        user.setId(ObjectId.next());
        user.setKind(UserKinds.CompanyUser.getIndex());


        // UserDto dto = new UserDto();
        // dto.id = user.getId();
        // dto.kind = user.getKind();
        // dto.companyId = user.getCompanyId();
        // dto.account = user.getAccount();
        // // 写死了
        // dto.password = "888888";
        // dto.contact = user.getContact();
        // dto.email = user.getEmail();
        // dto.enable = user.isEnable();
        // dto.name = user.getName();
        // dto.phone = user.getPhone();
        // dto.pid = user.getPid();
        // dto.remark = user.getRemark();
        // dto.serviceEndDate = user.getServiceEndDate();
        // dto.serviceStartDate = user.getServiceStartDate();
        doCreate(user);
        userDao.create(user);
    }


    public User doCreate(User user) {

        String credentials = "888888";

        user.setPassword(credentials);

        credentials = MD5.doMD5(user);
        user.setPassword(credentials);
        return user;

    }

    @RequiresPermissions("baseinfo.user.update")
    @Log(name = "修改用户资料")
    @Transactional
    public void update(User user) {
        // UserDto dto = new UserDto();
        // dto.id = user.getId();
        // dto.kind = user.getKind();
        // dto.companyId = user.getCompanyId();
        // dto.account = user.getAccount();
        // dto.contact = user.getContact();
        // dto.email = user.getEmail();
        // dto.enable = user.isEnable();
        // dto.name = user.getName();
        // dto.phone = user.getPhone();
        // dto.pid = user.getPid();
        // dto.remark = user.getRemark();
        // dto.serviceEndDate = user.getServiceEndDate();
        // dto.serviceStartDate = user.getServiceStartDate();
        // dto.editTime = user.getEditTime();
        doCreate(user);
        int rows = userDao.update(user);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    @RequiresPermissions("baseinfo.user.delete")
    @Log(name = "删除用户资料")
    @Transactional
    public void delete(String id) {
        userDao.deleteUser(id);
        userDao.deleteInfo(id);
        userDao.deleteMaplayer(id);
        userDao.deleteTextmessage(id);
    }

    public User fetch(String id) {
        User user = userDao.fetch(id);
        // User user = new User();
        // user.setKind(dto.kind);
        // user.setAccount(dto.account);
        // user.setContact(dto.contact);
        // user.setCreateTime(dto.createTime);
        // user.setEditTime(dto.editTime);
        // user.setEmail(dto.email);
        // user.setEnable(dto.enable);
        // user.setId(dto.id);
        // user.setName(dto.name);
        // user.setPhone(dto.phone);
        // user.setCompanyId(dto.companyId);
        // user.setRemark(dto.remark);
        // user.setServiceEndDate(dto.serviceEndDate);
        // user.setServiceStartDate(dto.serviceStartDate);

        return user;
    }

    public boolean exist(String account) {
        return userDao.exist(account);
    }

    public boolean exist(String account, String id) {
        return userDao.existOutId(account, id);
    }

    public Page<MonitorInfo> getTargets(String companyId, String filter, int pageIndex, int pageSize) {
        int total = userDao.targetsPageCount(companyId, filter);
        List<MonitorInfo> rows = userDao.targetsPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
        Page<MonitorInfo> monitors = new Page<MonitorInfo>();
        monitors.total = total;
        monitors.rows.addAll(rows);
        // for (MonitorInfoDto dto : rows) {
        //     MonitorInfo info = new MonitorInfo();
        //     info.setId(dto.id);
        //     info.setName(dto.name);
        //     info.setType(dto.type);
        //     info.setRemark(dto.remark);
        //
        //     monitors.rows.add(info);
        // }

        return monitors;
    }

    public List<MonitorInfo> assignedMonitors(String userId, String companyId) {
        List<MonitorInfo> monitors = userDao.assignedMonitors(userId, companyId);
        // List<MonitorInfo> monitors = new ArrayList<MonitorInfo>();

        // for (MonitorInfoDto dto : list) {
        //     MonitorInfo info = new MonitorInfo();
        //     info.setId(dto.id);
        //     info.setName(dto.name);
        //     info.setType(dto.type);
        //     info.setRemark(dto.remark);
        //
        //     monitors.add(info);
        // }

        return monitors;
    }


    @RequiresPermissions("baseinfo.user.addMonitors")
    @Log(name = "用户分配监控对象")
    @Transactional
    public void addMonitors(String userId, List<String> monitors) {


        List<KeyValue> rows = monitors.parallelStream().map(id -> {
            KeyValue row = new KeyValue();
            row.setKey(userId);
            row.setValue(id);
            return row;
        }).collect(Collectors.toList());

        rows.forEach(System.out::println);

        // List<KeyValue> rows = new ArrayList<KeyValue>();
        // for (String id : monitors) {
        //     KeyValue row = new KeyValue();
        //     row.setKey(userId);
        //     row.setValue(id);
        //
        //     rows.add(row);
        // }
        userDao.addMonitors(rows);
    }


    @RequiresPermissions("baseinfo.user.removeMonitor")
    @Log(name = "用户解除监控对象")
    @Transactional
    public void removeMonitor(String userId, String targetId) {
        userDao.removeMonitor(userId, targetId);
    }

    public List<RoleInfo> assignedRoles(String userId) {
        List<Role> list = userDao.assignedRoles(userId);
        List<RoleInfo> monitors = new ArrayList<RoleInfo>();
        for (Role dto : list) {
            RoleInfo info = new RoleInfo();
            BeanUtils.copyProperties(dto, info);
            // info.setId(dto.id);
            // info.setName(dto.name);
            // info.setRemark(dto.remark);

            monitors.add(info);
        }

        return monitors;
    }

    @RequiresPermissions("baseinfo.user.addRoles")
    @Log(name = "用户绑定角色")
    @Transactional
    public void addRoles(String userId, List<String> roles) {
        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (String id : roles) {
            KeyValue row = new KeyValue();
            row.setKey(userId);
            row.setValue(id);

            rows.add(row);
        }
        userDao.addRoles(rows);
    }

    @RequiresPermissions("baseinfo.user.removeRole")
    @Log(name = "用户解除角色")
    @Transactional
    public void removeRole(String userId, String roleId) {
        userDao.removeRole(userId, roleId);
    }

    @SuppressWarnings("unchecked")
    public Map<Object, Object> getUserOptions(String userId, UserOptions type) {
        Map<Object, Object> values = new Hashtable<Object, Object>();
        String json = userDao.getUserOptions(userId, type.getIndex());
        values = JsonMapper.toObject(json, values.getClass());
        return values;
    }

    public void saveUserOptions(String userId, UserOptions type, Object values) {
        String json = JsonMapper.toJson(values);
        userDao.saveUserOptions(userId, type.getIndex(), json);
    }
}
