package com.skypremiuminternational.app.domain.interactor.credit_card;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;

public class DeleteCreditCard extends UseCase<ResponseBody, String> {

  @Inject
  protected DeleteCreditCard(DataManager dataManager, ThreadExecutor subscriberThread,
                          PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<ResponseBody> provideObservable(String cardId) {
    return getDataManager().deleteCreditCard(cardId);
  }
}
