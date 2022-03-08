package com.skypremiuminternational.app.data.network.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ActivityLogRequest implements Serializable {
  @SerializedName("device")
  String device;

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }
}
