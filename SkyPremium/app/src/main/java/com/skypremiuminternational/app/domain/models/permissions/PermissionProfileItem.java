package com.skypremiuminternational.app.domain.models.permissions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PermissionProfileItem {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("nav_item")
    @Expose
    private String navItem;
    @SerializedName("permission_can_view")
    @Expose
    private String permissionCanView;
    @SerializedName("permission_can_use")
    @Expose
    private String getPermissionCanUse;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNavItem() {
        return navItem;
    }

    public void setNavItem(String navItem) {
        this.navItem = navItem;
    }

    public String getPermissionCanView() {
        return permissionCanView;
    }

    public void setPermissionCanView(String permissionCanView) {
        this.permissionCanView = permissionCanView;
    }

    public String getGetPermissionCanUse() {
        return getPermissionCanUse;
    }

    public void setGetPermissionCanUse(String getPermissionCanUse) {
        this.getPermissionCanUse = getPermissionCanUse;
    }
}
