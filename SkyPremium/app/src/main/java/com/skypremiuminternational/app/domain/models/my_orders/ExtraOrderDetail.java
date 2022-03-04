package com.skypremiuminternational.app.domain.models.my_orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;



public class ExtraOrderDetail implements Serializable {
  @SerializedName("success")
  @Expose
  private Boolean success;
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("extra_fee")
  @Expose
  private List<ExtraFee> extraFee = null;

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<ExtraFee> getExtraFee() {
    return extraFee;
  }

  public void setExtraFee(
      List<ExtraFee> extraFee) {
    this.extraFee = extraFee;
  }
}
