package com.skypremiuminternational.app.domain.models.favourite;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.skypremiuminternational.app.domain.models.products.TierItem;
import com.skypremiuminternational.app.domain.models.products.TimeSaleTier;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by aeindraaung on 1/24/18.
 */

public class Product implements Serializable {
  @SerializedName("row_id")
  @Expose
  private String rowId;
  @SerializedName("entity_id")
  @Expose
  private String entityId;
  @SerializedName("created_in")
  @Expose
  private String createdIn;
  @SerializedName("updated_in")
  @Expose
  private String updatedIn;
  @SerializedName("attribute_set_id")
  @Expose
  private String attributeSetId;
  @SerializedName("attribute_set_name")
  @Expose
  private String attributeSetName;
  @SerializedName("type_id")
  @Expose
  private String typeId;
  @SerializedName("sku")
  @Expose
  private String sku;
  @SerializedName("has_options")
  @Expose
  private String hasOptions;
  @SerializedName("required_options")
  @Expose
  private String requiredOptions;
  @SerializedName("events_event_type")
  @Expose
  private String eventsEventType;
  @SerializedName("created_at")
  @Expose
  private String createdAt;
  @SerializedName("updated_at")
  @Expose
  private String updatedAt;
  @SerializedName("price")
  @Expose
  private String price;
  @SerializedName("special_price")
  @Expose
  private String specialPrice;
  @SerializedName("special_from_date")
  @Expose
  private String specialFromDate;
  @SerializedName("special_to_date")
  @Expose
  private String specialToDate;
  @SerializedName("tax_class_id")
  @Expose
  private String taxClassId;
  @SerializedName("final_price")
  @Expose
  private Object finalPrice;
  @SerializedName("minimal_price")
  @Expose
  private String minimalPrice;
  @SerializedName("min_price")
  @Expose
  private String minPrice;
  @SerializedName("max_price")
  @Expose
  private String maxPrice;
  @SerializedName("tier_price")
  @Expose
  private Object tierPrice;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("small_image")
  @Expose
  private String smallImage;
  @SerializedName("thumbnail")
  @Expose
  private String thumbnail;
  @SerializedName("popularity_order")
  @Expose
  private String popularityOrder;
  @SerializedName("visibility")
  @Expose
  private String visibility;
  @SerializedName("partners_benefits")
  @Expose
  private String partnersBenefits;
  @SerializedName("partners_terms_conditions")
  @Expose
  private String partnersTermsConditions;
  @SerializedName("current_server_time")
  @Expose
  private String currentServerTime;
  @SerializedName("deal_discount_type")
  @Expose
  private String dealDiscountType;
  @SerializedName("deal_discount_percentage")
  @Expose
  private String dealDiscountPercentage;
  @SerializedName("deal_value")
  @Expose
  private String dealValue;
  @SerializedName("deal_to_date")
  @Expose
  private String dealToDate;
  @SerializedName("deal_from_date")
  @Expose
  private String dealFromDate;
  @SerializedName("deal_status")
  @Expose
  private String dealStatus;

  @SerializedName("news_from_date")
  @Expose
  private String newsFromDate;
  @SerializedName("news_to_date")
  @Expose
  private String newsToDate;
  @SerializedName("flash_sales")
  @Expose
  private boolean flashSales;
  @SerializedName("request_path")
  @Expose
  private String requestPath;
  @SerializedName("loyalty_value_to_earn")
  @Expose
  private String loyaltyValueToEarn;
  @SerializedName("category_ids")
  @Expose
  private List<String> categoryIds = null;
  @SerializedName("is_in_stock")
  @Expose
  private boolean isInStock;
  @SerializedName("display_platinum_icon")
  @Expose
  private String displayPlatinumIcon;
  @SerializedName("type_price")
  @Expose
  private String typePrice;
  @SerializedName("tier_price_value")
  @Expose
  private List<TierItem> tierPriceValue;
  @SerializedName("time_sales_tier")
  @Expose
  private List<TimeSaleTier> timeSaleTier;
  @SerializedName("button_type")
  @Expose
  private String buttonType;
  @SerializedName("time_sales_status")
  @Expose
  private String timeSalesStatus;
  @SerializedName("message_skydollar")
  @Expose
  private String messageSkyDollar;
//  @SerializedName("media_gallery")
//  @Expose
//  private MediaGallery mediaGallery;
  @SerializedName("media_gallery")
  @Expose
  private MediaGallery mediaGallery;
  @SerializedName("display_price")
  @Expose
  private String displayPrice;
  @SerializedName("enable_review_tab")
  @Expose
  private String enableReviewTab;
  @SerializedName("select_display_category")
  @Expose
  private String selectDisplayCategory;
  @SerializedName("information_unique")
  @Expose
  private String informationUnique;
  @SerializedName("title_tab_benefit_privileges")
  @Expose
  private String titleTabBenefitPrivileges;
  @SerializedName("toptbl_benefits_privileges")
  @Expose
  private String toptbl_benefits_privileges;
  @SerializedName("toptable_cuisine_menu_type")
  @Expose
  private String toptableCuisineMenuType;
  @SerializedName("toptbl_reservation_date")
  @Expose
  private String toptblReservationDate;
  @SerializedName("toptbl_reservation_time")
  @Expose
  private String toptblReservationTime;
  @SerializedName("product_totable_from")
  @Expose
  private String productTotableFrom;
  @SerializedName("product_totable_to")
  @Expose
  private String productTotableTo;
  @SerializedName("cheftbl_short_description")
  @Expose
  private String cheftblShortDescription;
  @SerializedName("cheftbl_awards_accolades")
  @Expose
  private String cheftblAwardsAccolades;
  @SerializedName("cheftbl_menu_description")
  @Expose
  private String cheftbl_menu_description;
  @SerializedName("is_booking_top_table")
  @Expose
  private Boolean isBookingTopTable;
  @SerializedName("status")
  @Expose
  private String status;

  public Boolean getBookingTopTable() {
    return isBookingTopTable;
  }

  public void setBookingTopTable(Boolean isBookingTopTable) {
    this.isBookingTopTable = isBookingTopTable;
  }

  public String getCheftblAwardsAccolades() {
    return cheftblAwardsAccolades;
  }

  public void setCheftblAwardsAccolades(String cheftblAwardsAccolades) {
    this.cheftblAwardsAccolades = cheftblAwardsAccolades;
  }

  public String getCheftblShortDescription() {
    return cheftblShortDescription;
  }

  public void setCheftblShortDescription(String cheftblShortDescription) {
    this.cheftblShortDescription = cheftblShortDescription;
  }

  public String getProductTotableTo() {
    return productTotableTo;
  }

  public void setProductTotableTo(String productTotableTo) {
    this.productTotableTo = productTotableTo;
  }

  public String getProductTotableFrom() {
    return productTotableFrom;
  }

  public void setProductTotableFrom(String productTotableFrom) {
    this.productTotableFrom = productTotableFrom;
  }

  public String getToptblReservationTime() {
    return toptblReservationTime;
  }

  public void setToptblReservationTime(String toptblReservationTime) {
    this.toptblReservationTime = toptblReservationTime;
  }

  public String getToptblReservationDate() {
    return toptblReservationDate;
  }

  public void setToptblReservationDate(String toptblReservationDate) {
    this.toptblReservationDate = toptblReservationDate;
  }

  public String getToptableCuisineMenuType() {
    return toptableCuisineMenuType;
  }

  public void setToptableCuisineMenuType(String toptableCuisineMenuType) {
    this.toptableCuisineMenuType = toptableCuisineMenuType;
  }

  public String getTitleTabBenefitPrivileges() {
    return titleTabBenefitPrivileges;
  }

  public void setTitleTabBenefitPrivileges(String titleTabBenefitPrivileges) {
    this.titleTabBenefitPrivileges = titleTabBenefitPrivileges;
  }

  public String getInformationUnique() {
    return informationUnique;
  }

  public void setInformationUnique(String informationUnique) {
    this.informationUnique = informationUnique;
  }

  public String getSelectDisplayCategory() {
    return selectDisplayCategory;
  }

  public void setSelectDisplayCategory(String selectDisplayCategory) {
    this.selectDisplayCategory = selectDisplayCategory;
  }

  public String getEnableReviewTab() {
    return enableReviewTab;
  }

  public void setEnableReviewTab(String enableReviewTab) {
    this.enableReviewTab = enableReviewTab;
  }

  public String getDisplayPrice() {
    return displayPrice;
  }

  public void setDisplayPrice(String displayPrice) {
    this.displayPrice = displayPrice;
  }

  public MediaGallery getMediaGallery() {

    return mediaGallery;
  }

  public void setMediaGallery(MediaGallery mediaGallery) {
    this.mediaGallery = mediaGallery;
  }



  public String getMessageSkyDollar() {
    return messageSkyDollar;
  }

  public void setMessageSkyDollar(String messageSkyDollar) {
    this.messageSkyDollar = messageSkyDollar;
  }

  public String getTimeSalesStatus() {
    return timeSalesStatus;
  }

  public void setTimeSalesStatus(String timeSalesStatus) {
    this.timeSalesStatus = timeSalesStatus;
  }

  public String getButtonType() {
    return buttonType;
  }

  public void setButtonType(String buttonType) {
    this.buttonType = buttonType;
  }

  public List<TimeSaleTier> getTimeSaleTier() {
    return timeSaleTier;
  }

  public void setTimeSaleTier(List<TimeSaleTier> timeSaleTier) {
    this.timeSaleTier = timeSaleTier;
  }

  public List<TierItem> getTierPriceValue() {
    return tierPriceValue;
  }

  public void setTierPriceValue(List<TierItem> tierPriceValue) {
    this.tierPriceValue = tierPriceValue;
  }

  public String getTypePrice() {
    return typePrice;
  }

  public void setTypePrice(String typePrice) {
    this.typePrice = typePrice;
  }

  public String getDisplayPlatinumIcon() {
    return displayPlatinumIcon;
  }

  public void setDisplayPlatinumIcon(String displayPlatinumIcon) {
    this.displayPlatinumIcon = displayPlatinumIcon;
  }

  public boolean isInStock() {
    return isInStock;
  }

  public void setInStock(boolean inStock) {
    isInStock = inStock;
  }

  public String getLoyaltyValueToEarn() {
    return loyaltyValueToEarn;
  }

  public void setLoyaltyValueToEarn(String loyaltyValueToEarn) {
    this.loyaltyValueToEarn = loyaltyValueToEarn;
  }

  public String getCurrentServerTime() {
    return currentServerTime;
  }

  public void setCurrentServerTime(String currentServerTime) {
    this.currentServerTime = currentServerTime;
  }

  public String getDealDiscountType() {
    return dealDiscountType;
  }

  public void setDealDiscountType(String dealDiscountType) {
    this.dealDiscountType = dealDiscountType;
  }

  public String getDealDiscountPercentage() {
    return dealDiscountPercentage;
  }

  public void setDealDiscountPercentage(String dealDiscountPercentage) {
    this.dealDiscountPercentage = dealDiscountPercentage;
  }

  public String getDealValue() {
    return dealValue;
  }

  public void setDealValue(String dealValue) {
    this.dealValue = dealValue;
  }

  public String getDealToDate() {
    return dealToDate;
  }

  public void setDealToDate(String dealToDate) {
    this.dealToDate = dealToDate;
  }

  public String getDealFromDate() {
    return dealFromDate;
  }

  public void setDealFromDate(String dealFromDate) {
    this.dealFromDate = dealFromDate;
  }

  public String getNewsFromDate() {
    return newsFromDate;
  }

  public void setNewsFromDate(String newsFromDate) {
    this.newsFromDate = newsFromDate;
  }

  public String getNewsToDate() {
    return newsToDate;
  }

  public void setNewsToDate(String newsToDate) {
    this.newsToDate = newsToDate;
  }

  public String getDealStatus() {
    return dealStatus;
  }

  public void setDealStatus(String dealStatus) {
    this.dealStatus = dealStatus;
  }

  public boolean isFlashSales() {
    return flashSales;
  }

  public void setFlashSales(boolean flashSales) {
    this.flashSales = flashSales;
  }

  public String getSpecialPrice() {
    return specialPrice;
  }

  public void setSpecialPrice(String specialPrice) {
    this.specialPrice = specialPrice;
  }

  public String getSpecialFromDate() {
    return specialFromDate;
  }

  public void setSpecialFromDate(String specialFromDate) {
    this.specialFromDate = specialFromDate;
  }

  public String getSpecialToDate() {
    return specialToDate;
  }

  public void setSpecialToDate(String specialToDate) {
    this.specialToDate = specialToDate;
  }

  public String getRowId() {
    return rowId;
  }

  public void setRowId(String rowId) {
    this.rowId = rowId;
  }

  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public String getCreatedIn() {
    return createdIn;
  }

  public void setCreatedIn(String createdIn) {
    this.createdIn = createdIn;
  }

  public String getUpdatedIn() {
    return updatedIn;
  }

  public void setUpdatedIn(String updatedIn) {
    this.updatedIn = updatedIn;
  }

  public String getAttributeSetId() {
    return attributeSetId;
  }

  public void setAttributeSetId(String attributeSetId) {
    this.attributeSetId = attributeSetId;
  }

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getHasOptions() {
    return hasOptions;
  }

  public void setHasOptions(String hasOptions) {
    this.hasOptions = hasOptions;
  }

  public String getRequiredOptions() {
    return requiredOptions;
  }

  public void setRequiredOptions(String requiredOptions) {
    this.requiredOptions = requiredOptions;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getTaxClassId() {
    return taxClassId;
  }

  public void setTaxClassId(String taxClassId) {
    this.taxClassId = taxClassId;
  }

  public Object getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(Object finalPrice) {
    this.finalPrice = finalPrice;
  }

  public String getMinimalPrice() {
    return minimalPrice;
  }

  public void setMinimalPrice(String minimalPrice) {
    this.minimalPrice = minimalPrice;
  }

  public String getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(String minPrice) {
    this.minPrice = minPrice;
  }

  public String getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(String maxPrice) {
    this.maxPrice = maxPrice;
  }

  public Object getTierPrice() {
    return tierPrice;
  }

  public void setTierPrice(Object tierPrice) {
    this.tierPrice = tierPrice;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSmallImage() {
    return smallImage;
  }

  public void setSmallImage(String smallImage) {
    this.smallImage = smallImage;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getPopularityOrder() {
    return popularityOrder;
  }

  public void setPopularityOrder(String popularityOrder) {
    this.popularityOrder = popularityOrder;
  }

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public String getPartnersBenefits() {
    return partnersBenefits;
  }

  public void setPartnersBenefits(String partnersBenefits) {
    this.partnersBenefits = partnersBenefits;
  }

  public String getPartnersTermsConditions() {
    return partnersTermsConditions;
  }

  public void setPartnersTermsConditions(String partnersTermsConditions) {
    this.partnersTermsConditions = partnersTermsConditions;
  }

  public String getRequestPath() {
    return requestPath;
  }

  public void setRequestPath(String requestPath) {
    this.requestPath = requestPath;
  }

  public List<String> getCategoryIds() {
    return categoryIds;
  }

  public void setCategoryIds(List<String> categoryIds) {
    this.categoryIds = categoryIds;
  }

  public String getAttributeSetName() {
    return attributeSetName == null ? "" : attributeSetName;
  }

  public void setAttributeSetName(String attributeSetName) {
    this.attributeSetName = attributeSetName;
  }

  public String getEventsEventType() {
    return eventsEventType;
  }

  public void setEventsEventType(String eventsEventType) {
    this.eventsEventType = eventsEventType;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCheftbl_menu_description() {
    return cheftbl_menu_description;
  }

  public void setCheftbl_menu_description(String cheftbl_menu_description) {
    this.cheftbl_menu_description = cheftbl_menu_description;
  }

  public String getToptbl_benefits_privileges() {
    return toptbl_benefits_privileges;
  }

  public void setToptbl_benefits_privileges(String toptbl_benefits_privileges) {
    this.toptbl_benefits_privileges = toptbl_benefits_privileges;
  }
}
