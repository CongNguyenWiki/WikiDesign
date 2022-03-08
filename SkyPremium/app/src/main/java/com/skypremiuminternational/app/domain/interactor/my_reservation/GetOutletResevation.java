package com.skypremiuminternational.app.domain.interactor.my_reservation;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.reservation.OutletItem;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetOutletResevation extends UseCase<List<OutletItem>, String> {

  @Inject
  protected GetOutletResevation(DataManager dataManager, ThreadExecutor subscriberThread,
                                PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<List<OutletItem>> provideObservable(String productId) {
    return getDataManager().getOutletByProductID(productId);
  }
}