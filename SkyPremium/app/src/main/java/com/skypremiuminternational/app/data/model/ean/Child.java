package com.skypremiuminternational.app.data.model.ean;

import android.os.Parcel;
import android.os.Parcelable;



public class Child implements Parcelable {

  public int digitAge;

  public String age;

  public String name;

  public Child(int digitAge, String age, String name) {
    this.digitAge = digitAge;
    this.age = age;
    this.name = name;
  }

  protected Child(Parcel in) {
    digitAge = in.readInt();
    age = in.readString();
    name = in.readString();
  }

  public static final Creator<Child> CREATOR = new Creator<Child>() {
    @Override
    public Child createFromParcel(Parcel in) {
      return new Child(in);
    }

    @Override
    public Child[] newArray(int size) {
      return new Child[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(digitAge);
    dest.writeString(age);
    dest.writeString(name);
  }
}
