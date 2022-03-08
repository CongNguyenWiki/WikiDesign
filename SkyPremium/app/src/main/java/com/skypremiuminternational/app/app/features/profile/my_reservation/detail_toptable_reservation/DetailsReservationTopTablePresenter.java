package com.skypremiuminternational.app.app.features.profile.my_reservation.detail_toptable_reservation;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.my_reservation.GetResevationBookingDetail;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;


import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class DetailsReservationTopTablePresenter extends BasePresenter<DetailsReservationTopTableActivity> {
  InternalStorageManager internalStorageManager;
  private CompositeSubscription compositeSubscription = new CompositeSubscription();
  private final GetResevationBookingDetail getResevationBookingDetail;

  @Inject
  public DetailsReservationTopTablePresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                                               GetResevationBookingDetail getResevationBookingDetail

  ) {
    super(getAppVersion, internalStorageManager);

    this.internalStorageManager = internalStorageManager;
    this.getResevationBookingDetail = getResevationBookingDetail;

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







  @Override
  public void onStop() {
    compositeSubscription.clear();
    super.onStop();
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);

  }
}
