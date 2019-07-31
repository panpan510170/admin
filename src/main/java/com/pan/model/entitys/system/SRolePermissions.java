package com.pan.model.entitys.system;

import com.pan.model.BaseDO;

public class SRolePermissions extends BaseDO {
    private Long id;

    private Long roleId;

    private Long permissionsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Long permissionsId) {
        this.permissionsId = permissionsId;
    }
}