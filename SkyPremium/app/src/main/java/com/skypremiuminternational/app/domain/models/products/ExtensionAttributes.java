package com.skypremiuminternational.app.domain.models.products;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.skypremiuminternational.app.domain.models.StockItem;
import com.skypremiuminternational.app.domain.models.favourite.DisplayReservationsCategory;

import java.io.Serializable;

/**
 * Created by sandi on 10/24/17.
 */

public class ExtensionAttributes implements Serializable {

  @SerializedName("stock_item")
  @Expose
  public StockItem stockItem;
  @SerializedName("summary_rating")
  @Expose
  public float summaryRating;
  @SerializedName("rating_dollar")
  @Expose
  public String ratingDollar;
  @SerializedName("count_rating")
  @Expose
  public String countRating;
  @SerializedName("unique")
  @Expose
  public Unique unique;
  @SerializedName("link_product_detail")
  @Expose
  public String linkProductDetail;
  @SerializedName("date_time_event_slot")
  @Expose
  public String dateTimeEventSlot;
  @SerializedName("is_available_event_slot")
  @Expose
  public boolean isAvailableEventSlot;
//  @SerializedName("article")
//  @Expose
//  private Article article;
  @SerializedName("display_reservations")
  @Expose
  public boolean displayReservations;
  @SerializedName("display_category")
  @Expose
  public String displayCategory;
  @SerializedName("grant_product_permissions_view")
  private Boolean grantProductPermissionsView = true;
  @SerializedName("is_exist_product_permissions")
  private Boolean isExistProductPermissions;
  @SerializedName("grant_product_permissions_use")
  private Boolean grantProductPermissionsUse = true;
  @SerializedName("grant_category_permissions_view")
  private Boolean grantCategoryPermissionsView  = true;
  @SerializedName("grant_category_permissions_use")
  private Boolean grantCategoryPermissionsUse = true;
  @SerializedName("is_gift_order")
  @Expose
  private int  isGiftOrder;
  @SerializedName("total_sky_dollar_earn")
  @Expose
  private double  totalSkyDollarEarn;
  @SerializedName("totalloyalty")
  @Expose
  private String  totalloyalty;

  @SerializedName("display_reservations_category")
  @Expose
  public DisplayReservationsCategory displayReservationsCategory;
  @SerializedName("is_in_stock")
  private String isInStock;

  public boolean isInStock() {
    if(isInStock!=null&&!TextUtils.isEmpty(isInStock)){
      if(isInStock.equalsIgnoreCase("1")){
        return true;
      }else {
        return false;
      }
    }
    else{
      return false;
    }
  }

  public void setInStock(boolean inStock) {
    if(inStock)
      isInStock = "1";
    else
      isInStock = "0";
  }

  public Boolean isExistProductPermissions() {
    return isExistProductPermissions != null ? isExistProductPermissions : true ;
  }

  public void setExistProductPermissions(Boolean existProductPermissions) {
    isExistProductPermissions = existProductPermissions;
  }

  public Boolean isGrantProductPermissionsView() {
    return grantProductPermissionsView != null ? grantProductPermissionsView : true ;
  }

  public void setGrantProductPermissionsView(Boolean grantProductPermissionsView) {
    this.grantProductPermissionsView = grantProductPermissionsView;
  }

  public Boolean isGrantProductPermissionsUse() {
    return grantProductPermissionsUse != null ? grantProductPermissionsUse : true ;
  }

  public void setGrantProductPermissionsUse(Boolean grantProductPermissionsUse) {
    this.grantProductPermissionsUse = grantProductPermissionsUse;
  }

  public Boolean isGrantCategoryPermissionsView() {
    return grantCategoryPermissionsView != null ? grantCategoryPermissionsView : true;
  }

  public void setGrantCategoryPermissionsView(Boolean grantCategoryPermissionsView) {
    this.grantCategoryPermissionsView = grantCategoryPermissionsView;
  }

  public Boolean isGrantCategoryPermissionsUse() {
    return grantCategoryPermissionsUse != null ? grantCategoryPermissionsUse : true ;
  }

  public void setGrantCategoryPermissionsUse(Boolean grantCategoryPermissionsUse) {
    this.grantCategoryPermissionsUse = grantCategoryPermissionsUse;
  }



  public StockItem getStockItem() {
    return stockItem;
  }

  public void setStockItem(StockItem stockItem) {
    this.stockItem = stockItem;
  }

  public float getSummaryRating() {
    return summaryRating;
  }

  public void setSummaryRating(float summaryRating) {
    this.summaryRating = summaryRating;
  }

  public boolean isDisplayReservations() {
    return displayReservations;
  }

  public void setDisplayReservations(boolean displayReservations) {
    this.displayReservations = displayReservations;
  }

  public Unique getUnique() {
    return unique;
  }

  public void setUnique(Unique unique) {
    this.unique = unique;
  }

  public DisplayReservationsCategory getDisplayReservationsCategory() {
    return displayReservationsCategory;
  }

  public void setDisplayReservationsCategory(DisplayReservationsCategory displayReservationsCategory) {
    this.displayReservationsCategory = displayReservationsCategory;
  }

  public String getRatingDollar() {
    return ratingDollar;
  }

  public void setRatingDollar(String ratingDollar) {
    this.ratingDollar = ratingDollar;
  }



  public String getLinkProductDetail() {
    return linkProductDetail;
  }

  public void setLinkProductDetail(String linkProductDetail) {
    this.linkProductDetail = linkProductDetail;
  }

  public String getCountRating() {
    return countRating;
  }

  public void setCountRating(String countRating) {
    this.countRating = countRating;
  }

  //
//  public Article getArticle() {
//    return article;
//  }
//
//  public void setArticle(Article article) {
//    this.article = article;
//  }

  public String getDateTimeEventSlot() {
    return dateTimeEventSlot;
  }

  public void setDateTimeEventSlot(String dateTimeEventSlot) {
    this.dateTimeEventSlot = dateTimeEventSlot;
  }

  public boolean isAvailableEventSlot() {
    return isAvailableEventSlot;
  }

  public void setAvailableEventSlot(boolean availableEventSlot) {
    isAvailableEventSlot = availableEventSlot;
  }

  public String getDisplayCategory() {
    return displayCategory;
  }

  public void setDisplayCategory(String displayCategory) {
    this.displayCategory = displayCategory;
  }

  public int getIsGiftOrder() {
    return isGiftOrder;
  }

  public void setIsGiftOrder(int isGiftOrder) {
    this.isGiftOrder = isGiftOrder;
  }

  public double getTotalSkyDollarEarn() {
    return totalSkyDollarEarn;
  }

  public void setTotalSkyDollarEarn(double totalSkyDollarEarn) {
    this.totalSkyDollarEarn = totalSkyDollarEarn;
  }

  public String getTotalloyalty() {
    return totalloyalty;
  }

  public void setTotalloyalty(String totalloyalty) {
    this.totalloyalty = totalloyalty;
  }
}
