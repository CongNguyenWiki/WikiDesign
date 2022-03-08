package com.skypremiuminternational.app.domain.models.mymembership_statement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UrlResponse implements Serializable {

  @SerializedName("url_cancel")
  @Expose
  private String urlCancel;
  @SerializedName("url_upgrade")
  @Expose
  private String urlUpgrade;
  @SerializedName("url_downgrade")
  @Expose
  private String urlDowngrade;
  @SerializedName("url_renewal")
  @Expose
  private String urlRenewal;

  public String getUrlCancel() {
    return urlCancel;
  }

  public void setUrlCancel(String urlCancel) {
    this.urlCancel = urlCancel;
  }

  public String getUrlUpgrade() {
    return urlUpgrade;
  }

  public void setUrlUpgrade(String urlUpgrade) {
    this.urlUpgrade = urlUpgrade;
  }

  public String getUrlDowngrade() {
    return urlDowngrade;
  }

  public void setUrlDowngrade(String urlDowngrade) {
    this.urlDowngrade = urlDowngrade;
  }

  public String getUrlRenewal() {
    return urlRenewal;
  }

  public void setUrlRenewal(String urlRenewal) {
    this.urlRenewal = urlRenewal;
  }
}
