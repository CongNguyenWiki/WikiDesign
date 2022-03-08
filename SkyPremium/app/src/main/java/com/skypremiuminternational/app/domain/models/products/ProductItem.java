package com.skypremiuminternational.app.domain.models.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductItem implements Serializable {


  @SerializedName("visibility")
  private int visibility;
  @SerializedName("type_id")
  private String typeId;
  @SerializedName("created_at")
  private String createdAt;
  @SerializedName("extension_attributes")
  private ExtensionAttributes extensionAttributes;
  @SerializedName("tier_prices")
  private List<Object> tierPrices;
  @SerializedName("custom_attributes")
  private List<CustomAttributesItem> customAttributes;
  @SerializedName("attribute_set_id")
  private int attributeSetId;
  @SerializedName("attribute_set_name")
  private String attributeSetName;
  @SerializedName("updated_at")
  private String updatedAt;
  @SerializedName("price")
  private String price;
  @SerializedName("name")
  private String name;
  @SerializedName("id")
  private int id;
  @SerializedName("sku")
  private String sku;
  @SerializedName("product_links")
  private List<Object> productLinks;
  @SerializedName("flash_sale")
  private boolean isFlashSales;
  @SerializedName("status")
  private int status;
  @SerializedName("button_type")
  private String buttonType;
  @SerializedName("type_price")
  private String typePrice;
  @SerializedName("tier_price_value")
  private List<TierItem> tierPriceValue;
  @SerializedName("time_sales_tier")
  private List<TimeSaleTier> timeSalesTier;
  @SerializedName("image_thumbnail_url")
  @Expose
  private String imageThumbnailUrl;
  @SerializedName("product_name")
  @Expose
  private String productName;
  @SerializedName("product_article_date")
  @Expose
  private String productArticleDate;
  @SerializedName("product_url")
  @Expose
  private String productUrl;
  @SerializedName("category_id")
  @Expose
  private List<Integer> categoryId;
  @SerializedName("category_name")
  @Expose
  private String Category_Name;
  @SerializedName("is_booking_top_table")
  private Boolean isBookingTopTable;
  @SerializedName("message_sky_dollar")
  private String messageSkyDollar;
  @SerializedName("article_listing_image")
  private String articleListingImage;
  @SerializedName("article_banner_image")
  private String articleBannerImage;
  @SerializedName("media_gallery_entries")
  @Expose
  public List<MediaGalleryEntry>
      mediaGalleryEntries = null;


  public int getVisibility() {
    return visibility;
  }

  public void setVisibility(int visibility) {
    this.visibility = visibility;
  }

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public ExtensionAttributes getExtensionAttributes() {
    return extensionAttributes;
  }

  public void setExtensionAttributes(ExtensionAttributes extensionAttributes) {
    this.extensionAttributes = extensionAttributes;
  }

  public List<Object> getTierPrices() {
    return tierPrices;
  }

  public void setTierPrices(List<Object> tierPrices) {
    this.tierPrices = tierPrices;
  }

  public List<CustomAttributesItem> getCustomAttributes() {
    return customAttributes;
  }

  public void setCustomAttributes(List<CustomAttributesItem> customAttributes) {
    this.customAttributes = customAttributes;
  }

  public int getAttributeSetId() {
    return attributeSetId;
  }

  public void setAttributeSetId(int attributeSetId) {
    this.attributeSetId = attributeSetId;
  }

  public String getAttributeSetName() {
    return attributeSetName;
  }

  public void setAttributeSetName(String attributeSetName) {
    this.attributeSetName = attributeSetName;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public List<Object> getProductLinks() {
    return productLinks;
  }

  public void setProductLinks(List<Object> productLinks) {
    this.productLinks = productLinks;
  }

  public boolean isFlashSales() {
    return isFlashSales;
  }

  public void setFlashSales(boolean flashSales) {
    isFlashSales = flashSales;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getButtonType() {
    return buttonType;
  }

  public void setButtonType(String buttonType) {
    this.buttonType = buttonType;
  }

  public String getTypePrice() {
    return typePrice;
  }

  public void setTypePrice(String typePrice) {
    this.typePrice = typePrice;
  }

  public List<TierItem> getTierPriceValue() {
    return tierPriceValue;
  }

  public void setTierPriceValue(List<TierItem> tierPriceValue) {
    this.tierPriceValue = tierPriceValue;
  }

  public List<TimeSaleTier> getTimeSalesTier() {
    return timeSalesTier;
  }

  public void setTimeSalesTier(List<TimeSaleTier> timeSalesTier) {
    this.timeSalesTier = timeSalesTier;
  }

  public String getImageThumbnailUrl() {
    return imageThumbnailUrl;
  }

  public void setImageThumbnailUrl(String imageThumbnailUrl) {
    this.imageThumbnailUrl = imageThumbnailUrl;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductArticleDate() {
    return productArticleDate;
  }

  public void setProductArticleDate(String productArticleDate) {
    this.productArticleDate = productArticleDate;
  }

  public String getProductUrl() {
    return productUrl;
  }

  public void setProductUrl(String productUrl) {
    this.productUrl = productUrl;
  }

  public List<Integer> getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(List<Integer> categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategory_Name() {
    return Category_Name;
  }

  public void setCategory_Name(String category_Name) {
    Category_Name = category_Name;
  }

  public Boolean getBookingTopTable() {
    return isBookingTopTable;
  }

  public void setBookingTopTable(Boolean bookingTopTable) {
    isBookingTopTable = bookingTopTable;
  }

  public String getMessageSkyDollar() {
    return messageSkyDollar;
  }

  public void setMessageSkyDollar(String messageSkyDollar) {
    this.messageSkyDollar = messageSkyDollar;
  }

  public String getArticleListingImage() {
    return articleListingImage;
  }

  public void setArticleListingImage(String articleListingImage) {
    this.articleListingImage = articleListingImage;
  }

  public String getArticleBannerImage() {
    return articleBannerImage;
  }

  public void setArticleBannerImage(String articleBannerImage) {
    this.articleBannerImage = articleBannerImage;
  }

  public List<MediaGalleryEntry> getMediaGalleryEntries() {
    return mediaGalleryEntries;
  }

  public void setMediaGalleryEntries(List<MediaGalleryEntry> mediaGalleryEntries) {
    this.mediaGalleryEntries = mediaGalleryEntries;
  }
}
