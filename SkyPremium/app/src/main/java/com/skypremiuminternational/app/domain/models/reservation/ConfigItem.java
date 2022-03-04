package com.skypremiuminternational.app.domain.models.reservation;

import com.google.gson.annotations.SerializedName;


public class ConfigItem {


  @SerializedName("authen_code")
  String authenCode;
  @SerializedName("partner_code")
  String partnerCode;
  @SerializedName("widget_id")
  String widgetId;
  @SerializedName("domain_hgw")
  String domainHgw;

  public String getAuthenCode() {
    return authenCode;
  }

  public void setAuthenCode(String authenCode) {
    this.authenCode = authenCode;
  }

  public String getPartnerCode() {
    return partnerCode;
  }

  public void setPartnerCode(String partnerCode) {
    this.partnerCode = partnerCode;
  }

  public String getWidgetId() {
    return widgetId;
  }

  public void setWidgetId(String widgetId) {
    this.widgetId = widgetId;
  }

  public String getDomainHgw() {
    return domainHgw;
  }

  public void setDomainHgw(String domainHgw) {
    this.domainHgw = domainHgw;
  }
}
