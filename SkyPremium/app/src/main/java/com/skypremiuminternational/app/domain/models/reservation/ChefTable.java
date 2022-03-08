package com.skypremiuminternational.app.domain.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChefTable implements Serializable {

  @SerializedName("loyalty_cheftable")
  String loyaltyCheftable;
  @SerializedName("cancelled_policy_cheftable")
  String cancelledPolicyCheftable;
  @SerializedName("reservation_image_detail")
  String reservation_image_detail;


  public String getLoyaltyCheftable() {
    return loyaltyCheftable;
  }

  public void setLoyaltyCheftable(String loyaltyCheftable) {
    this.loyaltyCheftable = loyaltyCheftable;
  }

  public String getCancelledPolicyCheftable() {
    return cancelledPolicyCheftable;
  }

  public void setCancelledPolicyCheftable(String cancelledPolicyCheftable) {
    this.cancelledPolicyCheftable = cancelledPolicyCheftable;
  }

  public String getReservation_image_detail() {
    return reservation_image_detail;
  }

  public void setReservation_image_detail(String reservation_image_detail) {
    this.reservation_image_detail = reservation_image_detail;
  }
}
