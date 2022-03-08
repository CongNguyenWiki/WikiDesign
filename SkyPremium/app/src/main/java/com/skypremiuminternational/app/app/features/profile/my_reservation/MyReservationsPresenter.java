package com.skypremiuminternational.app.app.features.profile.my_reservation;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;

import com.skypremiuminternational.app.domain.interactor.my_reservation.GetHistoryAll;
import com.skypremiuminternational.app.domain.interactor.my_reservation.GetHistoryFilter;
import com.skypremiuminternational.app.domain.interactor.my_reservation.GetResevationBookingDetail;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryRespone;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MyReservationsPresenter extends BasePresenter<MyReservationsView> {

  InternalStorageManager internalStorageManager;
  private final GetHistoryAll getHistoryAll;
  private final GetHistoryFilter getHistoryFilter;
  private final GetResevationBookingDetail getResevationBookingDetail;


  private CompositeSubscription compositeSubscription = new CompositeSubscription();


  @Inject
  public MyReservationsPresenter(
      GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
      GetHistoryAll getHistoryAll, GetResevationBookingDetail getResevationBookingDetail,
      GetHistoryFilter getHistoryFilter
  ) {
    super(getAppVersion, internalStorageManager);

    this.internalStorageManager = internalStorageManager;
    this.getResevationBookingDetail = getResevationBookingDetail;
    this.getHistoryFilter = getHistoryFilter;
    this.getHistoryAll = getHistoryAll;

  }

  @Override
  public void onStop() {
    compositeSubscription.clear();
    super.onStop();
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }


  public  void getHistoryAll(String sortBy){

    getView().showLoading();
    GetHistoryAll.Params params = new GetHistoryAll.Params(sortBy,"","");

    add(getHistoryAll.execute(new SingleSubscriber<ReserveHistoryRespone>() {
      @Override
      public void onSuccess(ReserveHistoryRespone value) {
          getView().hideLoading();
          getView().renderHistoryReservation(value);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().renderError(error.getMessage());
      }
    },params));
  }
  public  void getHistoryFilter(String sortBy, String refineCondition){

    getView().showLoading();
    String refine =  refineCondition;

    if(!refineCondition.equalsIgnoreCase("4")){
      Date currentDate =  Calendar.getInstance().getTime();
      SimpleDateFormat sdf =  new SimpleDateFormat(Constants.PATTERN_DATE_TIME);
      String sDate =  sdf.format(currentDate);
      refine = sDate;
    }


    GetHistoryFilter.Params params = new GetHistoryFilter.Params(sortBy,refine,refineCondition,"","");

    add(getHistoryFilter.execute(new SingleSubscriber<ReserveHistoryRespone>() {
      @Override
      public void onSuccess(ReserveHistoryRespone value) {
          getView().hideLoading();
          getView().renderHistoryReservation(value);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().renderError(error.getMessage());
      }
    },params));
  }


  public  void getBookingDetail(String id){

    getView().showLoading();

    add(getResevationBookingDetail.execute(new SingleSubscriber<ReserveHistoryItem>() {
      @Override
      public void onSuccess(ReserveHistoryItem value) {
          getView().hideLoading();
          getView().renderGotoBookingDetail(value);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().renderError(error.getMessage());
      }
    },id));
  }



}
