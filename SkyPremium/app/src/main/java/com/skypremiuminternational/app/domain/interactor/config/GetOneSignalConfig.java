package com.skypremiuminternational.app.domain.interactor.config;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.system.OneSignalConfig;

import javax.inject.Inject;

import rx.Observable;

public class GetOneSignalConfig extends UseCase<OneSignalConfig, Void> {
  @Inject
  protected GetOneSignalConfig(DataManager dataManager, ThreadExecutor subscriberThread,
                               PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<OneSignalConfig> provideObservable(Void v) {
    return getDataManager().getOneSignalConfig();
  }
}