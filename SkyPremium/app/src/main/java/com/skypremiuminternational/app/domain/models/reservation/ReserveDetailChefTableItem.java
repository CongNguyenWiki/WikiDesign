package com.skypremiuminternational.app.domain.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReserveDetailChefTableItem implements Serializable {

  @SerializedName("h_gw_id")
  String hgwId;
  @SerializedName("reservation_id")
  String reservationId;
  @SerializedName("restaurant_name")
  String restaurantName;
  @SerializedName("reservation_on_date")
  String reservationOnDate;
  @SerializedName("reservation_date")
  String reservationDate;
  @SerializedName("membership_id")
  String membershipId;
  @SerializedName("adult_guests")
  String adultGuests;
  @SerializedName("child_guests")
  String childGuests;
  @SerializedName("reservation_status")
  String reservationStatus;
  @SerializedName("reservation_sky_dollar_earn")
  String reservationSkyDollarEarn;
  @SerializedName("reservation_data")
  String reservationData;
  @SerializedName("product_id")
  String productId;
  @SerializedName("entity_id")
  String entity_id;
  @SerializedName("loyal_expiried")
  String loyalExpiried;
  @SerializedName("extension_data")
  List<ExtensionData> extensionData;
  @SerializedName("location")
  String location;
  @SerializedName("menu_type")
  String menu_type;
  @SerializedName("order_id")
  String order_id;
  @SerializedName("reservation_time_submit")
  String reservation_time_submit;
  @SerializedName("reservation_status_remarks")
  String reservation_status_remarks;
  @SerializedName("number_hosts")
  String numberHosts;
  @SerializedName("number_guests")
  String numberGuests;
  @SerializedName("reservation_cuisine_data")
  ReservationCuisineData reservationCuisineData;
  @SerializedName("reservation_datetime_options")
  ReservationDatetimeOptions reservationDatetimeOptions;
  @SerializedName("request_remarks")
  String requestRemarks;
  @SerializedName("special_occasion")
  String specialOccasion;
  @SerializedName("reservation_confirm_date")
  String reservationConfirmDate;
  @SerializedName("reservation_confirm_time")
  String reservationConfirmTime;
  @SerializedName("chef_name")
  String chefName;





  String status;
  private String reviewId;

  public String getHgwId() {
    return hgwId;
  }

  public void setHgwId(String hgwId) {
    this.hgwId = hgwId;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getRestaurantName() {
    return restaurantName;
  }

  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }

  public String getReservationOnDate() {
    return reservationOnDate;
  }

  public void setReservationOnDate(String reservationOnDate) {
    this.reservationOnDate = reservationOnDate;
  }

  public String getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(String reservationDate) {
    this.reservationDate = reservationDate;
  }

  public String getMembershipId() {
    return membershipId;
  }

  public void setMembershipId(String membershipId) {
    this.membershipId = membershipId;
  }

  public String getAdultGuests() {
    return adultGuests;
  }

  public void setAdultGuests(String adultGuests) {
    this.adultGuests = adultGuests;
  }

  public String getChildGuests() {
    return childGuests;
  }

  public void setChildGuests(String childGuests) {
    this.childGuests = childGuests;
  }

  public String getReservationStatus() {
    return reservationStatus;
  }

  public void setReservationStatus(String reservationStatus) {
    this.reservationStatus = reservationStatus;
  }

  public String getReservationSkyDollarEarn() {
    return reservationSkyDollarEarn;
  }

  public void setReservationSkyDollarEarn(String reservationSkyDollarEarn) {
    this.reservationSkyDollarEarn = reservationSkyDollarEarn;
  }

  public String getReservationData() {
    return reservationData;
  }

  public void setReservationData(String reservationData) {
    this.reservationData = reservationData;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getEntity_id() {
    return entity_id;
  }

  public void setEntity_id(String entity_id) {
    this.entity_id = entity_id;
  }

  public String getLoyalExpiried() {
    return loyalExpiried;
  }

  public void setLoyalExpiried(String loyalExpiried) {
    this.loyalExpiried = loyalExpiried;
  }

  public List<ExtensionData> getExtensionData() {
    return extensionData;
  }

  public void setExtensionData(List<ExtensionData> extensionData) {
    this.extensionData = extensionData;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getMenu_type() {
    return menu_type;
  }

  public void setMenu_type(String menu_type) {
    this.menu_type = menu_type;
  }

  public String getOrder_id() {
    return order_id;
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  public String getReservation_time_submit() {
    return reservation_time_submit;
  }

  public void setReservation_time_submit(String reservation_time_submit) {
    this.reservation_time_submit = reservation_time_submit;
  }

  public String getReservation_status_remarks() {
    return reservation_status_remarks;
  }

  public void setReservation_status_remarks(String reservation_status_remarks) {
    this.reservation_status_remarks = reservation_status_remarks;
  }

  public String getNumberHosts() {
    return numberHosts;
  }

  public void setNumberHosts(String numberHosts) {
    this.numberHosts = numberHosts;
  }

  public String getNumberGuests() {
    return numberGuests;
  }

  public void setNumberGuests(String numberGuests) {
    this.numberGuests = numberGuests;
  }

  public ReservationCuisineData getReservationCuisineData() {
    return reservationCuisineData;
  }

  public void setReservationCuisineData(ReservationCuisineData reservationCuisineData) {
    this.reservationCuisineData = reservationCuisineData;
  }

  public ReservationDatetimeOptions getReservationDatetimeOptions() {
    return reservationDatetimeOptions;
  }

  public void setReservationDatetimeOptions(ReservationDatetimeOptions reservationDatetimeOptions) {
    this.reservationDatetimeOptions = reservationDatetimeOptions;
  }

  public String getRequestRemarks() {
    return requestRemarks;
  }

  public void setRequestRemarks(String requestRemarks) {
    this.requestRemarks = requestRemarks;
  }

  public String getSpecialOccasion() {
    return specialOccasion;
  }

  public void setSpecialOccasion(String specialOccasion) {
    this.specialOccasion = specialOccasion;
  }

  public String getReservationConfirmDate() {
    return reservationConfirmDate;
  }

  public void setReservationConfirmDate(String reservationConfirmDate) {
    this.reservationConfirmDate = reservationConfirmDate;
  }

  public String getReservationConfirmTime() {
    return reservationConfirmTime;
  }

  public void setReservationConfirmTime(String reservationConfirmTime) {
    this.reservationConfirmTime = reservationConfirmTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getReviewId() {
    return reviewId;
  }

  public void setReviewId(String reviewId) {
    this.reviewId = reviewId;
  }

  public String getChefName() {
    return chefName;
  }

  public void setChefName(String chefName) {
    this.chefName = chefName;
  }
}
