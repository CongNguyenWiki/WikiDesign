package com.skypremiuminternational.app.domain.models.products;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Unique implements Serializable {

  @SerializedName("labels")
  private List<String> laBels;

  public List<String> getLaBels() {
    return laBels;
  }

  public void setLaBels(List<String> laBels) {
    this.laBels = laBels;
  }
}
