package com.skypremiuminternational.app.domain.models.permissions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PermissionCheckoutItem implements Serializable {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("permission_checkout_can_view")
    @Expose
    private String permission_checkout_can_view;
    @SerializedName("permission_checkout_can_use")
    @Expose
    private String permission_checkout_can_use;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPermission_checkout_can_view() {
        return permission_checkout_can_view;
    }

    public void setPermission_checkout_can_view(String permission_checkout_can_view) {
        this.permission_checkout_can_view = permission_checkout_can_view;
    }

    public String getPermission_checkout_can_use() {
        return permission_checkout_can_use;
    }

    public void setPermission_checkout_can_use(String permission_checkout_can_use) {
        this.permission_checkout_can_use = permission_checkout_can_use;
    }
}
