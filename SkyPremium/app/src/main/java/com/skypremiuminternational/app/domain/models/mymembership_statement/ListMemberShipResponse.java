package com.skypremiuminternational.app.domain.models.mymembership_statement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListMemberShipResponse implements Serializable {

  @SerializedName("record_count")
  @Expose
  private int record_count;
  @SerializedName("data")
  @Expose
  private List<MyMembershipStatementItem> data;
  @SerializedName("code")
  @Expose
  private String code;

  public int getRecord_count() {
    return record_count;
  }

  public void setRecord_count(int record_count) {
    this.record_count = record_count;
  }

  public List<MyMembershipStatementItem> getData() {
    return data;
  }

  public void setData(List<MyMembershipStatementItem> data) {
    this.data = data;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
