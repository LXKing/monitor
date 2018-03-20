package com.edata.godp.domain.permission;

import java.util.List;

/**
 * 收集权限总表应答
 */
public class CollectPermissionResponse {
    private List<CollectPermissionInfo> permissions;

    /**
     * 获取权限总表
     */
    public List<CollectPermissionInfo> getPermissions() {
        return permissions;
    }

    /**
     * 设置权限总表
     */
    public void setPermissions(List<CollectPermissionInfo> permissions) {
        this.permissions = permissions;
    }
}
