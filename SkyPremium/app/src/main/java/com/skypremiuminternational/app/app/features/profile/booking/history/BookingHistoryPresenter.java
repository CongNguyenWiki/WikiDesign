package com.skypremiuminternational.app.app.features.profile.booking.history;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.utils.Validator;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.mapper.BookingHistoryMapper;
import com.skypremiuminternational.app.data.model.BookingHistory;
import com.skypremiuminternational.app.data.model.ean.booking.history.Booking;
import com.skypremiuminternational.app.data.model.ean.booking.history.BookingData;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.auth.GetTravflexToken;
import com.skypremiuminternational.app.domain.interactor.ean.CancelBooking;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingDetailValue;
import com.skypremiuminternational.app.domain.interactor.ean.GetBookingHistories;
import com.skypremiuminternational.app.domain.interactor.ean.GetToggleTravel;
import com.skypremiuminternational.app.domain.interactor.permission_profile.PermissionProfile;
import com.skypremiuminternational.app.domain.models.booking.ToggleTravelResponse;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BookingHistoryPresenter extends BasePresenter<BookingHistoryView> {

  private final GetBookingHistories getBookingHistories;
  private final CancelBooking cancelBooking;
  private CompositeSubscription compositeSubscription;
  private final GetBookingDetailValue getBookingDetailvalue;
  private BookingHistoryMapper bookingHistoryMapper;
  private GetToggleTravel getToggleTravel;
  private GetTravflexToken getTravflexToken;
  private PermissionProfile getPermissionProfile;
  InternalStorageManager internalStorageManager;

  @Inject
  public BookingHistoryPresenter(GetAppVersion getAppVersion,
                                 InternalStorageManager internalStorageManager, GetBookingHistories getBookingHistories,
                                 CancelBooking cancelBooking,GetBookingDetailValue getBookingDetailvalue,BookingHistoryMapper bookingHistoryMapper,
                                 GetToggleTravel getToggleTravel, GetTravflexToken getTravflexToken,PermissionProfile getPermissionProfile

  ) {
    super(getAppVersion, internalStorageManager);
    this.internalStorageManager = internalStorageManager;
    this.cancelBooking = cancelBooking;
    compositeSubscription = new CompositeSubscription();
    this.getBookingHistories = getBookingHistories;
    this.getBookingDetailvalue = getBookingDetailvalue;
    this.bookingHistoryMapper = bookingHistoryMapper;
    this.getToggleTravel = getToggleTravel;
    this.getTravflexToken = getTravflexToken;
    this.getPermissionProfile = getPermissionProfile;
  }

  public void getBookingHistories(String status, String sortingField, String sortingDirection) {
    getView().showLoading();
    add(getBookingHistories.execute(new SingleSubscriber<List<BookingHistory>>() {
      @Override
      public void onSuccess(List<BookingHistory> bookingHistories) {
        getView().hideLoading();
        getView().render(bookingHistories);
//        Log.d("skyHistory",bookingHistories.toString());
      }

      @Override
      public void onError(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().render(error);
      }
    }, new GetBookingHistories.Params(sortingDirection, sortingField, status)));
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  @Override
  public void onStop() {
    super.onStop();
    compositeSubscription.clear();
  }

  public void getBookingDetailvalueview(String bookingId,
                                        ArrayList<BookingHistory.Room> rooms, int pos) {
    getView().showLoading();
    add(getBookingDetailvalue.execute(new SingleSubscriber<Booking>() {
      @Override
      public void onSuccess(Booking value) {



        List<BookingData> bookingDataList =  bookingHistoryMapper.mapBookingDatas(value.getExtensionData().getBookingData());

        if (bookingDataList != null && bookingDataList.size() > 0) {

          if(bookingDataList.get(0).getCancelsPenaltiesList().size()>1){
            getView().hideLoading();

            if(Validator.isTextValid(bookingDataList.get(0).getCancelsPenaltiesList().get(0).getStart())){
              getView().render(bookingDataList.get(0),true,bookingId,pos,rooms);
            }else {

              getView().render(bookingDataList.get(0),true,bookingId,pos,rooms);
            }

          }else{

            getView().hideLoading();


             getView().render(bookingDataList.get(0),true,bookingId,pos,rooms);
          }

        }
      }

      @Override
      public void onError(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().render(null,false,bookingId,pos,rooms);
        getView().render(error);
      }
    }, new GetBookingDetailValue.Params(bookingId, rooms)));
  }

  public void getBookingDetailvalue(String bookingId,
                                    ArrayList<BookingHistory.Room> rooms,int pos) {
     getView().showLoading();
    add(getBookingDetailvalue.execute(new SingleSubscriber<Booking>() {
      @Override
      public void onSuccess(Booking value) {



        List<BookingData> bookingDataList =  bookingHistoryMapper.mapBookingDatas(value.getExtensionData().getBookingData());

        if (bookingDataList != null && bookingDataList.size() > 0) {

          if(bookingDataList.get(0).getCancelsPenaltiesList().size()>1){
            getView().hideLoading();

            if(Validator.isTextValid(bookingDataList.get(0).getCancelsPenaltiesList().get(0).getStart())){
              getView().render(bookingDataList.get(0),true,bookingId,pos);
            }else {

              getView().render(bookingDataList.get(0),true,bookingId,pos);
            }

          }else{

             getView().hideLoading();

              getView().render(bookingDataList.get(0),true,bookingId,pos);
          }

        }
      }

      @Override
      public void onError(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().render(null,false,bookingId,pos);
        // getView().render(error);
      }
    }, new GetBookingDetailValue.Params(bookingId, rooms)));
  }

  public void cancelBooking(String bookingId, String status, String sortingField,
                            String sortingDirection) {

    add(cancelBooking.execute(new SingleSubscriber<List<BookingHistory>>() {
      @Override
      public void onSuccess(List<BookingHistory> value) {

        getView().render(value);
        getView().render(true,status);
      }

      @Override
      public void onError(Throwable error) {

        getView().render(false,status);
        getView().render(new Exception("Failed to cancel the booking"));
      }
    }, new CancelBooking.Params(bookingId, sortingDirection, sortingField, status)));
  }
  public void loadWebView(String urlTravflex) {


    UserDetailResponse userDetail =  internalStorageManager.getUserDetail();
    String memberNumber = userDetail.getMemberNumber();
    String salutation = userDetail.getSalutation();
    String firstName = userDetail.getFirstname();
    String lastName = userDetail.getLastname();
    getTokenTravflexAndRenderWebView(memberNumber+"|"+salutation+"|"+firstName+"|"+lastName,urlTravflex);
  }


  public void getConfigAndRenderBookingHistory(){
//    getView().showLoading();
    add(getToggleTravel.execute(new SingleSubscriber<ToggleTravelResponse>() {
      @Override
      public void onSuccess(ToggleTravelResponse value) {
     //   getView().hideLoading();
        internalStorageManager.saveTravelToggle(value.isEnable());
        internalStorageManager.saveTravelFlexBaseUrl(value.getBaseUrl());

        if(value.isEnable()){
          getView().renderTravflexHistory(value.getBaseUrl());
        }else {
          getView().renderExpediaHistory();
        }


      }

      @Override
      public void onError(Throwable error) {
        //getView().hideLoading();
        getView().renderExpediaHistory();

      }
    }));
  }

  public void getTokenTravflexAndRenderWebView(String content,String urlTravflex){
    add(getTravflexToken.execute(new SingleSubscriber<ResponseBody>() {
      @Override
      public void onSuccess(ResponseBody token) {
        try {
          getView().renderWebView(urlTravflex,token.string());
        } catch (IOException e) {
          e.printStackTrace();
        }

      }

      @Override
      public void onError(Throwable error) {

      }
    },content));
  }

  public void getPermissionProfile(){
//    getView().showLoading();
    add(getPermissionProfile.execute(new SingleSubscriber<List<PermissionProfileItem>>() {
      @Override
      public void onSuccess(List<PermissionProfileItem> value) {

        getView().renderPermission(value);
//        getView().hideLoading();
      }

      @Override
      public void onError(Throwable error) {

//        getView().hideLoading();
      }
    }));
  }
}
