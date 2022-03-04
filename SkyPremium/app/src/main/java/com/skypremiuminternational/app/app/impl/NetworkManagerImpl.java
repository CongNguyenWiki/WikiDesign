package com.skypremiuminternational.app.app.impl;

import static com.skypremiuminternational.app.data.network.URL.GET_RESERVATION_TIME;
import static com.skypremiuminternational.app.data.network.URL.GET_RESTAURANT_MSG;
import static com.skypremiuminternational.app.data.network.URL.IP_ADDRESS;

import com.skypremiuminternational.app.BuildConfig;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utl.EanAuthHeaderGenerator;
import com.skypremiuminternational.app.data.NetworkManager;
import com.skypremiuminternational.app.data.mapper.AddBillingAddressRequestMapper;
import com.skypremiuminternational.app.data.mapper.BookRequestMapper;
import com.skypremiuminternational.app.data.mapper.BookingDetailMapper;
import com.skypremiuminternational.app.data.mapper.BookingHistoryMapper;
import com.skypremiuminternational.app.data.mapper.EditBillingAddressRequestMapper;
import com.skypremiuminternational.app.data.mapper.PriceCheckMapper;
import com.skypremiuminternational.app.data.model.BookingHistory;
import com.skypremiuminternational.app.data.model.billingaddress.BillingAddress;
import com.skypremiuminternational.app.data.model.billingaddress.BillingAddressesResponse;
import com.skypremiuminternational.app.data.model.ean.availability.AvailableProperty;
import com.skypremiuminternational.app.data.model.ean.booking.book.BookResponse;
import com.skypremiuminternational.app.data.model.ean.booking.history.Booking;
import com.skypremiuminternational.app.data.model.ean.booking.itinerary.ItineraryResponse;
import com.skypremiuminternational.app.data.model.ean.content.PropertyContent;
import com.skypremiuminternational.app.data.model.ean.payment.CardOption;
import com.skypremiuminternational.app.data.network.RestAdapter;
import com.skypremiuminternational.app.data.network.request.ActivityLogRequest;
import com.skypremiuminternational.app.data.network.request.AddCreditCardRequest;
import com.skypremiuminternational.app.data.network.request.ForgotPasswordRequest;
import com.skypremiuminternational.app.data.network.request.GetFavouriteRequest;
import com.skypremiuminternational.app.data.network.request.GetMyMembershipStatementRequest;
import com.skypremiuminternational.app.data.network.request.GetOrderHistoryRequest;
import com.skypremiuminternational.app.data.network.request.GiftPopUpRequest;
import com.skypremiuminternational.app.data.network.request.PasswordHashRequest;
import com.skypremiuminternational.app.data.network.request.SignInRequest;
import com.skypremiuminternational.app.data.network.request.UpdateCreditCardRequest;
import com.skypremiuminternational.app.data.network.request.UpdatePasswordRequest;
import com.skypremiuminternational.app.data.network.request.UpdateUserDeliveryRequest;
import com.skypremiuminternational.app.data.network.request.UpdateUserRequest;
import com.skypremiuminternational.app.data.network.request.UploadAvatarRequest;
import com.skypremiuminternational.app.data.network.service.auth.AuthServices;
import com.skypremiuminternational.app.data.network.service.category.CategoryService;
import com.skypremiuminternational.app.data.network.service.countrycode.CountryCodesService;
import com.skypremiuminternational.app.data.network.service.credit_card.CreditCardService;
import com.skypremiuminternational.app.data.network.service.ean.EanService;
import com.skypremiuminternational.app.data.network.service.fag.FaqService;
import com.skypremiuminternational.app.data.network.service.favourite.FavouriteService;
import com.skypremiuminternational.app.data.network.service.hgw.HGWService;
import com.skypremiuminternational.app.data.network.service.rating_comment.RatingCommentService;
import com.skypremiuminternational.app.data.network.service.user.UserService;
import com.skypremiuminternational.app.data.network.service.cart.CartServices;
import com.skypremiuminternational.app.data.network.service.config.ConfigService;
import com.skypremiuminternational.app.data.network.service.my_membership.MembershipService;
import com.skypremiuminternational.app.data.network.service.my_order.MyOrderServices;
import com.skypremiuminternational.app.data.network.service.product.ProductServices;
import com.skypremiuminternational.app.domain.interactor.ean.BookRoom;
import com.skypremiuminternational.app.domain.interactor.ean.CheckPrice;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingDetail;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingDetailValue;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingHistories;
import com.skypremiuminternational.app.domain.models.InviteFriendDescription;
import com.skypremiuminternational.app.domain.models.Version;
import com.skypremiuminternational.app.domain.models.booking.BookingDetail;
import com.skypremiuminternational.app.domain.models.booking.PriceCheckResult;
import com.skypremiuminternational.app.domain.models.booking.ToggleTravelResponse;
import com.skypremiuminternational.app.domain.models.cart.AddCartItemCountRequest;
import com.skypremiuminternational.app.domain.models.cart.CartDetailItem;
import com.skypremiuminternational.app.domain.models.cart.CartDetailResponse;
import com.skypremiuminternational.app.domain.models.cart.CheckLimitResponse;
import com.skypremiuminternational.app.domain.models.category.CategoryResponse;
import com.skypremiuminternational.app.domain.models.comment_rating.ProductReviewResponse;
import com.skypremiuminternational.app.domain.models.comment_rating.RatingOption;
import com.skypremiuminternational.app.domain.models.comment_rating.RatingResult;
import com.skypremiuminternational.app.domain.models.comment_rating.ReviewDetailResponse;
import com.skypremiuminternational.app.domain.models.config.BackgroundLogin;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;
import com.skypremiuminternational.app.domain.models.country_code.CountryCodeCC;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;
import com.skypremiuminternational.app.domain.models.creditCard.CreditCardResponse;
import com.skypremiuminternational.app.domain.models.creditCard.GetFormDataCreditCardResponse;
import com.skypremiuminternational.app.domain.models.ean.ISOCountry;
import com.skypremiuminternational.app.domain.models.faq.FaqResponse;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteResponse;
import com.skypremiuminternational.app.domain.models.gift_redeemtion.GiftPopUpResponse;
import com.skypremiuminternational.app.domain.models.my_orders.ExtraOrderDetail;
import com.skypremiuminternational.app.domain.models.my_orders.MyOrderResponse;
import com.skypremiuminternational.app.domain.models.my_orders.detail.OrderDetailResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.InforMembershipResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.ListMemberShipResponse;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;
import com.skypremiuminternational.app.domain.models.products.DetailsItem;
import com.skypremiuminternational.app.domain.models.products.ProductListResponse;
import com.skypremiuminternational.app.domain.models.reservation.CancelReservationResponse;
import com.skypremiuminternational.app.domain.models.reservation.ConfigItem;
import com.skypremiuminternational.app.domain.models.reservation.OutletItem;
import com.skypremiuminternational.app.domain.models.reservation.ReservationResultResponse;
import com.skypremiuminternational.app.domain.models.reservation.ReservationTimeResponse;
import com.skypremiuminternational.app.domain.models.reservation.ReserveDetailChefTableItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryRespone;
import com.skypremiuminternational.app.domain.models.reservation.RestaurantMessageResponse;
import com.skypremiuminternational.app.domain.models.system.OneSignalConfig;
import com.skypremiuminternational.app.domain.models.user.UploadAvatarResponse;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import rx.Observable;


@Singleton
public class NetworkManagerImpl implements NetworkManager {

  final AuthServices authServices;
  final UserService userService;
  final CategoryService categoryService;
  final ProductServices productServices;
  final FavouriteService favouriteService;
  final CartServices cartServices;
  final MyOrderServices myOrderServices;
  final MembershipService membershipService;
  final ConfigService configService;
  final EanService eanService;
  final RatingCommentService ratingCommentService;
  final CountryCodesService countryCodesService;
  final HGWService hgwService;
  final CreditCardService creditCardService;
  final FaqService faqService;
  private final BookRequestMapper bookRequestMapper;
  private final BookingHistoryMapper bookingHistoryMapper;
  private final BookingDetailMapper bookingDetailMapper;
  private final PriceCheckMapper priceCheckMapper;


  @Inject
  public NetworkManagerImpl(BookRequestMapper bookRequestMapper,
                            BookingHistoryMapper bookingHistoryMapper,
                            BookingDetailMapper bookingDetailMapper,
                            PriceCheckMapper priceCheckMapper) {

    this.bookRequestMapper = bookRequestMapper;
    this.bookingHistoryMapper = bookingHistoryMapper;
    this.bookingDetailMapper = bookingDetailMapper;
    this.priceCheckMapper = priceCheckMapper;

    Retrofit retrofit = RestAdapter.create();

    this.authServices = retrofit.create(AuthServices.class);
    this.userService = retrofit.create(UserService.class);
    this.categoryService = retrofit.create(CategoryService.class);
    this.productServices = retrofit.create(ProductServices.class);
    this.cartServices = retrofit.create(CartServices.class);
    this.myOrderServices = retrofit.create(MyOrderServices.class);
    this.membershipService = retrofit.create(MembershipService.class);
    this.configService = retrofit.create(ConfigService.class);
    this.favouriteService = retrofit.create(FavouriteService.class);
    this.eanService = retrofit.create(EanService.class);
    this.ratingCommentService = retrofit.create(RatingCommentService.class);
    this.countryCodesService = retrofit.create(CountryCodesService.class);
    this.hgwService = retrofit.create(HGWService.class);
    this.creditCardService = retrofit.create(CreditCardService.class);
    this.faqService = retrofit.create(FaqService.class);
  }




  @Override
  public Observable<Version> getAppVersion(String accessToken) {
    return authServices.getAppVersion(accessToken);
  }

  @Override
  public Observable<ResponseBody> signIn(SignInRequest request) {
    return authServices.signIn(Constants.ACCESS_TOKEN,request);
  }

  @Override
  public Observable<UserDetailResponse> getUserDetail(String tokenUser) {
    return userService.getUserDetail(tokenUser);
  }

  @Override
  public Observable<CategoryResponse> getCategories(String token) {
    return categoryService.getCategories(token);
  }

  @Override
  public Observable<UserDetailResponse> updateUserDetail(UpdateUserRequest request) {
    return userService.updateUserDetail(Constants.ACCESS_TOKEN,request.getCustomer().getId() ,request);
  }

  @Override
  public Observable<ProductListResponse> getProducts(String accessToken) {
    return productServices.getProducts(accessToken);
  }



  @Override
  public Observable<DetailsItem> getProductDetails(String accessToken,String sku, String tokenUser) {
    return productServices.getDetails(accessToken,sku,tokenUser);
  }
  /**
   * Favourite
   */
  @Override
  public Observable<List<FavouriteItem>> getFavourites(String token, GetFavouriteRequest request,
                                                       String customerToken) {
    switch (request.requestType) {
      case GetFavouriteRequest.REQUEST_TYPE_ALL:
        return favouriteService.getAllFavourites(token, customerToken);
      case GetFavouriteRequest.REQUEST_TYPE_WITH_CATEGORYT:
        return favouriteService.getFavouriteWithCategory(token, request.categoryId,
                request.partnerType, request.sortingField, request.sortingDirection, customerToken);
      case GetFavouriteRequest.REQUEST_TYPE_WITHOUT_CATEGORY:
        return favouriteService.getFavouriteWithoutCategoty(token, request.partnerType,
                request.sortingField, request.sortingDirection, customerToken);
      default:
        return favouriteService.getAllFavourites(token, customerToken);
    }
  }

  @Override
  public Observable<FavouriteResponse> removeFromFavourite(String token, String wishListItemId) {
    return favouriteService.removeFromFav(token, wishListItemId)
        .map(favouriteResponses -> favouriteResponses.get(0));
  }

  /**
   *  My order
   */
  @Override
  public Observable<MyOrderResponse> getOrderHistory(String token, String customerId,
                                                     GetOrderHistoryRequest request) {
    String sorting = "DESC";
    String field = "created_at";
    if (request.selectedSorting.equalsIgnoreCase("Earliest first")) {
      sorting = "ASC";
    } else if (request.selectedSorting.equalsIgnoreCase("Order ID")) {
      field = "entity_id";
    }
    switch (request.requestType) {
      case GetOrderHistoryRequest.REQUEST_TYPE_ALL:
        return myOrderServices.getOrderHistory(token, customerId, sorting);
      case GetOrderHistoryRequest.REQUEST_TYPE_WITH_FILTER:

        return myOrderServices.getOrderHistoryWithFilter(token, customerId,
            getCategory(request.selectedCategory),
            sorting, field);
      default:
        return myOrderServices.getOrderHistory(token, customerId, sorting);
    }
  }

  private String getCategory(String selectedCategory) {
    switch (selectedCategory) {
      case "Processing":
        return Constants.ORDER_PROCESSING;
      case "Complete":
        return Constants.ORDER_COMPLETE;
      case "Cancelled":
        return Constants.ORDER_CANCELLED;
      case "Fail":
        return Constants.ORDER_FAIL;
      case "Shipping":
        return Constants.ORDER_SHIPPING;
    }
    return "";
  }




  /**
   * Activity log
   */
  @Override
  public Observable<CartDetailResponse> getCartInfo(String tokenUser) {
    return cartServices.getInfoShoppingCart(tokenUser);
  }

  @Override
  public Observable<CartDetailResponse> getCartDetail(String token) {
    return cartServices.getCartDetail(token);
  }

  @Override
  public Observable<Object> getCartId(String auth) {
    return cartServices.createCart(auth);
  }

  @Override
  public Observable<List<CartDetailItem>> getCartItems(String token) {
    return cartServices.getCartItems(token);
  }

  @Override
  public Observable<AddCartItemCountRequest.Cart> addToCart(AddCartItemCountRequest addCartItemCountRequest, String auth) {
    return cartServices.addToCart(auth, addCartItemCountRequest);

  }

  @Override
  public Observable<CheckLimitResponse> checkIfCartHasReachedLimit(String userToken, boolean buyNow, boolean isCheckout) {
    if (buyNow) {
      return cartServices.checkIfCartHasReachedLimitBuyNow(userToken, "1");
    }
    return cartServices.checkIfCartHasReachedLimit(userToken, isCheckout ? "1" : "0");

  }

  @Override
  public Observable<ResponseBody> putLogin(String auth, ActivityLogRequest request) {
    return authServices.putLogin(auth,request);
  }

  @Override
  public Observable<ResponseBody> putLogout(String auth, ActivityLogRequest request) {
    return authServices.putLogout(auth,request);
  }

  @Override
  public Observable<ResponseBody> updatePassword(String token, UpdatePasswordRequest updatePasswordRequest) {
    return authServices.updatePassword(token, updatePasswordRequest);
  }

  @Override
  public Observable<ResponseBody> getPasswordHash(String token, PasswordHashRequest passwordHashRequest) {
    return authServices.passwordHash(token, passwordHashRequest);
  }

  @Override
  public Observable<List<ListMemberShipResponse>> getMyMembershipStatement(String token, GetMyMembershipStatementRequest request) {



    String sorting = "created_at_desc";


    if (request.selectedSorting.equalsIgnoreCase("Earliest first")) {
      sorting = "created_at_asc";
    }

    return membershipService.getMembershipWithFilter(token, getRefineMembership(request.selectedCategory), sorting, request.memberNumber, request.limit, request.currentPage);


  }

  private String getRefineMembership(String selectedCategory) {

    switch (selectedCategory) {
      case "All":
        return "";
      case "On-Boarding":
        return "on_boarding";
      case "Renewal":
        return "renewal";
      case "Upgrade":
        return "upgrade";
      case "Downgrade":
        return "downgrade";
      case "Cancellation":
        return "cancellation";

    }
    return "";
  }


  @Override
  public Observable<List<InforMembershipResponse>> getInfoMembership(String userToken) {
    return membershipService.getMembershipInfo(userToken);
  }

  @Override
  public Observable<List<CountryCode>> getCountryCodes() {
    return configService.getCountryCodes();
  }

  @Override
  public Observable<List<Nationality>> getNationalities() {
    return configService.getNationalities();
  }

  @Override
  public Observable<List<ISOCountry>> getISOCountryCodes() {
    String url = "https://skypremium.s3.amazonaws.com/iSOCountryList.json";//"https://skypremium.s3.amazonaws.com/iSOCountryList.json";//"https://s3-ap-southeast-1.amazonaws.com/codigo-skypremium/iSOCountryList.json";
    return eanService.getISOCountryCodes(url).map(response -> response.countryCodes);
  }

  @Override
  public Observable<List<PermissionProfileItem>> getPermissionProfile(String userToken) {
    return userService.getPermissionProfile(userToken);
  }

  @Override
  public Observable<RatingResult> addRatingComment(String userToken, Map<String, String> addCommentRequest) {
    return ratingCommentService.addRatingCommment(userToken,addCommentRequest);
  }

  @Override
  public Observable<RatingResult> deleteReview(String userToken, Map<String, String> request) {
    return ratingCommentService.deleteReview(userToken, request);
  }

  @Override
  public Observable<RatingResult> updateReview(String userToken, Map<String, String> updateReview) {
    return ratingCommentService.updateReview(userToken, updateReview);
  }

  @Override
  public Observable<ReviewDetailResponse> getReviewDetail(String userToken, Map<String, String> addCommentRequest) {
    return ratingCommentService.getReviewDetail(userToken, addCommentRequest);
  }

  @Override
  public Observable<ResponseBody> getRatingSummaryByProduct(String userToken, Map<String, String> request) {
    return ratingCommentService.getRatingSummaryByProduct(userToken, request);
  }

  @Override
  public Observable<RatingOption> getRatingOption(String userToken) {
    return ratingCommentService.getRatingOption(userToken);
  }

  @Override
  public Observable<ProductReviewResponse> getListReviewByProduct(String userToken, Map<String, String> productReviewRequest) {
    return ratingCommentService.getListReviewByProduct(userToken, productReviewRequest);
  }

  @Override
  public Observable<UploadAvatarResponse> uploadAvatar(String token, UploadAvatarRequest uploadAvatarRequest) {
    return authServices.uploadAvatar(token,uploadAvatarRequest);
  }

  @Override
  public Observable<InviteFriendDescription> getInviteDescription(String token) {
    return userService.getInviteDescription(token);
  }


  @Override
  public Observable<List<CountryCodeCC>> getCountryCodeCc(String auth) {
    return countryCodesService.getCountryCodeCc(auth);
  }

  @Override
  public Observable<Boolean> deleteAddress(String clientToken, Integer addressId) {
    return userService.deleteAddress(clientToken, addressId);
  }


  @Override
  public Observable<UserDetailResponse> updateUserDetail(String token, String member_number, UpdateUserDeliveryRequest updateUserRequest) {
    return userService.getUserDetailDelivery(token, member_number, updateUserRequest);
  }


  @Override
  public Observable<ReserveHistoryRespone> getHistoryAll(String userToken, String memberNumber, String sortBy, String currentPage, String pageSize) {

    if (sortBy.equals("hgw_id")) {
      return hgwService.getHistoryAll(userToken,
          "customer_id",
          memberNumber,
          "eq",

          "DESC",
          "hgw_id"
        /*,
        currentPage, pageSize*/);
    }

    return hgwService.getHistoryAll(userToken,
        "customer_id",
        memberNumber,
        "eq",

        sortBy,
        "reservation_date"
        /*,
        currentPage, pageSize*/);
  }

  @Override
  public Observable<ReserveHistoryRespone> getHistoryFilter(String userToken, String memberNumber, String sortBy, String refineBy, String conditionRefine, String currentPage, String pageSize) {
    if (sortBy.equals("hgw_id")) {
      return hgwService.getHistoryFilter(userToken,
          "customer_id",
          memberNumber,
          "eq",

          "DESC",
          "hgw_id",

          "reservation_status",
          conditionRefine,
          "eq");

    }


    return hgwService.getHistoryFilter(userToken,
        "customer_id",
        memberNumber,
        "eq",

        sortBy,
        "reservation_date",

        "reservation_status",
        conditionRefine,
        "eq");

//    if(refineBy.equalsIgnoreCase("4")){
//      return  hgwService.getHistoryFilter(userToken,
//          "customer_id",
//          memberNumber,
//          "eq",
//
//          sortBy,
//          "reservation_date",
//
//          "reservation_status",
//          "4",
//          "eq");
//    }

//    return hgwService.getHistoryFilterByDate(userToken,
//        "customer_id",
//        memberNumber,
//        "eq",
//
//        sortBy,
//        "reservation_date",
//
//        "reservation_date",
//        refineBy,
//        conditionRefine,
//
//        "reservation_status",
//        "1",
//        "eq"
//        /*,
//        currentPage, pageSize*/);
  }

  @Override
  public Observable<ReserveHistoryItem> getDetailReservationBooking(String userToken, String bookingId) {
    return hgwService.getDetailReservationBooking(userToken, bookingId);

  }

  @Override
  public Observable<List<OutletItem>> getOutletByProductID(String oAuth, String productId) {
    return hgwService.getOutletByProductID(oAuth, productId);

  }

  @Override
  public Observable<CancelReservationResponse> cancelReservation(String userToken, String reservationId, String verificationKey) {
    return hgwService.cancelReservation(userToken, reservationId, verificationKey);

  }

  @Override
  public Observable<List<ConfigItem>> getHGWConfig(String userToken) {
    return hgwService.getHGWConfig(userToken);
  }

  @Override
  public Observable<ReservationTimeResponse> getReservationTime(String path, String date, String restaurantId, String partnerCode, String partnerAuthCode) {
    String fullPath = path + GET_RESERVATION_TIME + "/" + date + "/" + restaurantId + "/" + partnerCode + "/" + partnerAuthCode;


    return hgwService.getReservationTime(fullPath);
  }

  @Override
  public Observable<RestaurantMessageResponse> getRestaurantMsg(String path, String restaurantId) {
    String fullPath = path + GET_RESTAURANT_MSG + "/" + restaurantId;

    return hgwService.getRestaurantMsg(fullPath);
  }

  @Override
  public Observable<ReservationResultResponse> sendCreateReservation(String userToken, Map<String, String> mapRequest) {
    return hgwService.sendCreateReservation(userToken, mapRequest);

  }

  @Override
  public Observable<ReservationResultResponse> sendEditReservation(String userToken, Map<String, String> mapRequest, String reservationId, String verificationKey) {
    return hgwService.sendEditReservation(userToken, mapRequest, reservationId, verificationKey);

  }

  @Override
  public Observable<ResponseBody> getTokenTravflex(String token, Map<String, String> params) {
    return authServices.getTokenTravflex(token, params);
  }

  @Override
  public Observable<String> getIpAddress() {
    return eanService.getIpAddress(IP_ADDRESS)
        .map(response -> response.ip);
  }

  @Override
  public Observable<BookingDetail> bookRoom(String userToken, BookRoom.Params params, String sessionId, String ipAddress) {
    return eanService.bookRoom(userToken, bookRequestMapper.map(params, sessionId, ipAddress))
        .flatMap(bookResponse -> Observable.from(bookResponse.getEanResult())
            .first()
            .flatMap(eanResult -> {
              if (eanResult != null
                  && !eanResult.getBookingStatus()
                  && eanResult.getType() != null
                  && eanResult.getType().equals("create.system_failure")) {
                List<BookResponse.Error> errors = new ArrayList<>();
                BookResponse.Error error =
                    new BookResponse.Error(eanResult.getType(), eanResult.getMessage(),
                        new ArrayList<>());
                errors.add(error);
                return Observable.just(bookingDetailMapper.mapFail(errors));
              } else if (eanResult.getErrors() != null && eanResult.getErrors().size() > 0) {
                return Observable.just(bookingDetailMapper.mapFail(eanResult.getErrors()));
              } else {
                return getItinerary(eanResult.getLinks().getRetrieve().getHref(), sessionId,
                    ipAddress)
                    .flatMap(itineraryResponse -> {
                      List<String> propertyIds = new ArrayList<>();
                      propertyIds.add(itineraryResponse.getPropertyId());

                      return getPropertyContents(propertyIds, sessionId, ipAddress)
                          .map(contentMap -> bookingDetailMapper.map(contentMap, itineraryResponse, bookResponse, params.skyDollar));
                    });
              }
            }));
  }

  @Override
  public Observable<Booking> getBookingDetail(GetBookingDetail.Params params) {
    return eanService.getBookingDetail(Constants.ACCESS_TOKEN, params.bookingId);

  }

  @Override
  public Observable<ItineraryResponse> getItinerary(String endpoint, String sessionId, String ipAddress) {
    String url = BuildConfig.EAN_BASE_URL + endpoint;
    String authHeader = new EanAuthHeaderGenerator().generate(BuildConfig.EAN_API_KEY,
        BuildConfig.EAN_SHARED_SECRET);

    return eanService.getItinerary(authHeader, ipAddress,
        sessionId, url);
  }

  @Override
  public Observable<Map<String, PropertyContent>> getPropertyContents(List<String> availablePropertyIds, String sessionId, String ipAddress) {
    StringBuilder propertyIdBuilder = new StringBuilder();
    for (String propertyId : availablePropertyIds) {
      propertyIdBuilder.append("&");
      propertyIdBuilder.append("property_id=");
      propertyIdBuilder.append(propertyId);
    }

    String url = BuildConfig.EAN_BASE_URL
        + "/"
        + BuildConfig.EAN_API_VERSION
        + "/properties/content?language=en-US"
        + propertyIdBuilder.toString();
    String authHeader = new EanAuthHeaderGenerator().generate(BuildConfig.EAN_API_KEY,
        BuildConfig.EAN_SHARED_SECRET);

    return eanService.getPropertyContents(authHeader, ipAddress,
        sessionId, url);
  }

  @Override
  public Observable<Boolean> cancelBooking(String userToken, String bookingId) {
    return eanService.cancelBooking(userToken, bookingId)
        .map(response -> response.toString().equalsIgnoreCase("true"));
  }

  @Override
  public Observable<PriceCheckResult> checkPrice(CheckPrice.Params params, String sessionId, String ipAddress) {
    String url = BuildConfig.EAN_BASE_URL + params.bookingLink;
    String authHeader = new EanAuthHeaderGenerator().generate(BuildConfig.EAN_API_KEY,
        BuildConfig.EAN_SHARED_SECRET);

    return eanService.checkPrice(authHeader, ipAddress,
        sessionId, url)
        .map(response -> priceCheckMapper.map(response, params.roomCount, params.adultCount,
            params.children));
  }

  @Override
  public Observable<Booking> getBookingDetailValue(GetBookingDetailValue.Params params) {
    return eanService.getBookingDetail(Constants.ACCESS_TOKEN, params.bookingId);

  }

  @Override
  public Observable<List<BookingHistory>> getBookingHistories(String userToken, GetBookingHistories.Params params) {
    if (params.status.equalsIgnoreCase("All")) {
      return eanService.getBookingHistoriesWithoutFilter(userToken, params.sortDirection,
          params.sortField).map(bookingHistoryMapper::map);
    } else {
      return eanService.getBookingHistoriesWithFilter(userToken, params.status, params.sortDirection,
          params.sortField).map(bookingHistoryMapper::map);
    }
  }


  @Override
  public Observable<List<CardOption>> getPaymentOptions(String paymentOptionLink, String sessionId, String ipAddress) {
    String url = BuildConfig.EAN_BASE_URL + paymentOptionLink;
    String authHeader = new EanAuthHeaderGenerator().generate(BuildConfig.EAN_API_KEY,
        BuildConfig.EAN_SHARED_SECRET);

    return eanService.getPaymentOptions(authHeader, ipAddress,
        sessionId, url)
        .map(response -> response.getCreditCard().getCardOptions());
  }

  @Override
  public Observable<ToggleTravelResponse> getToggleTravel(String auth) {
    return eanService.getToggleTravel(auth);
  }

  @Override
  public Observable<List<AvailableProperty>> getAvailableProperties(List<String> propertyIds, String arrival, String departure, String countryCode, List<String> occupancies, String sessionId, String ipAddress) {
    StringBuilder propertyIdBuilder = new StringBuilder();
    for (String propertyId : propertyIds) {
      propertyIdBuilder.append("&");
      propertyIdBuilder.append("property_id=");
      propertyIdBuilder.append(propertyId);
    }

    String url = BuildConfig.EAN_BASE_URL
        + "/"
        +
        BuildConfig.EAN_API_VERSION
        + "/properties/availability?checkin="
        + arrival
        + "&checkout="
        + departure
        + "&rate_plan_count=100"
        +
        "&currency=SGD&language=en-US&country_code="
        + countryCode
        + joinOccupancies(occupancies)
        + propertyIdBuilder.toString()
        + "&sales_channel=mobile_app&sales_environment="
        + Constants.SALE_ENVIRONMENT
        + "&sort_type=preferred"
        + "&include="
        + Constants.INCLUDE
        + "&billing_terms=123&payment_terms=Payment_CC&partner_point_of_sale=SG";
    String authHeader = new EanAuthHeaderGenerator().generate(BuildConfig.EAN_API_KEY,
        BuildConfig.EAN_SHARED_SECRET);

    return eanService.getAvailableProperties(authHeader, ipAddress,
        sessionId, url).onErrorResumeNext(
        throwable -> {
          if (throwable instanceof HttpException && ((HttpException) throwable).code() == 404) {
            return Observable.just(new ArrayList<>());
          }
          return Observable.error(throwable);
        });
  }

  @Override
  public Observable<CreditCardResponse> getCreditCard(String userToken) {
    return creditCardService.getListCreditCard(userToken);
  }

  @Override
  public Observable<GetFormDataCreditCardResponse> getFormDataCreditCard(String userToken) {
    return creditCardService.getFormDataCreditCard(userToken);
  }

  @Override
  public Observable<CardItem> addCreditCard(String userToken, AddCreditCardRequest request) {
    return creditCardService.addCreditCard(userToken,request);
  }

  @Override
  public Observable<CardItem> updateCreditCard(String userToken, String cardId, UpdateCreditCardRequest updateCreditCardRequest) {
    return creditCardService.updateCreditCard(userToken,cardId,updateCreditCardRequest);
  }


  @Override
  public Observable<ResponseBody> deleteCreditCard(String userToken, String cardId) {
    return creditCardService.deleteCreditCard(userToken,cardId);
  }

  @Override
  public Observable<FaqResponse> getFaqs(String token) {
    return faqService.getFaqs(token);
  }

  @Override
  public Observable<ResponseBody> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
    return authServices.forgotPassword(forgotPasswordRequest);

  }

  @Override
  public Observable<OneSignalConfig> getOneSignalConfig(String auth) {
    return configService.getOneSignalConfig(auth);
  }

  @Override
  public Observable<ReserveDetailChefTableItem> getDetailReservationBookingChefTable(String userToken, String bookingId) {
    return hgwService.getDetailReservationBookingChefTable(userToken, bookingId);
  }

  @Override
  public Observable<List<GiftPopUpResponse>> showPopUp(String auth, GiftPopUpRequest giftPopUpRequest) {
    return configService.showPopUp(auth, giftPopUpRequest);
  }

  private String joinOccupancies(List<String> occupancies) {
    StringBuilder builder = new StringBuilder();
    for (String occupancy : occupancies) {
      builder.append("&occupancy=");
      builder.append(occupancy);
    }
    return builder.toString();
  }


  @Override
  public Observable<PhoneCode> getPhoneCodes() {
    return configService.getPhoneCodes();
  }


  @Override
  public Observable<List<BackgroundLogin>> getBackgroundLogin(String token) {
    return configService.getBackgroundLogin(token);
  }

  @Override
  public Observable<OrderDetailResponse> getOrderDetail(String token, Integer orderId) {
    return myOrderServices.getOrderDetail(token, orderId);
  }

  @Override
  public Observable<ExtraOrderDetail> getExtraOrderDetail(String clientToken, int orderId) {
    return myOrderServices.getOrderHistoryExtra(clientToken,orderId);
  }
}
