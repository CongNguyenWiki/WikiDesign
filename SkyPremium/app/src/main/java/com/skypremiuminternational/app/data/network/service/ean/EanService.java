package com.skypremiuminternational.app.data.network.service.ean;

import com.skypremiuminternational.app.data.mapper.AddBillingAddressRequestMapper;
import com.skypremiuminternational.app.data.model.ISOCountryResponse;
import com.skypremiuminternational.app.data.model.ean.availability.AvailableProperty;
import com.skypremiuminternational.app.data.model.ean.booking.book.BookResponse;
import com.skypremiuminternational.app.data.model.ean.booking.history.Booking;
import com.skypremiuminternational.app.data.model.ean.booking.history.BookingHistoryResponse;
import com.skypremiuminternational.app.data.model.ean.booking.itinerary.ItineraryResponse;
import com.skypremiuminternational.app.data.model.ean.content.PropertyContent;
import com.skypremiuminternational.app.data.model.ean.payment.PaymentOptionResponse;
import com.skypremiuminternational.app.data.model.ean.pricecheck.PriceCheckResponse;
import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.data.network.request.BookRoomRequest;
import com.skypremiuminternational.app.domain.models.booking.ToggleTravelResponse;
import com.skypremiuminternational.app.domain.models.ean.IpAddress;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface EanService {


  @GET
  @Headers({"Accept:application/json"})
  Observable<Map<String, PropertyContent>> getPropertyContents(
      @Header("Authorization") String authorization,
      @Header("Customer-Ip") String customerIp,
      @Header("Customer-Session-Id") String customerSessionId, @Url String url);

  @GET
  Observable<ISOCountryResponse> getISOCountryCodes(@Url String url);

  @GET
  Observable<IpAddress> getIpAddress(@Url String url);

  @POST(URL.CREATE_BOOKING)
  @Headers({"Content-Type:application/json"})
  Observable<BookResponse> bookRoom(@Header("Authorization") String userToken,
                                    @Body BookRoomRequest request);

  @GET(URL.GET_BOOKING_DETAIL)
  @Headers({"Content-Type:application/json"})
  Observable<Booking> getBookingDetail(@Header("Authorization") String clientToken,
                                       @Path("booking_id") String bookingId);

  @GET
  @Headers({"Accept:application/json"})
  Observable<ItineraryResponse> getItinerary(@Header("Authorization") String authHeader,
                                             @Header("Customer-Ip") String customerIp,
                                             @Header("Customer-Session-Id") String customerSessionId, @Url String url);

  @PUT(URL.CANCEL_BOOKING)
  @Headers({"Content-Type:application/json"})
  Observable<Object> cancelBooking(@Header("Authorization") String userToken,
                                   @Path("booking_id") String bookingId);

  @GET
  @Headers({"Accept:application/json"})
  Observable<PriceCheckResponse> checkPrice(@Header("Authorization") String authHeader,
                                            @Header("Customer-Ip") String customerIp,
                                            @Header("Customer-Session-Id") String customerSessionId, @Url String url);

  @GET(URL.GET_BOOKING_HISTORIES_WITH_FILTER)
  @Headers({"Content-Type:application/json"})
  Observable<BookingHistoryResponse> getBookingHistoriesWithFilter(
      @Header("Authorization") String authHeader,
      @Query("searchCriteria[filterGroups][0][filters][0][value]") String status,
      @Query("searchCriteria[sortOrders][0][direction] ") String sortDirection,
      @Query("searchCriteria[sortOrders][0][field]") String sortField);

  @GET(URL.GET_BOOKING_HISTORIES_WITHOUT_FILTER)
  @Headers({"Content-Type:application/json"})
  Observable<BookingHistoryResponse> getBookingHistoriesWithoutFilter(
      @Header("Authorization") String token,
      @Query("searchCriteria[sortOrders][0][direction] ") String sortDirection,
      @Query("searchCriteria[sortOrders][0][field]") String sortField);

  @GET
  @Headers({"Accept:application/json"})
  Observable<PaymentOptionResponse> getPaymentOptions(@Header("Authorization") String authHeader,
                                                      @Header("Customer-Ip") String customerIp,
                                                      @Header("Customer-Session-Id") String customerSessionId, @Url String url);

  @Headers("Content-Type: application/json")
  @POST(URL.TOGGLE_TRAVEL)
  Observable<ToggleTravelResponse> getToggleTravel(@Header("Authorization") String auth);

  @GET
  @Headers({"Accept:application/json"})
  Observable<List<AvailableProperty>> getAvailableProperties(
      @Header("Authorization") String authorization, @Header("Customer-Ip") String customerIp,
      @Header("Customer-Session-Id") String customerSessionId, @Url String url);


}
