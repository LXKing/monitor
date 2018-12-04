package mmp.gps.monitor.service;


import mmp.gps.domain.permission.Permission;
import mmp.gps.monitor.dao.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    public List<Permission> listPermission() {
        return permissionMapper.listPermission();
    }


    public Integer deleteByPrimaryKey(String permissionId) {
        return permissionMapper.deleteByPrimaryKey(permissionId);
    }

    public Integer insertSelective(Permission permission) {
        return permissionMapper.insertSelective(permission);
    }

    public Integer updateByPrimaryKeySelective(Permission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }


}
