package com.skypremiuminternational.app.domain.models.creditCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {

  @SerializedName("value")
  @Expose
  private String value;
  @SerializedName("label")
  @Expose
  private String label;
  @SerializedName("regions")
  @Expose
  private List<Region> regions;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<Region> getRegions() {
    return regions;
  }

  public void setRegions(List<Region> regions) {
    this.regions = regions;
  }
}
