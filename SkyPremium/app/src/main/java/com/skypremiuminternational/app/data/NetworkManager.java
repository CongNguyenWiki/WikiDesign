package com.skypremiuminternational.app.data;


import com.skypremiuminternational.app.data.model.BookingHistory;
import com.skypremiuminternational.app.data.model.billingaddress.BillingAddress;
import com.skypremiuminternational.app.data.model.ean.availability.AvailableProperty;
import com.skypremiuminternational.app.data.model.ean.booking.history.Booking;
import com.skypremiuminternational.app.data.model.ean.booking.itinerary.ItineraryResponse;
import com.skypremiuminternational.app.data.model.ean.content.PropertyContent;
import com.skypremiuminternational.app.data.model.ean.payment.CardOption;
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
import com.skypremiuminternational.app.domain.interactor.ean.BookRoom;
import com.skypremiuminternational.app.domain.interactor.ean.CheckPrice;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingDetail;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingDetailValue;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingHistories;
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
import com.skypremiuminternational.app.domain.models.user.BillingAddressPayment;
import com.skypremiuminternational.app.domain.models.user.UploadAvatarResponse;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;
import com.skypremiuminternational.app.domain.models.Version;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;

public interface NetworkManager {

  Observable<Version> getAppVersion(String accessToken);

  Observable<ResponseBody> signIn(SignInRequest request);

  Observable<UserDetailResponse> getUserDetail(String tokenUser);

  Observable<CategoryResponse> getCategories(String token);

  Observable<UserDetailResponse> updateUserDetail( UpdateUserRequest request);

  Observable<ProductListResponse> getProducts(String accessToken);

  Observable<DetailsItem> getProductDetails(String accessToken,String sku,String tokenUser);

  Observable<List<FavouriteItem>> getFavourites(String token, GetFavouriteRequest request,String customerToken);

  Observable<FavouriteResponse> removeFromFavourite(String token, String wishListItemId);

  Observable<CartDetailResponse> getCartInfo(String tokenUser);

  Observable<CartDetailResponse> getCartDetail(String token);

  Observable<Object> getCartId(String auth);

  Observable<List<CartDetailItem>> getCartItems(String token);

  Observable<AddCartItemCountRequest.Cart> addToCart(
      AddCartItemCountRequest addCartItemCountRequest, String auth);

  Observable<CheckLimitResponse> checkIfCartHasReachedLimit(String userToken, boolean buyNow, boolean isCheckout);

  Observable<ResponseBody> putLogin(String auth, ActivityLogRequest request);

  Observable<ResponseBody> putLogout(String auth, ActivityLogRequest request);

  Observable<ResponseBody> updatePassword(String token,
                                          UpdatePasswordRequest updatePasswordRequest);

  Observable<ResponseBody> getPasswordHash(String token, PasswordHashRequest passwordHashRequest);

  Observable<MyOrderResponse> getOrderHistory(String token, String email,
                                              GetOrderHistoryRequest request);

  Observable<List<ListMemberShipResponse>> getMyMembershipStatement(String token, GetMyMembershipStatementRequest getMyMembershipStatementRequest);

  Observable<List<InforMembershipResponse>> getInfoMembership(String userToken);

  Observable<PhoneCode> getPhoneCodes();

  Observable<List<BackgroundLogin>> getBackgroundLogin(String token);

  Observable<OrderDetailResponse> getOrderDetail(String userToken, Integer orderId);

  Observable<ExtraOrderDetail> getExtraOrderDetail(String clientToken, int orderId);

  Observable<List<CountryCode>> getCountryCodes();

  Observable<List<Nationality>> getNationalities();

  Observable<List<ISOCountry>> getISOCountryCodes();

  Observable<List<PermissionProfileItem>> getPermissionProfile(String userToken);

  Observable<RatingResult> addRatingComment(String userToken, Map<String, String> addCommentRequest);

  Observable<RatingResult> deleteReview(String userToken, Map<String, String> request);

  Observable<RatingResult> updateReview(String userToken, Map<String, String> updateReview);

  Observable<ReviewDetailResponse> getReviewDetail(String userToken, Map<String, String> addCommentRequest);

  Observable<ResponseBody> getRatingSummaryByProduct(String userToken, Map<String, String> request);

  Observable<RatingOption> getRatingOption(String userToken);

  Observable<ProductReviewResponse> getListReviewByProduct(String userToken, Map<String, String> productReviewRequest);

  Observable<UploadAvatarResponse> uploadAvatar(String token,
                                                UploadAvatarRequest uploadAvatarRequest);

  Observable<InviteFriendDescription> getInviteDescription(String token);

  Observable<List<CountryCodeCC>> getCountryCodeCc(String auth);

  Observable<Boolean> deleteAddress(String clientToken, Integer addressId);


















  Observable<UserDetailResponse> updateUserDetail(String token, String member_number,
                                                  UpdateUserDeliveryRequest updateUserRequest);


  Observable<ReserveHistoryRespone> getHistoryAll(String userToken , String memberNumber, String sortBy, String currentPage, String pageSize);

  Observable<ReserveHistoryRespone> getHistoryFilter(String userToken , String memberNumber,
                                                     String sortBy,
                                                     String refine,String conditionRefine,
                                                     String currentPage, String pageSize);

  Observable<ReserveHistoryItem> getDetailReservationBooking(String userToken , String bookingId);

  Observable<List<OutletItem>> getOutletByProductID(String oAuth , String productId);

  Observable<CancelReservationResponse>cancelReservation(String userToken, String reservationId,
                                                         String verificationKey);

  Observable<List<ConfigItem>> getHGWConfig(String userToken);

  Observable<ReservationTimeResponse> getReservationTime(String path, String date , String restaurantId, String partnerCode, String partnerAuthCode);

  Observable<RestaurantMessageResponse> getRestaurantMsg(String path, String restaurantId);

  Observable<ReservationResultResponse> sendCreateReservation(String userToken , Map<String,String> mapRequest);

  Observable<ReservationResultResponse>sendEditReservation(String userToken,
                                                           Map<String,String> mapRequest,String reservationId,
                                                           String verificationKey);

  Observable<ResponseBody> getTokenTravflex(String token, Map<String, String> params);

  Observable<String> getIpAddress();

  Observable<BookingDetail> bookRoom(String userToken, BookRoom.Params params,
                                     String sessionId, String ipAddress);

  Observable<Booking> getBookingDetail(GetBookingDetail.Params params);

  Observable<ItineraryResponse> getItinerary(String endpoint, String sessionId,
                                             String ipAddress);

  Observable<Map<String, PropertyContent>> getPropertyContents(List<String> availablePropertyIds,
                                                               String sessionId, String ipAddress);

  Observable<Boolean> cancelBooking(String userToken, String bookingId);

  Observable<PriceCheckResult> checkPrice(CheckPrice.Params params, String sessionId,
                                          String ipAddress);

  Observable<Booking> getBookingDetailValue(GetBookingDetailValue.Params params);

  Observable<List<BookingHistory>> getBookingHistories(String userToken,
                                                       GetBookingHistories.Params params);

  Observable<List<CardOption>> getPaymentOptions(String paymentOptionLink,
                                                 String sessionId, String ipAddress);

  Observable<ToggleTravelResponse> getToggleTravel(String auth);

  Observable<List<AvailableProperty>> getAvailableProperties(List<String> propertyIds,
                                                             String arrival, String departure,
                                                             String countryCode, List<String> occupancies,
                                                             String sessionId, String ipAddress);

  Observable<CreditCardResponse> getCreditCard(String userToken);

  Observable<GetFormDataCreditCardResponse> getFormDataCreditCard(String userToken);

  Observable<CardItem> addCreditCard(String userToken, AddCreditCardRequest request);

  Observable<CardItem> updateCreditCard(String userToken,
                                        String cardId,
                                        UpdateCreditCardRequest updateCreditCardRequest);

  Observable<ResponseBody> deleteCreditCard(String userToken, String cardId);

  Observable<FaqResponse> getFaqs(String token);

  Observable<ResponseBody> forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

  Observable<OneSignalConfig> getOneSignalConfig(String auth);

  Observable<ReserveDetailChefTableItem> getDetailReservationBookingChefTable(String userToken , String bookingId);

  Observable<List<GiftPopUpResponse>> showPopUp(String auth, GiftPopUpRequest giftPopUpRequest);


}
