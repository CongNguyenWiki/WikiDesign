package com.skypremiuminternational.app.domain.interactor.config;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetCountryCodes extends UseCase<List<CountryCode>,Void> {

  @Inject
  protected GetCountryCodes(DataManager dataManager, ThreadExecutor subscriberThread,
                           PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<List<CountryCode>> provideObservable(Void unused) {
    return getDataManager().getCountryCodesFromApi();
  }
}
