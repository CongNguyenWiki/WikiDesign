package com.skypremiuminternational.app.domain.models.favourite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.skypremiuminternational.app.domain.models.products.ExtensionAttributes;

import java.io.Serializable;

public class FavouriteItem implements Serializable {
  @SerializedName("wishlist_item_id")
  @Expose
  private String wishlistItemId;
  @SerializedName("wishlist_id")
  @Expose
  private String wishlistId;
  @SerializedName("product_id")
  @Expose
  private String productId;
  @SerializedName("store_id")
  @Expose
  private String storeId;
  @SerializedName("added_at")
  @Expose
  private String addedAt;
  @SerializedName("description")
  @Expose
  private Object description;
  @SerializedName("qty")
  @Expose
  private Integer qty;
  @SerializedName("product")
  @Expose
  private Product product;
  @SerializedName("extension_attributes")
  @Expose
  private ExtensionAttributes extensionAttributes;

  int imgPos = 0;
  private String pillarId;
  private String categoryName;
  private String pillarName;


  private boolean isCanShow = true;
  private boolean isCanUse = true;
  private boolean isHasPermission = false;
   boolean isTopTable;
   boolean isChefTable;

  public int getImgPos() {
    return imgPos;
  }
  public void setImgPos(int imgPos) {
    this.imgPos = imgPos;
  }


  public boolean isTopTable() {
    return isTopTable;
  }

  public void setTopTable(boolean topTable) {
    isTopTable = topTable;
  }

  public boolean isChefTable() {
    return isChefTable;
  }

  public void setChefTable(boolean chefTable) {
    isChefTable = chefTable;
  }

  public boolean isCanShow() {
    return isCanShow;
  }

  public void setCanShow(boolean canShow) {
    isCanShow = canShow;
  }

  public boolean isCanUse() {
    return isCanUse;
  }

  public void setCanUse(boolean canUse) {
    isCanUse = canUse;
  }

  public boolean isHasPermission() {
    return isHasPermission;
  }

  public void setHasPermission(boolean hasPermission) {
    isHasPermission = hasPermission;
  }

  public ExtensionAttributes getExtensionAttributes() {
    return extensionAttributes;
  }

  public void setExtensionAttributes(ExtensionAttributes extensionAttributes) {
    this.extensionAttributes = extensionAttributes;
  }

  public String getWishlistItemId() {
    return wishlistItemId;
  }

  public void setWishlistItemId(String wishlistItemId) {
    this.wishlistItemId = wishlistItemId;
  }

  public String getWishlistId() {
    return wishlistId;
  }

  public void setWishlistId(String wishlistId) {
    this.wishlistId = wishlistId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getAddedAt() {
    return addedAt;
  }

  public void setAddedAt(String addedAt) {
    this.addedAt = addedAt;
  }

  public Object getDescription() {
    return description;
  }

  public void setDescription(Object description) {
    this.description = description;
  }

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getPillarId() {
    return pillarId == null ? "" : pillarId;
  }

  public void setPillarId(String pillarId) {
    this.pillarId = pillarId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getPillarName() {
    return pillarName;
  }

  public void setPillarName(String pillarName) {
    this.pillarName = pillarName;
  }

}
