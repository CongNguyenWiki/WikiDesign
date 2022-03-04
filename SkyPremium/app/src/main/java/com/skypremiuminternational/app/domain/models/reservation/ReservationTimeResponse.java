package com.skypremiuminternational.app.domain.models.reservation;

import com.google.gson.annotations.SerializedName;

public class ReservationTimeResponse {

  @SerializedName("response")
  ReserveTime reserveTime;

  public ReserveTime getReserveTime() {
    return reserveTime;
  }

  public void setReserveTime(ReserveTime reserveTime) {
    this.reserveTime = reserveTime;
  }
}
