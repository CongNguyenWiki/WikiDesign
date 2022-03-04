package com.skypremiuminternational.app.domain.models.creditCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetFormDataCreditCardResponse implements Serializable {

  @SerializedName("brands")
  @Expose
  private List<Brand> brands;
  @SerializedName("month")
  @Expose
  private List<Month> month;
  @SerializedName("year")
  @Expose
  private List<Year> year;
  @SerializedName("countries")
  @Expose
  private List<Country> countries;

  public List<Brand> getBrands() {
    return brands;
  }

  public void setBrands(List<Brand> brands) {
    this.brands = brands;
  }

  public List<Month> getMonth() {
    return month;
  }

  public void setMonth(List<Month> month) {
    this.month = month;
  }

  public List<Year> getYear() {
    return year;
  }

  public void setYear(List<Year> year) {
    this.year = year;
  }

  public List<Country> getCountries() {
    return countries;
  }

  public void setCountries(List<Country> countries) {
    this.countries = countries;
  }
}
