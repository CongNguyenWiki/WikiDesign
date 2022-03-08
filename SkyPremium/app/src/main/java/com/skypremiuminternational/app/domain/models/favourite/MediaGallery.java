package com.skypremiuminternational.app.domain.models.favourite;

import com.google.gson.annotations.SerializedName;
import com.skypremiuminternational.app.domain.models.products.MediaGalleryEntry;

import java.util.List;
import java.util.Map;

public class MediaGallery {

  @SerializedName("images")
  Map<String,MediaGalleryEntry>  images;

  public Map<String,MediaGalleryEntry> getImages() {
    return images;
  }

  public void setImages(Map<String,MediaGalleryEntry>  images) {
    this.images = images;
  }
}
