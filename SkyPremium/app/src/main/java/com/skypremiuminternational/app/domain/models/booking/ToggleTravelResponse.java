package com.skypremiuminternational.app.domain.models.booking;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ToggleTravelResponse implements Serializable {



  @SerializedName("enable")
  boolean isEnable = false;
  @SerializedName("base_url")
  String baseUrl;


  public boolean isEnable() {
    return isEnable;
  }

  public void setEnable(boolean enable) {
    isEnable = enable;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }
}
