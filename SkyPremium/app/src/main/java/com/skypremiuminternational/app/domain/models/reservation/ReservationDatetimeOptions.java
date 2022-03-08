package com.skypremiuminternational.app.domain.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReservationDatetimeOptions implements Serializable {

  @SerializedName("reservation_date_options_first")
  String reservationDateOptionsFirst;
  @SerializedName("reservation_date_options_second")
  String reservationDateOptionsSecond;
  @SerializedName("reservation_date_options_third")
  String reservationDateOptionsThird;
  @SerializedName("reservation_time_options_first")
  String reservationTimeOptionsFirst;
  @SerializedName("reservation_time_options_second")
  String reservationTimeOptionsSecond;
  @SerializedName("reservation_time_options_third")
  String reservationTimeOptionsThird;

  public String getReservationDateOptionsFirst() {
    return reservationDateOptionsFirst;
  }

  public void setReservationDateOptionsFirst(String reservationDateOptionsFirst) {
    this.reservationDateOptionsFirst = reservationDateOptionsFirst;
  }

  public String getReservationDateOptionsSecond() {
    return reservationDateOptionsSecond;
  }

  public void setReservationDateOptionsSecond(String reservationDateOptionsSecond) {
    this.reservationDateOptionsSecond = reservationDateOptionsSecond;
  }

  public String getReservationDateOptionsThird() {
    return reservationDateOptionsThird;
  }

  public void setReservationDateOptionsThird(String reservationDateOptionsThird) {
    this.reservationDateOptionsThird = reservationDateOptionsThird;
  }

  public String getReservationTimeOptionsFirst() {
    return reservationTimeOptionsFirst;
  }

  public void setReservationTimeOptionsFirst(String reservationTimeOptionsFirst) {
    this.reservationTimeOptionsFirst = reservationTimeOptionsFirst;
  }

  public String getReservationTimeOptionsSecond() {
    return reservationTimeOptionsSecond;
  }

  public void setReservationTimeOptionsSecond(String reservationTimeOptionsSecond) {
    this.reservationTimeOptionsSecond = reservationTimeOptionsSecond;
  }

  public String getReservationTimeOptionsThird() {
    return reservationTimeOptionsThird;
  }

  public void setReservationTimeOptionsThird(String reservationTimeOptionsThird) {
    this.reservationTimeOptionsThird = reservationTimeOptionsThird;
  }
}
