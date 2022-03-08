package com.skypremiuminternational.app.domain.interactor.my_reservation;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.reservation.ConfigItem;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetConfigHGW extends UseCase<List<ConfigItem>,Void> {

  @Inject
  protected GetConfigHGW(DataManager dataManager, ThreadExecutor subscriberThread,
                         PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<List<ConfigItem>> provideObservable(Void aVoid) {
    return getDataManager().getHGWConfig();
  }
}
