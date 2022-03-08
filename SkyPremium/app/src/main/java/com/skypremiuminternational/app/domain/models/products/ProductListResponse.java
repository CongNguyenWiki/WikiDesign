package com.skypremiuminternational.app.domain.models.products;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductListResponse implements Serializable {

  @SerializedName("total_count")
  private int totalCount;

  @SerializedName("items")
  private List<ProductItem> items;
//
//  @SerializedName("search_criteria")
//  private SearchCriteria searchCriteria;

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setItems(List<ProductItem> items) {
    this.items = items;
  }

  public List<ProductItem> getItems() {
    return items;
  }

//  public SearchCriteria getSearchCriteria() {
//    return searchCriteria;
//  }
//  public void setSearchCriteria(SearchCriteria searchCriteria) {
//    this.searchCriteria = searchCriteria;
//  }


}