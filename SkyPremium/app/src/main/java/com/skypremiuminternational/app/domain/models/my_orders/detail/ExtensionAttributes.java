package com.skypremiuminternational.app.domain.models.my_orders.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExtensionAttributes {

    @SerializedName("shipping_assignments")
    private List<ShippingAssignmentsItem> shippingAssignments;
    @SerializedName("thumbnail_image_url")
    private String thumbnailImageUrl;
    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("total_sky_dollar_earn")
    private double totalSkyDollarEarn;
    @SerializedName("is_gift_order")
    @Expose
    private int  isGiftOrder;
    @SerializedName("is_gift_wrapping")
    @Expose
    private int  isGiftWrapping;
    @SerializedName("is_gift_message")
    @Expose
    private int  isGiftMessage;
    @SerializedName("to")
    @Expose
    private String  toValue;
    @SerializedName("from")
    @Expose
    private String  fromValue;
    @SerializedName("message")
    @Expose
    private String  messageValue;
    @SerializedName("gift_summary")
    @Expose
    private String  giftSummary;
    @SerializedName("gift_image")
    @Expose
    private String  giftImage;
    @SerializedName("country_code")
    @Expose
    private String  countryCode;
    @SerializedName("is_apply_coupon_free_shipping")
    @Expose
    private String  isApplyCouponFreeShipping;

  public String getIsApplyCouponFreeShipping() {
    return isApplyCouponFreeShipping;
  }

  public void setIsApplyCouponFreeShipping(String isApplyCouponFreeShipping) {
    this.isApplyCouponFreeShipping = isApplyCouponFreeShipping;
  }

  public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public void setShippingAssignments(List<ShippingAssignmentsItem> shippingAssignments) {
    this.shippingAssignments = shippingAssignments;
  }

  public List<ShippingAssignmentsItem> getShippingAssignments() {
    return shippingAssignments;
  }

  public double getTotalSkyDollarEarn() {
    return totalSkyDollarEarn;
  }

  public void setTotalSkyDollarEarn(double totalSkyDollarEarn) {
    this.totalSkyDollarEarn = totalSkyDollarEarn;
  }

  public String getThumbnailImageUrl() {
    return thumbnailImageUrl;
  }

  public void setThumbnailImageUrl(String thumbnailImageUrl) {
    this.thumbnailImageUrl = thumbnailImageUrl;
  }

  @Override
  public String toString() {
    return
        "ExtensionAttributes{" + "shipping_assignments = '" + shippingAssignments + '\'' + "}";
  }

    public int getIsGiftOrder() {
        return isGiftOrder;
    }

    public void setIsGiftOrder(int isGiftOrder) {
        this.isGiftOrder = isGiftOrder;
    }

    public int getIsGiftWrapping() {
        return isGiftWrapping;
    }

    public void setIsGiftWrapping(int isGiftWrapping) {
        this.isGiftWrapping = isGiftWrapping;
    }

    public int getIsGiftMessage() {
        return isGiftMessage;
    }

    public void setIsGiftMessage(int isGiftMessage) {
        this.isGiftMessage = isGiftMessage;
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getMessageValue() {
        return messageValue;
    }

    public void setMessageValue(String messageValue) {
        this.messageValue = messageValue;
    }


    public String getGiftSummary() {
        return giftSummary;
    }

    public void setGiftSummary(String giftSummary) {
        this.giftSummary = giftSummary;
    }

    public String getGiftImage() {
        return giftImage;
    }

    public void setGiftImage(String giftImage) {
        this.giftImage = giftImage;
    }
}