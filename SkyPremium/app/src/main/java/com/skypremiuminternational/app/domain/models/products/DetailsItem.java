package com.skypremiuminternational.app.domain.models.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.skypremiuminternational.app.domain.models.CustomAttribute;


import java.io.Serializable;
import java.util.List;

/**
 * Created by sandi on 10/24/17.
 */

public class DetailsItem implements Serializable {

  @SerializedName("id")
  @Expose
  public Integer id;
  @SerializedName("sku")
  @Expose
  public String sku;
  @SerializedName("name")
  @Expose
  public String name;
  @SerializedName("price")
  @Expose
  public String price;
  @SerializedName("custom_attributes")
  @Expose
  public List<CustomAttributesItem> customAttributes;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public List<CustomAttributesItem> getCustomAttributes() {
    return customAttributes;
  }

  public void setCustomAttributes(List<CustomAttributesItem> customAttributes) {
    this.customAttributes = customAttributes;
  }
}
