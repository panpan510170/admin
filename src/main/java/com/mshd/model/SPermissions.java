package com.mshd.model;

import java.util.Date;

public class SPermissions {
    private Long id;

    private String permissionsName;

    private String permissionsUrl;

    private String permissionsImageUrl;

    private Integer serialNumber;

    private Integer type;

    private Long parentId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName == null ? null : permissionsName.trim();
    }

    public String getPermissionsUrl() {
        return permissionsUrl;
    }

    public void setPermissionsUrl(String permissionsUrl) {
        this.permissionsUrl = permissionsUrl == null ? null : permissionsUrl.trim();
    }

    public String getPermissionsImageUrl() {
        return permissionsImageUrl;
    }

    public void setPermissionsImageUrl(String permissionsImageUrl) {
        this.permissionsImageUrl = permissionsImageUrl == null ? null : permissionsImageUrl.trim();
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}