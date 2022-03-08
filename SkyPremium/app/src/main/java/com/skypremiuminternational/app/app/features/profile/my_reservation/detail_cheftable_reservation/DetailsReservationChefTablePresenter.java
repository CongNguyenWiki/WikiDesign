package com.skypremiuminternational.app.app.features.profile.my_reservation.detail_cheftable_reservation;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;

import com.skypremiuminternational.app.domain.interactor.my_reservation.GetResevationBookingChefTableDetail;
import com.skypremiuminternational.app.domain.models.reservation.ReserveDetailChefTableItem;


import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class DetailsReservationChefTablePresenter extends BasePresenter<DetailsReservationChefTableActivity> {
  InternalStorageManager internalStorageManager;
  private CompositeSubscription compositeSubscription = new CompositeSubscription();
  private final GetResevationBookingChefTableDetail getResevationBookingDetail;

  @Inject
  public DetailsReservationChefTablePresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                                              GetResevationBookingChefTableDetail getResevationBookingDetail
  ) {
    super(getAppVersion, internalStorageManager);

    this.internalStorageManager = internalStorageManager;
    this.getResevationBookingDetail = getResevationBookingDetail;

  }

  public  void getBookingDetail(String id){

    getView().showLoading();

    add(getResevationBookingDetail.execute(new SingleSubscriber<ReserveDetailChefTableItem>() {
      @Override
      public void onSuccess(ReserveDetailChefTableItem value) {
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
