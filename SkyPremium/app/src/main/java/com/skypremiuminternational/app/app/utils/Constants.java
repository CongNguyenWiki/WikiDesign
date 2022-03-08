package com.skypremiuminternational.app.app.utils;

import com.skypremiuminternational.app.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by johnsonmaung on 1/7/17.
 * <p>
 * Author: Levister Gutierrez
 * Task: To include the AWS Access Token and Keys on Cloudsearch APIs, add the variable "key"
 * and "secret"
 * Included: HMS_TOKEN_KEY & HMS_SECRET_KEY
 * Date: 15 Apr 2019
 */
public class Constants {

  public static final String[] CHILDREN_AGES = new String[]{
      "Under 1", "1yo", "2yo", "3yo", "4yo", "5yo", "6yo", "7yo", "8yo", "9yo", "10yo", "11yo",
      "12yo",
      "13yo", "14yo", "15yo", "16yo", "17yo"
  };

  public static final int[] CHILDREN_AGES_IN_DIGIT = new int[]{
      0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17
  };

  public static final String REGEX_VALIDATION_CREDITCARD = "^[a-zA-Z0-9, ().<>-]*$";
  public static final String REGEX_VALIDATION_FULLNAME = "([a-zA-Z0-9,().<>-]+\\s?\\b){2,}";

  public static final String AUTH_METHOD = "Bearer ";
  public static final String ACCESS_TOKEN = AUTH_METHOD + BuildConfig.ACCESS_TOKEN;

  public static final String TITLE = "Title";
  public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

  public static final String HMS_TOKEN_KEY = "HMS_TOKEN_KEY";
  public static final String HMS_SECRET_KEY = "HMS_SECRET_KEY";

  public static final String ORDER_SUSPECTED_FRAUD = "fraud";
  public static final String ORDER_PAYMENT_REVIEW = "payment_review";
  public static final String ORDER_FAIL = "fail";
  public static final String ORDER_PENDING = "pending";
  public static final String ORDER_PROCESSING = "processing";
  public static final String ORDER_COMPLETE = "complete";
  public static final String ORDER_HOLDED = "holded";
  public static final String ORDER_CLOSED = "closed";
  public static final String ORDER_CANCELLED = "canceled";
  public static final String ORDER_SHIPPING = "shipping";

  public static final String[] sortingArrOrder = {"Latest first", "Earliest first", "Order ID"};

  public static final String[] categoryArrOrder = {"All", "Cancelled", "Complete", "Fail", "Processing", "Shipping"};


  public static final String[] SALUTATIONS_NEW = {"Mr.", "Mrs.", "Mdm.", "Miss.", "Sir.","Dr. (F)","Dr. (M)","Prof. (F)","Prof. (M)","Lady.","Ms."};
  public static final String[] SALUTATIONS_CODE_NEW = {"Mr", "Mrs", "Mdm", "Miss", "Sir","DrF", "DrM", "ProfF", "ProfM", "Lady", "Ms"};
  public static final String VISA_BRAND = "visa";
  public static final String MASTER_BRAND = "mastercard";
  public static final String AMERICAN_EXPRESS_BRAND = "amex";
  public static final String DISCOVER_BRAND = "Discover";
  public static final String DINERS_CLUB_BRAND = "Diners Club";
  public static final String JCB_BRAND = "JCB";

  public static final String BOOKING_DATE_FORMAT = "dd MMM yyyy (E)";

  //Comment status
  public static final String COMMENT_APPROVED = "Approved";
  public static final String COMMENT_NOT_APPROVED = "Not Approved";
  public static final String COMMENT_PENDING = "Pending";
  public static final String CART_COUNT = "cart_count";
  public static final String UNIT_NUMBER = "unit_number";
  public static final String PATTERN_DATE_SHORT = "yyyy-MM-dd";


  public static final String PATTERN_TIME = "HH.mm";
  public static final String DATE_RANGE_FORMAT = "dd MMM yyyy";
  public static final String DAY_RANGE_FORMAT = "dd";
  public static final String BOOKING_STATUS_CONFIRMED = "confirmed";

  public final static int DEFAULT_ROOM_COUNT = 1;
  public final static int DEFAULT_ADULT_COUNT = 2;
  public static final String[] SALUTATIONS = {"Mr.", "Ms.", "Mrs.", "Dr.", "Prof."};
  public static final String PHONE_CODE_DEFAULT = "65";
  public static final String COUNTRY_DEFAULT = "Singapore ";
  public static final String[] EU_COUNTRY_CODE = new String[]{
      "AT", "BE", "BG", "CY", "CZ", "DK", "EE", "FI", "FR", "DE", "GR", "HU", "HR", "IE", "IT",
      "LV", "LT", "LU", "MT", "NL", "PL", "PT", "RO", "SK", "SI", "ES", "SE", "GB"
  };
  public static final String SALE_ENVIRONMENT = "hotel_only";
  public static final String INCLUDE = "all_rates";


  public static SimpleDateFormat DATE_FORMAT_DOB = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

  public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy", Locale.US);

  public static final String PATTERN_CURRENT_SERVER_TIME = "dd-MM-yyyy HH:mm:ss";
  public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
  public static final String PATTERN_TIME_SHORT = "hh.mma";
  public static final String PATTERN_DATE_FULL_SP_REV = "dd MMMM yyyy";
  public static final String PATTERN_DATE_SEMI_DAY_NEM_SP_REV = "EEE dd MMM yyyy";

  public static SimpleDateFormat DATE_YEAR_FORMAT =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

  public static int MIN_AGE = -10;


  public static final String[] refineArrMembershipStatement = {"All", "On-Boarding", "Renewal", /*"Upgrade", "Downgrade",*/ "Cancellation", "Change"};

  public static final String[] sortArrMembershipStatement = {"Latest first", "Earliest first"};




  public static  final  String SHOW_PLATINUM_ICON  = "1";

  //
  public static final String FAVOURITE_PARTNER_TYPE_PARTNERS = "1";
  public static final String FAVOURITE_PARTNER_TYPE_PRODUCTS = "2";
  public static final String FAVOURITE_PARTNER_TYPE_EVENT = "3";

  public static final String[] sortingEventArrFav =
          {"Latest first", "Earliest first", "Name: A–Z", "Name: Z–A"};
  public static final String[] sortDirectionEventFav =
          {"DESC", "ASC", "ASC", "DESC"};
  public static final String[] sortDirectionFav =
          {"ASC","ASC", "DESC", "DESC", "ASC", "ASC"};
  public static final String[] sortEventFieldFav =
          {"created_at_event", "created_at_event", "name", "name"};
  public static final String[] sortFieldFav =
          {"popularity_order","name", "name", "added_at", "added_at", "geo_location_"};
  public static final String[] sortingArrFav =
          {"Popular","A - Z", "Z - A", "Latest first", "Earliest first", "Location"};

  public static final String DISCOUNT_TYPE_PERCENT = "percent";

  //product label state
  public static  final  String PRICE_ORIGINAL  = "display_original_price";
  public static  final  String PRICE_SPECIAL  = "display_special_price";
  public static  final  String PRICE_TIER  = "display_tier_price";

  //product label state
  public static  final  String ENABLE_TIME_SALE  = "1";
  public static  final  String STATE_IN_STOCK  = "in_stock";
  public static  final  String STATE_SOLD_OUT  = "sold_out";
  public static  final  String STATE_SALE_OVER  = "sales_over";
  public static  final  String STATE_SALE_NOT_START  = "sale_not_started";




  //  Categories ID

  public static int TRAVEL_ID = 24;


  public static final String[] refineArrReservation = {"All", "Request Pending", "Request Unsuccessful", "Request Retracted", "Reservation Confirmed", "Reservation Cancelled", "Reservation Completed", "Request Processing"};
  public static final String[] sortArrReservation = {"Latest first", "Earliest first", "Reservation ID"};
  public static final String[] sortDirectionReservation = {"DESC", "ASC", "hgw_id"};

  public static final String[] refineDirectionReservation = {"", "6", "5", "3" , "1", "4", "2", "7"};

  public static final String[] categoryArrBooking =
      {"All", "Booked", "Cancellation Under Review", "Canceled"};

  public static final String[] categoryValueBooking =
      {"All", "confirmed", "cancellation", "cancelled"};

  public static final String[] sortingArrBooking =
      {"Latest Booking Date First", "Booking ID", "Earliest Booking Date First"};

  public static final String[] sortingFieldBooking = {"created_at", "booking_id", "created_at"};
  public static final String[] sortingDirectionBooking = {"DESC", "ASC", "ASC"};

  //Reservation
  public static final String RESERVATION_STATUS_CONFIRMED    = "1";
  public static final String RESERVATION_STATUS_COMPLETED    = "2";
  public static final String RESERVATION_STATUS_RETRACTED    = "3";
  public static final String RESERVATION_STATUS_CANCELLED    = "4";
  public static final String RESERVATION_STATUS_UNSUCCESSFUL = "5";
  public static final String RESERVATION_STATUS_PENDING      = "6";
  public static final String RESERVATION_STATUS_PROCESSING = "7";

}

