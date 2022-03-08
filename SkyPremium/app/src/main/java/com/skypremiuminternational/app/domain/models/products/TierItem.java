package com.skypremiuminternational.app.domain.models.products;

import com.google.gson.annotations.SerializedName;

public class TierItem {
  /**
   * {
   *                     "customer_group_id": "15",
   *                     "value": "20.0000",
   *                     "qty": 1,
   *                     "group_code": "Platinum Member"
   *                 }
   */

  @SerializedName("customer_group_id")
  private String customerGroupId;
  @SerializedName("value")
  private String value;
  @SerializedName("group_code")
  private String groupCode;


  public String getCustomerGroupId() {
    return customerGroupId;
  }

  public void setCustomerGroupId(String customerGroupId) {
    this.customerGroupId = customerGroupId;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getGroupCode() {
    return groupCode;
  }

  public void setGroupCode(String groupCode) {
    this.groupCode = groupCode;
  }
}
