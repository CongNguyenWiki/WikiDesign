package com.skypremiuminternational.app.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class ImageViewParce implements Parcelable {


  ImageView imageView;

  public ImageViewParce(ImageView imageView) {
    this.imageView = imageView;
  }

  public ImageViewParce(Parcel in) {
  }

  public static final Creator<ImageViewParce> CREATOR = new Creator<ImageViewParce>() {
    @Override
    public ImageViewParce createFromParcel(Parcel in) {
      return new ImageViewParce(in);
    }

    @Override
    public ImageViewParce[] newArray(int size) {
      return new ImageViewParce[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
  }

  public ImageView getImageView() {
    return imageView;
  }

  public void setImageView(ImageView imageView) {
    this.imageView = imageView;
  }

  public static Creator<ImageViewParce> getCREATOR() {
    return CREATOR;
  }

}
