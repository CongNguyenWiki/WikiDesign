package com.skypremiuminternational.app.app.features.profile.my_reservation.detail;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;

import com.skypremiuminternational.app.domain.interactor.my_reservation.GetOutletResevation;
import com.skypremiuminternational.app.domain.interactor.my_reservation.GetResevationBookingDetail;
import com.skypremiuminternational.app.domain.models.reservation.OutletItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;

import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class DetailsResevationPresenter extends BasePresenter<DetailsResevationActivity> {

  InternalStorageManager internalStorageManager;
  private CompositeSubscription compositeSubscription = new CompositeSubscription();
  private final GetOutletResevation getOutletResevation;
  private final GetResevationBookingDetail getResevationBookingDetail;

  @Inject
  public DetailsResevationPresenter(
      GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
      GetOutletResevation getOutletResevation, GetResevationBookingDetail getResevationBookingDetail
  ) {
    super(getAppVersion, internalStorageManager);

    this.internalStorageManager = internalStorageManager;
    this.getOutletResevation = getOutletResevation;
    this.getResevationBookingDetail = getResevationBookingDetail;
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

  public void getOutletResevation(String productId,int action){
    getView().showLoading();
    add(getOutletResevation.execute(new SingleSubscriber<List<OutletItem>>() {
      @Override
      public void onSuccess(List<OutletItem> value) {
        getView().hideLoading();
        getView().renderGetOutletSuccess(value,action);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().renderGetOutletFailed();
      }
    },productId));
  }

  public  void getBookingDetail(String id){

    getView().showLoading();

    add(getResevationBookingDetail.execute(new SingleSubscriber<ReserveHistoryItem>() {
      @Override
      public void onSuccess(ReserveHistoryItem value) {
        getView().hideLoading();
        getView().renderBookingDetail(value);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().renderError(error.getMessage());
      }
    },id));
  }

}
