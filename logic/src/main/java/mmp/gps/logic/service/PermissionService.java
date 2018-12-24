package mmp.gps.logic.service;


import mmp.gps.common.util.ObjectId;
import mmp.gps.domain.permission.*;
import mmp.gps.logic.cache.Authorizes;
import mmp.gps.logic.component.ServiceComponents;
import mmp.gps.logic.dao.IPermissionDao;
import mmp.gps.logic.portal.ServiceMethodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Transactional
    public void create(CreatePermissionRequest request) {
        if (this.permissionDao.exsits(request.getName())) {
            throw new RuntimeException("此权限已存在");
        }
        PermissionDto dto = new PermissionDto();
        dto.id = ObjectId.next();
        dto.name = request.getName();
        dto.remark = request.getRemark();

        String amdinRoleId = this.permissionDao.getAdminRoleId();
        this.permissionDao.adminRoleAuthorize(amdinRoleId, dto.id);
        this.permissionDao.create(dto);

        Authorizes.authorize(request.getName(), "admin", true);
    }

    public FetchPermissionResponse fetch(String id) {
        PermissionDto dto = this.permissionDao.fetch(id);
        FetchPermissionResponse response = new FetchPermissionResponse();
        response.setId(dto.id);
        response.setName(dto.name);
        response.setRemark(dto.remark);
        response.setEditTime(dto.editTime);

        return response;
    }

    @Transactional
    public void delete(String id) {
        PermissionDto old = this.permissionDao.fetch(id);
        Authorizes.removePermission(old.name);
        this.permissionDao.deleteAuthorize(id);
        this.permissionDao.delete(id);
    }

    public ListPermissionResponse list() {
        ListPermissionResponse response = new ListPermissionResponse();

        List<PermissionDto> rows = this.permissionDao.list();
        response.setTotal(rows.size());

        ArrayList<PermissionInfo> permissions = new ArrayList();
        for (PermissionDto dto : rows) {
            PermissionInfo info = new PermissionInfo();
            info.setId(dto.id);
            info.setName(dto.name);
            info.setRemark(dto.remark);

            permissions.add(info);
        }
        response.setPermissions(permissions);

        return response;
    }

    public boolean exists(String key, String id, boolean checkId) {
        if (checkId) {
            return this.permissionDao.exsitsOutId(key, id);
        }
        return this.permissionDao.exsits(key);
    }

    public CollectPermissionResponse collect() {
        CollectPermissionResponse response = new CollectPermissionResponse();
        ArrayList<ServiceMethodInfo> list = ServiceComponents.getServiceMethods();

        ArrayList<CollectPermissionInfo> query = new ArrayList();
        for (ServiceMethodInfo method : list) {
            CollectPermissionInfo info = new CollectPermissionInfo();
            info.setAllowAnoumous(method.isAllowAnoumous());
            info.setDecription(method.getDescription());
            info.setName(method.getName());
            query.add(info);
        }
//        Collections.sort(query, new PermissionService .1 (this));

        response.setPermissions(query);
        return response;
    }
}
