package com.skypremiuminternational.app.domain.models.products;

import com.google.gson.annotations.SerializedName;

public class TimeSaleTier {

  @SerializedName("from_date")
  String fromDate;
  @SerializedName("to_date")
  String toDate;

  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }
}
