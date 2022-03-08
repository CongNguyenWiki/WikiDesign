package com.skypremiuminternational.app.domain;

import android.os.Build;
import android.util.Log;


import com.skypremiuminternational.app.data.network.request.AddCreditCardRequest;
import com.skypremiuminternational.app.data.network.request.ForgotPasswordRequest;
import com.skypremiuminternational.app.data.network.request.GiftPopUpRequest;
import com.skypremiuminternational.app.domain.interactor.credit_card.UpdateCreditCard;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;
import com.skypremiuminternational.app.domain.models.creditCard.CreditCardResponse;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.Validator;
import com.skypremiuminternational.app.app.utl.OccupancyArranger;
import com.skypremiuminternational.app.data.DBManager;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.NetworkManager;
import com.skypremiuminternational.app.data.hash.CustomerSessionIdHasher;
import com.skypremiuminternational.app.data.mapper.BookingDetailMapper;
import com.skypremiuminternational.app.data.mapper.BookingHistoryMapper;
import com.skypremiuminternational.app.data.mapper.PropertyMapper;
import com.skypremiuminternational.app.data.model.BookingHistory;
import com.skypremiuminternational.app.data.model.ean.Child;
import com.skypremiuminternational.app.data.model.ean.availability.AvailableProperty;
import com.skypremiuminternational.app.data.model.ean.booking.history.Booking;
import com.skypremiuminternational.app.data.model.ean.booking.history.BookingData;
import com.skypremiuminternational.app.data.model.ean.content.PropertyContent;
import com.skypremiuminternational.app.data.model.ean.payment.CardOption;
import com.skypremiuminternational.app.data.network.request.ActivityLogRequest;
import com.skypremiuminternational.app.data.network.request.AddCommentRequest;
import com.skypremiuminternational.app.data.network.request.GetFavouriteRequest;
import com.skypremiuminternational.app.data.network.request.GetMyMembershipStatementRequest;
import com.skypremiuminternational.app.data.network.request.GetOrderHistoryRequest;
import com.skypremiuminternational.app.data.network.request.PasswordHashRequest;
import com.skypremiuminternational.app.data.network.request.ProductReviewRequest;
import com.skypremiuminternational.app.data.network.request.ReviewDetailRequest;
import com.skypremiuminternational.app.data.network.request.SignInRequest;
import com.skypremiuminternational.app.data.network.request.UpdateCommentRequest;
import com.skypremiuminternational.app.data.network.request.UpdatePasswordRequest;
import com.skypremiuminternational.app.data.network.request.UpdateUserDeliveryRequest;
import com.skypremiuminternational.app.data.network.request.UpdateUserRequest;
import com.skypremiuminternational.app.data.network.request.UploadAvatarRequest;
import com.skypremiuminternational.app.domain.interactor.ean.BookRoom;
import com.skypremiuminternational.app.domain.interactor.ean.CheckPrice;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingDetail;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingDetailValue;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingHistories;
import com.skypremiuminternational.app.domain.interactor.ean.GetPaymentOptions;
import com.skypremiuminternational.app.domain.interactor.user.DeleteAddress;
import com.skypremiuminternational.app.domain.models.InviteFriendDescription;
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
import com.skypremiuminternational.app.domain.models.creditCard.GetFormDataCreditCardResponse;
import com.skypremiuminternational.app.domain.models.ean.ISOCountry;
import com.skypremiuminternational.app.domain.models.ean.Property;
import com.skypremiuminternational.app.domain.models.faq.FaqItem;
import com.skypremiuminternational.app.domain.models.faq.FaqResponse;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteResponse;
import com.skypremiuminternational.app.domain.models.gift_redeemtion.GiftPopUpResponse;
import com.skypremiuminternational.app.domain.models.my_orders.ExtraOrderDetail;
import com.skypremiuminternational.app.domain.models.my_orders.detail.OrderDetailResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.InforMembershipResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.ListMemberShipResponse;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;
import com.skypremiuminternational.app.domain.models.products.DetailsItem;
import com.skypremiuminternational.app.domain.models.my_orders.MyOrderResponse;
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
import com.skypremiuminternational.app.domain.models.user.BillingAddressPayment;
import com.skypremiuminternational.app.domain.models.user.UploadAvatarResponse;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;
import com.skypremiuminternational.app.domain.models.Version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;


@Singleton
public class DataManager {

  private final InternalStorageManager internalStorageManager;
  private final DBManager dbManager;
  private final NetworkManager networkManager;
  private String sessionId;
  private final CustomerSessionIdHasher customerSessionIdHasher;
  private final BookingHistoryMapper bookingHistoryMapper;
  private final BookingDetailMapper bookingDetailMapper;
  private final OccupancyArranger occupancyArranger;
  private final PropertyMapper propertyMapper;

  @Inject
  public DataManager(CustomerSessionIdHasher customerSessionIdHasher,
      InternalStorageManager internalStorageManager,
                     DBManager dbManager,
                     NetworkManager networkManager,
                     BookingHistoryMapper bookingHistoryMapper,
                     BookingDetailMapper bookingDetailMapper,
                     OccupancyArranger occupancyArranger,
                     PropertyMapper propertyMapper
                     ) {
    this.internalStorageManager = internalStorageManager;
    this.bookingHistoryMapper = bookingHistoryMapper;
    this.bookingDetailMapper = bookingDetailMapper;
    this.customerSessionIdHasher = customerSessionIdHasher;
    this.dbManager = dbManager;
    this.networkManager = networkManager;
    this.occupancyArranger = occupancyArranger;
    this.propertyMapper = propertyMapper;
  }

  public void clearAuthToken() {
    internalStorageManager.clearAuthToken();
  }




  public Observable<Version> getAppVersion(){
    return networkManager.getAppVersion(Constants.ACCESS_TOKEN);
  }

  public Observable<ResponseBody> signIn(SignInRequest request){
    return networkManager.signIn(request);
  }

  public Observable<UserDetailResponse> getUserDetailFromAPI() {
    return networkManager.getUserDetail(getAuthUserToken());
  }

  public Observable<CategoryResponse> getCategoriesFromAPI() {
    return networkManager.getCategories(Constants.ACCESS_TOKEN);
  }

  public void saveCategories(CategoryResponse response) {
    internalStorageManager.saveCategories(response);
  }

  public CategoryResponse getCategories() {
    return internalStorageManager.getCategories();
  }


  public UserDetailResponse getUserDetail() {
    return internalStorageManager.getUserDetail();
  }

  public Observable<UserDetailResponse> updateUserDetail(UpdateUserRequest  request) {
    return networkManager.updateUserDetail(request);
  }

  public Observable<UserDetailResponse> updateUserDetail(UpdateUserDeliveryRequest updateUserRequest) {
    return networkManager.updateUserDetail(getAuthUserToken(),
        updateUserRequest.getMember_id(), updateUserRequest);
  }


  public Observable<ProductListResponse> getProducts() {
    return networkManager.getProducts(Constants.ACCESS_TOKEN);
  }

  public Observable<DetailsItem> getProductDetails(String sku) {
    return networkManager.getProductDetails(Constants.ACCESS_TOKEN,sku,getUserToken());
  }


  public Observable<List<FavouriteItem>> getFavourites(GetFavouriteRequest request) {
    return networkManager.getFavourites(getAuthUserToken(), request,internalStorageManager.getUserAuthToken());
  }

  public Observable<FavouriteResponse> removeFromFav(String wishlistItemId) {
    return networkManager.removeFromFavourite(getAuthUserToken(), wishlistItemId);
  }


  public Observable<CartDetailResponse> getCartInfo() {
    return networkManager.getCartInfo(getAuthUserToken());
  }

  public Observable<CartDetailResponse> getCartDetail() {
    return networkManager.getCartDetail(getAuthUserToken());
  }

  public Observable<String> createCart() {
    return networkManager.getCartId(getAuthUserToken())
        .map(Object::toString);
  }

  public Observable<AddCartItemCountRequest.Cart> addToCart(AddCartItemCountRequest request) {
    return networkManager.addToCart(request, getAuthUserToken());
  }

  public Observable<List<CartDetailItem>> getCartItems() {
    return networkManager.getCartItems(getAuthUserToken());
  }

  public void saveCartCount(String count) {
    internalStorageManager.saveCartCount(count);
  }

  public String getCartCount() {
    return internalStorageManager.getCartCount();
  }


  public Observable<CheckLimitResponse> checkIfCartHasReachedLimit(boolean isBuyNow, boolean isCheckOut) {
    return networkManager.checkIfCartHasReachedLimit(getUserToken(),isBuyNow,isCheckOut);
  }


  public Observable<MyOrderResponse> getOrderHistoryFromAPI(GetOrderHistoryRequest request) {



      return networkManager.getOrderHistory(Constants.ACCESS_TOKEN, internalStorageManager.getUserDetail().getId().toString(),
          request);


  }

  public Observable<ResponseBody> putLogin() {
    ActivityLogRequest request = new ActivityLogRequest();
    request.setDevice(Build.MODEL +", "+ "Android "+ Build.VERSION.RELEASE);
    return networkManager.putLogin(getAuthUserToken(),request);
  }

  public Observable<ResponseBody> putLogout() {
    ActivityLogRequest request = new ActivityLogRequest();
    request.setDevice(Build.MODEL +", "+ "Android "+ Build.VERSION.RELEASE);
    return networkManager.putLogout(getAuthUserToken(),request);
  }

  public Observable<ResponseBody> updatePassword(UpdatePasswordRequest updatePasswordRequest) {
    return networkManager.updatePassword(getAuthUserToken(), updatePasswordRequest);
  }

  public Observable<ResponseBody> getPasswordHash(PasswordHashRequest passwordHashRequest) {
    return networkManager.getPasswordHash(Constants.ACCESS_TOKEN, passwordHashRequest);
  }


  public Observable<List<ListMemberShipResponse>> getMyMembershipStatement(GetMyMembershipStatementRequest getMyMembershipStatementRequest) {
    UserDetailResponse userDetail =  internalStorageManager.getUserDetail();

    if(userDetail !=null){
      return networkManager.getMyMembershipStatement(getAuthUserToken(),getMyMembershipStatementRequest);
    } else {
      return networkManager.getMyMembershipStatement(getAuthUserToken(),getMyMembershipStatementRequest);
    }
  }

  public Observable<List<InforMembershipResponse>> getInfoMembership() {
    return networkManager.getInfoMembership(getAuthUserToken());
  }

  public void saveUserDetails(UserDetailResponse response){
    internalStorageManager.saveUserDetail(response);
  }

  public void saveUserToken(String token) {
    internalStorageManager.saveUserAuthToken(token);
  }

  public String getAuthUserToken() {
    return "Bearer " + internalStorageManager.getUserAuthToken();
  }


  public String getUserToken() {
    return internalStorageManager.getUserAuthToken();
  }

  public List<CountryCode> getCountryCodes() {
    return internalStorageManager.getCountryCodes();
  }

  public Observable<List<CountryCode>> getCountryCodesFromApi() {
    return networkManager.getCountryCodes();
  }

  public Observable<List<Nationality>> getNationalities() {
    return networkManager.getNationalities();
  }
  public Observable<PhoneCode> getPhoneCodes() {
    return networkManager.getPhoneCodes();
  }

  public Observable<List<BackgroundLogin>> getBackgroundLogin(){
    return networkManager.getBackgroundLogin(Constants.ACCESS_TOKEN);
  }

  public Observable<UploadAvatarResponse> uploadAvatar(String token,
                                                       UploadAvatarRequest uploadAvatarRequest) {
    return networkManager.uploadAvatar(token, uploadAvatarRequest);


  }

  public Observable<List<ISOCountry>> getISOCountryCodes() {
    return networkManager.getISOCountryCodes();
  }


  public Observable<List<PermissionProfileItem>> getPermissionProfile() {
    return networkManager.getPermissionProfile(getUserToken());
  }

  public void saveRatingOption(RatingOption response) {
    internalStorageManager.saveRatingOption(response);
  }

  public RatingOption getRatingOption() {
    return internalStorageManager.getRatingOption();
  }

  public Observable<RatingResult> addRatingComment(AddCommentRequest request) {
    return networkManager.addRatingComment(getAuthUserToken(), request.toMap());
  }

  public Observable<RatingResult> deleteReview(Map<String, String> request) {
    return networkManager.deleteReview(getAuthUserToken(), request);
  }

  public Observable<RatingResult> updateReview(UpdateCommentRequest request) {
    return networkManager.updateReview(getAuthUserToken(), request.toMap());
  }

  public Observable<ReviewDetailResponse> getReviewDetail(ReviewDetailRequest request) {
    return networkManager.getReviewDetail(getAuthUserToken(), request.toMap());
  }

  public Observable<ResponseBody> getRatingSummaryByProduct(Map<String, String> request) {
    return networkManager.getRatingSummaryByProduct(getAuthUserToken(), request);
  }

  public Observable<RatingOption> getRatingOptionFormAPI() {
    return networkManager.getRatingOption(getAuthUserToken());
  }

  public Observable<ProductReviewResponse> getListReviewByProduct(ProductReviewRequest request) {
    return networkManager.getListReviewByProduct(getAuthUserToken(), request.toMap());
  }

  public Observable<ExtraOrderDetail> getExtraOrderDetail(String orderId) {
    return networkManager.getExtraOrderDetail(Constants.ACCESS_TOKEN, Integer.parseInt(orderId));
  }


  public Observable<OrderDetailResponse> getOrderDetail(String orderId) {
    return networkManager.getOrderDetail(Constants.ACCESS_TOKEN, Integer.parseInt(orderId));
  }

  public Observable<InviteFriendDescription> getInviteFriendDescription() {
    return networkManager.getInviteDescription(Constants.ACCESS_TOKEN);
  }





  public Observable<List<CountryCodeCC>> getCountryCodeCc() {
    return networkManager.getCountryCodeCc(getAuthUserToken());
  }





  public Observable<Boolean> deleteAddress(DeleteAddress.Params params) {
    return networkManager.deleteAddress(Constants.ACCESS_TOKEN, params.addressId);
  }



  public Observable<ReserveHistoryRespone> getHistoryAll(String sortBy, String currentPage, String pageSize) {
    return networkManager.getHistoryAll(getAuthUserToken(),getUserDetail().getId().toString().trim(),sortBy,currentPage,pageSize);
  }

  public Observable<ReserveHistoryRespone> getHistoryFilter(String sortBy,
                                                            String refine,String conditionType,
                                                            String currentPage, String pageSize) {
    return networkManager.getHistoryFilter(getAuthUserToken(),getUserDetail().getId().toString().trim(),
        sortBy,
        refine,conditionType,
        currentPage,pageSize);
  }

  public Observable<ReserveHistoryItem> getDetailReservationBooking(String bookingId) {
    return networkManager.getDetailReservationBooking(getAuthUserToken(), bookingId);
  }

  public Observable<List<OutletItem>> getOutletByProductID(String productId) {
    return  networkManager.getOutletByProductID(getAuthUserToken(),productId);
  }

  public Observable<CancelReservationResponse> cancelReservation(String reservationId,
                                                                 String verificationKey) {
    return networkManager.cancelReservation(getAuthUserToken(),reservationId, verificationKey);
  }

  public Observable<List<ConfigItem>> getHGWConfig() {
    return networkManager.getHGWConfig(getAuthUserToken());
  }

  public Observable<ReservationTimeResponse> getReservationTime(String path, String date, String restaurantId,
                                                                String partnerCode, String partnerAuthCode) {
    return networkManager.getReservationTime(path,date, restaurantId, partnerCode, partnerAuthCode);
  }

  public Observable<RestaurantMessageResponse> getRestaurantMsg(String path, String restaurantId) {
    return networkManager.getRestaurantMsg(path,restaurantId);
  }

  public Observable<ReservationResultResponse> sendCreateReservation(Map<String,String> mapRequest) {
    return networkManager.sendCreateReservation(getAuthUserToken(),mapRequest);
  }

  public Observable<ReservationResultResponse> sendEditReservation(Map<String,String> mapRequest,String reservationId,
                                                                   String verificationKey) {
    return networkManager.sendEditReservation(getAuthUserToken(),mapRequest,reservationId, verificationKey);
  }

  public Observable<ResponseBody> getTokenTravflex(String params) {

    Map<String, String> map = new HashMap<>();

    map.put("input", params);
    return networkManager.getTokenTravflex(Constants.ACCESS_TOKEN, map);
  }

  public Boolean getTravelToggle(){
    Boolean isTravelToggle = internalStorageManager.getTravelToggle();
    return isTravelToggle;
  }

  public Observable<BookingDetail> bookRoom(BookRoom.Params params) {

    return networkManager.getIpAddress()
        .flatMap(
            ipAddress -> networkManager.bookRoom(getAuthUserToken(), params, getCustomerSessionId(),
                ipAddress));
  }

  private String getCustomerSessionId() {
    if (Validator.isTextValid(sessionId)) {
      return sessionId;
    }

    String userId = String.valueOf(internalStorageManager.getUserDetail().getId());
    sessionId = customerSessionIdHasher.hash(getAuthUserToken(), userId);

    return sessionId;
  }

  public Observable<BookingDetail> getBookingDetail(GetBookingDetail.Params params) {
    Log.d("bookingDetail", params.toString());
    return networkManager.getIpAddress()
        .flatMap(ipAddress -> networkManager.getBookingDetail(params)
            .flatMap(booking -> {
              BookingData bookingData = bookingHistoryMapper.mapBookingData(booking.getExtensionData().getBookingData());
              return networkManager.getItinerary(bookingData.getLinks().getRetrieve().getHref(), getCustomerSessionId(), ipAddress)
                  .flatMap(itinerary -> {
                    List<String> propertyIds = new ArrayList<>();
                    propertyIds.add(itinerary.getPropertyId());
                    return networkManager.getPropertyContents(propertyIds, getCustomerSessionId(), ipAddress)
                        .map(contents -> bookingDetailMapper.map(booking, itinerary, contents, bookingData, params.rooms));
                  });
            }));
  }

  public Observable<Boolean> cancelBooking(String bookingId) {
    return networkManager.cancelBooking(getAuthUserToken(), bookingId);
  }

  public Observable<PriceCheckResult> checkPrice(CheckPrice.Params params) {
    return networkManager.getIpAddress()
        .flatMap(ipAddress -> networkManager.checkPrice(params, getCustomerSessionId(), ipAddress));
  }

  public Observable<Booking> getBookingDetailvalue(GetBookingDetailValue.Params params) {

    return networkManager.getBookingDetailValue(params);

  }

  public Observable<List<BookingHistory>> getBookingHistories(GetBookingHistories.Params params) {
    return networkManager.getBookingHistories(getAuthUserToken(), params);
  }

  public Observable<List<CardOption>> getPaymentOptions(GetPaymentOptions.Params params) {
    return networkManager.getIpAddress()
        .flatMap(ipAddress -> networkManager.getPaymentOptions(params.paymentOptionLink,
            getCustomerSessionId(),
            ipAddress));
  }

  public Observable<ToggleTravelResponse> getToggleTravel() {
    return networkManager.getToggleTravel(Constants.ACCESS_TOKEN);
  }

  public Observable<Map<String, Property>> getAvailableProperties(List<String> propertyIds,
                                                                  int roomCount, int adultCount,
                                                                  List<Child> children, String arrival,
                                                                  String departure, String countryCode) {
    List<String> occupancies =
        occupancyArranger.groupOccupancies(roomCount, adultCount, children);

    String roomItemOccupancy =
        occupancyArranger.arrangeForRoomItem(adultCount, children);

    return networkManager.getIpAddress()
        .flatMap(ipAddress -> networkManager.getAvailableProperties(propertyIds, arrival, departure,
            countryCode, occupancies, getCustomerSessionId(), ipAddress)
            .flatMap(
                (Func1<List<AvailableProperty>, Observable<Map<String, Property>>>) (List<AvailableProperty> availableProperties) -> {
                  if (availableProperties != null && availableProperties.size() > 0) {
                    return Observable
                        .from(availableProperties)
                        .map(AvailableProperty::getPropertyId)
                        .toList()
                        .flatMap(
                            availablePropertyIds -> networkManager.getPropertyContents(
                                availablePropertyIds,
                                getCustomerSessionId(), ipAddress)
                        )
                        .map(contentMap -> {
                          Map<String, PropertyContent> map = contentMap;

                          Map<String, Property> map2 = propertyMapper.transform(contentMap, availableProperties,
                              occupancies, roomItemOccupancy);

                          int i = 0;
                          return propertyMapper.transform(contentMap, availableProperties,
                              occupancies, roomItemOccupancy);
                        });
                  } else {
                    return Observable.empty();
                  }
                }));
  }

  public Observable<CreditCardResponse> getCreditCard() {
    return networkManager.getCreditCard(getAuthUserToken());
  }

  public Observable<GetFormDataCreditCardResponse> getFormDataCreditCard() {
    return networkManager.getFormDataCreditCard(getAuthUserToken());
  }

  public Observable<CardItem> addCreditCard(AddCreditCardRequest request) {
    return networkManager.addCreditCard(getAuthUserToken(),request);
  }

  public Observable<CardItem> updateCreditCard(UpdateCreditCard.UpdateRequestCreditCardParams request) {
    return networkManager.updateCreditCard(getAuthUserToken(),request.getCardId(),request.getUpdateCreditCardRequest());
  }

  public Observable<ResponseBody> deleteCreditCard(String cardId) {
    return networkManager.deleteCreditCard(getAuthUserToken(),cardId);
  }

  public Observable<FaqResponse> getFaqFromAPI() {
    return networkManager.getFaqs(Constants.ACCESS_TOKEN);
  }

  public void saveFaqItem(List<FaqItem> value) {
    dbManager.saveFaqItem(value);
  }

  public List<FaqItem> getAllFaqItem() {
    return dbManager.getAllFaqItem();
  }

  public List<FaqItem> getFaqItemByKeyword(String keyword) {
    return dbManager.getFaqItemByKeyword(keyword);
  }

  public Observable<ResponseBody> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
    return networkManager.forgotPassword(forgotPasswordRequest);
  }

  public Observable<OneSignalConfig> getOneSignalConfig(){
    return networkManager.getOneSignalConfig(Constants.ACCESS_TOKEN);
  }

  public Observable<ReserveDetailChefTableItem> getDetailReservationBookingChefTable(String bookingId) {
    return networkManager.getDetailReservationBookingChefTable(getUserToken(), bookingId);
  }

  public Observable<List<GiftPopUpResponse>> showPopUp(GiftPopUpRequest giftPopUpRequest){
    return networkManager.showPopUp(Constants.ACCESS_TOKEN,giftPopUpRequest);
  }




}
