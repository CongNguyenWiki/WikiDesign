package com.skypremiuminternational.app.data.model.ean.booking.history;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExtensionData implements Serializable {

  @SerializedName("booking_day_of_week")
  private String bookingDayOfWeek;
  @SerializedName("customer_name")
  private String customerName;
  @SerializedName("hotel_address")
  private String hotelAddress;
  @SerializedName("hotel_image")
  private String hotelImage;
  @SerializedName("room_type")
  private String roomType;
  @SerializedName("number_of_room")
  private String numberOfRoom;
  @SerializedName("number_of_person")
  private String numberOfPerson;
  @SerializedName("number_of_night")
  private String numberOfNight;
  @SerializedName("cancel_policy")
  private String cancelPolicy;
  @SerializedName("meal_plan")
  private String mealPlan;
  @SerializedName("notification")
  private String notification;
  @SerializedName("mandatory_tax")
  private String mandatoryTax;
  @SerializedName("rooms")
  private Object rooms;
  @SerializedName("booking_data")
  private List<BookingData> bookingData;

  public String getBookingDayOfWeek() {
    return bookingDayOfWeek;
  }

  public void setBookingDayOfWeek(String bookingDayOfWeek) {
    this.bookingDayOfWeek = bookingDayOfWeek;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getHotelAddress() {
    return hotelAddress;
  }

  public void setHotelAddress(String hotelAddress) {
    this.hotelAddress = hotelAddress;
  }

  public String getHotelImage() {
    return hotelImage;
  }

  public void setHotelImage(String hotelImage) {
    this.hotelImage = hotelImage;
  }

  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  public String getNumberOfRoom() {
    return numberOfRoom;
  }

  public void setNumberOfRoom(String numberOfRoom) {
    this.numberOfRoom = numberOfRoom;
  }

  public String getNumberOfPerson() {
    return numberOfPerson;
  }

  public void setNumberOfPerson(String numberOfPerson) {
    this.numberOfPerson = numberOfPerson;
  }

  public String getNumberOfNight() {
    return numberOfNight;
  }

  public void setNumberOfNight(String numberOfNight) {
    this.numberOfNight = numberOfNight;
  }

  public String getCancelPolicy() {
    return cancelPolicy;
  }

  public void setCancelPolicy(String cancelPolicy) {
    this.cancelPolicy = cancelPolicy;
  }

  public String getMealPlan() {
    return mealPlan;
  }

  public void setMealPlan(String mealPlan) {
    this.mealPlan = mealPlan;
  }

  public String getNotification() {
    return notification;
  }

  public void setNotification(String notification) {
    this.notification = notification;
  }

  public String getMandatoryTax() {
    return mandatoryTax;
  }

  public void setMandatoryTax(String mandatoryTax) {
    this.mandatoryTax = mandatoryTax;
  }

  public Object getRooms() {
    return rooms;
  }

  public void setRooms(Object rooms) {
    this.rooms = rooms;
  }

  public List<BookingData> getBookingData() {
    return bookingData;
  }

  public void setBookingData(List<BookingData> bookingData) {
    this.bookingData = bookingData;
  }
}
