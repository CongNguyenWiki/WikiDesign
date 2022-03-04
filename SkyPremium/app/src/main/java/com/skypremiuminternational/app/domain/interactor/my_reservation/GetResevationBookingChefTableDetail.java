package com.skypremiuminternational.app.domain.interactor.my_reservation;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.reservation.ReserveDetailChefTableItem;

import javax.inject.Inject;

import rx.Observable;


public class GetResevationBookingChefTableDetail extends UseCase<ReserveDetailChefTableItem,String> {

  @Inject
  protected GetResevationBookingChefTableDetail(DataManager dataManager, ThreadExecutor subscriberThread,
                                                PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<ReserveDetailChefTableItem> provideObservable(String bookingId) {
    return getDataManager().getDetailReservationBookingChefTable(bookingId);

  }
}