package com.skypremiuminternational.app.app.features.profile.my_reservation.make_reservation;

import com.skypremiuminternational.app.app.internal.mvp.BaseFragmentPresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.my_reservation.GetConfigHGW;
import com.skypremiuminternational.app.domain.interactor.my_reservation.GetReservationTime;
import com.skypremiuminternational.app.domain.interactor.my_reservation.GetRestaurantMsg;
import com.skypremiuminternational.app.domain.models.reservation.ConfigItem;
import com.skypremiuminternational.app.domain.models.reservation.DataMessage;
import com.skypremiuminternational.app.domain.models.reservation.ReservationTimeResponse;
import com.skypremiuminternational.app.domain.models.reservation.RestaurantMessageResponse;


import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MakeAReservationPresenter extends BaseFragmentPresenter<MakeAReservationView> {



  private CompositeSubscription compositeSubscription = new CompositeSubscription();
  InternalStorageManager internalStorageManager;
  private final GetReservationTime getReservationTime;
  private final GetRestaurantMsg getRestaurantMsg;
  private final GetConfigHGW getConfigHGW;


  @Inject
  public MakeAReservationPresenter(
                InternalStorageManager internalStorageManager, GetReservationTime getReservationTime,
                GetRestaurantMsg getRestaurantMsg, GetConfigHGW getConfigHGW
  ) {
    this.internalStorageManager = internalStorageManager;
    this.getReservationTime = getReservationTime;
    this.getRestaurantMsg = getRestaurantMsg;
    this.getConfigHGW = getConfigHGW;
    compositeSubscription = new CompositeSubscription();
  }

  @Override
  public void onStop() {
    compositeSubscription.clear();
    super.onStop();
  }

  private void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }


  public void getReservationTime(String date , String restaurantId){
    getView().showLoading();
    add(getConfigHGW.execute(new SingleSubscriber<List<ConfigItem>>() {
      @Override
      public void onSuccess(List<ConfigItem> value) {
        String path = value.get(0).getDomainHgw();
        String partnerCode = value.get(0).getPartnerCode();
        String partnerAuthCode = value.get(0).getAuthenCode();

        getTimeSlot(path,date,restaurantId,partnerCode,partnerAuthCode);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
      }
    }));
  }


  private void getTimeSlot(String path,String date,String restaurantId,String partnerCode, String partnerAuthCode){
    GetReservationTime.Params params = new GetReservationTime.Params(path,date,restaurantId,partnerCode,partnerAuthCode);

    add(getReservationTime.execute(new SingleSubscriber<ReservationTimeResponse>() {
      @Override
      public void onSuccess(ReservationTimeResponse value) {
        getView().hideLoading();
        getView().renderReservationTime(value);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().renderReservationTimeFailed();

      }
    },params));
  }

  public void getGetRestaurantMsg(String restaurantId) {
    getView().showLoading();
    add(getConfigHGW.execute(new SingleSubscriber<List<ConfigItem>>() {
      @Override
      public void onSuccess(List<ConfigItem> value) {
        String path = value.get(0).getDomainHgw();

        getMessage(path,restaurantId);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
      }
    }));
  }


  private void getMessage(String path,String restaurantId){
    GetRestaurantMsg.Params params = new GetRestaurantMsg.Params(path,restaurantId);

    add(getRestaurantMsg.execute(new SingleSubscriber<RestaurantMessageResponse>() {
      @Override
      public void onSuccess(RestaurantMessageResponse value) {
        getView().hideLoading();
        String msg = "";

        for(DataMessage item : value.getMessage().getData() ){
          if(item.getRestaurantId().equals(restaurantId)){
            msg =  item.getMessage();
          }
        }

        getView().renderRestaurantMsg(msg);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();

      }
    },params));
  }
}
