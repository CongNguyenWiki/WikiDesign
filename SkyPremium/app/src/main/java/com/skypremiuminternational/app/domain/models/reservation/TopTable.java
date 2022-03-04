package com.skypremiuminternational.app.domain.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TopTable implements Serializable {

  @SerializedName("loyalty_toptable")
  String loyaltyToptable;
  @SerializedName("cancelled_policy_toptable")
  String cancelledPolicyToptable;
  @SerializedName("grand_total")
  String grandTotal;
  @SerializedName("website")
  String website;
  @SerializedName("opening_hour")
  String openingHour;
  @SerializedName("text_reservation_status")
  String textReservationStatus;
  @SerializedName("reservation_time")
  String reservation_time;
  @SerializedName("price")
  String price;



  public String getLoyaltyToptable() {
    return loyaltyToptable;
  }

  public void setLoyaltyToptable(String loyaltyToptable) {
    this.loyaltyToptable = loyaltyToptable;
  }

  public String getCancelledPolicyToptable() {
    return cancelledPolicyToptable;
  }

  public void setCancelledPolicyToptable(String cancelledPolicyToptable) {
    this.cancelledPolicyToptable = cancelledPolicyToptable;
  }

  public String getGrandTotal() {
    return grandTotal;
  }

  public void setGrandTotal(String grandTotal) {
    this.grandTotal = grandTotal;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getOpeningHour() {
    return openingHour;
  }

  public void setOpeningHour(String openingHour) {
    this.openingHour = openingHour;
  }

  public String getTextReservationStatus() {
    return textReservationStatus;
  }

  public void setTextReservationStatus(String textReservationStatus) {
    this.textReservationStatus = textReservationStatus;
  }

  public String getReservation_time() {
    return reservation_time;
  }

  public void setReservation_time(String reservation_time) {
    this.reservation_time = reservation_time;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
