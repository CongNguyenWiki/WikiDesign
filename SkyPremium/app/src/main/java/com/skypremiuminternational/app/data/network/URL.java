package com.skypremiuminternational.app.data.network;

import com.skypremiuminternational.app.BuildConfig;

import static com.skypremiuminternational.app.BuildConfig.API_V2;

/**
 * Created by johnsonmaung on 9/26/17.
 */

public class URL {


    private static final String SEARCH_PAGE_SIZE = "200";
  private static final String PAGE_SIZE = "100";
  private static final String CURRENT_PAGE = "1";


  public static final String IP_ADDRESS = "https://api.ipify.org?format=json";

  private static final String API = BuildConfig.API;

  public static final String TRAVFLEX_TOKEN = API + "/skypremium-travelflex/tokens";

  public static final String CREATE_BOOKING = API + "/booking/itineraries";

  public static final String CANCEL_BOOKING =
      API + "/customers/mine/ean/cancel_booking/{booking_id}";

  public static final String GET_BOOKING_HISTORIES_WITHOUT_FILTER = API
      + "/customers/mine/ean/history_booking?"
      + "searchCriteria[pageSize]="
      + SEARCH_PAGE_SIZE
      + "&searchCriteria[currentPage]="
      + CURRENT_PAGE;

  public static final String GET_BOOKING_HISTORIES_WITH_FILTER = API
      + "/customers/mine/ean/history_booking?"
      + "searchCriteria[pageSize]="
      + SEARCH_PAGE_SIZE
      + "&searchCriteria[currentPage]="
      + CURRENT_PAGE
      + "&searchCriteria[filterGroups][0][filters][0][field]=booking_status"
      + "&searchCriteria[filterGroups][0][filters][0][conditionType]=eq";

  public static final String TOGGLE_TRAVEL = API + "/skypremium-travelflex/config";


  public static final String GET_BOOKING_DETAIL = API + "/ean/history_booking/{booking_id}";


  public static final String GET_OUTLET_BY_PRODUCT = API + "/skypremium-hgw/getOptionOutlet/{product_id}";


  public static final String GET_HISTORY = API + "/skypremium-hgw/reservation/history";

  public static final String GET_DETAIL_RESERVATION_BOOKING = API + "/skypremium-hgw/history-detail/{booking_id}";

  public static final String CANCEL_RESERVATION = API + "/skypremium-hgw/cancelReservation/{reservation_id}/{verificationKey}";

  public static final String GET_HGW_CONFIG = API + "/skypremium-hgw/getHgwConfig";

  // FROM HGW
  public static final String GET_RESERVATION_TIME = "/tabledb-web/middlelayer/availability/slots";

  public static final String GET_RESTAURANT_MSG = "/tabledb-web/restaurantMessageSetting";

  public static final String SEND_CREATE_RESERVATION = API + "/skypremium-hgw/sendBooking";

  public static final String SEND_EDIT_RESERVATION = API + "/skypremium-hgw/editReservation/{reservation_id}/{verificationKey}";


  public static final String SIGN_IN = API + "/integration/customer/token";

  public static final String GET_APP_VERSION = API + "/app_version.json";

  /**
   * Config API
   */

  public static final String GET_COUNTRY =
      "https://skypremium.s3.amazonaws.com/CountryCodes.json";

  public static final String GET_PHONE_CODE =
      "https://skypremium.s3.amazonaws.com/iSOCountryList.json";

  public static final String GET_NATIONALITIES =
      "https://skypremium.s3.amazonaws.com/Nationalities.json";

  public static final String GET_LOGIN_BG = API +
        "/media/loginBackgroundImage";

  public static final String GET_CATEGORIES = API + "/categories";

  public static final String GET_COUNTRY_CC = API_V2 + "/customers/mine/getCountryList";



  /*
  Review Comment API
*/
  public static final String ADD_RATING = API + "/skypremium-review/addReview";
  public static final String GET_REVIEW_DETAIL = API + "/skypremium-review/getReviewDetail";
  public static final String GET_PRODUCT_REVIEW_LIST = API + "/skypremium-review/getReviewProduct";
  public static final String EDIT_REVIEW = API + "/skypremium-review/editReview";
  public static final String GET_RATING_SUMMARY = API + "/skypremium-review/getRatingValueProduct";
  public static final String DELETE_REVIEW = API + "/skypremium-review/deleteReview";
  public static final String GET_RATING_OPTION = API + "/skypremium-review/getRatingDetail";


  /**
   * orther api
   */


  public static final String USER_DETAIL = API +
      "/customers/meV2";

  public static final String GET_INVITE_FRIEND_DESCRIPTION = API + "/invite/description";

  public static final String BILLING_ADDRESS_PAYMENT = API + "/carts/mine/billing-address-payment";

  public static final String DELETE_ADDRESS = API + "/addresses/{address_id}";



  public static final String USER_UPDATE = API + "/customers/{member_id}";

  public static final String GET_PERMISSION_PROFILE = API + "/skypremium-permissionpage/getNavCustomer";

  public static final String PRODUCTS = API +
      "/products?searchCriteria[filterGroups][0][filters][0][field]=visibility" +
      "&searchCriteria[filterGroups][0][filters][0][conditionType]=in" +
      "&searchCriteria[filterGroups][0][filters][0][value]=2,4" +
      "&searchCriteria[filterGroups][0][filters][0][field]=category_id" +
      "&searchCriteria[filterGroups][0][filters][0][conditionType]=eq" +
      "&searchCriteria[filterGroups][0][filters][0][value]=55" +
      "&searchCriteria[currentPage]=1&searchCriteria[pageSize]=30" +
      "&searchCriteria[sortOrders][2][field]=created_at" +
      "&searchCriteria[sortOrders][2][direction]=DESC" +
      "&searchCriteria[filterGroups][3][filters][1][field]=status" +
      "&searchCriteria[filterGroups][3][filters][1][conditionType]=eq" +
      "&searchCriteria[filterGroups][3][filters][1][value]=1";

  public static final String DETAILS = API + "/products/{sku}";

  //Activity log
  public static final String PUT_LOGIN = API +
      "/customers/freshworks/activity/login";

  public static final String PUT_LOGOUT = API +
      "/customers/freshworks/activity/logout";

  public static final String UPDATE_PASSWORD = API+ "/customers/me/password";

  public static final String FORGOT_PASSWORD = API + "/customers/password";


  public static final String PASSWORD_HASH = API + "/passwordHash";


  public static final String GET_FAVOURITE = API + "/wishlist/items";


  public static final String GET_ALL_FAVOURITE = GET_FAVOURITE
          +"?searchCriteria[filter_groups][0][filters][0][field]="
          +"&searchCriteria[filter_groups][0][filters][0][value]="
          + "&searchCriteria[sortOrders][0][field]=added_at"
          + "&searchCriteria[sortOrders][0][direction]=ASC";

  public static final String GET_ALL_FAVOURITE_NO_ORDERS = GET_FAVOURITE
          + "?searchCriteria[filter_groups][0][filters][0][field]=partner_type";

  public static final String GET_FAVOURITE_WITH_CATEGORY = GET_FAVOURITE
          + "?searchCriteria[filter_groups][1][filters][0][field]=category_id"
          + "&searchCriteria[filter_groups][0][filters][0][field]=partner_type";

  public static final String GET_FAVOURITE_WITHOUT_CATEGORY =
          GET_FAVOURITE + "?searchCriteria[filter_groups][0][filters][0][field]=partner_type";
//  public static final String FAV_LIST = API +
  public static final String UPLOAD_AVATAR = API +
      "/updateCustomersV2/uploadPictureProfile";

  public static final String CHECK_ADD_CARD_FIRST_TIME = API_V2 + "/customers/mine/add_stripe_is_show_form";


  public static final String WISH_LIST = API +
      "/wishlist/items?searchCriteria[filter_groups][0][filters][0][field]=" +
      "&searchCriteria[filter_groups][0][filters][0][value]=" +
      "&searchCriteria[sortOrders][0][field]=added_at" +
      "&searchCriteria[sortOrders][0][direction]=ASC";

  public static final String ORDER_HISTORY = API + "/orders?"
      + "searchCriteria[filterGroups][0][filters][0][field]=customer_id"
      + "&searchCriteria[filterGroups][0][filters][0][conditionType]=eq"
      + "&searchCriteria[sortOrders][0][field]=created_at";

  public static final String ORDERHISTORY_WITH_CATEGORY = API
      + "/orders?"
      + "searchCriteria[filterGroups][0][filters][0][field]=customer_id"
      + "&searchCriteria[filterGroups][0][filters][0][conditionType]=eq"
      + "&searchCriteria[filterGroups][1][filters][0][field]=status"
      + "&searchCriteria[filterGroups][1][filters][0][conditionType]=eq";

  public static final String ORDER_DETAIL = API + "/orders/{order_id}";

  public static final String ORDER_HISTORY_EXTRA = API + "/orders/{order_id}/order_history_extra";

  public static final String ADD_TO_FAVOURITE = API + "/wishlist/add/{product_id}";
  public static final String REMOVE_FROM_FAVOURITE = API + "/wishlist/delete/{wishlist_item_id}";


  public static final String FILTER_PRODUCT = API
      + "/products?";

  //Cart
  public static final String GET_INFO_SHOPPING_CART = API + "/carts/mine?is_buynow=0";

  //Create Cart
  public static final String CART = API + "/carts/mine";

  //Add To Cart
  public static final String CART_ITEMS = API + "/carts/mine/items?is_buynow=0";

  public static final String CART_DETAIL = API + "/carts/mine?is_buynow=0";

  public static final String CHECK_CART_LIMIT = API + "/carts/mine/checkLimit";


  //membership statement
  public static final String GET_MEMBERSHIP_STATEMENT = API + "/membership-statements/getList?";

  public static final String GET_MEMBERSHIP_INFO = API + "/membership-statements/getInfoMembership";

/*
  Api Credit Card
*/

  public static final String GET_CREDIT_CARD = API + "/cards/mine/";

  public static final String GET_FORM_DATA_CREDIT_CARD = API + "/cards/form-data";

  public static final String ADD_CREDIT_CARD = API + "/cards/mine/";

  public static final String UPDATE_CREDIT_CARD = API + "/cards/mine/{cardId}";

  public static final String DELETE_CREDIT_CARD_ = API + "/cards/mine/{cardId}";

  public static final String GET_FAQ = API + "/faq/faqs";

  //Toggle crm
  public static final String ONESIGNAL_CONFIG = API + "/system/config/onesignal_mapp";

  public static final String GET_GIFT_POPUP = API + "/gift/floating-button";


}