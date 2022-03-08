package com.skypremiuminternational.app.domain.models.permissions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PermissionFooterItem {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("footer_item")
    @Expose
    private String footerItem;
    @SerializedName("permission_can_view")
    @Expose
    private String permissionCanView;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getFooterItem() {
        return footerItem;
    }

    public void setFooterItem(String footerItem) {
        this.footerItem = footerItem;
    }

    public String getPermissionCanView() {
        return permissionCanView;
    }

    public void setPermissionCanView(String permissionCanView) {
        this.permissionCanView = permissionCanView;
    }
}
