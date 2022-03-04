package com.skypremiuminternational.app.domain.models.system;

import com.google.gson.annotations.SerializedName;

public class OneSignalConfig {

  @SerializedName("app_id")
  String appID;
  @SerializedName("rest_api_key")
  String restApiKey;
  @SerializedName("template_id")
  String templateID;


  public String getAppID() {
    return appID;
  }

  public void setAppID(String appID) {
    this.appID = appID;
  }

  public String getRestApiKey() {
    return restApiKey;
  }

  public void setRestApiKey(String restApiKey) {
    this.restApiKey = restApiKey;
  }

  public String getTemplateID() {
    return templateID;
  }

  public void setTemplateID(String templateID) {
    this.templateID = templateID;
  }
}
